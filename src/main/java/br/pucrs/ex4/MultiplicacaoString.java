package br.pucrs.ex4;

public class MultiplicacaoString {
    private long chamadas;

    public long mult(String x, String y, long n) {
        chamadas++;

        long xConvertido = Long.parseLong(x, 2);
        long yConvertido = Long.parseLong(y, 2);

        if (n == 1) {
            return xConvertido * yConvertido;
        }

        long m = n / 2;              // divisão inteira (arredonda para baixo)
        long potenciaM = 1L << m;    // 2^m

        long a = xConvertido >> m;                 // ⌊x / 2^m⌋
        long b = xConvertido & (potenciaM - 1);    // x mod 2^m
        long c = yConvertido >> m;                 // ⌊y / 2^m⌋
        long d = yConvertido & (potenciaM - 1);    // y mod 2^m

        long e = mult(Long.toBinaryString(a), Long.toBinaryString(c), m);
        long f = mult(Long.toBinaryString(b), Long.toBinaryString(d), m);
        long g = mult(Long.toBinaryString(b), Long.toBinaryString(c), m);
        long h = mult(Long.toBinaryString(a), Long.toBinaryString(d), m);

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