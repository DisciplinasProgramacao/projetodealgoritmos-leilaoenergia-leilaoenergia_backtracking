package utils;

import entities.Compradora;
import entities.CountTime;
import entities.Lance;
import entities.MaxResultado;
import lombok.experimental.UtilityClass;
import services.Backtracking;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static utils.Constantes.QUANTIDADE_MAXIMA_LANCE;
import static utils.GenerateObject.gerarLances;

@UtilityClass
public class TestGenerator {

    public static void testBacktracking() {
        int tamanhoMinimo = 10;
        int incremento = 1;
        int tempoLimiteMillis = 30000;

        int tamanhoAtual = tamanhoMinimo;
        boolean continuar = true;

        while (continuar) {
            List<Double> tempos = new ArrayList<>();

            for (int i = 0; i < 10; i++) { // 10 execuções para cada tamanho
                List<Compradora> compradoras = GenerateObject.gerarCompradoras(tamanhoAtual);
                List<Lance> lances = GenerateObject.gerarLances(compradoras, Constantes.QUANTIDADE_MAXIMA_LANCE);

                MaxResultado maxResultado = new MaxResultado();
                Backtracking backtracking = new Backtracking();

                CountTime countTime = new CountTime();
                countTime.start();
                backtracking.executar(0, maxResultado, 0, lances, new ArrayList<>());
                countTime.stop();

                tempos.add((double) countTime.getTempoExecucao());
            }

            double tempoMedio = tempos.stream().mapToDouble(Double::doubleValue).average().orElse(0);

            System.out.printf("Tamanho do conjunto: %d, Tempo médio: %.2f segundos%n", tamanhoAtual, tempoMedio / 1000);

            if (tempoMedio > tempoLimiteMillis) {
                continuar = false;
            }

            tamanhoAtual += incremento;
        }
    }


}

