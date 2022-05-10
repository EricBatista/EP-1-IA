package br.erbatista.ep1ia.math;

import java.util.Random;

public class BinManipulation {
    public static double getMax() {
        return max;
    }

    public static void setMax(double max) {
        BinManipulation.max = max;
    }

    public static double getMin() {
        return min;
    }

    public static void setMin(double min) {
        BinManipulation.min = min;
    }

    private static double max;
    private static double min;
    private static long seed = 1;

    private static Random rng = new Random(seed);

    public static String genBin32(){
        String result = "";
        for(int i = 0; i<32;i++){
            if(rng.nextBoolean()) result += "1";
            else result += "0";
        }
        return result;
    }

    public static long binToLong(String bin){
        return Long.parseLong(bin,2);
    }

    public static String longToBin(long l){
        StringBuilder baseLong = new StringBuilder(Long.toString(l, 2));
        for (int i = baseLong.length(); i<32; i++)
            baseLong.insert(0, "0");
        return baseLong.toString();
    }

    public static double mapping(String bin){
        return min + (max - min) * binToLong(bin) / (Math.pow(2,32)-1);
    }
}
