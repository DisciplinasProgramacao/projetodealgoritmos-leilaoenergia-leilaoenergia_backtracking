package services;

import entities.Lance;
import entities.MaxResultado;
import services.interfaces.OperacoesAlgoritmo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramacaoDinamica implements OperacoesAlgoritmo {

    @Override
    public void executar (int lucro, MaxResultado maxResultado, int indice, List<Lance> lancesTotais, List<Lance> lancesSelecionados) {
        // Mapa para armazenar resultados intermediários da programação dinâmica
        Map<String, Integer> memo = new HashMap<> ();

        // Chama a função de programação dinâmica para calcular o lucro máximo
        int lucroMaximizado = dp(maxResultado, lancesTotais, indice, 0, memo, lancesSelecionados);

        // Atualiza o resultado máximo com o lucro obtido e os lances selecionados
        maxResultado.setLucroMax (lucroMaximizado);
        maxResultado.setLancesEleitos (new ArrayList<> (lancesSelecionados));
    }


    /**
     * Função recursiva para calcular o lucro máximo utilizando programação dinâmica.
     *
     * @param melhorResultado O objeto que mantém o resultado máximo
     * @param todosLances Lista de todos os lances disponíveis
     * @param indice Índice atual na lista de lances
     * @param qtdeSelecionada Quantidade total selecionada até o momento
     * @param memo Mapa para memoização dos resultados intermediários
     * @param resultado Lista dos lances selecionados até o momento
     * @return O lucro máximo possível
     */
    private int dp(
            MaxResultado maxResultado, List<Lance> todosLances, int indice, int qtdeSelecionada,
            Map<String, Integer> memo, List<Lance> resultado) {

        // Se a quantidade selecionada ultrapassar a quantidade disponível, retorna um valor mínimo
        if (qtdeSelecionada > maxResultado.getEmpresa ().getQuantidade ()) {
            return Integer.MIN_VALUE;
        }

        // Se chegou ao final da lista de lances, retorna lucro zero
        if (indice == todosLances.size()) {
            return 0;
        }

        String key = indice + "-" + qtdeSelecionada;

        // Verifica se o resultado já foi calculado e memoizado
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        Lance lanceAnalisado = todosLances.get(indice);

        // Escolhe o lance atual
        resultado.add(lanceAnalisado);
        int incluir = lanceAnalisado.getValor() + dp(maxResultado, todosLances, indice + 1,
                qtdeSelecionada + lanceAnalisado.getQuantidade(), memo, resultado);
        resultado.remove(resultado.size() - 1);

        // Não escolhe o lance atual
        int excluir = dp(maxResultado, todosLances, indice + 1, qtdeSelecionada, memo, resultado);

        // Calcula o lucro máximo entre incluir ou excluir o lance atual
        int maxLucro = Math.max(incluir, excluir);

        // Memoiza o resultado para reutilização
        memo.put(key, maxLucro);

        return maxLucro;
    }
}
