package br.erbatista.ep1ia.math.functions;

public class SumSFun implements Fun{

    public double value(double x, double y){

        double[] ints = {x,y};
        int n = 2;
        double s = 0;
        for(int i = 1; i<=n; i++)
            s = s+i*Math.pow(ints[i-1],2);

        return s;

    }

    @Override
    public String name() {
        return "SumS";
    }
}
