import greenfoot.*;

public class Parede extends Actor {
    public Parede() {
        GreenfootImage img = new GreenfootImage(40, 40); // Tamanho ajustado para o grid
        img.setColor(Color.GRAY); // Cor das paredes
        img.fillRect(0, 0, img.getWidth(), img.getHeight());
        setImage(img);
    }
}
