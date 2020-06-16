/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpitic.sara.app;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author paulo
 */
@ManagedBean
@RequestScoped
public class cadastroBeans {

    public String cadastroFuncionarios(){
        return "cadastrarFuncionarios";
    }
    public String consultarFuncionarios(){
        return "consultarFuncionarios";
    }
    public String editarFuncionarios(){
        return "editarFuncionario";
    } 
    
}
