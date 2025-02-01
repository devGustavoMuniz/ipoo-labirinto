import greenfoot.*;

public class Labirinto extends World {
    private static final int TAMANHO_BLOCO = 40;
    private GameState gameState;
    private long tempoInicioFase;
    private int[][] labirinto;

    public Labirinto(GameState gameState) {
        super(600, 400, 1);
        this.gameState = gameState;
        this.tempoInicioFase = System.currentTimeMillis();
        criarFundoPreto();
        criarLabirinto(gameState.getFaseAtual());
        prepare();
    }

    private void criarFundoPreto() {
        GreenfootImage bg = new GreenfootImage(600, 400);
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);
    }

    private void criarLabirinto(int index) {
        GeradoraLabirintos geradora = new GeradoraLabirintos();
        labirinto = geradora.getLabirinto(index);
        
        for (int linha = 0; linha < labirinto.length; linha++) {
            for (int coluna = 0; coluna < labirinto[linha].length; coluna++) {
                int[] coordenadas = converterParaCoordenadasMundo(linha, coluna);
                if (labirinto[linha][coluna] == 1) { // Parede
                    addObject(new Parede(), coordenadas[0], coordenadas[1]);
                } else if (labirinto[linha][coluna] == 2) { // Café
                    addObject(new Cafe(), coordenadas[0], coordenadas[1]);
                } else if (labirinto[linha][coluna] == 3) { // Inimigo
                    addObject(new Inimigo(), coordenadas[0], coordenadas[1]);
                } else if (labirinto[linha][coluna] == 4) { // Pedra
                    addObject(new Pedra(), coordenadas[0], coordenadas[1]);
                }
            }
        }
    }

    private int[] converterParaCoordenadasMundo(int linha, int coluna) {
        int x = coluna * TAMANHO_BLOCO + TAMANHO_BLOCO / 2;
        int y = linha * TAMANHO_BLOCO + TAMANHO_BLOCO / 2;
        return new int[]{x, y};
    }

    private void prepare() {
        Jogador jogador = new Jogador();
        jogador.getImage().scale(30, 30);
        int[] posicaoJogador = {60, 140};
        addObject(jogador, posicaoJogador[0], posicaoJogador[1]);

        // Posições fixas por fase
        int[][] posicoesChave = {
            {540, 340}, // Fase 0
            {540, 60},  // Fase 1
            {60, 310},  // Fase 2
            {300, 215}  // Fase 3
        };

        int[][] posicoesPorta = {
            {540, 229}, // Fase 0
            {60, 229},  // Fase 1
            {540, 60},  // Fase 2
            {500, 380}  // Fase 3
        };

        int faseAtual = gameState.getFaseAtual();
        
        // Garante que não ultrapasse o número de fases configuradas
        faseAtual = Math.min(faseAtual, posicoesChave.length - 1);

        // Adicionar chave
        Chave chave = new Chave();
        chave.getImage().scale(20, 20);
        addObject(chave, posicoesChave[faseAtual][0], posicoesChave[faseAtual][1]);

        // Adicionar porta
        Porta porta = new Porta();
        porta.getImage().scale(40, 60);
        addObject(porta, posicoesPorta[faseAtual][0], posicoesPorta[faseAtual][1]);

        // Gerar moedas (mantida a geração aleatória original)
        GeradorItens gerador = new GeradorItens(labirinto, TAMANHO_BLOCO);
        gerador.gerarItens(this, 3, Moeda.class, posicaoJogador);
        
        atualizarHUD();
    }

    public void act() {
        atualizarHUD();
    }

    private void atualizarHUD() {
        // Atualiza pontos
        showText("Pontos: " + gameState.getPontosTotais(), 50, 20);
        
        // Atualiza timer
        long tempoDecorridoFaseAtual = System.currentTimeMillis() - tempoInicioFase;
        long tempoTotalExibicao = gameState.getTempoTotal() + tempoDecorridoFaseAtual;
        long segundos = (tempoTotalExibicao / 1000) % 60;
        long minutos = (tempoTotalExibicao / (1000 * 60)) % 60;
        String tempoFormatado = String.format("Tempo: %02d:%02d", minutos, segundos);
        showText(tempoFormatado, 500, 20);
    }

    public void adicionarPontos(int valor) {
        gameState.addPontos(valor);
        atualizarHUD();
    }

    public void finalizarJogo(boolean venceu) {
        long tempoDecorrido = System.currentTimeMillis() - tempoInicioFase;
        gameState.addTempo(tempoDecorrido);

        if (!venceu) {
            Greenfoot.setWorld(new TelaFinal(gameState, false));
            return;
        }

        if (gameState.getFaseAtual() >= 3) {
            Greenfoot.setWorld(new TelaFinal(gameState, true));
        } else {
            gameState.avancarFase();
            Greenfoot.setWorld(new Labirinto(gameState));
        }
    }
}