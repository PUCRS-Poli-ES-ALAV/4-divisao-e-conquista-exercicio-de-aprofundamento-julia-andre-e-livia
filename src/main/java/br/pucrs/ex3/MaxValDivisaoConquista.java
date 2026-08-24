package br.pucrs.ex3;

/**
 * Exercício 3 — Máximo valor de um vetor usando Divisão e Conquista.
 *
 * Algoritmo (como enunciado):
 *
 * long maxVal2(long A[], int init, int end) {
 *     if (end - init <= 1)
 *         return max(A[init], A[end]);
 *     else {
 *         int m = (init + end)/2;
 *         long v1 = maxVal2(A,init,m);
 *         long v2 = maxVal2(A,m+1,end);
 *         return max(v1,v2);
 *     }
 * }
 *
 * A implementação abaixo é exatamente esse algoritmo, apenas com um contador
 * de chamadas recursivas (usado como "número de iterações" nos testes).
 */
public class MaxValDivisaoConquista {

    private long chamadas;

    /**
     * Encontra o maior valor em A[init..end] (inclusive) por divisão e conquista.
     */
    public long maxVal2(long[] a, int init, int end) {
        chamadas++;
        if (end - init <= 1) {
            return Math.max(a[init], a[end]);
        } else {
            int m = (init + end) / 2;
            long v1 = maxVal2(a, init, m);
            long v2 = maxVal2(a, m + 1, end);
            return Math.max(v1, v2);
        }
    }

    /** Número de chamadas recursivas realizadas na última execução. */
    public long getChamadas() {
        return chamadas;
    }

    /** Zera o contador de chamadas, para poder medir uma nova execução. */
    public void resetContador() {
        chamadas = 0;
    }
}
