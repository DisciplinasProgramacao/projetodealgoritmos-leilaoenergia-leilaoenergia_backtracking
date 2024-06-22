package services;

import entities.Lance;
import entities.MaxResultado;
import services.interfaces.OperacoesAlgoritmo;

import java.util.*;

public class GulosoMaxValueMw implements OperacoesAlgoritmo {


    @Override
    public void executar (int lucro, MaxResultado maxResultado, int indice, List<Lance> lancesTotais, List<Lance> lancesSelecionados) {

        List<Lance> lancesMutaveis = new ArrayList<>(lancesTotais);

        // Ordenar os lances em ordem decrescente de valor por megawatt
        Collections.sort(lancesMutaveis, Comparator.comparingDouble(Lance::valorPorMw).reversed());

        int energiaRestante = 1000;

        // Remover lances já selecionados da lista de lances mutáveis
        lancesMutaveis.removeIf(lance -> lancesSelecionados.stream().anyMatch(sel -> sel.getId().equals(lance.getId())));

        // Selecionar lances enquanto houver energia restante
        for (Lance lance : lancesMutaveis) {
            if (lance.getQuantidade() <= energiaRestante) {
                lancesSelecionados.add(lance);
                energiaRestante -= lance.getQuantidade();
            }
        }

        // Calcular o valor total dos lances selecionados
        int valorTotal = lancesSelecionados.stream().mapToInt(Lance::getValor).sum();

        // Exibir informações de diagnóstico
        System.out.println("Lances selecionados:");
        for (Lance lance : lancesSelecionados) {
            System.out.println("Interessada: " + lance.getIdCompradora() + ", Megawatts: " + lance.getQuantidade() + ", Valor: " + lance.getValor());
        }
        System.out.println("Valor total obtido: " + valorTotal + " Energia restante: " + energiaRestante + " MW");

    }
}
