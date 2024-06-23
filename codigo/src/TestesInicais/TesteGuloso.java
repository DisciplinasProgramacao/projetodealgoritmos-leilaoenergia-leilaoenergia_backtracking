package TestesInicais;

import algoritmos.Guloso;
import entity.Lance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TesteGuloso {
    public static void main(String[] args) {
        int qtdLancesInciais = 10;
        int tamanhoPasso = 1;
        int numTestes = 10;
        int energiaDisp = 1000;
        long tempoLimit = 30000; // 30 seconds in milliseconds
        int T = 50;


        try (FileWriter writer = new FileWriter("resultadosGuloso.csv")) {
            // Cabeçalho para os dados de saída
            writer.append(
                    "Tamanho DuraçãoMédia(ms)  MédiadoValorTotaldasSoluções MédiadaEnergiaTotaldasSoluções TipoGuloso\n");
            for (int size = qtdLancesInciais; size < T; size += tamanhoPasso) {
                List<Long> duracaoValorPorEnergia = new ArrayList<>();
                List<Long> duracaoMaiorValor = new ArrayList<>();
                int valorTotalValorPorEnergia = 0;
                int valorTotalMaiorValor = 0;
                int energiaTotalValorPorEnergia = 0;
                int energiaTotalMaiorValor = 0;

                for (int test = 0; test < numTestes; test++) {
                    List<Lance> lances = GeradorLances.gerarLancesAleatorios(size, energiaDisp, test);

                    // Teste resolverPorEnergiaProValor
                    long tempoInicial = System.currentTimeMillis();
                    List<Lance> solucaoValorPorEnergia = Guloso.resolverPorEnergiaProValor(lances, energiaDisp);
                    long tempoFinal = System.currentTimeMillis();
                    long duracao = tempoFinal - tempoInicial;
                    duracaoValorPorEnergia.add(duracao);

                    int lucroTotalValorPorEnergia = solucaoValorPorEnergia.stream().mapToInt(lance -> lance.valor).sum();
                    valorTotalValorPorEnergia += lucroTotalValorPorEnergia;
                    int energiaConsumidaValorPorEnergia = solucaoValorPorEnergia.stream().mapToInt(lance -> lance.energia).sum();
                    energiaTotalValorPorEnergia += energiaConsumidaValorPorEnergia;

                    // Teste resolverPorMaiorValor
                    tempoInicial = System.currentTimeMillis();
                    List<Lance> solucaoMaiorValor = Guloso.resolverPorMaiorValor(lances, energiaDisp);
                    tempoFinal = System.currentTimeMillis();
                    duracao = tempoFinal - tempoInicial;
                    duracaoMaiorValor.add(duracao);

                    int lucroTotalMaiorValor = solucaoMaiorValor.stream().mapToInt(lance -> lance.valor).sum();
                    valorTotalMaiorValor += lucroTotalMaiorValor;
                    int energiaConsumidaMaiorValor = solucaoMaiorValor.stream().mapToInt(lance -> lance.energia).sum();
                    energiaTotalMaiorValor += energiaConsumidaMaiorValor;
                }

                // Calcula duracao media de resolverPorEnergiaProValor
                long duracaoTotalValorPorEnergia = duracaoValorPorEnergia.stream().mapToLong(Long::longValue).sum();
                double duracaoMediaValorPorEnergia = duracaoTotalValorPorEnergia / (double) numTestes;

                // Calcula duracao media de resolverPorMaiorValor
                long duracaoTotalMaiorValor = duracaoMaiorValor.stream().mapToLong(Long::longValue).sum();
                double duracaoMediaMaiorValor = duracaoTotalMaiorValor / (double) numTestes;

                double mediaValorValorPorEnergia = (double) valorTotalValorPorEnergia / (double) numTestes;
                double mediaValorTotalMaiorValor = (double) valorTotalMaiorValor / (double) numTestes;

                // Calcula a média da energia total consumida
                double mediaEnergiaValorPorEnergia = (double) energiaTotalValorPorEnergia / (double) numTestes;
                double mediaEnergiaTotalMaiorValor = (double) energiaTotalMaiorValor / (double) numTestes;

                writer.append(String.format("%d %.2f %.2f %.2f %d\n",
                        size, duracaoMediaValorPorEnergia, mediaValorValorPorEnergia, mediaEnergiaValorPorEnergia, 1));
                writer.append(String.format("%d %.2f %.2f %.2f %d\n",
                        size, duracaoMediaMaiorValor, mediaValorTotalMaiorValor, mediaEnergiaTotalMaiorValor, 2));
            }

            tamanhoPasso = T;

            for (int size = T; size <= (10*T); size += tamanhoPasso) {
                List<Long> duracaoValorPorEnergia = new ArrayList<>();
                List<Long> duracaoMaiorValor = new ArrayList<>();
                int valorTotalValorPorEnergia = 0;
                int valorTotalMaiorValor = 0;
                int energiaTotalValorPorEnergia = 0;
                int energiaTotalMaiorValor = 0;

                for (int test = 0; test < numTestes; test++) {
                    List<Lance> lances = GeradorLances.gerarLancesAleatorios(size, energiaDisp, test);

                    // Teste resolverPorEnergiaProValor
                    long tempoInicial = System.currentTimeMillis();
                    List<Lance> solucaoValorPorEnergia = Guloso.resolverPorEnergiaProValor(lances, energiaDisp);
                    long tempoFinal = System.currentTimeMillis();
                    long duracao = tempoFinal - tempoInicial;
                    duracaoValorPorEnergia.add(duracao);

                    int lucroTotalValorPorEnergia = solucaoValorPorEnergia.stream().mapToInt(lance -> lance.valor).sum();
                    valorTotalValorPorEnergia += lucroTotalValorPorEnergia;
                    int energiaConsumidaValorPorEnergia = solucaoValorPorEnergia.stream().mapToInt(lance -> lance.energia).sum();
                    energiaTotalValorPorEnergia += energiaConsumidaValorPorEnergia;

                    // Teste resolverPorMaiorValor
                    tempoInicial = System.currentTimeMillis();
                    List<Lance> solucaoMaiorValor = Guloso.resolverPorMaiorValor(lances, energiaDisp);
                    tempoFinal = System.currentTimeMillis();
                    duracao = tempoFinal - tempoInicial;
                    duracaoMaiorValor.add(duracao);

                    int lucroTotalMaiorValor = solucaoMaiorValor.stream().mapToInt(lance -> lance.valor).sum();
                    valorTotalMaiorValor += lucroTotalMaiorValor;
                    int energiaConsumidaMaiorValor = solucaoMaiorValor.stream().mapToInt(lance -> lance.energia).sum();
                    energiaTotalMaiorValor += energiaConsumidaMaiorValor;
                }

                // Calcula duracao media de resolverPorEnergiaProValor
                long duracaoTotalValorPorEnergia = duracaoValorPorEnergia.stream().mapToLong(Long::longValue).sum();
                double duracaoMediaValorPorEnergia = duracaoTotalValorPorEnergia / (double) numTestes;

                // Calcula duracao media de resolverPorMaiorValor
                long duracaoTotalMaiorValor = duracaoMaiorValor.stream().mapToLong(Long::longValue).sum();
                double duracaoMediaMaiorValor = duracaoTotalMaiorValor / (double) numTestes;

                double mediaValorValorPorEnergia = (double) valorTotalValorPorEnergia / (double) numTestes;
                double mediaValorTotalMaiorValor = (double) valorTotalMaiorValor / (double) numTestes;

                // Calcula a média da energia total consumida
                double mediaEnergiaValorPorEnergia = (double) energiaTotalValorPorEnergia / (double) numTestes;
                double mediaEnergiaTotalMaiorValor = (double) energiaTotalMaiorValor / (double) numTestes;

                writer.append(String.format("%d %.2f %.2f %.2f %d\n",
                        size, duracaoMediaValorPorEnergia, mediaValorValorPorEnergia, mediaEnergiaValorPorEnergia, 1));
                writer.append(String.format("%d %.2f %.2f %.2f %d\n",
                        size, duracaoMediaMaiorValor, mediaValorTotalMaiorValor, mediaEnergiaTotalMaiorValor, 2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
