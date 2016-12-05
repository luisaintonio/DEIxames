/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

import java.util.*;

/**
 *
 * @author fabio
 */
public abstract class Exame {
    protected Disciplina discp;
    protected Date data;
    protected int duracao;
    protected String Sala;
    protected Docente docResp;
    //protected String tipo;
    protected ArrayList<Docente> vigilantes;
    protected ArrayList<NDocente> funcionarios;
    protected HashMap<Aluno, Integer> alunosInscr;

    public Exame(Disciplina discp, Date data, int duracao, String Sala, Docente docResp) {
        this.discp = discp;
        this.data = data;
        this.duracao = duracao;
        this.Sala = Sala;
        this.docResp = docResp;
        //this.tipo = tipo;
        vigilantes = new ArrayList<Docente>();
        funcionarios = new ArrayList<NDocente>();
        alunosInscr = new HashMap<Aluno, Integer>();
    }
    
    //public abstract void setTipo(String tipo);
    public abstract void adicionaAluno(Aluno novo);
    
    

}
