package TestesInicais;

import algoritmos.ProgramacaoDinamica;
import algoritmos.ProgramacaoDinamica.Solucao;
import entity.Lance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static TestesInicais.GeradorLances.gerarLancesAleatorios;

public class TesteProgramacaoDinamica {
    public static void main(String[] args) {
        int tamanhoInicial = 10;
        int incremento = 1;
        int numTestes = 10;
        int energia = 1000;
        long limiteTempo = 30000; // 30 segundos em milissegundos
        int T = 130;

        try (FileWriter writer = new FileWriter("resultadosProgramacaoDinamica.csv")) {
            // Cabeçalho para os dados de saída
            writer.append(
                    "Tamanho DuraçãoMédia(ms) MédiadoValorTotaldasSoluções MédiadaEnergiaTotaldasSoluções\n");

            for (int tamanho = tamanhoInicial; tamanho < T; tamanho += incremento) {
                List<Long> duracoes = new ArrayList<>();
                List<Solucao> todasSolucoes = new ArrayList<>();

                for (int teste = 0; teste < numTestes; teste++) {
                    List<Lance> lances = gerarLancesAleatorios(tamanho, energia, teste);

                    long inicioTempo = System.currentTimeMillis();
                    Solucao solucao = ProgramacaoDinamica.getSolucao(lances, energia);
                    long fimTempo = System.currentTimeMillis();

                    long duracao = fimTempo - inicioTempo;
                    duracoes.add(duracao);
                    todasSolucoes.add(solucao);
                }

                // Calcular a duração média para cada 10 execuções
                long duracaoTotal = 0;
                for (long duracao : duracoes) {
                    duracaoTotal += duracao;
                }
                double duracaoMedia = duracaoTotal / (double) numTestes;

                // Calcular a média dos valores e energias entre as 10 soluções
                double[] medias = calcularMedias(todasSolucoes);
                double mediaValorTotal = medias[0];
                double mediaEnergiaTotal = medias[1];

                System.out.println("Tamanho: " + tamanho);
                System.out.println("Duração média: " + duracaoMedia + " ms");
                System.out.println("Média do valor total das soluções: " + mediaValorTotal);
                System.out.println("Média da energia total das soluções: " + mediaEnergiaTotal);
                System.out.println();

                // Formatar a saída em CSV e escrever no arquivo
                writer.append(String.format("%d,%.2f,%.2f,%.2f\n",
                        tamanho, duracaoMedia, mediaValorTotal,
                        mediaEnergiaTotal));

                // Verificar se a duração média excede o limite de tempo
                if (duracaoMedia > limiteTempo) {
                    writer.append("Tamanho " + tamanho + " excedeu o limite de 30 segundos\n");
                    break;
                }
            }

            incremento = T;
            for (int tamanho = T; tamanho <= (T * 10); tamanho += incremento) {
                List<Long> duracoes = new ArrayList<>();
                List<Solucao> todasSolucoes = new ArrayList<>();

                for (int teste = 0; teste < numTestes; teste++) {
                    List<Lance> lances = gerarLancesAleatorios(tamanho, energia, teste);

                    long inicioTempo = System.currentTimeMillis();
                    Solucao solucao = ProgramacaoDinamica.getSolucao(lances, energia);
                    long fimTempo = System.currentTimeMillis();

                    long duracao = fimTempo - inicioTempo;
                    duracoes.add(duracao);
                    todasSolucoes.add(solucao);
                }

                // Calcular a duração média para cada 10 execuções
                long duracaoTotal = 0;
                for (long duracao : duracoes) {
                    duracaoTotal += duracao;
                }
                double duracaoMedia = duracaoTotal / (double) numTestes;

                // Calcular a média dos valores e energias entre as 10 soluções
                double[] medias = calcularMedias(todasSolucoes);
                double mediaValorTotal = medias[0];
                double mediaEnergiaTotal = medias[1];

                System.out.println("Tamanho: " + tamanho);
                System.out.println("Duração média: " + duracaoMedia + " ms");
                System.out.println("Média do valor total das soluções: " + mediaValorTotal);
                System.out.println("Média da energia total das soluções: " + mediaEnergiaTotal);
                System.out.println();

                // Formatar a saída em CSV e escrever no arquivo
                writer.append(String.format("%d,%.2f,%.2f,%.2f\n",
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

    private static double[] calcularMedias(List<ProgramacaoDinamica.Solucao> todasSolucoes) {
        double somaValorTotal = 0;
        double somaEnergiaTotal = 0;
        int numSolucoes = todasSolucoes.size();

        for (ProgramacaoDinamica.Solucao solucao : todasSolucoes) {
            int valorTotal = 0;
            int energiaTotal = 0;

            for (Lance lance : solucao.lancesSelecionados) {
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
