 import java.util.ArrayList;
 
public class GeradoraLabirintos  
{
    private ArrayList<int[][]> listaLabirintos;
    
    public GeradoraLabirintos()
    {
        listaLabirintos = new ArrayList<>();
         int[][] labirinto1 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,1,1,0,1,1,1,0,1},
            {1,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
            {1,1,1,1,1,1,1,0,1,1,1,0,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,1,0,1},
            {1,0,1,1,1,0,1,1,1,0,1,0,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
            {1,1,1,0,1,1,1,1,1,0,1,1,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        int[][] labirinto2 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,1,1,0,1},
            {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
            {1,0,1,0,1,1,1,1,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        int[][] labirinto3 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,1,0,0,0,1,0,0,0,0,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,1,1,1,1,0,1,1,1,1,1,0,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        int[][] labirinto4 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,0,1,0,1,1,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
            {1,0,1,1,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,1,0,1},
            {1,1,1,0,1,1,1,0,1,1,1,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        
        listaLabirintos.add(labirinto1);
        listaLabirintos.add(labirinto2);
        listaLabirintos.add(labirinto3);
        listaLabirintos.add(labirinto4);
    }

    public int[][] getLabirinto(int index){
        return this.listaLabirintos.get(index);
    }

}
