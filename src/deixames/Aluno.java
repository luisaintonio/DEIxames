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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getAnoMatric() {
        return anoMatric;
    }

    public void setAnoMatric(int anoMatric) {
        this.anoMatric = anoMatric;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
