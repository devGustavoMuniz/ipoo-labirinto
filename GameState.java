import java.util.List;
import java.util.ArrayList;

public class GameState {
    private long tempoTotal;
    private int pontosTotais;
    private List<Integer> pontosPorFase;
    private int faseAtual;

    public GameState() {
        this.tempoTotal = 0;
        this.pontosTotais = 0;
        this.pontosPorFase = new ArrayList<>();
        this.pontosPorFase.add(0); // Inicia com a fase 0
        this.faseAtual = 0;
    }

    public long getTempoTotal() {
        return tempoTotal;
    }

    public void addTempo(long tempo) {
        this.tempoTotal += tempo;
    }

    public int getPontosTotais() {
        return pontosTotais;
    }

    public List<Integer> getPontosPorFase() {
        return new ArrayList<>(pontosPorFase);
    }

    public int getFaseAtual() {
        return faseAtual;
    }

    public void avancarFase() {
        this.faseAtual++;
        this.pontosPorFase.add(0); // Nova fase inicia com 0 pontos
    }

    public void addPontos(int pontos) {
        this.pontosTotais += pontos;
        if (faseAtual >= 0 && faseAtual < pontosPorFase.size()) {
            int pontosAtuais = pontosPorFase.get(faseAtual);
            pontosPorFase.set(faseAtual, pontosAtuais + pontos);
        }
    }
}