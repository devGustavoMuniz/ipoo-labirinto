import greenfoot.*;

public class Jogador extends Actor {
    private GreenfootSound backgroundMusic;
    private GreenfootImage imagemOriginal;
    private boolean viradoParaDireita = true;
    private int velocidade = 2; // Velocidade inicial do jogador

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
    
        // Usa a velocidade atual para mover o jogador
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
            GreenfootImage imagemEspelhada = new GreenfootImage(imagemOriginal);
            imagemEspelhada.mirrorHorizontally();
            setImage(imagemEspelhada);
            viradoParaDireita = false;
        }
    }

    private void verificarColisoes() {
        Labirinto mundo = (Labirinto) getWorld();
        
        // Verifica colisão com obstáculos
        if (isTouching(Obstaculo.class)) {
            mundo.finalizarJogo(false);
            tocarSom("death.mp3", 60);
            backgroundMusic.stop();
        }

        // Verifica colisão com moedas
        if (isTouching(Moeda.class)) {
            removeTouching(Moeda.class);
            mundo.adicionarPontos(10);
            tocarSom("coin.wav", 60); 
        }

        // Verifica colisão com a chave
        if (isTouching(Chave.class)) {
            removeTouching(Chave.class);
            mundo.adicionarPontos(10);
            tocarSom("pickey.wav", 60); 
        }

        // Verifica colisão com a porta (se o jogador tem a chave)
        if (isTouching(Porta.class) && mundo.getObjects(Chave.class).isEmpty()) {
            mundo.finalizarJogo(true);
            tocarSom("open.wav", 60);
            backgroundMusic.stop(); 
            tocarSom("victory.mp3", 60);
        }

        // Verifica colisão com o café
        if (isTouching(Cafe.class)) {
            removeTouching(Cafe.class);
            mundo.adicionarPontos(200); // Ganha 200 pontos ao pegar o café
            aumentarVelocidade(); // Aumenta a velocidade do jogador
            tocarSom("coffe.mp3", 60); // Toca o som do café
        }
    }

    // Aumenta a velocidade do jogador
    private void aumentarVelocidade() {
        velocidade += 1; // Aumenta a velocidade em 1 unidade
    }

    private void tocarSom(String arquivo, int volume) {
        GreenfootSound som = new GreenfootSound(arquivo);
        som.setVolume(volume); 
        som.play(); 
    }
}