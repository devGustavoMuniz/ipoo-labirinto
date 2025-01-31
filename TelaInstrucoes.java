import greenfoot.*;

public class TelaInstrucoes extends World {
    public TelaInstrucoes() {    
        super(800, 600, 1);
        prepararTela();
    }

    private void prepararTela() {
        GreenfootImage bg = new GreenfootImage(800, 600);
        bg.setColor(new Color(10, 10, 30)); // Fundo azul escuro
        bg.fill();
        
        // Título estilizado
        Font fontTitulo = new Font("Castellar", true, false, 30);
        bg.setFont(fontTitulo);
        bg.setColor(new Color(200, 50, 50)); // Vermelho sombrio
        String titulo = "Cemitério dos Guerreiros Eternos";
        desenharTextoComBorda(bg, titulo, 80, 50);
        
        // História
        Font fontHistoria = new Font("Arial", false, false, 18);
        bg.setFont(fontHistoria);
        bg.setColor(Color.WHITE);
        String historia = "Como Necromante das Sombras, você deve invadir o antigo cemitério de guerra\n"
                        + "onde guerreiros lendários repousam. Sua missão: reivindicar os segredos proibidos\n"
                        + "da necromancia escondidos na cripta central. Mas cuidado - os defensores do local\n"
                        + "não descansam em paz...";
        desenharTextoComBorda(bg, historia, 40, 150);

        // Instruções
        Font fontInstrucoes = new Font("Arial", true, false, 22);
        bg.setFont(fontInstrucoes);
        String instrucoes = "Instruções:\n"
                          + "- Movimentação: setinhas\n"
                          + "- Colete chaves antigas ( ) para abrir portais\n"
                          + "- Reúna moedas de óbito para poder ancestral\n"
                          + "- Evite armadilhas mortais e guerreiros espetrais\n"
                          + "- Sobreviva até alcançar a cripta central!";
        desenharTextoComBorda(bg, instrucoes, 40, 300, Color.YELLOW, Color.BLACK);


        // GLHF
        Font fontGLHF = new Font("Impact", false, false, 28);
        bg.setFont(fontGLHF);
        desenharTextoComBorda(bg, "Boa sorte, Necromante...", 200, 500, Color.ORANGE, Color.BLACK);

        // Texto para iniciar
        Font fontIniciar = new Font("Arial", false, false, 24);
        bg.setFont(fontIniciar);
        bg.setColor(Color.CYAN);
        bg.drawString("Pressione ENTER para voltar ao menu", 180, 550);

        setBackground(bg);
    }

    private void desenharTextoComBorda(GreenfootImage bg, String texto, int x, int y, Color corFrente, Color corBorda) {
        bg.setColor(corBorda);
        bg.drawString(texto, x-1, y-1);
        bg.drawString(texto, x+1, y-1);
        bg.drawString(texto, x-1, y+1);
        bg.drawString(texto, x+1, y+1);
        bg.setColor(corFrente);
        bg.drawString(texto, x, y);
    }

    private void desenharTextoComBorda(GreenfootImage bg, String texto, int x, int y) {
        desenharTextoComBorda(bg, texto, x, y, Color.RED, Color.BLACK);
    }

    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new TelaInicial()); 
        }
    }
}