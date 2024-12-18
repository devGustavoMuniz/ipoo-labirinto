import greenfoot.*;

public class TelaFinal extends World {
    public TelaFinal(boolean venceu, int pontos) {    
        super(600, 400, 1);
        GreenfootImage bg = new GreenfootImage(600, 400);
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);

        // Mensagem de vitória ou derrota estilizada
        String mensagem = venceu ? "Você venceu!" : "Você perdeu!";
        GreenfootImage titulo = new GreenfootImage(mensagem, 40, Color.WHITE, null);
        bg.drawImage(titulo, (getWidth() - titulo.getWidth()) / 2, 100);

        // Pontuação final
        GreenfootImage pontuacao = new GreenfootImage("Pontuação final: " + pontos, 24, Color.LIGHT_GRAY, null);
        bg.drawImage(pontuacao, (getWidth() - pontuacao.getWidth()) / 2, 200);

        // Instrução para reiniciar
        GreenfootImage reiniciar = new GreenfootImage("Pressione R para reiniciar", 24, Color.YELLOW, null);
        bg.drawImage(reiniciar, (getWidth() - reiniciar.getWidth()) / 2, 250);
    }

    public void act() {
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new TelaInicial());
        }
    }
}
