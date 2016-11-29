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
public class Aluno extends Pessoa{
    private int numero;
    private String curso;
    private int anoMatric;
    private String regime;

    public Aluno(String nome, String email,int numero, String curso, int anoMatric, String regime) {
        super(nome, email);
        this.numero = numero;
        this.curso = curso;
        this.anoMatric = anoMatric;
        this.regime = regime;
    }
    @Override
    public String toString(){
        return nome+"   "+email+"   "+numero+"  "+curso+"   "+anoMatric+"   "+regime;
    }
}