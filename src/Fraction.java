import java.math.BigInteger;


class Fraction extends Feld implements Comparable<Fraction> {
    private BigInteger zähler;
    private BigInteger nenner;



    public Fraction(int x, int y, BigInteger zähler, BigInteger nenner) {

        super(x, y);
        BigInteger gcd = zähler.gcd(nenner);
        this.zähler = zähler.divide(gcd);
        this.nenner = nenner.divide(gcd);
            }

    Fraction addQuadriertes(Fraction r) {
        r.nenner = r.nenner.multiply(r.nenner);
        r.zähler = r.zähler.multiply(r.zähler);

        BigInteger lcm = nenner.multiply(r.nenner).divide(nenner.gcd(r.nenner));
        BigInteger newNumerator = zähler.multiply(lcm.divide(nenner)).add(r.zähler.multiply(lcm.divide(r.nenner)));
        return new Fraction(1, 1, newNumerator, lcm);
    }


    Fraction add(Fraction r) {
        BigInteger lcm = nenner.multiply(r.nenner).divide(nenner.gcd(r.nenner));
        BigInteger newNumerator = zähler.multiply(lcm.divide(nenner)).add(r.zähler.multiply(lcm.divide(r.nenner)));
        return new Fraction(1, 1, newNumerator, lcm);
    }

    Fraction subtract(Fraction r) {
        BigInteger lcm = nenner.multiply(r.nenner).divide(nenner.gcd(r.nenner));
        BigInteger newNumerator = zähler.multiply(lcm.divide(nenner)).subtract(r.zähler.multiply(lcm.divide(r.nenner)));
        return new Fraction(1, 1, newNumerator, lcm);
    }

    Fraction multiply(Fraction r) {
        return new Fraction(1, 1, zähler.multiply(r.zähler), nenner.multiply(r.nenner));
    }

    Fraction divide(Fraction r) {
        return new Fraction(1, 1, zähler.multiply(r.nenner), nenner.multiply(r.zähler));
    }

    public double toDouble() {
        return zähler.doubleValue() / nenner.doubleValue();

    }

    boolean isInteger() {
        return nenner.equals(BigInteger.ONE);
    }

    public String toString() {
        return zähler.toString() + "/" + nenner.toString();
    }

    public BigInteger getNumerator() {
        return zähler;
    }

    public BigInteger getDenominator() {
        return nenner;
    }



    public int intValue() {
        return zähler.divide(nenner).intValue();
    }


    @Override
    public int compareTo(Fraction o) {
        return zähler.multiply(o.nenner).compareTo(nenner.multiply(o.zähler));
    }


}