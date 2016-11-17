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
public class Docente extends Funcionario {
    private String areaInvetig;

    public Docente(String areaInvetig, int numMec, String nome, String email) {
        super(numMec, nome, email);
        this.areaInvetig = areaInvetig;
    }

    @Override
    public void setCategoria(String categoria) {
        this.categoria=categoria; 
    }
    
    
}
