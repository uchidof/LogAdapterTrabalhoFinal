/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import model.Log;

/**
 *
 * @author Fpc
 */
public class LogAdapterJSON extends LogAdapter{
    public LogAdapterJSON(File arquivo){
        super(arquivo);
    }
    
    @Override
    public void escrever(Log... log)throws IOException{
        if(log.length > 0) {
            try (FileWriter fw = new FileWriter(arquivo, true)) {
                Gson gson = new Gson();
                for (int i = 0; i < log.length; i++) {
                    String json = gson.toJson(log[i]).replaceAll("\n", " ");
                    fw.write(json + "\n");
                }
            } catch (IOException ex) {
                throw new IOException(
                        "Erro: não foi possível abrir o arquivo: " + arquivo.getAbsoluteFile()
                );
            }
        }
    }
    
    @Override
    public List<Log> exportarLogs() throws IOException{
        List<Log> listaLog = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            Gson gson = new Gson();
            String linha = "";
            while (true) {
                linha = br.readLine();

                if (linha != null) {
                    listaLog.add(gson.fromJson(linha, Log.class));
                } else {
                    break;
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new IOException(
                    "Falha ao abrir o arquivo: " + arquivo.getAbsoluteFile()
            );
        } finally {
            Files.delete(arquivo.toPath());
        }

        return listaLog;
    }
}
