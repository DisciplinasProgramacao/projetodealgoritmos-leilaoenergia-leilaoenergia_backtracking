package TestesInicais;

import algoritmos.Guloso;
import entity.Lance;

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


        for (int size = qtdLancesInciais; size < T; size += tamanhoPasso) {
            List<Long> duracaoValorPorEnergia = new ArrayList<>();
            List<Long> duracaoMaiorValor = new ArrayList<>();
            int valorTotalValorPorEnergia = 0;
            int valorTotalMaiorValor = 0;

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

                // Teste resolverPorMaiorValor
                tempoInicial = System.currentTimeMillis();
                List<Lance> solucaoMaiorValor = Guloso.resolverPorMaiorValor(lances, energiaDisp);
                tempoFinal = System.currentTimeMillis();
                duracao = tempoFinal - tempoInicial;
                duracaoMaiorValor.add(duracao);

                int lucroTotalMaiorValor = solucaoMaiorValor.stream().mapToInt(lance -> lance.valor).sum();
                valorTotalMaiorValor += lucroTotalMaiorValor;
            }

            // Calcula duracao media de resolverPorEnergiaProValor
            long duracaoTotalValorPorEnergia = duracaoValorPorEnergia.stream().mapToLong(Long::longValue).sum();
            double duracaoMediaValorPorEnergia = duracaoTotalValorPorEnergia / (double) numTestes;

            // Calcula duracao media de resolverPorMaiorValor
            long duracaoTotalMaiorValor = duracaoMaiorValor.stream().mapToLong(Long::longValue).sum();
            double duracaoMediaMaiorValor = duracaoTotalMaiorValor / (double) numTestes;

            double mediaValorValorPorEnergia = (double) valorTotalValorPorEnergia / (double) numTestes;
            double mediaValorTotalMaiorValor = (double) valorTotalMaiorValor / (double) numTestes;

            System.out.println("Duracao media (Valor Por Energia) para tamanho " + size + ": " + duracaoMediaValorPorEnergia + " ms");
            System.out.println("Duracao media (Maior Valor) para tamanho " + size + ": " + duracaoMediaMaiorValor + " ms");

            System.out.println("Valor media (Valor Por Energia) para tamanho " + size + ": " + mediaValorValorPorEnergia);
            System.out.println("Valor media (Maior Valor) para tamanho " + size + ": " + mediaValorTotalMaiorValor);
        }

        tamanhoPasso = T;

        for (int size = T; size <= (10*T); size += tamanhoPasso) {
            List<Long> duracaoValorPorEnergia = new ArrayList<>();
            List<Long> duracaoMaiorValor = new ArrayList<>();
            int valorTotalValorPorEnergia = 0;
            int valorTotalMaiorValor = 0;

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

                // Teste resolverPorMaiorValor
                tempoInicial = System.currentTimeMillis();
                List<Lance> solucaoMaiorValor = Guloso.resolverPorMaiorValor(lances, energiaDisp);
                tempoFinal = System.currentTimeMillis();
                duracao = tempoFinal - tempoInicial;
                duracaoMaiorValor.add(duracao);

                int lucroTotalMaiorValor = solucaoMaiorValor.stream().mapToInt(lance -> lance.valor).sum();
                valorTotalMaiorValor += lucroTotalMaiorValor;
            }

            // Calcula duracao media de resolverPorEnergiaProValor
            long duracaoTotalValorPorEnergia = duracaoValorPorEnergia.stream().mapToLong(Long::longValue).sum();
            double duracaoMediaValorPorEnergia = duracaoTotalValorPorEnergia / (double) numTestes;

            // Calcula duracao media de resolverPorMaiorValor
            long duracaoTotalMaiorValor = duracaoMaiorValor.stream().mapToLong(Long::longValue).sum();
            double duracaoMediaMaiorValor = duracaoTotalMaiorValor / (double) numTestes;

            double mediaValorValorPorEnergia = (double) valorTotalValorPorEnergia / (double) numTestes;
            double mediaValorTotalMaiorValor = (double) valorTotalMaiorValor / (double) numTestes;

            System.out.println("Duracao media (Valor Por Energia) para tamanho " + size + ": " + duracaoMediaValorPorEnergia + " ms");
            System.out.println("Duracao media (Maior Valor) para tamanho " + size + ": " + duracaoMediaMaiorValor + " ms");

            System.out.println("Valor media (Valor Por Energia) para tamanho " + size + ": " + mediaValorValorPorEnergia);
            System.out.println("Valor media (Maior Valor) para tamanho " + size + ": " + mediaValorTotalMaiorValor);
        }
    }
}
