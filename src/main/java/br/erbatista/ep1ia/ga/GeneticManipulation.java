package br.erbatista.ep1ia.ga;

import br.erbatista.ep1ia.math.BinManipulation;

import java.util.Random;

public class GeneticManipulation {
    public static Ind uniformCross(Ind ind1, Ind ind2){
        String mask = BinManipulation.genBin32();
        String result[] = {"",""};
        for(int j = 0; j<2;j++){
            for(int i = 0; i<32;i++){
                if(mask.charAt(i) == 1) result[j] += ind1.getCromossomo(j).charAt(i);
                else result[j] += ind2.getCromossomo(j).charAt(i);
            }
        }
        return new Ind(result[0],result[1]);

    }

    public static Ind simpleCross(Ind ind1, Ind ind2){
        String result[] = {"",""};
        for(int j = 0; j<2;j++){
            result [j] = ind1.getCromossomo(j).substring(0,16);
            result [j] += ind1.getCromossomo(j).substring(16);
        }
        return new Ind(result[0],result[1]);
    }

    public static Ind mutation(Ind ind, double tax){
        Random rng = new Random(1);
        String s[] = {"",""};
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 32; i++) {
                if(rng.nextDouble() < tax) s[j] += mutAux(ind.getCromossomo(j).charAt(i));
                else s[j] += ind.getCromossomo(j).charAt(i);
            }
        }
        return new Ind(s[0],s[1]);

    }

    private static String mutAux(char c){
        if(c == '0') return "1";
        else return "0";
    }

}
