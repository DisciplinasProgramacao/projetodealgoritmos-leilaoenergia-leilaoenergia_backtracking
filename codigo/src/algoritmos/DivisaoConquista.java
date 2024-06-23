package algoritmos;

import entity.Lance;

import java.util.*;

public class DivisaoConquista {

    public static List<Lance> resolver(List<Lance> lances, int energiaMaxima) {
        // Ordenar os lances por valor por unidade de energia em ordem decrescente
        lances.sort((l1, l2) -> Double.compare((double) l2.valor / l2.energia, (double) l1.valor / l1.energia));
        List<Lance> lancesSelecionados = new ArrayList<>();
        divisaoConquista(lances, 0, lances.size() - 1, energiaMaxima, lancesSelecionados);
        return lancesSelecionados;
    }

    private static void divisaoConquista(List<Lance> lances, int esquerda, int direita, int energiaRestante, List<Lance> lancesSelecionados) {
        if (esquerda > direita || energiaRestante <= 0) {
            return;
        }

        // Encontrar o lance que maximiza o valor por unidade de energia
        int indiceMaximoLucro = esquerda;
        for (int i = esquerda + 1; i <= direita; i++) {
            if ((double) lances.get(i).valor / lances.get(i).energia > (double) lances.get(indiceMaximoLucro).valor / lances.get(indiceMaximoLucro).energia) {
                indiceMaximoLucro = i;
            }
        }

        Lance lanceSelecionado = lances.get(indiceMaximoLucro);

        // Se o lance cabe na energia disponível
        if (lanceSelecionado.energia <= energiaRestante) {
            lancesSelecionados.add(lanceSelecionado);
            energiaRestante -= lanceSelecionado.energia;
        }

        // Resolver os subproblemas recursivamente
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
