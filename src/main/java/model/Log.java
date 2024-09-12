/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Fpc
 */
public class Log {
    private String nomeContato; //nome do contato sob o qual a operação está sendo realizada
    private String dataHora;    //data atual do sistema operacional, no formato DD/MM/AAAA
    private String nomeUser;    //usuário autenticado no sistema
    private String operacao;    //Operações a serem registradas
    
    private static DateTimeFormatter dataHoraformatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public Log (String op, String nomeC, LocalDateTime dtHr, String nomeU){
        this.nomeContato = nomeC;
        this.dataHora = dtHr.format(dataHoraformatada);
        this.nomeUser = nomeU;
        this.operacao = op;
    }
    
    public String getNomeContato(){
        return nomeContato;
    }
    
    public static DateTimeFormatter getDataHoraformatada() {
        return dataHoraformatada;
    }
    
    public static void setFormatoEscritaDataHora(DateTimeFormatter formatoDataHora) {
        if (formatoDataHora != null) {
            Log.dataHoraformatada = formatoDataHora;
        }
    }
    
    public String getDataHora(){
        return dataHora;
    }
    
    public String getNomeUser(){
        return nomeUser;
    }
    
    public String getOperacao(){
        return operacao;
    }    
    
    
    
}
