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

    public ExRecurso(Disciplina discp, Date data, int duracao, String Sala, Docente docResp, String tipo) {
        super(discp, data, duracao, Sala, docResp, tipo);
    }

    @Override
    public void setTipo(String tipo) {
        
    }

    @Override
    public void adicionaAluno(Aluno novo) {
        
    }
    
}
    

