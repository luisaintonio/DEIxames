/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

import java.io.*;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class GestorDEIxames {
    
    
    private ArrayList<Pessoa> ListPessoas;
    private ArrayList<Curso> ListCursos;
    //metodos 
    
    //leitura de um ficheiro de texto para um array, com toda a informaçao sobre Pessoas
    public void loadPessoas(String nome_fich) throws IOException {
        try{
            FileReader frd = new FileReader(new File(nome_fich));
            BufferedReader fR = new BufferedReader(frd);

            ListPessoas = new ArrayList<Pessoa>();
            String str;
            Pessoa novo;
            while((str=fR.readLine())!= null){
                String[] linha = str.split(",");
                /*O primeiro carater de cada linha do ficheiro de texto corresponde ao tipo de Pessoa que estamos a ler (a - Aluno , d - Docente , n - Funcionario(não docente)*/
                String tipo =linha[0];
                switch(tipo){
                    case "a":
                        //formato da linha:
                        //aluno,nome,email,numero,curso,anoMatricula,regime
                        int numero = Integer.parseInt(linha[3]);
                        int anoM = Integer.parseInt(linha[5]);
                        novo = new Aluno(linha[1],linha[2],numero,linha[4],anoM,linha[6]);
                        ListPessoas.add(novo);
                        break;
                    case "d":
                        //formato da linha:
                        //docente,nome,email,numeroMec,Area de Investigação
                        int numM = Integer.parseInt(linha[3]);
                        novo = new Docente(linha[1],linha[2],numM,linha[4]);
                        Docente d = (Docente) novo;
                        d.setCategoria(linha[5]);
                        ListPessoas.add(d);
                        break;
                    case "n":
                        //formato da linha:
                        //NDocente,nome,email,numeroMec,Cargo
                        int numMec = Integer.parseInt(linha[3]);
                        novo = new NDocente(linha[1],linha[2],numMec,linha[4]);
                        NDocente nd = (NDocente) novo;
                        nd.setCategoria(linha[5]);
                        ListPessoas.add(nd);
                        break;
                    default:
                        System.out.println("Erro: tipo inexistente!");
                }
      
            }
//            for(Pessoa p: ListPessoas){
//                System.out.println(p);
//            }
   
            fR.close();
        }catch (FileNotFoundException e){
            System.out.println("Erro ao abrir o ficheiro!");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Erro o ler do ficheiro!");
        }  
    }
    
    //criar apenas algumas disciplinas para atribuir aos cursos
    //design e mult

    //Disciplina d1 = new Disciplina('Desenho e Representação',);
void curso(){
    Docente d1 = (Docente) ListPessoas.get(15);
    Docente d2 = (Docente) ListPessoas.get(12);
    Docente d3 = (Docente) ListPessoas.get(13);
    Docente d4 = (Docente) ListPessoas.get(14);
    Docente d5 = (Docente) ListPessoas.get(16);
    Docente d6 = (Docente) ListPessoas.get(17);
    Docente d7 = (Docente) ListPessoas.get(18);
    Docente d8 = (Docente) ListPessoas.get(19);
    Docente[] docts = new Docente[3];
    Disciplina i1 = new Disciplina("Multimedia",d1,docts);
    Disciplina i2 = new Disciplina("Programacao Orientada a Objetps",d2,docts);
    Disciplina i3 = new Disciplina("Sistemas Operativos",d3,docts);
    Disciplina i4 = new Disciplina("Engenharia de Software",d4,docts);
    
    Disciplina i5 = new Disciplina("Multimedia",d1,docts);
    Disciplina i6 = new Disciplina("Programacao Orientada a Objetps",d2,docts);
    Disciplina i7 = new Disciplina("Sistemas Operativos",d3,docts);
    Disciplina i8 = new Disciplina("Engenharia de Software",d4,docts);

    Curso enginf = new Curso("Engenharia Informatica",3,"Licenciatura");
    Curso desmul = new Curso("Design e Multimedia",3,"Licenciatura");


    enginf.addDiscp(i1);
    enginf.addDiscp(i2);
    enginf.addDiscp(i3);
    enginf.addDiscp(i4);
    
    desmul.addDiscp(i5);
    desmul.addDiscp(i6);
    desmul.addDiscp(i7);
    desmul.addDiscp(i8);
    
    ListCursos = new ArrayList<Curso>();
    ListCursos.add(enginf);
    ListCursos.add(desmul);
    

    for(Curso c: ListCursos)
        System.out.println(c);
       

    try {
        FileOutputStream os = new FileOutputStream("cursos.dat");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        
        oos.writeObject(ListCursos);
    }catch (FileNotFoundException ex) {
        System.out.println("Ficheiro não encontrado");;
    }catch (IOException ex) {
        System.out.println("Erro ao escrever no ficheiro");
    }
    }

}
