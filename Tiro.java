import greenfoot.*;

public class Tiro extends Obstaculo
{
    public void act()
    {
        mover();
    }
    
    private void mover() {
        int x = getX();
        int y = getY();
        
        setLocation(x - 4, y);
        if (x == 0) {
            setLocation(600, y);
        }
    }
}
