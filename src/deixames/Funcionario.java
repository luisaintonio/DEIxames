/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

/**
 *
 * @author Proprietário
 */
public abstract class Funcionario extends Pessoa {
    protected int numMec;
    protected String categoria;

    public Funcionario(int numMec, String nome, String email) {
        super(nome, email);
        this.numMec = numMec;
    }

    public abstract void setCategoria(String categoria);
    
    
 }
