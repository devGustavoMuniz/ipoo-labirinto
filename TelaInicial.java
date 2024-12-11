import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

public class TelaInicial extends World {
    public TelaInicial() {    
        super(600, 400, 1);  // Dimensões do mundo
        showText("Escape do Labirinto", 300, 150);
        showText("Pressione ENTER para começar", 300, 200);
    }

    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new Labirinto());
        }
    }
}
