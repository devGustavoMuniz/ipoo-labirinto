import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

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
        addObject(jogador, 60, 140);

        // Adicionar a chave
        Chave chave = new Chave();
        chave.getImage().scale(20, 20);
        addObject(chave, 540, 340);

        // Adicionar a porta
        Porta porta = new Porta();
        porta.getImage().scale(40, 60);
        addObject(porta, 540, 229);

        // Gerar moedas no centro dos corredores
        gerarItens(3); // Gera 3 moedas
        showText("Pontos: " + pontos, 50, 20);
    }

    private void gerarItens(int quantidade) {
        // Lista para armazenar todas as posições válidas
        List<int[]> posicoesValidas = new ArrayList<>();

        // Percorrer a matriz do labirinto para encontrar posições válidas
        for (int linha = 0; linha < labirinto.length; linha++) {
            for (int coluna = 0; coluna < labirinto[linha].length; coluna++) {
                if (labirinto[linha][coluna] == 0) { // Verifica se é um caminho
                    int[] coordenadas = converterParaCoordenadasMundo(linha, coluna);
                    posicoesValidas.add(coordenadas);
                }
            }
        }

        // Verifica se há posições válidas suficientes
        if (posicoesValidas.size() < quantidade) {
            throw new IllegalStateException("Não há posições válidas suficientes para gerar todas as moedas!");
        }

        // Escolhe aleatoriamente posições válidas para gerar as moedas
        List<int[]> posicoesUtilizadas = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            int indiceAleatorio = Greenfoot.getRandomNumber(posicoesValidas.size());
            int[] posicao = posicoesValidas.get(indiceAleatorio);

            // Verifica se a posição já foi utilizada
            while (posicaoUtilizada(posicoesUtilizadas, posicao)) {
                indiceAleatorio = Greenfoot.getRandomNumber(posicoesValidas.size());
                posicao = posicoesValidas.get(indiceAleatorio);
            }

            // Adiciona a posição à lista de posições utilizadas
            posicoesUtilizadas.add(posicao);

            // Gera a moeda na posição
            Moeda moeda = new Moeda();
            moeda.getImage().scale(20, 20);
            addObject(moeda, posicao[0], posicao[1]);
        }
    }

    private boolean posicaoUtilizada(List<int[]> posicoesUtilizadas, int[] posicao) {
        // Verifica se a posição já está na lista de posições utilizadas
        for (int[] p : posicoesUtilizadas) {
            if (p[0] == posicao[0] && p[1] == posicao[1]) {
                return true;
            }
        }
        return false;
    }

    public void adicionarPontos(int valor) {
        pontos += valor;
        showText("Pontos: " + pontos, 50, 20);
    }

    public void finalizarJogo(boolean venceu) {
        if(index + 1 > 3){
            Greenfoot.setWorld(new TelaFinal(venceu, pontos));
        } else {
            Greenfoot.setWorld(new Labirinto(index+1, pontos));
        }
    }
}