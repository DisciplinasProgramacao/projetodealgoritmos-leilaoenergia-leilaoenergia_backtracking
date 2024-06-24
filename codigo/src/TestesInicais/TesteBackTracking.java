package TestesInicais;

import algoritmos.BackTracking;
import entity.Lance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static TestesInicais.GeradorLances.gerarLancesAleatorios;

public class TesteBackTracking {
    public static void main(String[] args) {
        int tamanhoInicial = 10;
        int incremento = 1;
        int numTestes = 10;
        int energia = 1000;
        long limiteTempo = 30000; // 30 segundos em milissegundos
        int T = 130;

        try (FileWriter writer = new FileWriter("resultadosBackTracking.csv")) {

            writer.append("Tamanho DuraçãoMédia(ms) MédiaValorTotalSoluções MédiaEnergiaTotalSoluções\n");

            for (int tamanho = tamanhoInicial; tamanho <= 130 ; tamanho += incremento) {
                List<Long> duracoes = new ArrayList<>();
                List<Lance> melhorSolucaoGeral = new ArrayList<>();
                int maxLucroGeral = 0;
                List<List<Lance>> todasSolucoes = new ArrayList<>();

                for (int teste = 0; teste < numTestes; teste++) {
                    List<Lance> lances = gerarLancesAleatorios(tamanho, energia, teste);
                    BackTracking solucao = new BackTracking();

                    long inicioTempo = System.currentTimeMillis();
                    solucao.resolver(lances, energia);
                    long fimTempo = System.currentTimeMillis();

                    long duracao = fimTempo - inicioTempo;
                    duracoes.add(duracao);

                    List<Lance> solucaoAtual = solucao.getSolucao();
                    todasSolucoes.add(solucaoAtual);

                    if (solucao.getMaiorLucro() > maxLucroGeral) {
                        maxLucroGeral = solucao.getMaiorLucro();
                        melhorSolucaoGeral = solucaoAtual;
                    }
                }

                // Calcular a duração média para cada 10 execuções
                long duracaoTotal = 0;
                for (long duracao : duracoes) {
                    duracaoTotal += duracao;
                }
                double duracaoMedia = duracaoTotal / (double) numTestes;

                // Calcular a energia total e o valor da melhor solução geral
                int energiaTotalMelhor = 0;
                int valorTotalMelhor = 0;
                for (Lance lance : melhorSolucaoGeral) {
                    energiaTotalMelhor += lance.energia;
                    valorTotalMelhor += lance.valor;
                }

                // Calcular a média dos valores e energias entre as 10 soluções
                double[] medias = calcularMedias(todasSolucoes);
                double mediaValorTotal = medias[0];
                double mediaEnergiaTotal = medias[1];

                System.out.println("Tamanho: " + tamanho);
                System.out.println("Duração média: " + duracaoMedia + " ms");
                System.out.println("Energia total da melhor solução: " + energiaTotalMelhor);
                System.out.println("Valor total da melhor solução: " + valorTotalMelhor);
                System.out.println("Média do valor total das soluções: " + mediaValorTotal);
                System.out.println("Média da energia total das soluções: " + mediaEnergiaTotal);
                System.out.println();

                // Formatar a saída em CSV e escrever no arquivo
                writer.append(String.format("%d %.2f %.2f %.2f\n",
                        tamanho, duracaoMedia, mediaValorTotal,
                        mediaEnergiaTotal));

                // Verificar se a duração média excede o limite de tempo
                if (duracaoMedia > limiteTempo) {
                    writer.append("Tamanho " + tamanho + " excedeu o limite de 30 segundos\n");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[] calcularMedias(List<List<Lance>> todasSolucoes) {
        double somaValorTotal = 0;
        double somaEnergiaTotal = 0;
        int numSolucoes = todasSolucoes.size();

        for (List<Lance> solucao : todasSolucoes) {
            int valorTotal = 0;
            int energiaTotal = 0;

            for (Lance lance : solucao) {
                valorTotal += lance.valor;
                energiaTotal += lance.energia;
            }

            somaValorTotal += valorTotal;
            somaEnergiaTotal += energiaTotal;
        }

        double mediaValorTotal = somaValorTotal / numSolucoes;
        double mediaEnergiaTotal = somaEnergiaTotal / numSolucoes;

        return new double[]{mediaValorTotal, mediaEnergiaTotal};
    }
}