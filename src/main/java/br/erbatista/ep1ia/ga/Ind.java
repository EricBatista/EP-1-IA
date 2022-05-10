package br.erbatista.ep1ia.ga;

import br.erbatista.ep1ia.math.BinManipulation;

import java.util.Arrays;

public class Ind implements Comparable<Ind>{
    public String getCromossomo(int index) {
        return cromossomo[index];
    }

    public void setCromossomo(String cromossomo, int index) {
        this.cromossomo[index] = cromossomo;
    }

    public double getValue(int index){
        return BinManipulation.mapping(getCromossomo(index));
    }

    public double getFit() {
        return fit;
    }

    public void setFit(double fit) {

        this.fit = fit;
    }

    public void calcFit(double value, double delta) {

        this.fit = 1.0/(value+delta);
    }

    public void setCromossomos(String[] cromossomo) {
        this.cromossomo = cromossomo;
    }

    private String[] cromossomo = {"",""};
    private double fit;

    public Ind(){
        setCromossomo(BinManipulation.genBin32(),0);
        setCromossomo(BinManipulation.genBin32(),1);
    }

    public Ind(String bin1, String bin2){
        setCromossomo(bin1,0);
        setCromossomo(bin2,1);
    }

    @Override
    public String toString() {
        return "Ind{" +
                "cromossomo=" + Arrays.toString(cromossomo) +
                ", fit=" + fit +
                '}';
    }


    @Override
    public int compareTo(Ind o) {
        if (this.getFit() < o.getFit()) {
            return 1;
        }
        if (this.getFit() > o.getFit()) {
            return -1;
        }
        return 0;
    }
}
