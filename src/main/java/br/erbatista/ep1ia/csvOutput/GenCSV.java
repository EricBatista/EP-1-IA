package br.erbatista.ep1ia.csvOutput;

import java.util.LinkedList;
//import com.opencsv.CSVWriter;
import java.time.LocalDateTime;

import br.erbatista.ep1ia.ga.GeneticManipulation;
import br.erbatista.ep1ia.ga.Ind;
import br.erbatista.ep1ia.math.BinManipulation;
import br.erbatista.ep1ia.math.functions.*;

public class GenCSV {

    //Writer writer = Files.newBufferedWriter("\\br.erbatista.ep1ia\csvOutput\{graph_title}_{date}.csv");
    //CSVWriter csvWriter = new CSVWriter(writer);
    public static final String date = LocalDateTime.now().toString();

    public static void main(String[] args) {
        System.out.println(date);
    }

    public void geraArquivo(){
        //csvWriter.flush();
        //writer.close();

        //graph.savefig(f"results\\graphs\\{graph_title}_{date}.png")
    }

    public void addLinha(LinkedList<Ind> ListInd){
        //csvWriter.writeAll(ListInd);
        //csvWriter.writeNext();
    }
}
