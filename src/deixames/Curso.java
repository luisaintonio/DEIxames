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
public class Curso implements Serializable {
    private String nome;
    private int duracaoAnos;
    private String grau;
    private ArrayList <Disciplina> listDiscp;

    public Curso(String nome, int duracaoAnos, String grau) {
        this.nome = nome;
        this.duracaoAnos = duracaoAnos;
        this.grau = grau;
        listDiscp = new ArrayList<Disciplina>();
    }

    public String getNome() {
        return nome;
    }
    
    public void addDiscp(Disciplina d){
        listDiscp.add(d);
    }
    
    public String toString(){
        return nome+"   "+duracaoAnos+"     "+grau;
    }
}
