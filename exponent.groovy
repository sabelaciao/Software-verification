import spock.lang.Specification
import spock.lang.Unroll
import spock.lang.Shared

class ExponentWithFloats extends Specification {
    def o = new Operations()
    
    // Las variables usadas en 'where' deben ser @Shared
    @Shared float pos_inf = Float.POSITIVE_INFINITY
    @Shared float neg_inf = Float.NEGATIVE_INFINITY
    @Shared float M = Float.MAX_VALUE

    @Unroll
    def "Test Exponent: exp(#input) =? #expected"() {
        expect:
            if (Float.isNaN(expected)) {
                assert Float.isNaN(o.Exp(input))
            } else {
                assert expected == o.Exp(input)
            }

        where:
        input     || expected
        pos_inf   || pos_inf
        neg_inf   || 0.0f
        0.0f      || 1.0f
        -0.0f     || 1.0f
        M         || pos_inf
        Float.NaN || Float.NaN
    }
}

class Operations {
    float Exp(float a) { return (float) Math.exp((double) a) }
}