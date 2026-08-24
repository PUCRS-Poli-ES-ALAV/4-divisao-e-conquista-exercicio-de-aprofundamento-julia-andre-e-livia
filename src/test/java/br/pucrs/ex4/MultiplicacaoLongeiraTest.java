package br.pucrs.ex4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

class MultiplicacaoLongeiraTest {

    private final MultiplicacaoLongeira algoritmo = new MultiplicacaoLongeira();

    @Test
    void deveMultiplicarComUmBit() {
        assertEquals(0, algoritmo.mult(0, 0, 1));
        assertEquals(0, algoritmo.mult(1, 0, 1));
        assertEquals(0, algoritmo.mult(0, 1, 1));
        assertEquals(1, algoritmo.mult(1, 1, 1));
    }

    @Test
    void deveMultiplicarComQuatroBits() {
        // valores válidos de 4 bits: 0 a 15
        assertEquals(3 * 5, algoritmo.mult(3, 5, 4));
        assertEquals(13 * 6, algoritmo.mult(13, 6, 4));
        assertEquals(15 * 15, algoritmo.mult(15, 15, 4));
        assertEquals(0, algoritmo.mult(0, 9, 4));
    }

    @Test
    void deveMultiplicarComDezesseisBits() {
        // valores válidos de 16 bits: 0 a 65535
        assertEquals(1000L * 250L, algoritmo.mult(1000, 250, 16));
        assertEquals(47321L * 12890L, algoritmo.mult(47321, 12890, 16));
        assertEquals(65535L * 65535L, algoritmo.mult(65535, 65535, 16));
    }

    @Test
    void deveMultiplicarComSessentaEQuatroBits() {
        long x = 4_294_967_296L; // 2^32
        long y = 4_294_967_296L; // 2^32
        assertEquals(x * y, algoritmo.mult(x, y, 64)); // == 2^64, estoura o long (comportamento esperado)

        long x2 = 123_456_789L;
        long y2 = 987_654_321L;
        assertEquals(x2 * y2, algoritmo.mult(x2, y2, 64));
    }

    @Test
    void deveContabilizarQuatroChamadasRecursivasPorNivel() {
        // T(n) = 1 + 4*T(n/2), com T(1) = 1 (cada chamada gera 4 filhas: e, f, g, h)
        // logo, para n = 2^k, T(n) = (4^(k+1) - 1) / 3
        algoritmo.resetContador();
        algoritmo.mult(13, 6, 4); // n = 4 -> k = 2
        long esperado = (long) ((Math.pow(4, 3) - 1) / 3); // = 21
        assertEquals(esperado, algoritmo.getChamadas());
    }

    @Test
    void deveBaterComMultiplicacaoNormalParaValoresAleatorios() {
        Random random = new Random(99);
        int[] tamanhosDeBits = {4, 8, 16, 32};

        for (int n : tamanhosDeBits) {
            long limite = (n == 32) ? Integer.MAX_VALUE : (1L << n);
            for (int tentativa = 0; tentativa < 20; tentativa++) {
                long x = (long) (random.nextDouble() * limite);
                long y = (long) (random.nextDouble() * limite);
                long esperado = x * y;
                long obtido = algoritmo.mult(x, y, n);
                assertEquals(esperado, obtido,
                        "Falhou para x=" + x + ", y=" + y + ", n=" + n);
            }
        }
    }

    @Test
    void deveFuncionarParaOsTresCasosDoEnunciado() {
        // 4 bits
        algoritmo.resetContador();
        assertEquals(78, algoritmo.mult(13, 6, 4));
        assertTrue(algoritmo.getChamadas() > 0);

        // 16 bits
        algoritmo.resetContador();
        assertEquals(47321L * 12890L, algoritmo.mult(47321, 12890, 16));
        assertTrue(algoritmo.getChamadas() > 0);

        // 64 bits
        algoritmo.resetContador();
        long x = 9_876_543_210L;
        long y = 123_456_789L;
        assertEquals(x * y, algoritmo.mult(x, y, 64));
        assertTrue(algoritmo.getChamadas() > 0);
    }
}
