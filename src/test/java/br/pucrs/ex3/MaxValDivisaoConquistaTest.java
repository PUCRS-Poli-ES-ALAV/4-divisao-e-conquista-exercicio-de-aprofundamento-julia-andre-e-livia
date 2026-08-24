package br.pucrs.ex3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

class MaxValDivisaoConquistaTest {

    private final MaxValDivisaoConquista algoritmo = new MaxValDivisaoConquista();

    @Test
    void deveEncontrarMaximoEmVetorComUmElemento() {
        long[] a = {42};
        assertEquals(42, algoritmo.maxVal2(a, 0, 0));
    }

    @Test
    void deveEncontrarMaximoEmVetorComDoisElementos() {
        long[] a = {5, 9};
        assertEquals(9, algoritmo.maxVal2(a, 0, 1));

        long[] b = {9, 5};
        assertEquals(9, algoritmo.maxVal2(b, 0, 1));
    }

    @Test
    void deveEncontrarMaximoQuandoEstaNoInicio() {
        long[] a = {100, 3, 7, 2, -5, 40};
        assertEquals(100, algoritmo.maxVal2(a, 0, a.length - 1));
    }

    @Test
    void deveEncontrarMaximoQuandoEstaNoFim() {
        long[] a = {3, 7, 2, -5, 40, 100};
        assertEquals(100, algoritmo.maxVal2(a, 0, a.length - 1));
    }

    @Test
    void deveEncontrarMaximoQuandoEstaNoMeio() {
        long[] a = {3, 7, 999, -5, 40, 12};
        assertEquals(999, algoritmo.maxVal2(a, 0, a.length - 1));
    }

    @Test
    void deveFuncionarComValoresNegativos() {
        long[] a = {-10, -3, -50, -1, -20};
        assertEquals(-1, algoritmo.maxVal2(a, 0, a.length - 1));
    }

    @Test
    void deveFuncionarComValoresRepetidos() {
        long[] a = {5, 5, 5, 5, 5};
        assertEquals(5, algoritmo.maxVal2(a, 0, a.length - 1));
    }

    @Test
    void deveContabilizarChamadasRecursivas() {
        long[] a = {1, 2, 3, 4};
        algoritmo.resetContador();
        algoritmo.maxVal2(a, 0, a.length - 1);
        // para n elementos (n potência de 2), o número de chamadas recursivas é n - 1
        assertEquals(a.length - 1, algoritmo.getChamadas());
    }

    @Test
    void deveBaterComResultadoDeVarredurasAleatorias() {
        Random random = new Random(123);
        for (int tentativa = 0; tentativa < 50; tentativa++) {
            int n = 1 + random.nextInt(500);
            long[] a = new long[n];
            long esperado = Long.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                a[i] = random.nextLong();
                esperado = Math.max(esperado, a[i]);
            }
            long obtido = algoritmo.maxVal2(a, 0, n - 1);
            assertEquals(esperado, obtido, "Falhou para n=" + n);
        }
    }

    @Test
    void deveFuncionarParaTamanhosDoEnunciado() {
        Random random = new Random(7);
        for (int n : new int[] {32, 2048, 1_048_576}) {
            long[] a = new long[n];
            long esperado = Long.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                a[i] = random.nextLong();
                esperado = Math.max(esperado, a[i]);
            }
            algoritmo.resetContador();
            long obtido = algoritmo.maxVal2(a, 0, n - 1);
            assertEquals(esperado, obtido, "Falhou para n=" + n);
            assertTrue(algoritmo.getChamadas() > 0);
        }
    }
}
