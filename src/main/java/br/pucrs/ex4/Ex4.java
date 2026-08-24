package br.pucrs.ex4;

import java.util.Random;

/**
 * Executa o mult (divisão e conquista) para os 3 casos pedidos no enunciado:
 * valores de 4 bits, 16 bits e 64 bits. Contabiliza o número de chamadas
 * recursivas (iterações) e o tempo gasto em cada execução.
 *
 * Observação: como "long" em Java é um tipo de 64 bits COM SINAL, o caso de
 * 64 bits usa valores dentro da faixa positiva representável (0 a 2^63-1)
 * para x e y; o produto pode ultrapassar 64 bits e sofrer overflow (assim
 * como aconteceria em qualquer aritmética de tamanho fixo) — isso é
 * esperado e mostrado nos testes.
 *
 * Rode com: mvn -q exec:java -Dexec.mainClass=br.pucrs.ex4.Ex4Runner
 * ou compile e rode manualmente com java/javac.
 */
public class Ex4 {

    public static void main(String[] args) {
        System.out.println("=== Exercício 4 — mult (Divisão e Conquista) ===");
        System.out.printf("%-10s %-25s %-25s %-12s %-15s%n",
                "n bits", "x", "y", "Chamadas", "Tempo (ms)");

        // Caso 1: 4 bits (valores de 0 a 15)
        rodarCaso(4, 13, 6);

        // Caso 2: 16 bits (valores de 0 a 65.535)
        rodarCaso(16, 47321, 12890);

        // Caso 3: 64 bits (valores positivos representáveis em long)
        Random random = new Random(42);
        long x64 = Math.abs(random.nextLong());
        long y64 = Math.abs(random.nextLong());
        rodarCaso(64, x64, y64);
    }

    private static void rodarCaso(long n, long x, long y) {
        MultiplicacaoLongeira algoritmo = new MultiplicacaoLongeira();

        long inicio = System.nanoTime();
        long resultado = algoritmo.mult(x, y, n);
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;

        System.out.printf("%-10d %-25d %-25d %-12d %-15.4f -> x*y = %d (esperado: %d)%n",
                n, x, y, algoritmo.getChamadas(), tempoMs, resultado, x * y);
    }
}
