import greenfoot.*;

public class TelaFinal extends World {
    public TelaFinal(boolean venceu, int pontos) {    
        super(600, 400, 1);
        if (venceu) {
            showText("Você venceu!", 300, 150);
        } else {
            showText("Você perdeu!", 300, 150);
        }
        showText("Pontuação final: " + pontos, 300, 200);
        showText("Pressione R para reiniciar", 300, 250);
    }

    public void act() {
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new TelaInicial());
        }
    }
}
