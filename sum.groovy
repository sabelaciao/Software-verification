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

  def "Test S.1: (+INF) + (+INF) =? +INF"(){ //Test for positive infinity + positive infinity which is an extreme case
    expect:
      dpinf == o.dSum(dpinf, dpinf)
      fpinf == o.fSum(fpinf, fpinf)
  }

  def "Test S.2: (+INF) + (-INF) =? NaN"(){ //Test for positive infinity + negative infinity which is an indetermination case
    expect:
      Double.NaN == o.dSum(dpinf, dninf)
      Float.NaN == o.fSum(fpinf, fninf)
  }

  def "Test S.3: (-INF) + (-INF) =? -INF"(){ //Test for negative infinity + negative infinity which is an extreme case
    expect:
      dninf == o.dSum(dninf, dninf)
      fninf == o.fSum(fninf, fninf)
  }

  def "Test S.4: MAX + MAX =? INF"(){ //Test for surpasing the maximum possible float (which should succeeed)
    expect:
      dpinf == o.dSum(dmax, dmax)
      fpinf == o.fSum(fmax, fmax)
  }

  def "Test S.5: MAX + (-INF) =? -INF"(){ //Testing the maximum possible number is smaller that INF
     expect:
      dninf == o.dSum(dmax, dninf)
      fninf == o.fSum(fmax, fninf)
  }

  def "Test S.6: MAX + (+INF) =? +INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dpinf == o.dSum(dmax, dpinf)
      fpinf == o.fSum(fmax, fpinf)
  }

  def "Test S.7: MAX + MIN =? MAX"(){ //Testing if adding a really small number to a really big one makes no difference
     expect:
      dmax == o.dSum(dmax, dmin)
      fmax == o.fSum(fmax, fmin)
  }

  def "Test S.8: +0 + -0 =? +0"(){ //Testing the addition with zeros
     expect:
      dpzero == o.dSum(dpzero, dnzero)
      fpzero == o.fSum(fpzero, fnzero)
  }

  def "Test S.9: +0 + MAX =? MAX"(){ //Testing the addition with zeros
     expect:
      dmax == o.dSum(dpzero, dmax)
      fmax == o.fSum(fpzero, fmax)
  }

  def "Test S.10: +0 + MIN =? MIN"(){ //Testing the addition with zeros
     expect:
      dmin == o.dSum(dpzero, dmin)
      fmin == o.fSum(fpzero, fmin)
  }

  def "Test S.11: +0 + (+INF) =? +INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dpinf == o.dSum(dpzero, dpinf)
      fpinf == o.fSum(fpzero, fpinf)
  }

  def "Test S.12: +0 + (-INF) =? -INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dninf == o.dSum(dpzero, dninf)
      fninf == o.fSum(fpzero, fninf)
  }

  def "Test S.13: -0 + (+INF) =? +INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dpinf == o.dSum(dnzero, dpinf)
      fpinf == o.fSum(fnzero, fpinf)
  }

  def "Test S.14: -0 + (-INF) =? -INF"(){ //Testing if adding something to infinity is still infinity
     expect:
      dninf == o.dSum(dnzero, dninf)
      fninf == o.fSum(fnzero, fninf)
  }

  def "Test S.15: (+INF) + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dpinf, Double.NaN)
      Float.NaN == o.fSum(fpinf, Float.NaN)
  }

  def "Test S.16: (-INF) + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dninf, Double.NaN)
      Float.NaN == o.fSum(fninf, Float.NaN)
  }

  def "Test S.17: +0 + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dpzero, Double.NaN)
      Float.NaN == o.fSum(fpzero, Float.NaN)
  }

  def "Test S.18: -0 + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dnzero, Double.NaN)
      Float.NaN == o.fSum(fnzero, Float.NaN)
  }

  def "Test S.19: MAX + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dmax, Double.NaN)
      Float.NaN == o.fSum(fmax, Float.NaN)
  }

  def "Test S.20: MIN + NaN =? NaN"(){ //Test for any number with NaN should raise a NaN
    expect:
      Double.NaN == o.dSum(dmin, Double.NaN)
      Float.NaN == o.fSum(fmin, Float.NaN)
  }

  //Double + single

  def "Test Sm.1: s(+INF) + d(+INF) =? d(+INF)"(){ //Test for addition between float and double INF
    expect:
      dpinf == o.mSum(dpinf, fpinf)
  }

  def "Test Sm.2: s(-INF) + d(+INF) =? d(NaN)"(){ //Test for addition between float and double INF
    expect:
      Double.NaN == o.mSum(dpinf, fninf)
  }

  def "Test Sm.3: s(+INF) + d(-INF) =? d(NaN)"(){ //Test for addition between float and double INF
    expect:
      Double.NaN == o.mSum(dninf, fpinf)
  }

  def "Test Sm.4: s(-INF) + d(-INF) =? d(-INF)"(){ //Test for addition between float and double INF
    expect:
      dninf == o.mSum(dninf, fninf)
  }

  def "Test Sm.5: s(0) + d(+INF) =? d(-INF)"(){   //Test for addition with zero
    expect:
      dpinf == o.mSum(dpinf, fpzero)
  }

  def "Test Sm.6: s(0) + d(-INF) =? d(-INF)"(){
    expect:
      dninf == o.mSum(dninf, fpzero)
  }

  def "Test Sm.7: s(+0) + d(+0) =? 0"(){  //Test for addition between zeros
    expect:
      0 == o.mSum(dpzero, fpzero)
  }

  def "Test Sm.8: s(-0) + d(+0) =? 0"(){  
    expect:
      0 == o.mSum(dpzero, fnzero)
  }

  def "Test Sm.9: s(+0) + d(-0) =? 0"(){  
    expect:
      0 == o.mSum(dnzero, fpzero)
  }

  def "Test Sm.10: s(-0) + d(-0) =? 0"(){  
    expect:
      0 == o.mSum(dnzero, fnzero)
  }

  def "Test Sm.11: s(MAX) + d(0) =? s(MAX)"(){  //Test with max/min plus zero
    expect:
      fmax == o.mSum(dpzero, fmax)
  }

  def "Test Sm.12: s(MIN) + d(0) =? s(MIN)"(){
    expect:
      fmin == o.mSum(dpzero, fmin)
  }

  def "Test Sm.13: s(MAX) + d(MAX) =? d(MAX)"(){  //Test with different max and min
    expect:
      dmax == o.mSum(dmax, fmax)
  }

  def "Test Sm.14: s(MAX) + d(MIN) =? s(MAX)"(){
    expect:
      fmax == o.mSum(dmin, fmax)
  }

  def "Test Sm.15: s(MIN) + d(MAX) =? d(MAX)"(){
    expect:
      dmax == o.mSum(dmax, fmin)
  }

  def "Test Sm.16: s(MIN) + d(MIN) =? s(MIN)"(){
    expect:
      fmin == o.mSum(dmin, fmin)
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