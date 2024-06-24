package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import entity.Lance;

public class Guloso {
    public static List<Lance> resolverPorEnergiaProValor(List<Lance> lances, int energia) {
        //Organiza os lances em ordem decrecente em relação valor/energia
        Collections.sort(lances, Comparator.comparingDouble(b -> -(double) b.valor / b.energia));
        List<Lance> lancesSelecionados = new ArrayList<>();
        int totalValor = 0;
        for (Lance lance : lances) {
            //Se consegue colocar o lance com a enegia disponivel coloca
            if (energia >= lance.energia) {
                energia -= lance.energia;
                totalValor += lance.valor;
                lancesSelecionados.add(lance);
            }
            //Para quando tiver 10 ou menos de energia restante
            if (energia <= 10)
                break;
        }
        return lancesSelecionados;
    }

    public static List<Lance> resolverPorMaiorValor(List<Lance> lances, int energia) {
        //Organiza os lances em ordem decrecente em relação valor
        Collections.sort(lances, Comparator.comparingInt(b -> -b.valor));
        List<Lance> lancesSelecionados = new ArrayList<>();
        int totalValor = 0;
        for (Lance lance : lances) {
            if (energia >= lance.energia) {
                energia -= lance.energia;
                totalValor += lance.valor;
                lancesSelecionados.add(lance);
            }
            if (energia <= 10)
                break;
        }
        return lancesSelecionados;
    }
    
}
