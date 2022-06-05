/********************************************/
/*******EP 1 - Inteligencia Artificial*******/
/*************Algoritmo Genetico*************/
/*******Eric Batista da Silva 10783114*******/
/*****Matheus Amorim dos Santos 10882810*****/
/**Vinícius Zacarias Lacerda Brito 10783198**/
/********************************************/

package br.erbatista.ep1ia;


import br.erbatista.ep1ia.csvOutput.GenCSV;
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
        System.out.println("Selecione o valor equivalente a função:\n1 - Gold\n2 - SumS\n3 - DeJong\n4 - Ackley\n5 - Bump\n6 - Rastringin");
        int funcEntry = input.nextInt();
        Fun function;
        if (funcEntry == 1){
            function = new GoldFun();
        }
        else if (funcEntry == 2){
            function = new SumSFun();
        }
        else if (funcEntry == 3){
            function = new DeJongFun();
        }
        else if (funcEntry == 4){
            function = new AckleyFun();
        }
        else if (funcEntry == 5){
            function = new BumpFun();
        }
        else if (funcEntry == 6){
            function = new RastringinFun();
        }
        else {
            function = new RastringinFun();
            System.out.println("O valor inserido não é valido");
            System.exit(0);
        }

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
        listInd.forEach(System.out::println);

        //Calc Média
        double media = 0;
        for (Ind ind : listInd) {
            media += ind.getFit();
        }
        media = media / listInd.size();

        GenCSV csv = new GenCSV("exec"+function.name()+"Gen"+numGeracoes+"Pop"+numPop+".csv");
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
                crossUni.add(newInd);
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

            //MUTACAO COM ROLETA
            LinkedList<Ind> roulettemutation = new LinkedList<>();
            int numInd = 0;
            boolean roulette;
            int m;
            double fitBestInd = listInd.getFirst().getFit();
            for (int j = 0; j < listInd.size(); j++) {
                double nRoulette = rng.nextDouble(0, fitBestInd);
                for (m = 0; m < listInd.size()-1; m++) {
                    double fit = listInd.get(m).getFit();
                    if (fit <= nRoulette) {
                        break;
                    }
                }
                Ind newInd = GeneticManipulation.mutation(listInd.get(m), 0.35);
                newInd.calcFit(function.value(newInd.getValue(0),newInd.getValue(1)),numDelta);
                roulettemutation.add(newInd);
                numInd++;
                if (numInd >= numPop / 5) {
                    break;
                }
            }

            //Concatenando
            listInd = crossSimples;
            listInd.addAll(crossUni);
            listInd.addAll(melhoresEPiores);
            listInd.addAll(randomList);
            listInd.addAll(roulettemutation);

            Collections.sort(listInd);

            //Calc Média
            media = 0;
            for (Ind ind : listInd) {
                media += ind.getFit();
            }
            media = media / listInd.size();

            csv.write(i,media,listInd.get(0).getFit());

            System.out.println("FIM CICLO: " + i);

        }

        csv.close();

        System.out.println("Resultado");
        System.out.println(listInd.get(0));
        System.out.println(listInd.get(0).getValue(0));
        System.out.println(listInd.get(0).getValue(1));
    }
}
