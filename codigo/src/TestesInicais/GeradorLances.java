package TestesInicais;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Lance;

public class GeradorLances {
    
    public static List<Lance> gerarLancesAleatorios(int quantidade, int energiaMax, long seed) {
        Random random = new Random(seed);
        List<Lance> lances = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            int energy = random.nextInt(energiaMax) + 1;
            int value = random.nextInt(1000) + 1;
            lances.add(new Lance(energy, value));
        }
        return lances;
    }
}