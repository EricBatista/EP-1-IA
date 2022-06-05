package br.erbatista.ep1ia.math.functions;

public class BumpFun implements Fun{

    public double value(double x, double y) {

            double temp0 = Math.pow(Math.cos(x), 4) + Math.pow(Math.cos(y), 4);
            double temp1 = 2 * Math.pow(Math.cos(x),2) * Math.pow(Math.cos(y),2);
            double temp2 = Math.sqrt(Math.sqrt(x)) + 2 * Math.sqrt(y);

            return (Math.abs((temp0 - temp1) / temp2));
    }

    @Override
    public String name() {
        return "Bump";
    }
}
