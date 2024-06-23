package algoritmos;

import entity.Lance;

import java.util.*;

public class DivisaoConquista {

    public static List<Lance> resolver(List<Lance> lances, int energiaMaxima) {

        lances.sort((l1, l2) -> Double.compare((double) l2.valor / l2.energia, (double) l1.valor / l1.energia));
        List<Lance> lancesSelecionados = new ArrayList<>();
        divisaoConquista(lances, 0, lances.size() - 1, energiaMaxima, lancesSelecionados);
        return lancesSelecionados;
    }

    private static void divisaoConquista(List<Lance> lances, int esquerda, int direita, int energiaRestante, List<Lance> lancesSelecionados) {
        if (esquerda > direita || energiaRestante <= 0) {
            return;
        }


        int indiceMaximoLucro = esquerda;
        for (int i = esquerda + 1; i <= direita; i++) {
            if ((double) lances.get(i).valor / lances.get(i).energia > (double) lances.get(indiceMaximoLucro).valor / lances.get(indiceMaximoLucro).energia) {
                indiceMaximoLucro = i;
            }
        }

        Lance lanceSelecionado = lances.get(indiceMaximoLucro);


        if (lanceSelecionado.energia <= energiaRestante) {
            lancesSelecionados.add(lanceSelecionado);
            energiaRestante -= lanceSelecionado.energia;
        }


        divisaoConquista(lances, esquerda, indiceMaximoLucro - 1, energiaRestante, lancesSelecionados);
        divisaoConquista(lances, indiceMaximoLucro + 1, direita, energiaRestante, lancesSelecionados);
    }

    public int calcularLucro(List<Lance> lances) {
        int lucro = 0;
        for (Lance lance : lances) {
            lucro += lance.valor;
        }
        return lucro;
    }
}
