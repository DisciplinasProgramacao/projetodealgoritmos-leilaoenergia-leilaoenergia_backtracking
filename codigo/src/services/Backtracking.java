package services;

import entities.Lance;
import entities.MaxResultado;
import lombok.AllArgsConstructor;
import services.interfaces.OperacoesAlgoritmo;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Backtracking implements OperacoesAlgoritmo {

    /**
     * Encontrar a combinação de lances que maximize o lucro dentro das restrições.
     * @param lucro
     * @param maxResultado
     * @param indice
     * @param lancesTotais
     * @param lancesSelecionados
     */
    @Override
    public void executar(
            int lucro, MaxResultado maxResultado, int indice, List<Lance> lancesTotais,
            List<Lance> lancesSelecionados) {
        List<Lance> lancesValidos = new ArrayList<>();

        int qtdeSelecionada = lancesSelecionados.stream()
                .mapToInt(Lance::getQuantidade)
                .sum();

        Integer menorValor = Integer.MAX_VALUE;

        for (int i = indice; i < lancesTotais.size(); i++) {
            if (lancesTotais.get(i).getQuantidade () < menorValor) {
                menorValor = lancesTotais.get(i).getQuantidade ();
            }
            if (qtdeSelecionada + lancesTotais.get(i).getQuantidade () < 8000) {
                lancesValidos.add(lancesTotais.get(i));
            }
        }

        if (indice == lancesTotais.size()
                || qtdeSelecionada > maxResultado.getEmpresa ().getQuantidade ()
                || menorValor > (8000 - qtdeSelecionada)
                || lancesValidos.isEmpty()) {

            if (lucro > maxResultado.getLucroMax ()) {
                maxResultado.setLucroMax (lucro);
                maxResultado.setLancesEleitos (new ArrayList<>(lancesSelecionados));
            }
            return;
        }

        for (Lance lance : lancesValidos) {
            lancesSelecionados.add(lance);
            executar (lucro + lance.getValor (), maxResultado, lancesTotais.indexOf (lance) + 1,lancesTotais, lancesSelecionados);

            lancesSelecionados.remove(lancesSelecionados.size() - 1);
        }
    }


}
