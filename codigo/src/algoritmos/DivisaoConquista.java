package algoritmos;

import entity.Lance;

import java.util.*;

public class DivisaoConquista {

    public static List<Lance> selecionarLances(List<Lance> lances, int energiaMaxima) {
        List<Lance> lancesSelecionados = new ArrayList<>();
        selecionarLancesRecursivo(lances, 0, lances.size() - 1, energiaMaxima, lancesSelecionados);
        return lancesSelecionados;
    }

    private static void selecionarLancesRecursivo(List<Lance> lances, int left, int right, int energiaRestante, List<Lance> lancesSelecionados) {
        if (left > right) {
            return;
        }

        // Encontrar o lance que maximiza o valor por unidade de energia
        int indiceMaximoLucro = left;
        for (int i = left + 1; i <= right; i++) {
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
            selecionarLancesRecursivo(lances, left, indiceMaximoLucro - 1, energiaRestante, lancesSelecionados);
            selecionarLancesRecursivo(lances, indiceMaximoLucro + 1, right, energiaRestante, lancesSelecionados);
        } else {
            // Se não couber, resolver apenas com o restante da energia disponível
            selecionarLancesRecursivo(lances, left, indiceMaximoLucro - 1, energiaRestante, lancesSelecionados);
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

