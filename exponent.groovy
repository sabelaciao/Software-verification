import spock.lang.Specification

/**
 * Test Procedure: Formal Verification of the Exponential Function (e^x)
 * Target: IEEE-754 Single Precision (32-bit float)
 */
class ExponentialSpecification extends Specification {
    def o = new Operations()
    def neg_inf = Float.NEGATIVE_INFINITY
    def pos_inf = Float.POSITIVE_INFINITY
    def M = Float.MAX_VALUE
    
    // Test Case: Exponential of Positive Infinity
    def "Test: exp(+INF) =? +INF"() {
        expect:
            pos_inf == o.Exp(pos_inf)
    }

    // Test Case: Exponential of Negative Infinity (Limit at Zero)
    def "Test: exp(-INF) =? 0.0"() {
        expect:
            0.0f == o.Exp(neg_inf)
    }

    // Test Case: Exponential of Zero (Identity)
    def "Test: exp(0.0) =? 1.0"() {
        expect:
            1.0f == o.Exp(0.0f)
    }

    // Test Case: Exponential of Negative Zero (Sign preservation of result)
    def "Test: exp(-0.0) =? 1.0"() {
        expect:
            1.0f == o.Exp(-0.0f)
    }

    // Test Case: Natural Overflow for 32-bit Float
    // e^89 approx 4.4E38, which exceeds Float.MAX_VALUE (~3.4E38)
    def "Test: exp(89.0) =? +INF"() {
        expect:
            pos_inf == o.Exp(89.0f)
    }

    // Test Case: NaN Propagation
    def "Test: exp(NaN) =? NaN"() {
        expect:
            Float.isNaN(o.Exp(Float.NaN))
    }
}

class Operations {
    /**
     * Calculates the exponential of a float value.
     * Uses Math.exp but ensures float-specific precision and return types.
     */
    float Exp(float a) {
        float res = (float) Math.exp((double) a)
        return res
    }
}
