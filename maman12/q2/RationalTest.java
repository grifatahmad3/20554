import java.util.Scanner;

// Testing the rational class, includes the main method
public class RationalTest {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Rational first;
        Rational second;

        do{
            System.out.println("Please enter the first rational number in the format \"numenator denominator\": ");
            int n1 = scan.nextInt();
            int d1 = scan.nextInt();
            System.out.println("Please enter the second rational number in the format \"numenator denominator\": ");
            int n2 = scan.nextInt();
            int d2 = scan.nextInt();
            try {
                first = new Rational(n1, d1);
                second = new Rational(n2, d2);
                break;
            }
            catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
        while(true);

        if(first.greaterThan(second)){
            System.out.println(first.toString() + " > " + second.toString());
        }
        if(second.greaterThan(first)){
            System.out.println(second.toString() + " > " + first.toString());
        }
        if(first.equals(second)){
            System.out.println(first.toString() + " == " + second.toString());
        }
        System.out.println(first.toString() + " + " + second.toString() + " = " + first.plus(second).toString());
        System.out.println(first.toString() + " - " + second.toString() + " = " + first.minus(second).toString());
        System.out.println(first.toString() + " * " + second.toString() + " = " + first.multiply(second).toString());

        try{
            System.out.println(first.toString() + " / " + second.toString() + " = " + first.divide(second).toString());
        }
        catch (ArithmeticException ae) {
            System.out.println(ae.getMessage());
        }

        System.out.println(first.toString() + " reduced is " + first.reduce().toString());

    }
}
