import java.util.ArrayList;
import java.util.List;

public class GeradoraLabirintos {
    private ArrayList<int[][]> listaLabirintos;

    public GeradoraLabirintos() {
        listaLabirintos = new ArrayList<>();

        // Labirinto 1
        int[][] labirinto1 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,1,1,0,1,1,1,0,1},
            {1,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
            {1,1,1,1,1,1,1,0,1,1,1,0,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,1,0,1},
            {1,0,1,1,1,0,1,1,1,0,1,0,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
            {1,1,1,0,1,1,1,1,1,0,1,1,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        // Labirinto 2
        int[][] labirinto2 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,1,1,0,1},
            {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
            {1,0,1,0,1,1,1,1,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        // Labirinto 3
        int[][] labirinto3 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,1,0,0,0,1,0,0,0,0,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,1,1,1,1,0,1,1,1,1,1,0,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        // Labirinto 4
        int[][] labirinto4 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,0,1,0,1,1,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
            {1,0,1,1,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,1,0,1},
            {1,1,1,0,1,1,1,0,1,1,1,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        // Adiciona um café, um inimigo e uma pedra em cada labirinto
        labirinto1[2][2] = 2; // Café no labirinto 1
        labirinto1[3][4] = 3; // Inimigo no labirinto 1
        labirinto1[5][6] = 4; // Pedra no labirinto 1

        labirinto2[3][4] = 2; // Café no labirinto 2
        labirinto2[5][8] = 3; // Inimigo no labirinto 2
        labirinto2[7][8] = 4; // Pedra no labirinto 2

        labirinto3[5][6] = 2; // Café no labirinto 3
        labirinto3[7][8] = 3; // Inimigo no labirinto 3
        labirinto3[2][2] = 4; // Pedra no labirinto 3

        labirinto4[7][8] = 2; // Café no labirinto 4
        labirinto4[2][2] = 3; // Inimigo no labirinto 4
        labirinto4[5][6] = 4; // Pedra no labirinto 4

        // Adiciona os labirintos à lista
        listaLabirintos.add(labirinto1);
        listaLabirintos.add(labirinto2);
        listaLabirintos.add(labirinto3);
        listaLabirintos.add(labirinto4);
    }

    // Retorna o labirinto com base no índice
    public int[][] getLabirinto(int index) {
        return this.listaLabirintos.get(index);
    }

    // Retorna uma lista de posições válidas (onde o valor é 0)
    public List<int[]> getPosicoesValidas(int index) {
        int[][] labirinto = getLabirinto(index);
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
}