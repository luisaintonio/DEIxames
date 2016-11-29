/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

import java.io.*;
import static java.lang.System.exit;
import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class GestorDEIxames {
    
    
    private ArrayList<Pessoa> ListPessoas;
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
            fR.close();
        }catch (FileNotFoundException e){
            System.out.println("Erro ao abrir o ficheiro!");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Erro o ler do ficheiro!");
        }   
    }
    
}
