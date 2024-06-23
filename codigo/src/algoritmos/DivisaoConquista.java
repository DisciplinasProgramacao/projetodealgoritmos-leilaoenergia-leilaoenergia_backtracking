package algoritmos;

import entity.Lance;

import java.util.*;

public class DivisaoConquista {

    public static List<Lance> resolver(List<Lance> lances, int energiaMaxima) {
        List<Lance> lancesSelecionados = new ArrayList<>();
        divisaoConquista(lances, 0, lances.size() - 1, energiaMaxima, lancesSelecionados);
        return lancesSelecionados;
    }

    private static void divisaoConquista(List<Lance> lances, int esquerda, int direita, int energiaRestante, List<Lance> lancesSelecionados) {

        if (esquerda > direita) {
            return;
        }

        // Encontrar o lance que maximiza o valor por unidade de energia
        int indiceMaximoLucro = esquerda;
        for (int i = esquerda + 1; i <= direita; i++) {
            if (lances.get(i).getValor() * lances.get(indiceMaximoLucro).getEnergia() > lances.get(indiceMaximoLucro).getValor() * lances.get(i).getEnergia()) {
                indiceMaximoLucro = i;
            }
        }

        Lance lanceSelecionado = lances.get(indiceMaximoLucro);

        // Se o lance cabe na energia disponível
        if (lanceSelecionado.getEnergia() <= energiaRestante) {
            lancesSelecionados.add(lanceSelecionado);
            energiaRestante -= lanceSelecionado.getEnergia();

            // Resolver os subproblemas recursivamente
            divisaoConquista(lances, esquerda, indiceMaximoLucro - 1, energiaRestante, lancesSelecionados);
            divisaoConquista(lances, indiceMaximoLucro + 1, direita, energiaRestante, lancesSelecionados);
        } else {
            // Se não couber, resolver apenas com o restante da energia disponível
            divisaoConquista(lances, esquerda, indiceMaximoLucro - 1, energiaRestante, lancesSelecionados);
        }
    }

    public int calcularLucro(List<Lance> lances) {
        int lucro = 0;
        for (Lance lance : lances) {
            lucro += lance.valor;
        }
        return lucro;
    }




}

