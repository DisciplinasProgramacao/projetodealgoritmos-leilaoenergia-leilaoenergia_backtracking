package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DivisaoConquista {


    public List<Lance> resolver(List<Lance> lances, int energiaInicial) {
        return dividirEConquistar(lances, energiaInicial, 0);
    }


    /**
     * Cada chamada recursiva cria dois ramos possíveis na árvore de recursão: um ramo onde o lance atual é incluído
     * na solução e outro ramo onde o lance atual não é incluído. Cada nó representa uma decisão de inclusão ou não
     * inclusão de um lance.
     * @param lances
     * @param energiaRestante
     * @param inicio
     * @return
     */
    private List<Lance> dividirEConquistar(List<Lance> lances, int energiaRestante, int inicio) {

        if (inicio >= lances.size() || energiaRestante <= 0) {
            return new ArrayList<>();
        }

        Lance lanceAtual = lances.get(inicio);

        List<Lance> incluir = new ArrayList<>();
        if (lanceAtual.energia <= energiaRestante) {
            incluir.add(lanceAtual);
            incluir.addAll(dividirEConquistar(lances, energiaRestante - lanceAtual.energia, inicio + 1));
        }

        List<Lance> naoIncluir = dividirEConquistar(lances, energiaRestante, inicio + 1);

        int lucroIncluir = calcularLucro(incluir);
        int lucroNaoIncluir = calcularLucro(naoIncluir);

        if (lucroIncluir > lucroNaoIncluir && energiaRestante >= 0) {
            return incluir;
        } else {
            return naoIncluir;
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

