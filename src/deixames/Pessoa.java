/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

import java.io.Serializable;

/**
 *
 * @author Proprietário
 */
public class Pessoa implements Serializable{
    protected String nome;
    protected String email;
    
    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

  
    
    
}
