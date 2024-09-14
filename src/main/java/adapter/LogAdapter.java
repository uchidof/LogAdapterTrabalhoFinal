package adapter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Log;

/**
 * Classe abstrata que define o contrato para adaptadores de log.
 * Implementa o padrão Adapter para fornecer uma interface comum para diferentes formatos de log.
 */
public abstract class LogAdapter {
    protected File arquivo;  // Arquivo onde os logs serão gravados ou lidos

    // Construtor que recebe o arquivo de log como parâmetro
    public LogAdapter(File arquivo) {
        if (arquivo == null) {  // Verifica se o arquivo fornecido é nulo
            throw new NullPointerException("Erro: Arquivo NULO.");  // Lança uma exceção se o arquivo for nulo
        }
        this.arquivo = arquivo;  // Inicializa o atributo arquivo
    }

    /**
     * Método abstrato para gravar logs no arquivo.
     * Deve ser implementado por classes concretas que estendem LogAdapter.
     * 
     * @param log Um ou mais objetos do tipo Log a serem gravados no arquivo
     * @throws IOException Se ocorrer um erro ao escrever no arquivo
     */
    public abstract void escrever(Log... log) throws IOException;

    /**
     * Método abstrato para exportar os logs do arquivo.
     * Deve ser implementado por classes concretas que estendem LogAdapter.
     * 
     * @return Uma lista de objetos Log que foram exportados do arquivo
     * @throws IOException Se ocorrer um erro ao ler o arquivo
     */
    public abstract List<Log> exportarLogs() throws IOException;
}
