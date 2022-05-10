package br.erbatista.ep1ia;


import br.erbatista.ep1ia.ga.GeneticManipulation;
import br.erbatista.ep1ia.ga.Ind;
import br.erbatista.ep1ia.math.BinManipulation;
import br.erbatista.ep1ia.math.functions.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class EP1IA {
    private static final Scanner input = new Scanner(System.in);
    private static final Random rng = new Random(1);
    public static void main(String[] args) {
        System.out.println("Insira o número de gerações");
        int numGeracoes = input.nextInt();
        System.out.println("Insira o tamanho da população de indivíduos");
        int numPop = input.nextInt();
        System.out.println("Insira o Max");
        BinManipulation.setMax(input.nextDouble());
        System.out.println("Insira o Min");
        BinManipulation.setMin(input.nextDouble());
        System.out.println("Insira o Delta");
        double numDelta = input.nextDouble();
        //TODO: Select Function
        Fun function = new AckleyFun();

        //Gerando população
        System.out.println("Gerando População");
        LinkedList<Ind> listInd = new LinkedList<>();

        for (int i = 0; i < numPop; i++) {
            Ind newInd = new Ind();
            newInd.calcFit(function.value(newInd.getValue(0),newInd.getValue(1)),numDelta);
            System.out.println("I " + i + ": " + newInd);
            listInd.add(newInd);
        }

        //ordena lista
        Collections.sort(listInd);
        listInd.forEach(s -> {System.out.println(s);});

        //Calc Média
        double media = 0;
        for (Ind ind : listInd) {
            media += ind.getFit();
        }
        media = media / listInd.size();

        for (int i = 0; i < numGeracoes; i++) {
            //Crossover simples melhor com aleatórios
            LinkedList<Ind> crossSimples = new LinkedList<>();
            for (int j = 0; j < numPop/5; j++) {
                Ind newInd = GeneticManipulation.simpleCross(listInd.get(0),listInd.get(rng.nextInt(1,listInd.size())));
                newInd.calcFit(function.value(newInd.getValue(0),newInd.getValue(1)),numDelta);
                crossSimples.add(newInd);
            }
            //Crossover Uniforme melhor com aleatórios
            LinkedList<Ind> crossUni = new LinkedList<>();
            for (int j = 0; j < numPop/5; j++) {
                Ind newInd = GeneticManipulation.uniformCross(listInd.get(0),listInd.get(rng.nextInt(1,listInd.size())));
                newInd.calcFit(function.value(newInd.getValue(0),newInd.getValue(1)),numDelta);
                crossSimples.add(newInd);
            }
            //50 melhores e 50 piores
            LinkedList<Ind> melhoresEPiores = new LinkedList<>();
            for (int j = 0; j < numPop/10; j++) {
                melhoresEPiores.add(listInd.get(j));
                melhoresEPiores.add(listInd.get(listInd.size()-1-j));
            }

            //100 aleatorios
            LinkedList<Ind> randomList = new LinkedList<>();
            for (int j = 0; j < numPop/5; j++) {
                Ind newInd = new Ind();
                newInd.calcFit(function.value(newInd.getValue(0),newInd.getValue(1)),numDelta);
                randomList.add(newInd);
            }

            //TODO: MUTACAO COM ROLETA


            //Concatenando
            listInd = crossSimples;
            listInd.addAll(crossUni);
            listInd.addAll(melhoresEPiores);
            listInd.addAll(randomList);

            Collections.sort(listInd);
            //listInd.forEach(s -> {System.out.println(s);});

            //Calc Média
            media = 0;
            for (Ind ind : listInd) {
                media += ind.getFit();
            }
            media = media / listInd.size();

            System.out.println("FIM CICLO: " + i);

            //if(listInd.get(0).getFit() == listInd.get(1).getFit()) break;

        }

        System.out.println("Resultado");
        System.out.println(listInd.get(0));
        System.out.println(listInd.get(0).getValue(0));
        System.out.println(listInd.get(0).getValue(1));
    }
}
