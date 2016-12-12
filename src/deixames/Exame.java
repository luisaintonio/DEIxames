/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author fabio
 */
public abstract class Exame implements Serializable{
    protected Disciplina discp;
    protected Date data;
    protected int duracao;
    protected String Sala;
    protected Docente docResp;
    protected String estado = "PENDENTE";//estado de realização d exame, POR DEFINIÇÃO ESTA PENDENTE
    protected ArrayList<Docente> vigilantes;
    protected ArrayList<NDocente> funcionarios;
    protected HashMap<Aluno, Integer> alunosInscr;

    public Exame(Disciplina discp, Date data, int duracao, String Sala, Docente docResp) {
        this.discp = discp;
        this.data = data;
        this.duracao = duracao;
        this.Sala = Sala;
        this.docResp = docResp;
        
        vigilantes = new ArrayList<Docente>();
        funcionarios = new ArrayList<NDocente>();
        alunosInscr = new HashMap<Aluno, Integer>();
    }
    //metodos
//
//    public int setEstado(String estado) {
//        if (Objects.equals(estado,"REALIZADO")){
//            this.estado = estado;
//            return 1;
//        }else
//            return 0;
//    }

    public void setEstado(){
        this.estado = "REALIZADO";
    }

    public String getEstado() {
        return estado;
    }
    
    public void associaVigilante(Docente novo){
        vigilantes.add(novo);
    }
    //associaFuncionario(NDocente);
    
    public abstract void adicionaAluno(Aluno novo);//add ao hashMap e poe a sua nota a zero.
    
    

}
