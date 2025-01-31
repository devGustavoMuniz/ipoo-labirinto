import greenfoot.*;
import java.util.List;
public class TelaFinal extends World {
    public TelaFinal(GameState gameState, boolean venceu) {    
        super(600, 400, 1);
        GreenfootImage bg = new GreenfootImage(600, 400);
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);

        // Título
        String mensagem = venceu ? "Você venceu!" : "Você perdeu!";
        GreenfootImage titulo = new GreenfootImage(mensagem, 40, Color.WHITE, null);
        bg.drawImage(titulo, (getWidth() - titulo.getWidth()) / 2, 50);

        // Tempo total
        long tempoTotalMs = gameState.getTempoTotal();
        long segundos = (tempoTotalMs / 1000) % 60;
        long minutos = (tempoTotalMs / (1000 * 60)) % 60;
        String tempoFormatado = String.format("Tempo total: %02d:%02d", minutos, segundos);
        GreenfootImage tempoImg = new GreenfootImage(tempoFormatado, 24, Color.WHITE, null);
        bg.drawImage(tempoImg, (getWidth() - tempoImg.getWidth()) / 2, 100);

        // Pontuação
        GreenfootImage pontuacaoTotal = new GreenfootImage("Pontuação total: " + gameState.getPontosTotais(), 24, Color.LIGHT_GRAY, null);
        bg.drawImage(pontuacaoTotal, (getWidth() - pontuacaoTotal.getWidth()) / 2, 140);

        // Pontos por fase
        List<Integer> pontosPorFase = gameState.getPontosPorFase();
        int y = 180;
        for (int i = 0; i < pontosPorFase.size(); i++) {
            String pontosFase = "Fase " + (i + 1) + ": " + pontosPorFase.get(i) + " pontos";
            GreenfootImage imgFase = new GreenfootImage(pontosFase, 20, Color.GRAY, null);
            bg.drawImage(imgFase, (getWidth() - imgFase.getWidth()) / 2, y);
            y += 30;
        }

        // Instruções
        GreenfootImage reiniciar = new GreenfootImage("Pressione R para reiniciar do zero", 24, Color.YELLOW, null);
        bg.drawImage(reiniciar, (getWidth() - reiniciar.getWidth()) / 2, y + 20);

        GreenfootImage menu = new GreenfootImage("Pressione ENTER para voltar ao menu", 24, Color.YELLOW, null);
        bg.drawImage(menu, (getWidth() - menu.getWidth()) / 2, y + 50);
    }

    public void act() {
        if (Greenfoot.isKeyDown("r")) { // Reinicia jogo imediatamente
            Greenfoot.setWorld(new Labirinto(new GameState()));
        }
        if (Greenfoot.isKeyDown("enter")) { // Volta para tela inicial
            Greenfoot.setWorld(new TelaInicial());
        }
    }
}