import spock.lang.Specification
import spock.lang.Unroll
import spock.lang.Shared

/**
 * Verificación Formal: Función Exponencial (e^x)
 * Distinción entre Single Precision (32-bit) y Double Precision (64-bit).
 * Cobertura completa de casos E.1 a E.12 (Test Basis IEEE-754).
 */
class ExponentDistinctionSpec extends Specification {
    def o = new Operations()

    // Constantes compartidas para el bloque 'where'
    @Shared float F_MAX = Float.MAX_VALUE
    @Shared float F_MIN = Float.MIN_VALUE
    @Shared float INF = Float.POSITIVE_INFINITY
    @Shared float NaN = Float.NaN

    @Unroll
    def "Exponent Verification: #label"() {
        given: "Valores de entrada en ambas precisiones"
        float inputF = input as float
        double inputD = input as double

        when: "Se calcula la exponencial"
        float resF = o.expF(inputF)
        double resD = o.expD(inputD)

        then: "El resultado en Single Precision (float) cumple el estándar"
        if (Float.isNaN(expectedF as float)) {
            assert Float.isNaN(resF)
        } else if (Float.isInfinite(expectedF as float)) {
            assert Float.isInfinite(resF)
        } else {
            // Tolerancia de precisión para float (7 dígitos)
            assert Math.abs(resF - (expectedF as float)) <= Math.abs((expectedF as float) * 1e-7f)
        }

        and: "El resultado en Double Precision (double) es exacto"
        if (Double.isNaN(expectedD as double)) {
            assert Double.isNaN(resD)
        } else if (Double.isInfinite(expectedD as double)) {
            assert Double.isInfinite(resD)
        } else {
            // Tolerancia estricta de 1e-15 para double (64 bits)
            assert Math.abs(resD - (expectedD as double)) <= Math.abs((expectedD as double) * 1e-15d)
        }

        where:
        label                     | input      || expectedF | expectedD
        "E.1 Positive Infinity"   | INF        || INF       | Double.POSITIVE_INFINITY
        "E.2 Negative Infinity"   | -INF       || 0.0f      | 0.0d
        "E.3 Zero"                | 0.0f       || 1.0f      | 1.0d
        "E.4 Negative Zero"       | -0.0f      || 1.0f      | 1.0d
        "E.5 Exp(MAX_FLOAT)"      | F_MAX      || INF       | Double.POSITIVE_INFINITY
        "E.6 NaN"                 | NaN        || NaN       | Double.NaN
        "E.7 Overflow Single"     | 89.0f      || INF       | 4.4896128191743455E38d
        "E.8 Overflow Both"       | 710.0f     || INF       | Double.POSITIVE_INFINITY
        "E.9 Underflow Single"    | -104.0f    || 0.0f      | 6.813556821545298E-46d
        "E.10 Underflow Both"     | -746.0f    || 0.0f      | 0.0d
        "E.11 Absorption"         | F_MIN      || 1.0f      | 1.0d
        "E.12 Euler Number"       | 1.0f       || 2.7182817f| 2.718281828459045d
    }
}

class Operations {
    // Componente de prueba
    float expF(float a) { return (float) Math.exp((double) a) }
    double expD(double a) { return Math.exp(a) }
}