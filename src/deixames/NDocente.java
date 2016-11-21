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

    public NDocente(String cargo, int numMec, String nome, String email) {
        super(numMec, nome, email);
        this.cargo = cargo;
    }

    @Override
    public void setCategoria(String categoria) {
        this.categoria=categoria; 
    }
}
