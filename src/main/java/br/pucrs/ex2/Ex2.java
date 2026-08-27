package br.pucrs.ex2;

import java.util.Random;

/**
 * Executa o maxVal1 (sem divisão e conquista) para vetores randômicos de
 * tamanho 32, 2048 e 1.048.576, contabilizando o número de iterações do
 * laço e o tempo gasto em cada execução.
 *
 * Rode com: mvn -q exec:java -Dexec.mainClass=br.pucrs.ex2.Ex2
 * ou compile e rode manualmente com java/javac.
 */
public class Ex2 {

    private static final int[] TAMANHOS = {32, 2048, 1_048_576};

    public static void main(String[] args) {
        Random random = new Random(42); // seed fixa -> resultados reprodutíveis

        System.out.println("=== Exercício 2 — maxVal1 (sem Divisão e Conquista) ===");
        System.out.printf("%-12s %-15s %-20s %-15s%n", "Tamanho", "Iterações", "Tempo (ms)", "Máximo encontrado");

        for (int n : TAMANHOS) {
            long[] vetor = gerarVetorAleatorio(n, random);

            MaxVal1 algoritmo = new MaxVal1();

            long inicio = System.nanoTime();
            long max = algoritmo.maxVal1(vetor, n);
            long fim = System.nanoTime();

            double tempoMs = (fim - inicio) / 1_000_000.0;

            System.out.printf("%-12d %-15d %-20.4f %-15d%n",
                    n, algoritmo.getIteracoes(), tempoMs, max);
        }
    }

    private static long[] gerarVetorAleatorio(int n, Random random) {
        long[] vetor = new long[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = random.nextLong();
        }
        return vetor;
    }
}
