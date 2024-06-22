package services;

import entities.Lance;
import entities.MaxResultado;
import services.interfaces.OperacoesAlgoritmo;
import java.util.ArrayList;
import java.util.List;

public class DivisaoConquista implements OperacoesAlgoritmo {

    @Override
    public void executar(int lucro, MaxResultado maxResultado, int indice, List<Lance> lancesTotais, List<Lance> lancesSelecionados) {

        // Limite de energia disponível
        int energiaDisponivel = 1000;

        // Chamada inicial para o algoritmo de divisão e conquista
        List<Lance> resultado = maxProfit(lancesTotais, energiaDisponivel);

        // Calcular o lucro total dos lances selecionados
        int lucroMaximizado = resultado.stream().mapToInt(Lance::getValor).sum();

        // Atualizar o resultado máximo se o lucro atual for maior
        if (lucroMaximizado > maxResultado.getLucroMax()) {
            maxResultado.setLucroMax(lucroMaximizado);
            maxResultado.setLancesEleitos(new ArrayList<>(resultado));
        }

        // Exibir informações de diagnóstico
        System.out.println("Lances selecionados (Divisão e Conquista):");
        for (Lance lance : resultado) {
            System.out.println("Interessada: " + lance.getIdCompradora() + ", Megawatts: " + lance.getQuantidade() + ", Valor: " + lance.getValor());
        }
        System.out.println("Valor total obtido: " + lucroMaximizado + " Energia restante: " + (energiaDisponivel - resultado.stream().mapToInt(Lance::getQuantidade).sum()) + " MW");
    }

    /**
     * Função de divisão e conquista para resolver o problema da mochila (knapsack problem).
     *
     * @param lances Lista de lances disponíveis
     * @param energiaDisponivel Quantidade máxima de energia que pode ser selecionada
     * @return Lista de lances selecionados para maximizar o lucro
     */
    private List<Lance> maxProfit(List<Lance> lances, int energiaDisponivel) {
        // Caso base: se não há lances ou energia disponível é zero, retorna lista vazia
        if (lances.isEmpty() || energiaDisponivel <= 0) {
            return new ArrayList<>();
        }

        // Divide o problema em duas partes: incluir o lance atual ou não incluir
        Lance lanceAtual = lances.get(0);

        // Caso 1: incluir o lance atual
        List<Lance> incluirLance = new ArrayList<>();
        if (lanceAtual.getQuantidade() <= energiaDisponivel) {
            incluirLance.add(lanceAtual);
            incluirLance.addAll(maxProfit(lances.subList(1, lances.size()), energiaDisponivel - lanceAtual.getQuantidade()));
        }

        // Caso 2: não incluir o lance atual
        List<Lance> naoIncluirLance = maxProfit(lances.subList(1, lances.size()), energiaDisponivel);

        // Escolhe a melhor solução entre os dois casos
        int lucroIncluir = incluirLance.stream().mapToInt(Lance::getValor).sum();
        int lucroNaoIncluir = naoIncluirLance.stream().mapToInt(Lance::getValor).sum();

        if (lucroIncluir > lucroNaoIncluir) {
            return incluirLance;
        } else {
            return naoIncluirLance;
        }
    }
}

