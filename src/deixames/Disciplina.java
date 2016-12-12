/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class Disciplina implements Serializable{
    private String nome;
    private Docente docResp;
    private Docente [] docts;
    private ArrayList <Integer> alunosInscr; //numero de aluno

    public Disciplina(String nome, Docente docResp, Docente[] docts) {
        this.nome = nome;
        this.docResp = docResp;
        this.docts = docts;
        alunosInscr = new ArrayList <Integer>();
    }
    
}
