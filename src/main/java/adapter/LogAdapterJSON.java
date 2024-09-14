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
 * Implementação do adaptador de logs no formato JSON.
 */
public class LogAdapterJSON extends LogAdapter {

    // Construtor que recebe o arquivo onde os logs serão gravados
    public LogAdapterJSON(File arquivo) {
        super(arquivo);  // Chama o construtor da classe pai (LogAdapter) com o arquivo
    }

    // Sobrescreve o método "escrever" para gravar os logs no formato JSON
    @Override
    public void escrever(Log... log) throws IOException {
        if (log.length > 0) {  // Verifica se há logs para escrever
            try (FileWriter fw = new FileWriter(arquivo, true)) {  // Abre o arquivo para escrita (modo append)
                Gson gson = new Gson();  // Inicializa o objeto Gson para conversão de objetos em JSON
                
                // Itera sobre os logs e converte cada um deles para JSON
                for (int i = 0; i < log.length; i++) {
                    String json = gson.toJson(log[i]).replaceAll("\n", " ");  // Converte o objeto Log em JSON e remove quebras de linha
                    fw.write(json + "\n");  // Escreve o log em formato JSON no arquivo
                }
            } catch (IOException ex) {
                // Lança uma exceção se houver problemas ao abrir ou escrever no arquivo
                throw new IOException("Erro: não foi possível abrir o arquivo: " + arquivo.getAbsoluteFile());
            }
        }
    }

    // Sobrescreve o método "exportarLogs" para exportar os logs do arquivo JSON
    @Override
    public List<Log> exportarLogs() throws IOException {
        List<Log> listaLog = new ArrayList<>();  // Lista que armazenará os logs

        // Abre o arquivo JSON para leitura
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            Gson gson = new Gson();  // Inicializa o objeto Gson para conversão de JSON em objetos
            String linha = "";

            // Lê cada linha do arquivo JSON e converte de volta para um objeto Log
            while (true) {
                linha = br.readLine();  // Lê uma linha do arquivo

                if (linha != null) {
                    // Converte a linha JSON de volta para um objeto Log e o adiciona à lista
                    listaLog.add(gson.fromJson(linha, Log.class));
                } else {
                    break;  // Sai do loop quando não houver mais linhas para ler
                }
            }

        } catch (IOException ex) {
            // Exibe a mensagem de erro e lança uma exceção se houver problemas ao abrir ou ler o arquivo
            System.out.println(ex.getMessage());
            throw new IOException("Falha ao abrir o arquivo: " + arquivo.getAbsoluteFile());
        } finally {
            // Exclui o arquivo de log após exportá-lo
            Files.delete(arquivo.toPath());
        }

        return listaLog;  // Retorna a lista de logs
    }
}
