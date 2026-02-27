
import spock.lang.Specification

class MulWithFloats extends Specification {
def o = new Operations()
def neg_inf = Float.NEGATIVE_INFINITY
def pos_inf = Float.POSITIVE_INFINITY
def M = Float.MAX_VALUE
def N = Float.MIN_VALUE

//simple precision tests

//1.
def "pos_inf * pos_inf = pos_inf"() { //Test for positive infinity * positive infinity which is an extreme case
  expect:
  pos_inf == o.Mul(pos_inf, pos_inf)
}
//2.
def "pos_inf * neg_inf = neg_inf"() { //Test for positive infinity * negative infinity which is an extreme case
  expect:
  neg_inf == o.Mul(pos_inf, neg_inf)
}
//3.
def "neg_inf * neg_inf = pos_inf"() { //Test for negative infinity * negative infinity which is an extreme case
  expect:
  pos_inf == o.Mul(neg_inf, neg_inf)
}
//4.
def "M * M = pos_inf (overflow)"() { //Test for surpasing the maximum possible float (which should succeeed)
  expect:
  pos_inf == o.Mul(M, M)
}
//5.
def "M * neg_inf = neg_inf"() { //Testing the maximum possible nuumber times negative infinity is negative infinity
  expect:
  neg_inf == o.Mul(M, neg_inf)
}
//6.
def "M * pos_inf = pos_inf"() { //Testing the maximum possible number times positive infinity is positive infinity
  expect:
  pos_inf == o.Mul(M, pos_inf)
}
//7.
def "N * N = +0 (underflow)"() { //Test for surpasing the minimum possible float (which should succeeed and give us zero)
  expect:
  +0f == o.Mul(N, N)
}
//8.
def "N * neg_inf = neg_inf"() { //Testing the minimum possible nuumber times negative infinity is negative infinity
  expect:
  neg_inf == o.Mul(N, neg_inf)
}
//9.
def "N * pos_inf = pos_inf"() { //Testing the minimum possible number times positive infinity is positive infinity
  expect:
  pos_inf == o.Mul(N, pos_inf)
}
//10.
def "N * M is finite positive number"() { //Test for multiplying the minimum possible float with the maximum possible float which should give us a finite positive number
  expect:
  !Float.isInfinite(o.Mul(N, M)) &&
  !Float.isNaN(o.Mul(N, M)) &&
  o.Mul(N, M) > 0f
}
//11.
def "0 * pos_inf = NaN"() { //Test for zero times positive infinity which is an indetermination case
  expect:
  Float.isNaN(o.Mul(0f, pos_inf))
}
//12.
def "0 * neg_inf = NaN"() { //Test for zero times negative infinity which is an indetermination case
  expect:
  Float.isNaN(o.Mul(0f, neg_inf))
}
//13.
def "-0 * pos_inf = NaN"() { //Test for negative zero times positive infinity which is an indetermination case
  expect:
  Float.isNaN(o.Mul(-0f, pos_inf))
}
//14.
def "-0 * neg_inf = NaN"() { //Test for negative zero times negative infinity which is an indetermination case
  expect:
  Float.isNaN(o.Mul(-0f, neg_inf))
}
//15.
def "(+INF) * (NaN) = NaN"() { //Test for positive infinity times NaN which is an indetermination case
  expect:
  Float.isNaN(o.Mul(pos_inf, Float.NaN))
}
//16.
def "(-INF) * (NaN) = NaN"() { //Test for negative infinity times NaN which is an indetermination case
  expect:
  Float.isNaN(o.Mul(neg_inf, Float.NaN))
}
//17.
def "+0 * (NaN) = NaN"() { //Test for positive zero times NaN which is an indetermination case
  expect:
  Float.isNaN(o.Mul(+0f, Float.NaN))
}
//18.
def "-0 * (NaN) = NaN"() { //Test for negative zero times NaN which is an indetermination case
  expect:
  Float.isNaN(o.Mul(-0f, Float.NaN))
}
//19.
def "M * (NaN) = NaN"() { //Test for maximum possible float times NaN which is an indetermination case
  expect:
  Float.isNaN(o.Mul(M, Float.NaN))
}
//20.
def "N * (NaN) = NaN"() { //Test for minimum possible float times NaN which is an indetermination case
  expect:
  Float.isNaN(o.Mul(N, Float.NaN))
}
//21.
def "+0 * M = +0"() { //Test for positive zero times maximum possible float which should give us positive zero
  expect:
  +0f == o.Mul(+0f, M)
}
//22.
def "+0 * N = +0"() { //Test for positive zero times minimum possible float which should give us negative zero
  expect:
  +0f == o.Mul(+0f, N)
}
//23.
def "-0 * M = -0"() { //Test for negative zero times maximum possible float which should give us negative zero
  expect:
  -0f == o.Mul(-0f, M)
}
//24.
def "-0 * N = -0"() { //Test for negative zero times minimum possible float which should give us positive zero
  expect:
  -0f == o.Mul(-0f, N)
}
}

class Operations {

float Mul (float a=0.0, float b=0.0) {
float res=a*b
return res
}

}