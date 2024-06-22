package services;

import entities.Lance;
import entities.MaxResultado;
import services.interfaces.OperacoesAlgoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GulosoMaxQtdMw implements OperacoesAlgoritmo {

    @Override
    public void executar (int lucro, MaxResultado resultado, int indice, List<Lance> todosLances, List<Lance> lancesSelecionados) {
        List<Lance> lancesMutaveis = new ArrayList<> (todosLances);

        // Ordenar os lances em ordem decrescente de quantidade de megawatt
        Collections.sort(lancesMutaveis, Comparator.comparingInt(Lance::getQuantidade).reversed());

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
        System.out.println("Lances selecionados (Estratégia Gulosa por Quantidade):");
        for (Lance lance : lancesSelecionados) {
            System.out.println("Interessada: " + lance.getIdCompradora() + ", Megawatts: " + lance.getQuantidade() + ", Valor: " + lance.getValor());
        }
        System.out.println("Valor total obtido: " + valorTotal + " Energia restante: " + energiaRestante + " MW");

        // Atualizar o resultado máximo, se o lucro atual for maior que o registrado
        if (lucro > resultado.getLucroMax ()) {
            resultado.setLucroMax (lucro);
            resultado.setLancesEleitos (new ArrayList<>(lancesSelecionados));
        }
    }
}
