package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe responsável por representar uma entrada de log no sistema.
 * Um log contém informações sobre uma operação realizada, o nome do contato, 
 * a data e hora da operação e o nome do usuário que a executou.
 */
public class Log {
    private String operacao;    // Descrição da operação realizada
    private String nomeContato; // Nome do contato relacionado à operação
    private String dataHora;    // Data e hora da operação, formatada como uma string
    private String nomeUser;    // Nome do usuário autenticado que executou a operação
    
    // Formato padrão para a data e hora: "dd/MM/yyyy HH:mm:ss"
    private static DateTimeFormatter dataHoraformatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Construtor da classe Log que inicializa os atributos de um log
    public Log(String op, String nomeC, LocalDateTime dtHr, String nomeU) {
        this.operacao = op;  // Inicializa a operação realizada
        this.nomeContato = nomeC;  // Inicializa o nome do contato
        // Formata o LocalDateTime para uma string usando o formato padrão
        this.dataHora = dtHr.format(dataHoraformatada);  
        this.nomeUser = nomeU;  // Inicializa o nome do usuário
    }

    // Método para obter o nome do contato relacionado à operação
    public String getNomeContato() {
        return nomeContato;
    }

    // Método para obter o formato de data e hora atual (estático)
    public static DateTimeFormatter getDataHoraformatada() {
        return dataHoraformatada;
    }

    // Método estático para alterar o formato de escrita da data e hora
    public static void setFormatoEscritaDataHora(DateTimeFormatter formatoDataHora) {
        // Verifica se o novo formato não é nulo antes de alterar o formato global de data/hora
        if (formatoDataHora != null) {
            Log.dataHoraformatada = formatoDataHora;
        }
    }

    // Método para obter a data e hora da operação como string
    public String getDataHora() {
        return dataHora;
    }

    // Método para obter o nome do usuário que executou a operação
    public String getNomeUser() {
        return nomeUser;
    }

    // Método para obter a descrição da operação realizada
    public String getOperacao() {
        return operacao;
    }    
}
