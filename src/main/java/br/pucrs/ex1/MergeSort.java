package br.pucrs.ex1;

import java.util.Arrays;

/**
 * Exercício 1 — Merge Sort (Divisão e Conquista).
 *
 * Algoritmo (como enunciado):
 *
 * MERGE-SORT(L: List with n elements) : Ordered list with n elements
 *     IF (list L has one element)
 *         RETURN L.
 *     Divide the list into two halves A and B.
 *     A ← MERGE-SORT(A).
 *     B ← MERGE-SORT(B).
 *     L ← MERGE(A, B).
 *     RETURN L.
 *
 * A implementação abaixo segue exatamente essa estrutura, apenas com um
 * contador de chamadas recursivas (usado como "número de iterações" nos
 * testes).
 */
public class MergeSort {

    private long chamadas;

    // Ordena l por divisão e conquista, retornando um novo vetor ordenado.
    public int[] mergeSort(int[] l) {
        chamadas++;
        if (l.length <= 1) {
            return l;
        }

        int meio = l.length / 2;
        int[] a = mergeSort(Arrays.copyOfRange(l, 0, meio));
        int[] b = mergeSort(Arrays.copyOfRange(l, meio, l.length));

        return merge(a, b);
    }

    // Mescla dois vetores já ordenados em um único vetor ordenado.
    private int[] merge(int[] a, int[] b) {
        int[] resultado = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                resultado[k++] = a[i++];
            } else {
                resultado[k++] = b[j++];
            }
        }
        while (i < a.length) {
            resultado[k++] = a[i++];
        }
        while (j < b.length) {
            resultado[k++] = b[j++];
        }

        return resultado;
    }

    // Número de chamadas recursivas realizadas na última execução.
    public long getChamadas() {
        return chamadas;
    }

    // Zera o contador de chamadas, para poder medir uma nova execução.
    public void resetContador() {
        chamadas = 0;
    }
}
