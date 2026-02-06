//Substraction operation with floats, including extreme cases with infinity and indetermination cases with infinity

import spock.lang.Specification

class SumAndSubstractionWithFloats extends Specification {
  def o = new Operations()
  def neg_inf = Float.NEGATIVE_INFINITY
  def pos_inf = Float.POSITIVE_INFINITY

  def "Test (+INF) - (+INF)"(){ //Test for positive infinity - positive infinity which is an extreme case
    expect:
      Float.NaN == o.Substraction(pos_inf, pos_inf)
  }

  def "Test (+INF) - (-INF)"(){ //Test for positive infinity - negative infinity which is an indetermination case
    expect:
      pos_inf == o.Substraction(pos_inf, neg_inf)
  }

  def "Test (-INF) - (+INF)"(){ //Test for negative infinity - positive infinity which is an indetermination case
    expect:
      neg_inf == o.Substraction(neg_inf, pos_inf)
  }

  def "Test (-INF) - (-INF)"(){ //Test for negative infinity - negative infinity which is an extreme case
    expect:
      Float.NaN == o.Substraction(neg_inf, neg_inf)
  }

}



class Operations {
float Substraction (float a=0.0, float b=0.0) {
float res=a-b
return res
}
}