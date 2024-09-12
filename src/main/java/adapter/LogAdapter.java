/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Log;

/**
 *
 * @author Fpc
 */
public abstract class LogAdapter {
    protected File arquivo;
    
    public LogAdapter(File arquivo){
        if(arquivo == null){
            throw new NullPointerException("Erro: Arquivo NULO.");
        }
        this.arquivo = arquivo;
    }
    
    public abstract void escrever(Log... log) throws IOException;
    
    public abstract List<Log> exportarLogs() throws IOException;
    
}
