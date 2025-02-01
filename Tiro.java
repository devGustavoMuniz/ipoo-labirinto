import greenfoot.*;

public class Tiro extends Obstaculo
{
    public Tiro(String nomeImagem, int tamanho) {
        super(nomeImagem, tamanho);
    }
    public void mover() {
        int x = getX();
        int y = getY();
        
        setLocation(x - 4, y);
        if (x == 0) {
            setLocation(600, y);
        }
    }
}
