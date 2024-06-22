package utils;

import entities.Compradora;
import entities.Lance;
import lombok.experimental.UtilityClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static utils.Constantes.QUANTIDADE_OFERTA_MEGAWATTS;

@UtilityClass
public class GenerateObject {

    private static final String[] NOMES_COMPRADORAS = {
            "Empresa A", "Empresa B", "Empresa C", "Empresa D", "Empresa E",
            "Empresa F", "Empresa G", "Empresa H", "Empresa I", "Empresa J",
            "Empresa K", "Empresa L", "Empresa M", "Empresa N", "Empresa O"
    };

    private static final int MIN_QUANTIDADE_MW = 100;
    private static final int MAX_QUANTIDADE_MW = 1000;

    private static final Random random = new Random();

    private static final int MIN_VALOR_LANCE = 1000;
    private static final int MAX_VALOR_LANCE = 5000;

    public static List<Compradora> gerarCompradoras(int quantidadeCompradoras) {
        List<Compradora> compradoras = new ArrayList<>();
        for (int i = 0; i < quantidadeCompradoras; i++) {
            String nome = NOMES_COMPRADORAS[random.nextInt(NOMES_COMPRADORAS.length)];
            int quantidade = random.nextInt(MAX_QUANTIDADE_MW - MIN_QUANTIDADE_MW + 1) + MIN_QUANTIDADE_MW;
            Compradora compradora = new Compradora(nome, quantidade);
            compradoras.add(compradora);
        }
        return compradoras;
    }

    public static List<Lance> gerarLances(List<Compradora> compradoras, int quantidadeMaximaLance) {
        List<Lance> lances = new ArrayList<>();
        for (Compradora compradora : compradoras) {
            int quantidadeDisponivel = compradora.getQuantidade();
            while (quantidadeDisponivel > 0) {
                int quantidadeLance = random.nextInt(Math.min(quantidadeMaximaLance, quantidadeDisponivel)) + 1;
                int valor = random.nextInt(MAX_VALOR_LANCE - MIN_VALOR_LANCE + 1) + MIN_VALOR_LANCE;
                Lance lance = new Lance(valor, quantidadeLance, compradora.getId());
                lances.add(lance);
                quantidadeDisponivel -= quantidadeLance;
            }
        }
        return lances;
    }

}

