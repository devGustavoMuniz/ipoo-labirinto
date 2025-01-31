import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class GeradorItens {
    private int[][] labirinto;
    private int tamanhoBloco;

    public GeradorItens(int[][] labirinto, int tamanhoBloco) {
        this.labirinto = labirinto;
        this.tamanhoBloco = tamanhoBloco;
    }

    // Gera itens em posições válidas do labirinto
    public void gerarItens(World mundo, int quantidade, Class<?> tipoItem, int[] posicaoJogador) {
        List<int[]> posicoesValidas = getPosicoesValidas();

        // Verifica se há posições válidas suficientes
        if (posicoesValidas.size() < quantidade) {
            throw new IllegalStateException("Não há posições válidas suficientes para gerar todos os itens!");
        }

        // Converte a posição do jogador para coordenadas da matriz
        int[] posicaoJogadorMatriz = converterParaCoordenadasMatriz(posicaoJogador[0], posicaoJogador[1]);

        // Verifica se a posição do jogador é válida
        if (!posicaoValida(posicaoJogadorMatriz)) {
            throw new IllegalStateException("A posição do jogador não é válida!");
        }

        // Lista de posições já utilizadas (incluindo a posição do jogador)
        List<int[]> posicoesUtilizadas = new ArrayList<>();
        posicoesUtilizadas.add(posicaoJogadorMatriz); // Adiciona a posição do jogador

        // Escolhe aleatoriamente posições válidas para gerar os itens
        for (int i = 0; i < quantidade; i++) {
            int indiceAleatorio = Greenfoot.getRandomNumber(posicoesValidas.size());
            int[] posicao = posicoesValidas.get(indiceAleatorio);

            // Verifica se a posição já foi utilizada ou é a posição do jogador
            while (posicaoUtilizada(posicoesUtilizadas, posicao)) {
                indiceAleatorio = Greenfoot.getRandomNumber(posicoesValidas.size());
                posicao = posicoesValidas.get(indiceAleatorio);
            }

            // Adiciona a posição à lista de posições utilizadas
            posicoesUtilizadas.add(posicao);

            // Converte as coordenadas da matriz para coordenadas do mundo
            int[] coordenadasMundo = converterParaCoordenadasMundo(posicao[0], posicao[1]);

            // Gera o item na posição
            try {
                Actor item = (Actor) tipoItem.getDeclaredConstructor().newInstance();
                if (item instanceof Moeda) {
                    item.getImage().scale(20, 20); // Redimensiona a moeda
                }
                mundo.addObject(item, coordenadasMundo[0], coordenadasMundo[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Retorna uma lista de posições válidas no labirinto
    private List<int[]> getPosicoesValidas() {
        List<int[]> posicoesValidas = new ArrayList<>();

        for (int linha = 0; linha < labirinto.length; linha++) {
            for (int coluna = 0; coluna < labirinto[linha].length; coluna++) {
                if (labirinto[linha][coluna] == 0) { // Verifica se é um caminho
                    posicoesValidas.add(new int[]{linha, coluna});
                }
            }
        }

        return posicoesValidas;
    }

    // Converte coordenadas da matriz para coordenadas do mundo
    private int[] converterParaCoordenadasMundo(int linha, int coluna) {
        int x = coluna * tamanhoBloco + tamanhoBloco / 2;
        int y = linha * tamanhoBloco + tamanhoBloco / 2;
        return new int[]{x, y};
    }

    // Converte coordenadas do mundo para coordenadas da matriz
    private int[] converterParaCoordenadasMatriz(int x, int y) {
        int coluna = (x - tamanhoBloco / 2) / tamanhoBloco;
        int linha = (y - tamanhoBloco / 2) / tamanhoBloco;
        return new int[]{linha, coluna};
    }

    // Verifica se uma posição já foi utilizada
    private boolean posicaoUtilizada(List<int[]> posicoesUtilizadas, int[] posicao) {
        for (int[] p : posicoesUtilizadas) {
            if (p[0] == posicao[0] && p[1] == posicao[1]) {
                return true;
            }
        }
        return false;
    }

    // Verifica se uma posição é válida no labirinto
    private boolean posicaoValida(int[] posicao) {
        int linha = posicao[0];
        int coluna = posicao[1];
        return linha >= 0 && linha < labirinto.length && coluna >= 0 && coluna < labirinto[0].length;
    }
}