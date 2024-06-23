package algoritmos;

import java.util.ArrayList;
import java.util.List;

import entity.Lance;

public class ProgramacaoDinamica {
    public static int resolver(List<Lance> lances, int energia) {
        int n = lances.size();
        int[] lucrosMax = new int[energia + 1];

        for (int i = 0; i < n; i++) {
            for (int j = energia; j >= lances.get(i).energia; j--) {
                lucrosMax[j] = Math.max(lucrosMax[j], lucrosMax[j - lances.get(i).energia] + lances.get(i).valor);
            }
        }

        return lucrosMax[energia];
    }

    public static List<Lance> getSolucao(List<Lance> lances, int energia) {
        int n = lances.size();
        int[] lucrosMax = new int[energia + 1];
        int[][] escolhidos = new int[n + 1][energia + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = energia; j >= lances.get(i - 1).energia; j--) {
                if (lucrosMax[j] < lucrosMax[j - lances.get(i - 1).energia] + lances.get(i - 1).valor) {
                    lucrosMax[j] = lucrosMax[j - lances.get(i - 1).energia] + lances.get(i - 1).valor;
                    escolhidos[i][j] = 1;
                }
            }
        }

        List<Lance> solucao = new ArrayList<>();
        int j = energia;
        for (int i = n; i > 0; i--) {
            if (escolhidos[i][j] == 1) {
                solucao.add(lances.get(i - 1));
                j -= lances.get(i - 1).energia;
            }
        }

        return solucao;
    }

    public static void main(String[] args) {
        List<Lance> lances = new ArrayList<>();
        lances.add(new Lance(500, 500));
        lances.add(new Lance(500, 510));
        lances.add(new Lance(400, 520));
        lances.add(new Lance(300, 400));
        lances.add(new Lance(200, 220));
        lances.add(new Lance(900, 1110));

        int energia = 1000;
        int maiorLucro = resolver(lances, energia);
        List<Lance> solucao = getSolucao(lances, energia);

        System.out.println("Maior lucro: " + maiorLucro);
        System.out.println("Solução:");
        for (Lance lance : solucao) {
            System.out.println(lance);
        }
    }
}
