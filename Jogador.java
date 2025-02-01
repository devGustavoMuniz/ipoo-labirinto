import greenfoot.*;

public class Jogador extends Actor {
    private GreenfootSound backgroundMusic;
    private GreenfootImage imagemOriginal;
    private boolean viradoParaDireita = true;
    private int velocidade = 2;

    public Jogador() {
        imagemOriginal = new GreenfootImage("necro2.png");
        imagemOriginal.scale(25, 25);
        setImage(imagemOriginal);

        tocarSom("soundtrack.wav", 30, true);
    }

    public void act() {
        mover();
        verificarColisoes();
    }

    public void setBackgroundMusic (GreenfootSound som) {
        backgroundMusic = som;
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

        if (up) newY -= velocidade;
        if (down) newY += velocidade;
        if (left) {
            newX -= velocidade;
            virarParaEsquerda();
        }
        if (right) {
            newX += velocidade;
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
            GreenfootImage imagemEspelhada = imagemOriginal;
            imagemEspelhada.mirrorHorizontally();
            setImage(imagemEspelhada);
            viradoParaDireita = false;
        }
    }

    private void tocarObstaculo(Labirinto mundo) {
        mundo.finalizarJogo(false);
        tocarSom("death.mp3", 60, false);
        backgroundMusic.stop();
    }

    private void tocarMoeda(Labirinto mundo) {
        mundo.adicionarPontos(10);
        tocarSom("coin.wav", 30, false);
        removeTouching(Moeda.class);
    }

    private void tocarChave(Labirinto mundo) {
        mundo.adicionarPontos(10);
        tocarSom("pickey.wav", 60, false);
        removeTouching(Chave.class);
    }

    private void tocarPorta(Labirinto mundo) {
        mundo.finalizarJogo(true);
        tocarSom("open.wav", 60, false);
        tocarSom("victory.mp3", 60, false);
        backgroundMusic.stop();
    }

    private void tocarCafe(Labirinto mundo) {
        mundo.adicionarPontos(200);
        tocarSom("coffe.mp3", 60, false);
        removeTouching(Cafe.class);
        aumentarVelocidade();
    }

    private void verificarColisoes() {
        Labirinto mundo = (Labirinto) getWorld();

        if (isTouching(Obstaculo.class)) {
            tocarObstaculo(mundo);
        }

        if (isTouching(Moeda.class)) {
            tocarMoeda(mundo);
        }

        if (isTouching(Chave.class)) {
            tocarChave(mundo);
        }

        if (isTouching(Porta.class) && mundo.getObjects(Chave.class).isEmpty()) {
            tocarPorta(mundo);
        }

        if (isTouching(Cafe.class)) {
            tocarCafe(mundo);
        }
    }

    private void aumentarVelocidade() {
        velocidade += 1;
    }

    private void tocarSom(String arquivo, int volume, boolean isLoop) {
        GreenfootSound som = new GreenfootSound(arquivo);
        som.setVolume(volume);
        if (isLoop) {
            som.playLoop();
            setBackgroundMusic(som);
            return;
        }
        som.play();
    }
}