import greenfoot.*;

public class Jogador extends Actor {
    private GreenfootSound backgroundMusic;

    public Jogador() {
        GreenfootImage imagem = new GreenfootImage("necro2.png");
        imagem.scale(imagem.getWidth() / 2, imagem.getHeight() / 2);
        setImage(imagem);

        backgroundMusic = new GreenfootSound("soundtrack.wav");
        backgroundMusic.setVolume(30); 
        backgroundMusic.playLoop(); 
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
                setLocation(x, y); 
            }
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(x, y + 2);
            if (isTouching(Parede.class)) {
                setLocation(x, y); 
            }
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(x - 2, y);
            if (isTouching(Parede.class)) {
                setLocation(x, y); 
            }
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(x + 2, y);
            if (isTouching(Parede.class)) {
                setLocation(x, y); 
            }
        }
    }

    private void verificarColisoes() {
        if (isTouching(Moeda.class)) {
            removeTouching(Moeda.class);
            ((Labirinto) getWorld()).adicionarPontos(10);
            tocarSom("coin.wav", 60); 
        }

        if (isTouching(Chave.class)) {
            removeTouching(Chave.class);
            ((Labirinto) getWorld()).adicionarPontos(10);
            tocarSom("pickey.wav", 60); 
        }

        if (isTouching(Porta.class)) {
            Labirinto mundo = (Labirinto) getWorld();
            if (mundo.getObjects(Chave.class).isEmpty()) {
                mundo.finalizarJogo(true);
                tocarSom("open.wav", 60); 
            }
        }
    }

    private void tocarSom(String arquivo, int volume) {
        GreenfootSound som = new GreenfootSound(arquivo);
        som.setVolume(volume); 
        som.play(); 
    }
}
