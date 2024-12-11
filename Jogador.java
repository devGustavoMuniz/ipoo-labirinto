import greenfoot.*;  

public class Jogador extends Actor {

    public Jogador() {
        GreenfootImage imagem = new GreenfootImage("necro.png");
        imagem.scale(imagem.getWidth() / 2, imagem.getHeight() / 2); // Reduz a imagem pela metade
        setImage(imagem);
    }
    
    public void act() {
        mover();
        verificarColisoes();
    }

    private void mover() {
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - 2);
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + 2);
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - 2, getY());
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + 2, getY());
        }
    }

    private void verificarColisoes() {
        if (isTouching(Chave.class)) {
            removeTouching(Chave.class);
            ((Labirinto)getWorld()).adicionarPontos(10);
        }

        if (isTouching(Porta.class)) {
            Labirinto mundo = (Labirinto)getWorld();
            if (mundo.getObjects(Chave.class).isEmpty()) {
                mundo.finalizarJogo(true); // Vitória
            }
        }
    }
}
