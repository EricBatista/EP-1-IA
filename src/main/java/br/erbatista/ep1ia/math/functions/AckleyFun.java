package br.erbatista.ep1ia.math.functions;

public class AckleyFun implements Fun{
    public double value(double x, double y){

        double[] ints = {x,y};
        int n = 2;
        double a = 20;
        double b = 0.2;
        double c = Math.PI*2;
        double s1 = 0;
        double s2 = 0;

        for(int i = 1; i<=n; i++){
            s1 = s1 + ints[i-1]*ints[i-1];
            s2 = s2 + Math.cos(c*ints[i-1]);
        }

        return (-a*Math.exp(-b*Math.sqrt(1.0/n*s1))-Math.exp(1.0/n*s2)+a+Math.exp(1));

    }

    @Override
    public String name() {
        return "Ackley";
    }
}
