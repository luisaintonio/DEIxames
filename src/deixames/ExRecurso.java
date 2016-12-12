/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

import java.util.Date;

/**
 *
 * @author fabio
 */
public class ExRecurso extends Exame{

    public ExRecurso(Disciplina discp, Date data, int duracao, String Sala, Docente docResp) {
        super(discp, data, duracao, Sala, docResp);
    }

    @Override
    public void adicionaAluno(Aluno novo) {
        
    }
     @Override
    public String toString(){
        return "Exame Época Recurso de "+discp.getNome()+". A realizar a "+data.getDate()+"/"+data.getMonth()+"/"+data.getYear()+" às "+data.getHours()+":"+data.getMinutes()+" na sala "+Sala;
    }
    
}
    

