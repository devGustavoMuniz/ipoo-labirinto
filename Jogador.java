import greenfoot.*;

public class Jogador extends Actor {
    private GreenfootSound backgroundMusic;
    private GreenfootImage imagemOriginal;
    private boolean viradoParaDireita = true;

    public Jogador() {
        imagemOriginal = new GreenfootImage("necro2.png");
        imagemOriginal.scale(imagemOriginal.getWidth() / 2, imagemOriginal.getHeight() / 2);
        setImage(imagemOriginal);

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
        
        int newX = x;
        int newY = y;
    
        boolean up = Greenfoot.isKeyDown("up");
        boolean down = Greenfoot.isKeyDown("down");
        boolean left = Greenfoot.isKeyDown("left");
        boolean right = Greenfoot.isKeyDown("right");
    
        if (up) newY -= 2;
        if (down) newY += 2;
        if (left) {
            newX -= 2;
            virarParaEsquerda();
        }
        if (right) {
            newX += 2;
            virarParaDireita();
        }
    
        setLocation(newX, newY);
        if (isTouching(Parede.class)) {
            setLocation(x, newY);
            if (isTouching(Parede.class)) {
                setLocation(newX, y);
                if (isTouching(Parede.class)) {
                    setLocation(x, y);
                }
            }
        }
    }
    
    private void virarParaDireita() {
        if (!viradoParaDireita) {
            setImage(imagemOriginal);
            viradoParaDireita = true;
        }
    }

    private void virarParaEsquerda() {
        if (viradoParaDireita) {
            GreenfootImage imagemEspelhada = new GreenfootImage(imagemOriginal);
            imagemEspelhada.mirrorHorizontally();
            setImage(imagemEspelhada);
            viradoParaDireita = false;
        }
    }

    private void verificarColisoes() {
        Labirinto mundo = (Labirinto) getWorld();
        if (isTouching(Obstaculo.class)) {
            mundo.finalizarJogo(false);
            tocarSom("death.mp3",60);
            backgroundMusic.stop();
        }

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

        if (isTouching(Porta.class) && mundo.getObjects(Chave.class).isEmpty()) {
            mundo.finalizarJogo(true);
            tocarSom("open.wav", 60);
            backgroundMusic.stop(); 
            tocarSom("victory.mp3",60);
        }
    }

    private void tocarSom(String arquivo, int volume) {
        GreenfootSound som = new GreenfootSound(arquivo);
        som.setVolume(volume); 
        som.play(); 
    }
}
