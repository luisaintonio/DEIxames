/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

/**
 *
 * @author fabio
 */
public class NDocente extends Funcionario{
    private String cargo;

    public NDocente(String nome, String email, int numMec, String cargo) {
        super(numMec, nome, email);
        this.cargo = cargo;
    }

    @Override
    public void setCategoria(String categoria) {
        this.categoria=categoria; 
    }
    @Override
    public String toString(){
        return nome+"   "+email+"   "+numMec+"  "+cargo+"   "+categoria;
    }
}
