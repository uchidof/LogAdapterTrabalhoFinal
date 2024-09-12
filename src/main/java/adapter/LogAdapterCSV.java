/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import model.Log;
/**
 *
 * @author Fpc
 */
public class LogAdapterCSV extends LogAdapter{
    
    public LogAdapterCSV (File arquivo){
        super(arquivo);
    }
    
    @Override
    public void escrever(Log... log) throws IOException{
        if (log.length > 0) {
            try (CSVWriter w = new CSVWriter(new FileWriter(arquivo, true))) {
                if (arquivo.length() == 0) {
                    String[] header = {
                        "operacao",
                        "nomeContato",
                        "dataHora",
                        "nomeUser"
                    };
                    w.writeNext(header);
                }
                for (int i = 0; i < log.length; i++) {
                    String[] dados = {
                        log[i].getOperacao(),
                        log[i].getNomeContato(),
                        log[i].getDataHora(),
                        log[i].getNomeUser()
                    };
                    w.writeNext(dados);
                }
            } catch (IOException ex) {
                throw new IOException(
                        "Falha ao abrir o arquivo: " + arquivo.getAbsoluteFile()
                );
            }
        }
    }
    
    @Override
    public List<Log> exportarLogs() throws IOException{
        List<Log> listaLogs = new ArrayList<>();
        
        try (Reader reader = Files.newBufferedReader(arquivo.toPath())) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String line[];

                csvReader.readNext();

                while ((line = csvReader.readNext()) != null) {
                    listaLogs.add(
                            new Log(
                                    line[0],    //Operacao
                                    line[1],    //NomeContato
                                    LocalDateTime.parse(
                                            line[2],
                                            Log.getDataHoraformatada()),    //Data e Hora
                                    line[3] //NomeUser
                            )
                    );
                }

                
            }
        } catch (IOException ex) {
            throw new IOException(
                    "Falha ao abrir o arquivo: " + arquivo.getAbsoluteFile()
            );
        } finally{
            Files.delete(arquivo.toPath());
        }

        return listaLogs;
    }
    
    @Override
    public String toString() {
        return "CSV";
    }  
    
}
