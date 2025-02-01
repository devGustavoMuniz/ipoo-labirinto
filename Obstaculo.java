import greenfoot.*;

public class Obstaculo extends Actor
{
    private final GreenfootImage imagem;

    public Obstaculo(String nomeImagem, int tamanho) {
        GreenfootImage GreenfootImage = new GreenfootImage(nomeImagem);
        GreenfootImage.scale(tamanho, tamanho);
        setImage(GreenfootImage);
        imagem = GreenfootImage;
    }

    public GreenfootImage getImagem() {
        return imagem;
    }
}
