//Sum and substraction operations

import spock.lang.Specification

class SumAndSubstractionWithFloats extends Specification {
  def o = new Operations()
  def neg_inf = Float.NEGATIVE_INFINITY
  def pos_inf = Float.POSITIVE_INFINITY
  def M = Float.MAX_VALUE
  def N = Float.MIN_VALUE

  def "Test (+INF) + (+INF) =? +INF"(){ //Test for positive infinity + positive infinity which is an extreme case
    expect:
      pos_inf == o.Sum(pos_inf, pos_inf)
  }

  def "Test (+INF) + (-INF) =? NaN"(){ //Test for positive infinity + negative infinity which is an indetermination case
    expect:
      Float.NaN == o.Sum(pos_inf, neg_inf)
  }

  def "Test (-INF) + (+INF) =? NaN"(){ //Test for negative infinity + positive infinity which is an indetermination case
    expect:
      Float.NaN == o.Sum(neg_inf, pos_inf)
  }

  def "Test (-INF) + (-INF) =? -INF"(){ //Test for negative infinity + negative infinity which is an extreme case
    expect:
      neg_inf == o.Sum(neg_inf, neg_inf)
  }

  def "Test: F.MAX + F.MAX =? NaN"(){ //Test for surpasing the maximum possible float (which should fail)
    expect:
      Float.NaN == o.Sum(M, M)
  }

  def "Test: F.MAX + F.MAX =? INF"(){ //Test for surpasing the maximum possible float (which should succeeed)
    expect:
      pos_inf == o.Sum(M, M)
  }

  def "Test: F.MIN + F.MIN =? NaN"(){ //Test for surpasing the minimum possible float (which should fail)
    expect:
      Float.NaN == o.Sum(N, N)
  }

  def "Test: F.MIN + F.MIN =? -INF"(){ //Test for surpasing the minimum possible float (which should succeeed)
    expect:
      neg_inf == o.Sum(N, N)
  }

  def "Test: F.MAX + (-INF) =? -INF"(){ //Testing the maximum possible number is smaller that INF
     expect:
      neg_inf == o.Sum(M, neg_inf)
  }

  def "Test: F.MIN + (+INF) =? +INF"(){ //Testing the minimum possible number is bigger than -INF
     expect:
      pos_inf == o.Sum(N, pos_inf)
  }

}

class Operations {

float Sum (float a=0.0, float b=0.0) {
float res=a+b
return res
}

}
