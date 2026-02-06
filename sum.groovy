//Sum and substraction operations

import spock.lang.Specification

class SumAndSubstractionWithFloats extends Specification {
  def o = new Operations()
  def neg_inf = Float.NEGATIVE_INFINITY
  def pos_inf = Float.POSITIVE_INFINITY

  def "Test (+INF) + (+INF)"(){
    expect:
      pos_inf == o.Sum(pos_inf, pos_inf)
  }

  def "Test (+INF) + (-INF)"(){
    expect:
      Float.NaN == o.Sum(pos_inf, neg_inf)
  }

  def "Test (-INF) + (+INF)"(){
    expect:
      Float.NaN == o.Sum(neg_inf, pos_inf)
  }

  def "Test (-INF) + (-INF)"(){
    expect:
      neg_inf == o.Sum(neg_inf, neg_inf)
  }

  def "Tesr (+INF) - ("
  

}

class Operations {

float Sum (float a=0.0, float b=0.0) {
float res=a+b
return res
}

}
