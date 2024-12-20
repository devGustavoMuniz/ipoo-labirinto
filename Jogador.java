import greenfoot.*;

public class Jogador extends Actor {

    public Jogador() {
        GreenfootImage imagem = new GreenfootImage("necro2.png");
        imagem.scale(imagem.getWidth() / 2, imagem.getHeight() / 2);
        setImage(imagem);
    }

    public void act() {
        mover();
        verificarColisoes();
    }
    


    private void mover() {
        int x = getX();
        int y = getY();

        if (Greenfoot.isKeyDown("up")) {
            setLocation(x, y - 2);
            if (isTouching(Parede.class)) {
                setLocation(x, y); // Voltar para a posição original
            }
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(x, y + 2);
            if (isTouching(Parede.class)) {
                setLocation(x, y); // Voltar para a posição original
            }
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(x - 2, y);
            if (isTouching(Parede.class)) {
                setLocation(x, y); // Voltar para a posição original
            }
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(x + 2, y);
            if (isTouching(Parede.class)) {
                setLocation(x, y); // Voltar para a posição original
            }
        }
    }

    private void verificarColisoes() {
        if (isTouching(Moeda.class)) {
            removeTouching(Moeda.class);
            ((Labirinto) getWorld()).adicionarPontos(10);
        }
        
        if (isTouching(Chave.class)) {
            removeTouching(Chave.class);
            ((Labirinto) getWorld()).adicionarPontos(10);
        }

        if (isTouching(Porta.class)) {
            Labirinto mundo = (Labirinto) getWorld();
            if (mundo.getObjects(Chave.class).isEmpty()) {
                mundo.finalizarJogo(true);
            }
        }
    }
}
