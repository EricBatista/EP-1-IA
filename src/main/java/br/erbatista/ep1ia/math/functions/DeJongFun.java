package br.erbatista.ep1ia.math.functions;

public class DeJongFun implements Fun{
    public double value(double x, double y){
        double xSquare = Math.pow(x,2);
        double ySquare = Math.pow(y,2);
        return 100*Math.pow(xSquare-ySquare,2)+Math.pow(1-xSquare,2);
    }

    @Override
    public String name() {
        return "DeJong";
    }
}
