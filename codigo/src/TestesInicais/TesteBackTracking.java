package TestesInicais;

import algoritmos.BackTracking;
import algoritmos.Lance;

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
        Random random = new Random();
        long limiteTempo = 30000; // 30 segundos em milissegundos

        for (int tamanho = tamanhoInicial; ; tamanho += incremento) {
            List<Long> duracoes = new ArrayList<>();
            List<Lance> melhorSolucaoGeral = new ArrayList<>();
            int maxLucroGeral = 0;

            for (int teste = 0; teste < numTestes; teste++) {
                List<Lance> lances = gerarLancesAleatorios(tamanho, energia, teste);
                BackTracking solucao = new BackTracking();

                long inicioTempo = System.currentTimeMillis();
                solucao.resolver(lances, energia);
                long fimTempo = System.currentTimeMillis();

                long duracao = fimTempo - inicioTempo;
                duracoes.add(duracao);

                if (solucao.getMaiorLucro() > maxLucroGeral) {
                    maxLucroGeral = solucao.getMaiorLucro();
                    melhorSolucaoGeral = solucao.getSolucao();
                }
            }

            // Calcular a duração média
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

            // Registrar a duração média para o tamanho atual
            System.out.println("Duração média para tamanho " + tamanho + ": " + duracaoMedia + " ms");

            // Registrar a melhor solução geral encontrada
            // System.out.println("Melhor solução geral para tamanho " + tamanho + ": " + melhorSolucaoGeral);
            System.out.println("Valor total: " + valorTotalMelhor + ", Energia total: " + energiaTotalMelhor);

            // Verificar se a duração média excede o limite de tempo
            if (duracaoMedia > limiteTempo) {
                System.out.println("Tamanho " + tamanho + " excedeu o limite de 30 segundos");
                return;
            }
        }
    }
}
