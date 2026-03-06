import spock.lang.Specification
import spock.lang.Unroll
import spock.lang.Shared

/**
 * Verificación Formal: Potencias (x^y) y Raíces (sqrt)
 * Solución final: Corrección de precisión exacta para la raíz de MAX_VALUE (R.6).
 */
class PowerAndRootDistinctionSpec extends Specification {
    def o = new Operations()

    @Shared float F_MAX = Float.MAX_VALUE
    @Shared float INF = Float.POSITIVE_INFINITY
    @Shared float NaN = Float.NaN

    @Unroll
    def "Power Verification: #label"() {
        given:
        float bF = base as float
        float eF = exp as float
        double bD = base as double
        double eD = exp as double

        when:
        float resF = o.powF(bF, eF)
        double resD = o.powD(bD, eD)

        then: "Single Precision (float) check"
        if (Float.isNaN(expectedF as float)) assert Float.isNaN(resF)
        else if (Float.isInfinite(expectedF as float)) assert Float.isInfinite(resF)
        else if (expectedF == -0.0f) assert Float.floatToRawIntBits(resF) == Float.floatToRawIntBits(-0.0f)
        else assert Math.abs(resF - (expectedF as float)) <= Math.abs((expectedF as float) * 1e-7f)

        and: "Double Precision (double) check"
        if (Double.isNaN(expectedD as double)) assert Double.isNaN(resD)
        else if (Double.isInfinite(expectedD as double)) assert Double.isInfinite(resD)
        else if (expectedD == -0.0d) assert Double.doubleToRawLongBits(resD) == Double.doubleToRawLongBits(-0.0d)
        else assert Math.abs(resD - (expectedD as double)) <= Math.abs((expectedD as double) * 1e-15d)

        where:
        label                    | base   | exp    || expectedF | expectedD
        "P.1 Zero Power"         | 0.0f   | 0.0f   || 1.0f      | 1.0d
        "P.2 INF Power Zero"     | INF    | 0.0f   || 1.0f      | 1.0d
        "P.3 NaN Power Zero"     | NaN    | 0.0f   || 1.0f      | 1.0d
        "P.4 Base 1 ^ INF"       | 1.0f   | INF    || NaN       | Double.NaN
        "P.5 Base 1 ^ NaN"       | 1.0f   | NaN    || NaN       | Double.NaN
        "P.6 0 ^ Positive"       | 0.0f   | 2.0f   || 0.0f      | 0.0d
        "P.7 0 ^ Negative"       | 0.0f   | -2.0f  || INF       | Double.POSITIVE_INFINITY
        "P.8 INF ^ INF"          | INF    | INF    || INF       | Double.POSITIVE_INFINITY
        "P.9 NegBase ^ Frac"     | -2.0f  | 0.5f   || NaN       | Double.NaN
        "P.10 -0 ^ Odd Int"      | -0.0f  | 3.0f   || -0.0f     | -0.0d
        "P.11 -0 ^ Even Int"     | -0.0f  | 2.0f   || 0.0f      | 0.0d
        "P.12 Overflow Single"   | F_MAX  | 2.0f   || INF       | 1.1579207772152249E77d
        "P.13 Identity Power"    | 5.0f   | 1.0f   || 5.0f      | 5.0d
    }

    @Unroll
    def "Root Verification: #label"() {
        given:
        float iF = input as float
        double iD = input as double

        when:
        float resF = o.sqrtF(iF)
        double resD = o.sqrtD(iD)

        then:
        if (Float.isNaN(expectedF as float)) assert Float.isNaN(resF)
        else if (Float.isInfinite(expectedF as float)) assert Float.isInfinite(resF)
        else if (expectedF == -0.0f) assert Float.floatToRawIntBits(resF) == Float.floatToRawIntBits(-0.0f)
        else assert Math.abs(resF - (expectedF as float)) <= Math.abs((expectedF as float) * 1e-7f)

        and:
        if (Double.isNaN(expectedD as double)) assert Double.isNaN(resD)
        else if (Double.isInfinite(expectedD as double)) assert Double.isInfinite(resD)
        else if (expectedD == -0.0d) assert Double.doubleToRawLongBits(resD) == Double.doubleToRawLongBits(-0.0d)
        else assert Math.abs(resD - (expectedD as double)) <= Math.abs((expectedD as double) * 1e-15d)

        where:
        label                    | input  || expectedF      | expectedD
        "R.1 Sqrt INF"           | INF    || INF            | Double.POSITIVE_INFINITY
        "R.2 Sqrt -INF"          | -INF   || NaN            | Double.NaN
        "R.3 Sqrt Zero"          | 0.0f   || 0.0f           | 0.0d
        "R.4 Sqrt Negative Zero" | -0.0f  || -0.0f          | -0.0d
        "R.5 Sqrt Negative"      | -4.0f  || NaN            | Double.NaN
        "R.6 Sqrt Max Float"     | F_MAX  || 1.8446744E19f  | 1.844674361506659E19d
    }
}

class Operations {
    float powF(float a, float b) { (float) Math.pow((double) a, (double) b) }
    double powD(double a, double b) { Math.pow(a, b) }
    float sqrtF(float a) { (float) Math.sqrt((double) a) }
    double sqrtD(double a) { Math.sqrt(a) }
}