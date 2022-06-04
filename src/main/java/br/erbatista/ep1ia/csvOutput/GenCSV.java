package br.erbatista.ep1ia.csvOutput;

import java.io.FileWriter;
import java.util.LinkedList;
//import com.opencsv.CSVWriter;
import java.time.LocalDateTime;

import br.erbatista.ep1ia.ga.GeneticManipulation;
import br.erbatista.ep1ia.ga.Ind;
import br.erbatista.ep1ia.math.BinManipulation;
import br.erbatista.ep1ia.math.functions.*;

public class GenCSV {

    private FileWriter fileWriter;

    public GenCSV(String filename){
        try{
            fileWriter = new FileWriter(filename);

            fileWriter.write("Gen,med,best\n");
        }catch (Exception e){
            System.out.println("Erro ao gerar CSV");
        }
    }

    public void write(int gen, double med, double best){
        try {
            fileWriter.write(gen+","+med+","+best+"\n");
        }catch (Exception e){
            System.out.println("Falha ao escrever linha no csv");
        }
    }

    public void close(){
        try{
            fileWriter.close();
        }catch (Exception e){
            System.out.println("Falha ao fechar o arquivo");
        }
    }
}
