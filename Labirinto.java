import greenfoot.*;

public class Labirinto extends World {
    private int pontos;

    public Labirinto() {    
        super(600, 400, 1);
        pontos = 0;
        prepare();
    }

    private void prepare() {
        Jogador jogador = new Jogador();
        addObject(jogador, 50, 200);
        
        Chave chave = new Chave();
        addObject(chave, 300, 200);

        Porta porta = new Porta();
        addObject(porta, 550, 200);
    }

    public void adicionarPontos(int valor) {
        pontos += valor;
        showText("Pontos: " + pontos, 50, 20);
    }

    public void finalizarJogo(boolean venceu) {
        Greenfoot.setWorld(new TelaFinal(venceu, pontos));
    }
}
