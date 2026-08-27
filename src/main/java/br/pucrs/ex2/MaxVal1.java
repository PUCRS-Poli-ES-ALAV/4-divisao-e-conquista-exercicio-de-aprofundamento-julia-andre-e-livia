package br.pucrs.ex2;

/**
 * Exercício 2 — Máximo valor de um vetor SEM Divisão e Conquista.
 *
 * Algoritmo (como enunciado):
 *
 * long maxVal1(long A[], int n) {
 *     long max = A[0];
 *     for (int i = 1; i < n; i++) {
 *         if( A[i] > max )
 *            max = A[i];
 *     }
 *     return max;
 * }
 *
 * A implementação abaixo é exatamente esse algoritmo, apenas com um contador
 * de iterações do laço (usado como "número de iterações" nos testes).
 */
public class MaxVal1 {

    private long iteracoes;

    public long maxVal1(long[] a, int n) {
        long max = a[0];
        for (int i = 1; i < n; i++) {
            iteracoes++;
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    // Número de iterações do laço realizadas na última execução.
    public long getIteracoes() {
        return iteracoes;
    }

    // Zera o contador de iterações, para poder medir uma nova execução.
    public void resetContador() {
        iteracoes = 0;
    }
}
