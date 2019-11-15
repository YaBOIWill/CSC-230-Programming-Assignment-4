//question 2 for programming assignment 2
//importing big integer and objects
import java.math.BigInteger;
import java.util.Objects;
//main Big Rational class
class BigRational implements Comparable<BigRational> {
//big integer and big rational variables
    public static final BigRational ZERO = new BigRational();
    public static final BigRational ONE = new BigRational("1");
    private BigInteger num; 
    private BigInteger den; 
//big rational constructors
    public BigRational() {
        this(BigInteger.ZERO);
    }
    public BigRational(BigInteger n) {
        this(n, BigInteger.ONE);
    }
    public BigRational(BigInteger num, BigInteger den) {
        this.num = num;
        this.den = den;
        check00();
        fixSigns();
        reduce();
    }
    //throws empty strings
    public BigRational(String str) {
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
    //gets absolute value
    public BigRational abs() {
        return new BigRational(num.abs(), den);
    }
    //negates the numbers
    public BigRational negate() {
        return new BigRational(num.negate(), den);
    }
    //adds the numbers 
    public BigRational add(BigRational other) {
        BigInteger newNumerator = this.num.multiply(other.den).add(other.num.multiply(this.den));
        BigInteger newDenominator = this.den.multiply(this.num);
        return new BigRational(newNumerator, newDenominator);
    }
    //subtracts two numbers 
    public BigRational subtract(BigRational other) {
        return add(other.negate());
    }
    //multiplies two numbers 
    public BigRational multiply(BigRational other) {
        BigInteger newNumerator = this.num.multiply(other.num);
        BigInteger newDenominator = this.den.multiply(other.den);
        return new BigRational(newNumerator, newDenominator);
    }
    //divides two numbers 
    public BigRational divide(BigRational other) {
        BigInteger newNumerator = this.num.multiply(other.den);
        BigInteger newDenominator = this.den.multiply(other.num);
        return new BigRational(newNumerator, newDenominator);
    }
    //throws negative exponent
    public BigRational pow(int exp) {
        if (exp < 0) {
            throw new ArithmeticException("exponent is negative");
        }
        num = num.pow(exp);
        den = den.pow(exp);
        return null;
    }
    public BigRational reciprocal() {
        return new BigRational(den, num);
    }
    public BigInteger toBigInteger() {
        if (den.equals(BigInteger.ONE)) {
            return toBigInteger();
        }
        throw new ArithmeticException("denominator is not one");
    }
    public int toInteger() {
        if (den.equals(BigInteger.ONE)) {
            return num.intValue();
        }
        throw new ArithmeticException("denominator is not one");
    }
    @Override
    public boolean equals(Object other) {
        if (other instanceof BigRational) {
            BigRational rhs = ((BigRational) other);
            return num.equals(rhs.num) && den.equals(((BigRational) other).den);
        }
        return false;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.num);
        hash = 29 * hash + Objects.hashCode(this.den);
        return hash;
    }
    public int compareTo(BigInteger oth) {
        if (this.num.doubleValue() < oth.doubleValue()) {
            return -1;
        }
        if (this.num.doubleValue() > oth.doubleValue()) {
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
    @Override
    public int compareTo(BigRational o) {
        return 0;
    }
}
//test class to test big rational
public class BigRational2 {
    //main method
    public static void main(String[] args) {
        //test cases
        BigRational br1 = new BigRational(BigInteger.ONE, BigInteger.TEN);
        System.out.println(br1.toString());
        BigRational br2 = new BigRational("1/0");
        System.out.println(br2.toString());
        BigRational br3 = new BigRational("-1/0");
        System.out.println(br3.toString());
        BigRational result = (new BigRational("2/10")).multiply(new BigRational("2/5"));
        System.out.println(result.toString());
    }
}