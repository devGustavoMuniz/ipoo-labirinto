import greenfoot.*;

public class TelaInicial extends World {
    public TelaInicial() {    
        super(600, 400, 1);
        GreenfootImage bg = new GreenfootImage(600, 400);
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);

        // Título estilizado
        GreenfootImage titulo = new GreenfootImage("Escape do Labirinto", 40, Color.WHITE, null);
        bg.drawImage(titulo, (getWidth() - titulo.getWidth()) / 2, 100);

        // Instruções estilizadas
        GreenfootImage instrucoes = new GreenfootImage("Pressione ENTER para começar", 24, Color.LIGHT_GRAY, null);
        bg.drawImage(instrucoes, (getWidth() - instrucoes.getWidth()) / 2, 200);

        // Regras
        GreenfootImage regras = new GreenfootImage("Regras:\n- Moedas dão pontos\n- Colete a(s) chave(s) para escapar", 20, Color.YELLOW, null);
        bg.drawImage(regras, (getWidth() - regras.getWidth()) / 2, 250);
    }

    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new Labirinto());
        }
    }
}
