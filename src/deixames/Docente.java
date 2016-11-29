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
    private String areaInvestig;

    public Docente( String nome, String email, int numMec,String areaInvetig) {
        super(numMec, nome, email);
        this.areaInvestig = areaInvetig;
    }

    @Override
    public void setCategoria(String categoria) {
        this.categoria=categoria; 
    }
    @Override
    public String toString(){
        return nome+"   "+email+"   "+numMec+"  "+areaInvestig+"   "+categoria;
    }
}
