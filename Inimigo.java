import greenfoot.*;

public class Inimigo extends Obstaculo {
    private int direcao;
    private int contadorMovimento = 0;
    private GreenfootImage imagemOriginal;

    public Inimigo() {
        imagemOriginal = new GreenfootImage(getImage());
        redimensionarImagem(imagemOriginal);
        setImage(imagemOriginal);
        direcao = Greenfoot.getRandomNumber(4); 
    }

    public void act() {
        mover();
    }

    private void mover() {
        int x = getX();
        int y = getY();
        
        switch (direcao) {
            case 0: setLocation(x, y - 2); break; // Cima
            case 1: setLocation(x, y + 2); break; // Baixo
            case 2: 
                setLocation(x - 2, y); 
                setImage(imagemOriginal);
                break; // Esquerda
            case 3: 
                setLocation(x + 2, y); 
                espelharImagem();
                break; // Direita
        }
        
        if (isTouching(Parede.class)) {
            setLocation(x, y);
            mudarDirecao();
        }
        
        contadorMovimento++;
        if (contadorMovimento >= 200) {
            mudarDirecao();
            contadorMovimento = 0;
        }
    }

    private void mudarDirecao() {
        direcao = Greenfoot.getRandomNumber(4);
    }
    
    private void espelharImagem() {
        GreenfootImage imagem = new GreenfootImage(imagemOriginal);
        imagem.mirrorHorizontally();
        setImage(imagem);
    }
    
    private void redimensionarImagem(GreenfootImage imagem) {
        imagem.scale(30, 30);
    }
}