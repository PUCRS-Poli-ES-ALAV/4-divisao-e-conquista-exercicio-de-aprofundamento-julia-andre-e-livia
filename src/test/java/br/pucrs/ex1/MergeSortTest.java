package br.pucrs.ex1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

class MergeSortTest {

    private final MergeSort algoritmo = new MergeSort();

    @Test
    void deveOrdenarVetorVazio() {
        int[] a = {};
        assertArrayEquals(new int[] {}, algoritmo.mergeSort(a));
    }

    @Test
    void deveOrdenarVetorComUmElemento() {
        int[] a = {42};
        assertArrayEquals(new int[] {42}, algoritmo.mergeSort(a));
    }

    @Test
    void deveOrdenarVetorComDoisElementos() {
        int[] a = {9, 5};
        assertArrayEquals(new int[] {5, 9}, algoritmo.mergeSort(a));
    }

    @Test
    void deveOrdenarVetorJaOrdenado() {
        int[] a = {1, 2, 3, 4, 5};
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, algoritmo.mergeSort(a));
    }

    @Test
    void deveOrdenarVetorEmOrdemReversa() {
        int[] a = {5, 4, 3, 2, 1};
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, algoritmo.mergeSort(a));
    }

    @Test
    void deveOrdenarVetorComValoresNegativos() {
        int[] a = {-10, 3, -50, 0, 20, -1};
        assertArrayEquals(new int[] {-50, -10, -1, 0, 3, 20}, algoritmo.mergeSort(a));
    }

    @Test
    void deveOrdenarVetorComValoresRepetidos() {
        int[] a = {5, 3, 5, 1, 3, 5};
        assertArrayEquals(new int[] {1, 3, 3, 5, 5, 5}, algoritmo.mergeSort(a));
    }

    @Test
    void deveContabilizarChamadasRecursivas() {
        int[] a = {4, 3, 2, 1};
        algoritmo.resetContador();
        algoritmo.mergeSort(a);
        // para n elementos (n potência de 2), o número de chamadas recursivas é 2n - 1
        assertEquals(2 * a.length - 1, algoritmo.getChamadas());
    }

    @Test
    void deveBaterComResultadoDeVarredurasAleatorias() {
        Random random = new Random(123);
        for (int tentativa = 0; tentativa < 50; tentativa++) {
            int n = 1 + random.nextInt(500);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt();
            }

            int[] esperado = a.clone();
            Arrays.sort(esperado);

            int[] obtido = algoritmo.mergeSort(a);
            assertArrayEquals(esperado, obtido, "Falhou para n=" + n);
        }
    }

    @Test
    void deveFuncionarParaTamanhosDoEnunciado() {
        Random random = new Random(7);
        for (int n : new int[] {32, 2048, 1_048_576}) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt();
            }

            int[] esperado = a.clone();
            Arrays.sort(esperado);

            algoritmo.resetContador();
            int[] obtido = algoritmo.mergeSort(a);
            assertArrayEquals(esperado, obtido, "Falhou para n=" + n);
            assertTrue(algoritmo.getChamadas() > 0);
        }
    }
}
