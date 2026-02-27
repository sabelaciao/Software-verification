import spock.lang.Specification

/**
 * Test Procedure: Verification of Exponentiation (x^y) and Square Roots
 * Based on IEEE-754 Test Basis for Single Precision (float).
 */
class PowerAndRootSpecification extends Specification {
    def o = new Operations()
    def neg_inf = Float.NEGATIVE_INFINITY
    def pos_inf = Float.POSITIVE_INFINITY
    def M = Float.MAX_VALUE

    // --- Power Function: Zero and Infinity Cases ---

    def "Test: 0.0 ^ 0.0 =? 1.0"() {
        // IEEE-754 pow(0, 0) is defined as 1.0
        expect:
            1.0f == o.Pow(0.0f, 0.0f)
    }

    def "Test: INF ^ 0.0 =? 1.0"() {
        // Any number to the power of 0 is 1.0, including infinity
        expect:
            1.0f == o.Pow(pos_inf, 0.0f)
    }

    def "Test: 1.0 ^ INF =? 1.0"() {
        // In IEEE-754 pow logic, 1 to any power is 1
        expect:
            1.0f == o.Pow(1.0f, pos_inf)
    }

    def "Test: INF ^ INF =? INF"() {
        expect:
            pos_inf == o.Pow(pos_inf, pos_inf)
    }

    def "Test: 0.0 ^ INF =? 0.0"() {
        expect:
            0.0f == o.Pow(0.0f, pos_inf)
    }

    def "Test: (-1.0) ^ 0.5 =? NaN"() {
        // Square root of a negative number (base < 0 and non-integer exponent)
        expect:
            Float.isNaN(o.Pow(-1.0f, 0.5f))
    }

    // --- Square Root Specific Cases ---

    def "Test: Sqrt(-0.0) =? -0.0"() {
        // Special IEEE-754 requirement: sqrt(-0) must preserve the sign
        expect:
            Float.floatToRawIntBits(-0.0f) == Float.floatToRawIntBits(o.Sqrt(-0.0f))
    }

    def "Test: Sqrt(INF) =? INF"() {
        expect:
            pos_inf == o.Sqrt(pos_inf)
    }

    def "Test: Sqrt(-M) =? NaN"() {
        // Root of a large negative number
        expect:
            Float.isNaN(o.Sqrt(-M))
    }
}

class Operations {
    // General Exponentiation a^b
    float Pow(float a, float b) {
        return (float) Math.pow((double) a, (double) b)
    }

    // Square Root (Treated as a special case or direct function)
    float Sqrt(float a) {
        return (float) Math.sqrt((double) a)
    }
}