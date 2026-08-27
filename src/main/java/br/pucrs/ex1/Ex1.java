package br.pucrs.ex1;

import java.util.Random;

/**
 * Executa o mergeSort (divisão e conquista) para vetores randômicos de
 * tamanho 32, 2048 e 1.048.576, contabilizando o número de chamadas
 * recursivas (iterações) e o tempo gasto em cada execução.
 *
 * Rode com: mvn -q exec:java -Dexec.mainClass=br.pucrs.ex1.Ex1
 * ou compile e rode manualmente com java/javac.
 */
public class Ex1 {

    private static final int[] TAMANHOS = {32, 2048, 1_048_576};

    public static void main(String[] args) {
        Random random = new Random(42); // seed fixa -> resultados reprodutíveis

        System.out.println("=== Exercício 1 — Merge Sort (Divisão e Conquista) ===");
        System.out.printf("%-12s %-15s %-20s%n", "Tamanho", "Chamadas", "Tempo (ms)");

        for (int n : TAMANHOS) {
            int[] vetor = gerarVetorAleatorio(n, random);

            MergeSort algoritmo = new MergeSort();

            long inicio = System.nanoTime();
            algoritmo.mergeSort(vetor);
            long fim = System.nanoTime();

            double tempoMs = (fim - inicio) / 1_000_000.0;

            System.out.printf("%-12d %-15d %-20.4f%n", n, algoritmo.getChamadas(), tempoMs);
        }
    }

    private static int[] gerarVetorAleatorio(int n, Random random) {
        int[] vetor = new int[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = random.nextInt();
        }
        return vetor;
    }
}
