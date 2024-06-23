package algoritmos;

import java.util.ArrayList;
import java.util.List;

public class BackTracking {
    private int maiorLucro;
    private List<Lance> solucao;

    public void resolver(List<Lance> Lances, int energia) {
        maiorLucro = 0;
        solucao = new ArrayList<>();
        backtrack(Lances, new ArrayList<>(), energia, 0, 0);
    }

    private void backtrack(List<Lance> Lances, List<Lance> solucaoAtual, int energiaRestante, int lucroAtual, int comeco) {
        if (energiaRestante < 0) {
            return;
        }

        if (lucroAtual > maiorLucro) {
            maiorLucro = lucroAtual;
            solucao = new ArrayList<>(solucaoAtual);
        }

        for (int i = comeco; i < Lances.size(); i++) {
            Lance Lance = Lances.get(i);
            if (Lance.energia <= energiaRestante) {
                solucaoAtual.add(Lance);
                backtrack(Lances, solucaoAtual, energiaRestante - Lance.energia, lucroAtual + Lance.valor, i + 1);
                solucaoAtual.remove(solucaoAtual.size() - 1);
            }
        }
    }

    public int getMaiorLucro() {
        return maiorLucro;
    }

    public List<Lance> getSolucao() {
        return solucao;
    }

}

