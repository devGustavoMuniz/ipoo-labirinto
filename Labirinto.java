import greenfoot.*;

public class Labirinto extends World {
    private static final int TAMANHO_BLOCO = 40; // Tamanho de cada bloco (largura e altura)
    private int pontos;
    private int index;
    private int[][] labirinto;

    public Labirinto(int index, int pontos) {
        super(600, 400, 1);
        this.pontos = pontos;
        this.index = index;
        criarFundoPreto(); // Define o fundo do labirinto como preto
        criarLabirinto(index);
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
        // Matriz que define o labirinto (1 = parede, 0 = caminho)
        labirinto = geradora.getLabirinto(index);

        // Percorrer a matriz e adicionar paredes
        for (int linha = 0; linha < labirinto.length; linha++) {
            for (int coluna = 0; coluna < labirinto[linha].length; coluna++) {
                if (labirinto[linha][coluna] == 1) {
                    int[] coordenadas = converterParaCoordenadasMundo(linha, coluna);
                    addObject(new Parede(), coordenadas[0], coordenadas[1]);
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
        // Adicionar o jogador fora das paredes
        Jogador jogador = new Jogador();
        jogador.getImage().scale(30, 30); // Reduzir tamanho
        int[] posicaoJogador = {60, 140}; // Posição inicial do jogador
        addObject(jogador, posicaoJogador[0], posicaoJogador[1]);

        // Adicionar a chave
        Chave chave = new Chave();
        chave.getImage().scale(20, 20);
        addObject(chave, 540, 340);

        // Adicionar a porta
        Porta porta = new Porta();
        porta.getImage().scale(40, 60);
        addObject(porta, 540, 229);

        // Gerar moedas no centro dos corredores
        GeradorItens gerador = new GeradorItens(labirinto, TAMANHO_BLOCO);
        gerador.gerarItens(this, 3, Moeda.class, posicaoJogador); // Gera 3 moedas

        showText("Pontos: " + pontos, 50, 20);
    }

    public void adicionarPontos(int valor) {
        pontos += valor;
        showText("Pontos: " + pontos, 50, 20);
    }

    public void finalizarJogo(boolean venceu) {
        if (!venceu) {
            Greenfoot.setWorld(new TelaFinal(venceu, pontos));
            return;
        }

        if (index + 1 > 3) {
            Greenfoot.setWorld(new TelaFinal(venceu, pontos));
            return;
        }

        Greenfoot.setWorld(new Labirinto(index + 1, pontos));
    }
}