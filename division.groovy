import spock.lang.*

// Test indeterminate involving 0,1,+INF: https://mathworld.wolfram.com/Indeterminate.html

class SomeFloatCalculusTests extends Specification {
  def o = new Operations()

  def fninf = Float.NEGATIVE_INFINITY
  def fpinf = Float.POSITIVE_INFINITY
  def fmax = Float.MAX_VALUE
  def fmin = Float.MIN_VALUE

  def dninf = Double.NEGATIVE_INFINITY
  def dpinf = Double.POSITIVE_INFINITY
  def dmax = Double.MAX_VALUE
  def dmin = Double.MIN_VALUE


  def "Test D.1: INF/INF"() {
     expect:
    Float.NaN==o.Div(fpinf,fpinf)
    Double.NaN==o.dDiv(dpinf,dpinf)
    
    }

  def "Test D.2: INF/-INF"() {
     expect:
    Float.NaN==o.Div(fpinf,fninf)
    Double.NaN==o.dDiv(dpinf,dninf)
    }

  def "Test D.3: -INF/-INF"() {
     expect:
    Float.NaN==o.Div(fninf,fninf)
    Double.NaN==o.dDiv(dninf,dninf)
    }
  
  def "Test D.4: -INF/INF"() {
     expect:
    Float.NaN==o.Div(fninf,fpinf)
    Double.NaN==o.dDiv(dninf,dpinf)
    }

  def "Test D.5: +0/INF"() {
     expect:
    0.0f.equals(o.Div(0.0f,fpinf))
    0.0d.equals(o.dDiv(0.0d,dpinf))
    }

  def "Test D.6: +0/-INF"() {
     expect:
    (-0.0f).equals(o.Div(0.0f, fninf))
    (-0.0d).equals(o.dDiv(0.0d, dninf))
    }

  def "Test D.7: -0/+INF"(){
    expect:
   (-0.0f).equals( o.Div(-0.0f,fpinf))
   (-0.0d).equals( o.dDiv(-0.0d,dpinf)) 
  }

  def "Test D.8: -0/-INF"() {
     expect:
    0.0f.equals(o.Div(-0.0f, fninf))  
    0.0d.equals(o.dDiv(-0.0d, dninf))
    }
  
  def "Test D.9: +0/+0"() {
     expect:
    Float.NaN==o.Div(0.0f,0.0f)
    Double.NaN==o.dDiv(0.0d,0.0d)
    }

  def "Test D.10: +0/-0"() {
     expect:
    Float.NaN==o.Div(0.0f,(-0.0f))
    Double.NaN==o.dDiv(0.0d,(-0.0d))
    }

  def "Test D.11: -0/+0"() {
     expect:
    Float.NaN==o.Div((-0.0f),0.0f)
    Double.NaN==o.dDiv((-0.0d),0.0d)
    }

  def "Test D.12 :-0/-0"() {
     expect:
    Float.NaN==o.Div((-0.0f),(-0.0f))
    Double.NaN==o.dDiv((-0.0d),(-0.0d))
    }

  def "Test D.13: 1/+0"() {
     expect:
    fpinf==o.Div(1.0f,0.0f)
    dpinf==o.dDiv(1.0d,0.0d)
    }

  def "Test D.14: 1/-0"() {
     expect:
    fninf==o.Div(1.0f,(-0.0f))
    dninf==o.dDiv(1.0d,(-0.0d))
    }

  def "Test D.15: 1/+Inf"() {
     expect:
    0.0f==o.Div(1.0f,fpinf)
    0.0d==o.dDiv(1.0d,dpinf)
    }

  def "Test D.16: 1/-Inf"() {
     expect:
    (-0.0f)==o.Div(1.0f,fninf)
    (-0.0d)==o.dDiv(1.0d,dninf)
    }

  def "Test D.17: MAX/MIN"() {
     expect:
    fpinf==o.Div(fmax,fmin)
    dpinf==o.dDiv(dmax,dmin)
    }

  def "Test D.18: MIN/MAX"() {
     expect:
    0.0f==o.Div(fmin,fmax)
    0.0d==o.dDiv(dmin,dmax)
    }

  def "Test D.19: 1/MAX"() {
     given:
    def resultado = o.Div(1.0f, fmax)
    def resultado_double = o.dDiv(1.0d, dmax)

    expect:
    !Float.isNaN(resultado)         // 1. Different from NaN
    resultado != 0.0f               // 2. Different from 0
    resultado < Float.MIN_NORMAL    // 3. Smaller than minimum normal (aprox 1.17E-38)

    !Double.isNaN(resultado_double)    
    resultado_double != 0.0d            
    resultado_double < Double.MIN_NORMAL 
  
}

  def "Test D.20: 1/MIN"() {
     expect:
    fpinf==o.Div(1.0f,fmin)
    dpinf==o.dDiv(1.0d,dmin)
    }

}


// SUT: Operations

class Operations {

float Div (float a=0.0, float b=0.0) {
float res=a/b
return res
}

double dDiv (double a=0.0, double b=0.0) {
double res=a/b
return res
}
}
