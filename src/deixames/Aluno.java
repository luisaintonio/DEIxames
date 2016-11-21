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
    private Curso curso;
    private int anoMatric;
    private String regime;

    public Aluno(int numero, Curso curso, int anoMatric, String regime, String nome, String email) {
        super(nome, email);
        this.numero = numero;
        this.curso = curso;
        this.anoMatric = anoMatric;
        this.regime = regime;
    }
}