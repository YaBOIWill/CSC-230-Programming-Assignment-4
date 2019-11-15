//question 2 for programming assignment 4 for CSC 230
//big rational main public class 
import java.util.BigInteger;

public class BigRational1 implements Comparable<BigRational1>{
    public static final BigRational1 ZERO = new BigRational1(0);
    public static final BigRational1 ONE = new BigRational1(1);
    private BigInteger num; // only this can be nagative
    private BigInteger den; // never negative
   
    public BigRational1() {
        this(BigInteger.ZERO);
    }
    public BigRational1(BigInteger n) {
        this(n, BigInteger.ONE);
    }
    public BigRational1(BigInteger num, BigInteger den) {
        this.num = num;
        this.den = den;
        check00();
        fixSigns();
        reduce();
    }
    public BigRational1(String str) {
        if (str.length() == 0) {
            throw new IllegalArgumentException("empty string");
        }
        int slashIndex = str.indexOf('/');
        if (slashIndex == -1) {
            num = new BigInteger(str.trim());
            den = BigInteger.ONE; // den = 1
        } else {
            num = new BigInteger(str.substring(0, slashIndex).trim());
            den = new BigInteger(str.substring(slashIndex + 1).trim());
            check00();
            fixSigns();
            reduce();
        }
    }
    private void check00() {
  //here is logical error fix it by
  //if(den.equals(BigInteger.ZERO))
        if (den.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("division by zero");
        }
    }
    private void fixSigns() {
        if (den.compareTo(BigInteger.ZERO) < 0) {
            num = num.negate();
            den = den.negate();
        }
    }
    private void reduce() {
        BigInteger gcd = num.gcd(den);
        num = num.divide(gcd);
        den = den.divide(gcd);
    }
    /**
     * Get the absolute value
     *
     * @return
     */
    public BigRational1 abs() {
        return new BigRational1(num.abs(), den);
    }
    /**
     * Negate the number
     *
     * @return
     */
    public BigRational1 negate() {
        return new BigRational1(num.negate(), den);
    }
    /**
     * Add two numbers
     *
     * @param other
     * @return
     */
    public BigRational1 add(BigRational1 other) {
        BigInteger newNumerator = this.num.multiply(other.den).add(other.num.multiply(this.den));
        BigInteger newDenominator = this.den.multiply(this.num);
        return new BigRational1(newNumerator, newDenominator);
    }
    /**
     * Subtract two numbers
     *
     * @param other
     * @return
     */
    public BigRational1 subtract(BigRational1 other) {
        return add(other.negate());
    }
    /**
     * Multiply two numbers
     *
     * @param other
     * @return
     */
    public BigRational1 multiply(BigRational1 other) {
        BigInteger newNumerator = this.num.multiply(other.num);
        BigInteger newDenominator = this.den.multiply(other.den);
        return new BigRational1(newNumerator, newDenominator);
    }
    /**
     * Divide two numbers
     *
     * @param other
     * @return
     */
    public BigRational1 divide(BigRational1 other) {
        BigInteger newNumerator = this.num.multiply(other.den);
        BigInteger newDenominator = this.den.multiply(other.num);
        return new BigRational1(newNumerator, newDenominator);
    }
public BigRational1 pow( int exp ){
  if(exp < 0){
   throw new ArithmeticException("exponent is negative");
  }
  num = num.pow(exp);
  den = den.pow(exp);
  return 0;
}
public BigRational1 reciprocal(){
  return new BigRational1(den,num);
}
public BigInteger toBigInteger(){
  if(den.equals(BigInteger.ONE)){
   return new BigInteger(num);
  }
  throw new ArithmeticException("denominator is not one");
}
public int toInteger(){
  if(den.equals(BigInteger.ONE)){
   return num.intValue();
  }
  throw new ArithmeticException("denominator is not one");

}
    @Override
    public boolean equals(Object other) {
        if (other instanceof BigRational1) {
            BigRational1 rhs = ((BigRational1) other);
            return num.equals(rhs.num) && den.equals(((BigRational1) other).den);
        }
        return false;
    }
@Override
public int compareTo(BigRational1 oth){
  if(this.doubleValue() < oth.doubleValue()){
   return -1;
  }
  if(this.doubleValue() > oth.doubleValue()){
   return 1;
  }

  return 0;
}
    @Override
    public String toString() {
        if (den.equals(BigInteger.ZERO)) {
            if (num.compareTo(BigInteger.ZERO) < 0) {
                return "-Infinity";
            } else {
                return "Infinity";
            }
        }
        if (den.equals(BigInteger.ONE)) {
            return num.toString();
        } else {
            return num + " / " + den;
        }
    }
}
public class BigRationalTest {
    public static void main(String[] args) {
        BigRational1 br1 = new BigRational1(BigInteger.ONE, BigInteger.TEN);
        System.out.println(br1.toString());
       
        BigRational1 br2 = new BigRational1("1/0");
        System.out.println(br2.toString());
       
        BigRational1 br3 = new BigRational1("-1/0");
        System.out.println(br3.toString());
       
        BigRational1 result = (new BigRational1("2/10")).multiply(new BigRational1("2/5"));
        System.out.println(result.toString());
       
    }
}