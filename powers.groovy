import spock.lang.Specification
import spock.lang.Unroll
import spock.lang.Shared

class PowerAndRootWithFloats extends Specification {
    def o = new Operations()

    @Shared float pos_inf = Float.POSITIVE_INFINITY
    @Shared float neg_inf = Float.NEGATIVE_INFINITY
    @Shared float M = Float.MAX_VALUE

    @Unroll
    def "Test Power: (#base) ^ (#exp) =? #expected"() {
        expect:
        if (Float.isNaN(expected)) {
            assert Float.isNaN(o.Pow(base, exp))
        } else {
            assert expected == o.Pow(base, exp)
        }

        where:
        base      | exp       || expected
        0.0f      | 0.0f      || 1.0f
        pos_inf   | 0.0f      || 1.0f
        Float.NaN | 0.0f      || 1.0f
        1.0f      | pos_inf   || Float.NaN // JVM Specific: Indeterminate
        1.0f      | Float.NaN || Float.NaN // JVM Specific: Indeterminate
        0.0f      | 2.0f      || 0.0f
        0.0f      | -2.0f     || pos_inf
        pos_inf   | pos_inf   || pos_inf
        -2.0f     | 0.5f      || Float.NaN
    }

    @Unroll
    def "Test Root: sqrt(#input) =? #expected"() {
        expect:
        if (Float.isNaN(expected)) {
            assert Float.isNaN(o.Sqrt(input))
        } else if (expected == -0.0f) {
            // Bit-level check for Negative Zero
            assert Float.floatToRawIntBits(o.Sqrt(input)) == Float.floatToRawIntBits(-0.0f)
        } else {
            assert expected == o.Sqrt(input)
        }

        where:
        input     || expected
        pos_inf   || pos_inf
        neg_inf   || Float.NaN
        0.0f      || 0.0f
        -0.0f     || -0.0f
        Float.NaN || Float.NaN
        M         || (float)Math.sqrt(Float.MAX_VALUE)
        -4.0f     || Float.NaN
    }
}

class Operations {
    float Pow(float a, float b) { return (float) Math.pow((double) a, (double) b) }
    float Sqrt(float a) { return (float) Math.sqrt((double) a) }
}