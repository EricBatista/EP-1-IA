package br.erbatista.ep1ia.math.functions;

public class GoldFun implements Fun{
    public double value(double x, double y){
        double a = 1 + Math.pow((double) x+y+1,2) * (19 - 14*x+Math.pow((double)3*x,2) - 14*y + 6*x*y+3 * Math.pow((double) y,2));
        double b = 30+ Math.pow((double) 2*x - 3*y ,2) * (18-32*x+ Math.pow((double) 12*x,2) + 48*y - 36 * x * y + Math.pow((double) 27*y,2));
        return a*b;
    }
}
