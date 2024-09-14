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
 * Implementação do adaptador de logs no formato CSV.
 */
public class LogAdapterCSV extends LogAdapter {

    // Construtor que recebe o arquivo onde os logs serão gravados
    public LogAdapterCSV(File arquivo) {
        super(arquivo);  // Chama o construtor da classe pai (LogAdapter) com o arquivo
    }

    // Sobrescreve o método "escrever" para gravar os logs no formato CSV
    @Override
    public void escrever(Log... log) throws IOException {
        if (log.length > 0) {  // Verifica se há logs para escrever
            try (CSVWriter w = new CSVWriter(new FileWriter(arquivo, true))) {  // Abre o arquivo para escrita (modo append)
                
                // Se o arquivo estiver vazio, escreve o cabeçalho
                if (arquivo.length() == 0) {
                    String[] header = {
                        "operacao",    // Nome da operação
                        "nomeContato", // Nome do contato
                        "dataHora",    // Data e hora da operação
                        "nomeUser"     // Nome do usuário que realizou a operação
                    };
                    w.writeNext(header);  // Escreve o cabeçalho no arquivo CSV
                }

                // Itera sobre os logs e escreve cada um deles no arquivo CSV
                for (int i = 0; i < log.length; i++) {
                    String[] dados = {
                        log[i].getOperacao(),   // Operação realizada
                        log[i].getNomeContato(), // Nome do contato associado
                        log[i].getDataHora(),    // Data e hora da operação
                        log[i].getNomeUser()     // Nome do usuário que realizou a operação
                    };
                    w.writeNext(dados);  // Escreve os dados do log no arquivo CSV
                }
            } catch (IOException ex) {
                // Lança uma exceção se houver problemas ao abrir ou escrever no arquivo
                throw new IOException("Falha ao abrir o arquivo: " + arquivo.getAbsoluteFile());
            }
        }
    }

    // Sobrescreve o método "exportarLogs" para exportar os logs do arquivo CSV
    @Override
    public List<Log> exportarLogs() throws IOException {
        List<Log> listaLogs = new ArrayList<>();  // Lista que armazenará os logs

        // Abre o arquivo CSV para leitura
        try (Reader reader = Files.newBufferedReader(arquivo.toPath())) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String line[];

                // Pula a primeira linha que contém o cabeçalho
                csvReader.readNext();

                // Lê cada linha do arquivo CSV e converte para um objeto Log
                while ((line = csvReader.readNext()) != null) {
                    listaLogs.add(
                        new Log(
                            line[0],    // Operação
                            line[1],    // Nome do contato
                            LocalDateTime.parse(line[2], Log.getDataHoraformatada()), // Data e hora
                            line[3]     // Nome do usuário
                        )
                    );
                }
            }
        } catch (IOException ex) {
            // Lança uma exceção se houver problemas ao abrir o arquivo
            throw new IOException("Falha ao abrir o arquivo: " + arquivo.getAbsoluteFile());
        } finally {
            // Exclui o arquivo de log após exportá-lo
            Files.delete(arquivo.toPath());
        }

        return listaLogs;  // Retorna a lista de logs
    }

    // Sobrescreve o método "toString" para retornar o nome do tipo de log (CSV)
    @Override
    public String toString() {
        return "CSV";  // Retorna "CSV" como o tipo de log
    }
}