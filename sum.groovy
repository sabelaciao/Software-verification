{ printf (". \n") ; }//Sum and substraction operations

import spock.lang.Specification

class SumAndSubstractionWithFloats extends Specification {
  def o = new Operations()
  
  def fninf = Float.NEGATIVE_INFINITY
  def fpinf = Float.POSITIVE_INFINITY
  def fmax = Float.MAX_VALUE
  def fmin = Float.MIN_VALUE
  public static final float fpzero = 0.0;
  public static final float fnzero = -0.0;

  def dninf = Double.NEGATIVE_INFINITY
  def dpinf = Double.POSITIVE_INFINITY
  def dmax = Double.MAX_VALUE
  def dmin = Double.MIN_VALUE
  public static final double dpzero = 0.0;
  public static final double dnzero = -0.0;

  def "Test (+INF) + (+INF) =? +INF"(){ //Test for positive infinity + positive infinity which is an extreme case
    expect:
      dpinf == o.dSum(dpinf, dpinf)
      fpinf == o.fSum(fpinf, fpinf)
  }

  def "Test (+INF) + (-INF) =? NaN"(){ //Test for positive infinity + negative infinity which is an indetermination case
    expect:
      Double.NaN == o.dSum(dpinf, dninf)
      Float.NaN == o.fSum(fpinf, fninf)
  }

  def "Test (-INF) + (-INF) =? -INF"(){ //Test for negative infinity + negative infinity which is an extreme case
    expect:
      dninf == o.dSum(dninf, dninf)
      fninf == o.fSum(fninf, fninf)
  }

  def "Test: MAX + MAX =? INF"(){ //Test for surpasing the maximum possible float (which should succeeed)
    expect:
      dpinf == o.dSum(dmax, dmax)
      fpinf == o.fSum(fmax, fmax)
  }

  def "Test: MAX + (-INF) =? -INF"(){ //Testing the maximum possible number is smaller that INF
     expect:
      dninf == o.dSum(dmax, dninf)
      fninf == o.fSum(fmax, fninf)
  }

  def "Test: MAX + (+INF) =? +INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dpinf == o.dSum(dmax, dpinf)
      fpinf == o.fSum(fmax, fpinf)
  }

  def "Test: MAX + MIN =? MAX"(){ //Testing if adding a really small number to a really big one makes no difference
     expect:
      dmax == o.dSum(dmax, dmin)
      fmax == o.fSum(fmax, fmin)
  }

  def "Test: +0 + -0 =? +0"(){ //Testing the addition with zeros
     expect:
      dpzero == o.dSum(dpzero, dnzero)
      fpzero == o.fSum(fpzero, fnzero)
  }

  def "Test: +0 + MAX =? MAX"(){ //Testing the addition with zeros
     expect:
      dmax == o.dSum(dpzero, dmax)
      fmax == o.fSum(fpzero, fmax)
  }

  def "Test: +0 + MIN =? MIN"(){ //Testing the addition with zeros
     expect:
      dmin == o.dSum(dpzero, dmin)
      fmin == o.fSum(fpzero, fmin)
  }

  def "Test: +0 + (+INF) =? +INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dpinf == o.dSum(dpzero, dpinf)
      fpinf == o.fSum(fpzero, fpinf)
  }

  def "Test: +0 + (-INF) =? -INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dninf == o.dSum(dpzero, dninf)
      fninf == o.fSum(fpzero, fninf)
  }

  def "Test: -0 + (+INF) =? +INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dpinf == o.dSum(dnzero, dpinf)
      fpinf == o.fSum(fnzero, fpinf)
  }

  def "Test: -0 + (-INF) =? -INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dninf == o.dSum(dnzero, dninf)
      fninf == o.fSum(fnzero, fninf)
  }

  def "Test (+INF) + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dpinf, Double.NaN)
      Float.NaN == o.fSum(fpinf, Float.NaN)
  }

  def "Test (-INF) + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dninf, Double.NaN)
      Float.NaN == o.fSum(fninf, Float.NaN)
  }

  def "Test +0 + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dpzero, Double.NaN)
      Float.NaN == o.fSum(fpzero, Float.NaN)
  }

  def "Test -0 + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dnzero, Double.NaN)
      Float.NaN == o.fSum(fnzero, Float.NaN)
  }

  def "Test MAX + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dmax, Double.NaN)
      Float.NaN == o.fSum(fmax, Float.NaN)
  }

  def "Test MIN + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dmin, Double.NaN)
      Float.NaN == o.fSum(fmin, Float.NaN)
  }

  //Double + single

  def "Test s(+INF) + d(+INF) =? d(+INF)"(){ //Test for addition between float and double INF
    expect:
      dpinf == o.mSum(dpinf, fpinf)
  }

  def "Test s(-INF) + d(+INF) =? d(+INF)"(){ //Test for addition between float and double INF
    expect:
      dpinf == o.mSum(dpinf, fninf)
  }

  def "Test s(+INF) + d(-INF) =? d(-INF)"(){ //Test for addition between float and double INF
    expect:
      dninf == o.mSum(dninf, fpinf)
  }

  def "Test s(-INF) + d(-INF) =? d(-INF)"(){ //Test for addition between float and double INF
    expect:
      dninf == o.mSum(dninf, fninf)
  }
  
}

class Operations {

float fSum (float a=0.0, float b=0.0) {
float res=a+b
return res
}

double dSum (double a=0.0, double b=0.0) {
double res=a+b
return res
}

double mSum (double a=0.0, float b=0.0) {
double res=a+b
return res
}

}