
// Rational class to build rational numbers and define mathematical
// operations on them
public class Rational {
    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator <= 0) {
            throw new IllegalArgumentException("denominator cannot be less than or equal to zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) throws IllegalArgumentException{
        if(denominator<=0){
            throw new IllegalArgumentException("denominator cannot be less than or equal to zero");
        }
        this.denominator = denominator;
    }

    // returns true if this rational is grater than another rational
    public boolean greaterThan(Rational r){
        if(r == null) return false;
        return this.numerator*r.getDenominator() > this.denominator*r.getNumerator();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Rational)){
            return false;
        }
        Rational r = (Rational)obj;
        return !this.greaterThan(r) && !r.greaterThan(this);
    }

    // returns a new rational number that is the result of the addition of two rationals
    public Rational plus(Rational r){
        if(r == null) return null;
        int n = this.numerator * r.getDenominator() + this.denominator * r.getNumerator();
        int d = this.denominator * r.getDenominator();
        return new Rational(n,d);
    }

    // returns a new rational number that is the result of multiplication of this rational times another
    public Rational multiply(Rational r){
        if(r==null) return null;
        int n = this.numerator * r.getNumerator();
        int d = this.denominator * r.getDenominator();
        return new Rational(n,d);
    }

    // returns a new rational that is the result of the subtraction of another rational from this
    // NOTE!! I know it can be implemented by using multiply by -1 and then add, but when actually implemented
    // it was longer and looked more complicated than simply like this...
    public Rational minus(Rational r){
        if(r==null) return null;
        int n = this.numerator * r.getDenominator() - this.denominator * r.getNumerator();
        int d = this.denominator * r.getDenominator();
        return new Rational(n,d);
    }

    // return a new rational that is the result of the division of this on another
    public Rational divide(Rational r) throws ArithmeticException{
        if(r == null) return null;
        if(r.getNumerator()==0){
            throw new ArithmeticException("can't divide by zero");
        }
        Rational opposite = new Rational(r.getDenominator(), r.getNumerator());
        return this.multiply(opposite);
    }

    @Override
    public String toString(){
        return this.numerator + "/" + this.denominator;
    }

    // return a new rational that is the reduction of this
    public Rational reduce(){
    int gcd = findGCD(this.numerator, this.denominator);
    int n = this.numerator/gcd;
    int d = this.denominator/gcd;
    return new Rational(n,d);
    }

    // returns the GCD of two numbers
    private int findGCD(int n, int d){
        while(d!=0){
            int temp = d;
            d = n%d;
            n = temp;
        }
        return n;
    }



}
