import greenfoot.*;

public class TelaInicial extends World {
    public TelaInicial() {    
        super(600, 400, 1);
        prepararTela();
    }

    private void prepararTela() {
        GreenfootImage bg = new GreenfootImage(600, 400);
        bg.setColor(Color.BLACK);
        bg.fill();

        // Título principal
        GreenfootImage titulo = new GreenfootImage("Escape do Labirinto", 40, Color.WHITE, null);
        bg.drawImage(titulo, (getWidth() - titulo.getWidth()) / 2, 100);

        // Instrução para iniciar
        GreenfootImage iniciar = new GreenfootImage("Pressione ENTER para começar", 24, Color.LIGHT_GRAY, null);
        bg.drawImage(iniciar, (getWidth() - iniciar.getWidth()) / 2, 200);

        // Instrução para ajuda
        GreenfootImage ajuda = new GreenfootImage("Pressione I para instruções", 20, Color.GRAY, null);
        bg.drawImage(ajuda, (getWidth() - ajuda.getWidth()) / 2, 250);

        setBackground(bg);
    }

    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new Labirinto(new GameState())); // Inicia com novo GameState
        }
        if (Greenfoot.isKeyDown("i") || Greenfoot.isKeyDown("I")) {
            Greenfoot.setWorld(new TelaInstrucoes());
        }
    }
}