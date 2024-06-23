import algoritmos.Lance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorLances {
    
    private static List<Lance> gerarLancesAleatorios(int quantidade, int energiaMax, Random random) {
        List<Lance> lances = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            int energy = random.nextInt(energiaMax) + 1;
            int value = random.nextInt(1000) + 1;
            lances.add(new Lance(energy, value));
        }
        return lances;
    }
}