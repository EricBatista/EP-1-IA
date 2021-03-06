package br.erbatista.ep1ia.math.functions;

public class RastringinFun implements Fun{
    public double value(double x, double y){
        return ( (Math.sqrt(x) - 10 * Math.cos(2 * Math.PI * x) + 10 ) + ( Math.sqrt(y) - 10 * Math.cos(2 * Math.PI * y) + 10));
    }

    @Override
    public String name() {
        return "Rastringin";
    }
}
