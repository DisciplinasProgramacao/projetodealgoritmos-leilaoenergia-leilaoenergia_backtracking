package TestesSala;

import algoritmos.BackTracking;
import algoritmos.DivisaoConquista;
import entity.Lance;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Testes {
    public static void main(String[] args) {

        int energiaInicial = 8000;
        long tempoIncio;
        long tempoFinal;
        long duracao;

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Conjunto 1");
        System.out.println("2. Conjunto 2");
        List<Lance> lances = new ArrayList<>();

        int escolha = scanner.nextInt();
        if (escolha <= 1) {
            lances.add(new Lance(430, 1043));
            lances.add(new Lance(428, 1188));
            lances.add(new Lance(410, 1565));
            lances.add(new Lance(385, 1333));
            lances.add(new Lance(399, 1214));
            lances.add(new Lance(382, 1498));
            lances.add(new Lance(416, 1540));
            lances.add(new Lance(436, 1172));
            lances.add(new Lance(416, 1386));
            lances.add(new Lance(423, 1097));
            lances.add(new Lance(400, 1463));
            lances.add(new Lance(406, 1353));
            lances.add(new Lance(403, 1568));
            lances.add(new Lance(390, 1228));
            lances.add(new Lance(387, 1542));
            lances.add(new Lance(390, 1206));
            lances.add(new Lance(430, 1175));
            lances.add(new Lance(397, 1492));
            lances.add(new Lance(392, 1293));
            lances.add(new Lance(393, 1533));
            lances.add(new Lance(439, 1149));
            lances.add(new Lance(403, 1277));
            lances.add(new Lance(415, 1624));
            lances.add(new Lance(387, 1280));
            lances.add(new Lance(417, 1330));
        } else {
            lances.add(new Lance(313, 1496));
            lances.add(new Lance(398, 1768));
            lances.add(new Lance(240, 1210));
            lances.add(new Lance(433, 2327));
            lances.add(new Lance(301, 1263));
            lances.add(new Lance(297, 1499));
            lances.add(new Lance(232, 1209));
            lances.add(new Lance(614, 2342));
            lances.add(new Lance(558, 2983));
            lances.add(new Lance(495, 2259));
            lances.add(new Lance(310, 1381));
            lances.add(new Lance(213, 961));
            lances.add(new Lance(213, 1115));
            lances.add(new Lance(346, 1552));
            lances.add(new Lance(385, 2023));
            lances.add(new Lance(240, 1234));
            lances.add(new Lance(483, 2828));
            lances.add(new Lance(487, 2617));
            lances.add(new Lance(709, 2328));
            lances.add(new Lance(358, 1847));
            lances.add(new Lance(467, 2038));
            lances.add(new Lance(363, 2007));
            lances.add(new Lance(279, 1311));
            lances.add(new Lance(589, 3164));
            lances.add(new Lance(476, 2480));

        }

        System.out.println("Escolha o Algoritmo: ");
        System.out.println("1. Backtracking");
        System.out.println("2. Divisão e conquista");
        System.out.println("3. Algoritmo guloso");
        System.out.println("4. Programação dinâmica");

        escolha = scanner.nextInt();


        switch (escolha) {
            case 1:
                BackTracking solucaoBack = new BackTracking();

                tempoIncio = System.currentTimeMillis();
                solucaoBack.resolver(lances, energiaInicial);
                tempoFinal = System.currentTimeMillis();
                duracao = tempoFinal - tempoIncio;

                System.out.println("Tempo de execução: " + duracao);
                System.out.println("Resposta Encontrada: " + solucaoBack.getMaiorLucro());
                System.out.println(solucaoBack.getSolucao());
                break;
            case 2:
                DivisaoConquista divisaoConquista = new DivisaoConquista ();

                tempoIncio = System.currentTimeMillis();
                List<Lance> melhorConjunto = divisaoConquista.resolver (lances, energiaInicial);
                tempoFinal = System.currentTimeMillis();
                duracao = tempoFinal - tempoIncio;

                System.out.println("Tempo de execução: " + duracao);

                System.out.println("Melhor conjunto de lances para o maior lucro possível:");
                for (Lance lance : melhorConjunto) {
                    System.out.println("Id: " + lance.id + ", Energia: " + lance.energia + ", Valor: " + lance.valor);
                }

                System.out.println("Lucro total: " + divisaoConquista.calcularLucro (melhorConjunto));
                break;

        }
    }
}
