package br.pucrs.ex4;

/**
 * Exercício 4 — Multiplicação de n-bits por Divisão e Conquista.
 *
 * Algoritmo (como enunciado):
 *
 * MULTIPLY(x, y, n)
 *    IF (n = 1)
 *       RETURN x * y.
 *    ELSE
 *       m ← ⎡ n / 2 ⎤.
 *       a ← ⎣ x / 2^m ⎦; b ← x mod 2^m.
 *       c ← ⎣ y / 2^m ⎦; d ← y mod 2^m.
 *       e ← MULTIPLY(a, c, m).
 *       f ← MULTIPLY(b, d, m).
 *       g ← MULTIPLY(b, c, m).
 *       h ← MULTIPLY(a, d, m).
 *       RETURN 2^(2m)*e + 2^m*(g + h) + f.
 *
 * A implementação abaixo é o mesmo algoritmo (mesma lógica, mesmas 4
 * chamadas recursivas e a mesma composição do resultado). Os únicos ajustes
 * em relação ao rascunho original (ex4.java) foram:
 *   - "if (n = 1)"  -> "if (n == 1)"          (atribuição vs. comparação)
 *   - "2^m"          -> "1L << m"             (em Java, ^ é XOR, não potência)
 *   - "x / 2^m" / "x % 2^m" -> deslocamento/máscara de bits (equivalente,
 *     e evita overflow do Math.pow para n=64)
 *   - "MULTIPLY(...)" -> "mult(...)"          (chamar o próprio método)
 *
 * n deve ser uma potência de 2 (o enunciado usa 4, 16 e 64 bits).
 */

public class MultiplicacaoLongeira {

    private long chamadas;

    public long mult(long x, long y, long n) {
        chamadas++;

        if (n == 1) {
            return x * y;
        }

        long m = n / 2;              // divisão inteira (arredonda para baixo)
        long potenciaM = 1L << m;    // 2^m

        long a = x >> m;                 // ⌊x / 2^m⌋
        long b = x & (potenciaM - 1);    // x mod 2^m
        long c = y >> m;                 // ⌊y / 2^m⌋
        long d = y & (potenciaM - 1);    // y mod 2^m

        long e = mult(a, c, m);
        long f = mult(b, d, m);
        long g = mult(b, c, m);
        long h = mult(a, d, m);

        return (e << (2 * m)) + ((g + h) << m) + f;
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
