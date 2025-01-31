import greenfoot.*;

public class Labirinto extends World {
    private int tamanhoBloco = 40; // Tamanho de cada bloco (largura e altura)
    private int pontos;
    private int index;
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
        int[][] labirinto = geradora.getLabirinto(index);

        // Percorrer a matriz e adicionar paredes
        for (int linha = 0; linha < labirinto.length; linha++) {
            for (int coluna = 0; coluna < labirinto[linha].length; coluna++) {
                if (labirinto[linha][coluna] == 1) {
                    int x = coluna * tamanhoBloco + tamanhoBloco / 2;
                    int y = linha * tamanhoBloco + tamanhoBloco / 2;
                    addObject(new Parede(), x, y);
                }
            }
        }
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
    
        //Adicionar a moeda 1
        Moeda moeda1 = new Moeda();
        moeda1.getImage().scale(20, 20);
        addObject(moeda1, 219, 225);
        
        //Adicionar a moeda 2
        Moeda moeda2 = new Moeda();
        moeda2.getImage().scale(20, 20);
        addObject(moeda2, 140, 340);
        
        //Adicionar a moeda 3
        Moeda moeda3 = new Moeda();
        moeda3.getImage().scale(20, 20);
        addObject(moeda3, 380, 340);

        showText("Pontos: " + pontos, 50, 20);
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
