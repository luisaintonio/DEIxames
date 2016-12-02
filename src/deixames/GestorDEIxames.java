/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deixames;

import java.io.*;
import static java.lang.Math.abs;
import static java.lang.Math.log10;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fabio
 */
public class GestorDEIxames {
    
    
    private ArrayList<Pessoa> listPessoas;
    private ArrayList<Curso> listCursos;
    //metodos 
    
    //leitura de um ficheiro de texto para um array, com toda a informaçao sobre Pessoas
    public void loadPessoas(String nome_fich) throws IOException {
        try{
            FileReader frd = new FileReader(new File(nome_fich));
            BufferedReader fR = new BufferedReader(frd);

            listPessoas = new ArrayList<Pessoa>();
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
                        listPessoas.add(novo);
                        break;
                    case "d":
                        //formato da linha:
                        //docente,nome,email,numeroMec,Area de Investigação
                        int numM = Integer.parseInt(linha[3]);
                        novo = new Docente(linha[1],linha[2],numM,linha[4]);
                        Docente d = (Docente) novo;
                        d.setCategoria(linha[5]);
                        listPessoas.add(d);
                        break;
                    case "n":
                        //formato da linha:
                        //NDocente,nome,email,numeroMec,Cargo
                        int numMec = Integer.parseInt(linha[3]);
                        novo = new NDocente(linha[1],linha[2],numMec,linha[4]);
                        NDocente nd = (NDocente) novo;
                        nd.setCategoria(linha[5]);
                        listPessoas.add(nd);
                        break;
                    default:
                        System.out.println("Erro: tipo inexistente!");
                }
      
            }
//          for(Pessoa p: listPessoas) System.out.println(p);
//            
   
            fR.close();
        }catch (FileNotFoundException e){
            System.out.println("Erro ao abrir o ficheiro "+nome_fich);
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Erro o ler do ficheiro "+nome_fich);
        }  
    }

    
//    public void loadCursos(String nome_fich) throws IOException, ClassNotFoundException{
//        try{
//            ObjectInputStream iS = new ObjectInputStream(new FileInputStream(nome_fich));
//            listCursos = new ArrayList<Curso>();
//            listCursos = (ArrayList<Curso>)iS.readObject();
//            
//            for(Curso c: listCursos) System.out.println(c);
//       
//            
//        }catch (FileNotFoundException e){
//            System.out.println("Erro ao abrir o ficheiro "+nome_fich);
//            e.printStackTrace();
//        }catch (IOException e){
//            System.out.println("Erro ao ler do ficheiro "+nome_fich);
//            e.printStackTrace();
//        }catch (ClassNotFoundException e){
//            System.out.println("Classe não encontrada em "+nome_fich);
//            e.printStackTrace();
//        }
//    }
    public Pessoa novoAluno(int tipo){
        //criar novo Pessoa e associar a ListPessoas
        //tipo 1 para aluno, 2 para docente, 3 para nao docente
        Pessoa novo;
        switch(tipo){
            case 1:
                //novo Aluno
                System.out.println("NOVO ALUNO:\n");
                
                Scanner sc = new Scanner(System.in);
                System.out.print("Nome: ");
                String nome = sc.next();
                System.out.print("Email: ");
                String email = sc.next();
                System.out.print("Numero de Estudante (10 digitos):");

                int check = 0;
                do{
                    check=0;
                    int num = sc.nextInt();
                    if(!verificaNum(num,10)){
                        check = 1;
                        System.out.println("O numero introduzido não é valido!");
                        break;
                    }else if(verificaNum(num,10) && procuraPessoa_num(num) != null){
                        check = 1;
                        System.out.println("O numero introduzido ja existe!");
                        break;
                    }    
                }while(check!=0);
                System.out.print("Ano de matricula: ");
                do{
                    check = 0;
                    int anoMt = sc.nextInt();
                    if(!verificaNum(anoMt,4) && anoMt > 2016){
                        check = 1;
                        System.out.println("Ano introduzido invalido, lembre-se que estamos em 2016.");     
                    }
                }while(check != 0);
                System.out.println("Curso: ");
                
                
            
                //novo = new Aluno();
                break;
            case 2:
                //novo Docente
                break;
            case 3:
                //NDocente
                break;
            default:
                System.out.println("Erro na função novoAluno(int tipo) : Pessoa");
                return null;
        }
        return novo;
    }
    public int numeroDigitos(int n){
        return (int) (log10(abs(n))+1);
    }
    public boolean verificaNum(int num, int digitos){
        //verifica se o num tem um certo numero de digitos e se é negativo
        if ((num<0) || (numeroDigitos(num) !=digitos)){
            return false;
        }else
            return true;
        
    }
    
    public Pessoa procuraPessoa_num(int numero){
        //procura pelo numero na lista de Pessoas e devolve a pessoa de encontrada, senao devolve null
        for(Pessoa p: listPessoas){
            if (p instanceof Aluno){
                Aluno a = (Aluno)p;
                if (numero == a.getNumero())
                    return a;
            }else if (p instanceof Docente){
                Docente d = (Docente)p;
                if (numero == d.numMec)
                    return d;
            }else if (p instanceof NDocente){
                NDocente nd = (NDocente)p;
                if(numero == nd.numMec)
                    return nd;
            }else
                return null;
        }
    }
    
    public void criaExame(){
        //metodo para criar exame e adicionalo a lista de exames
        
        /*NOTA::
        escolher um curso, dentro curso a disciplina,docenResp = docente da disciplina
        atribuimos docentes disponiveis
        sem e alunos e com notas a zero
        tipo = exame por realizar
        depois, quando inscrever aluno apenas aparecem os exames que ainda naão tem notas lançadas
        isto é depois de notas lançadas ja não é possivel increverAluno porque o exames ja esta realizado... assim não temos em conta datas o que é mais facil
        
        aparecem todas as disciplinas do curso do aluno e escolhendo a disciplina aperecem os exames disponiveis
        ao inscrever aluno colocamos o aluno no hashmap com a nota = 0
        */
        
    }

    public void savePessoas(String nome_fich) throws IOException{
        //guardar num ficheiro de texto linha a linha cada elemento do array de pessoas PROCURAR FUNÇÃO JOIN
        //StringJoiner sj = new StringJoiner(",");/*atravez de sj.add(elemt) adicionamos elementos a uma string separados por vilgula*/
        try{
            BufferedWriter fW = new BufferedWriter(new FileWriter(nome_fich));
            
            String[] novaLinha;
            for (Pessoa p: listPessoas){
                if (p instanceof Aluno){
                    StringJoiner sj = new StringJoiner(",");/*atravez de sj.add(elemt) adicionamos elementos a uma string separados por vilgula*/
                    Aluno a = (Aluno)p;
                    /*perguntar ao stor qual é o acesso a uma var protected*/
                    String numero = Integer.toString(a.getNumero());
                    String anoM = Integer.toString(a.getAnoMatric());

                    novaLinha = new String[]{"a",a.nome,a.email,numero,a.getCurso(),anoM,a.getRegime()};
                    for(String e:novaLinha) sj.add(e);
                    //porque o StringJOIner nao é uma String
                    String j = sj.toString();
                    fW.write(j, 0, j.length());
                    fW.newLine();
                    
                }else if(p instanceof Docente){
                    StringJoiner sj = new StringJoiner(",");
                    Docente d = (Docente)p;

                    String numMec= Integer.toString(d.numMec);

                    novaLinha = new String[]{"d",d.nome,d.email,numMec,d.getAreaInvestig(),d.categoria};
                    for(String e:novaLinha) sj.add(e);

                    String j = sj.toString();
                    fW.write(j, 0, j.length());
                    fW.newLine();
                }else if (p instanceof NDocente){
                    StringJoiner sj = new StringJoiner(",");
                    NDocente nd = (NDocente)p;

                    String numMec= Integer.toString(nd.numMec);

                    novaLinha = new String[]{"n",nd.nome,nd.email,numMec,nd.getCargo(),nd.categoria};
                    for(String e:novaLinha) sj.add(e);

                    String j = sj.toString();
                    fW.write(j, 0, j.length());
                    fW.newLine();
                }
            }
            fW.close(); 
        }catch (FileNotFoundException e){
            System.out.println("Erro ao abrir o ficheiro "+nome_fich);
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Erro ao escrever do ficheiro "+nome_fich);
            e.printStackTrace();
        } 
 }
    //criar apenas algumas disciplinas para atribuir aos cursos e guardar num ficheiro
    public void save_curso(){
    Docente d1 = (Docente) listPessoas.get(15);
    Docente d2 = (Docente) listPessoas.get(12);
    Docente d3 = (Docente) listPessoas.get(13);
    Docente d4 = (Docente) listPessoas.get(14);
    Docente d5 = (Docente) listPessoas.get(16);
    Docente d6 = (Docente) listPessoas.get(17);
    Docente d7 = (Docente) listPessoas.get(18);
    Docente d8 = (Docente) listPessoas.get(19);
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
    
    listCursos = new ArrayList<Curso>();
    listCursos.add(enginf);
    listCursos.add(desmul);
    
    //imorimir cursos
    listCursos.forEach((c) -> {System.out.println(c);});

    try {
        FileOutputStream os = new FileOutputStream("ficheiros\\cursos.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(listCursos);
        }
    }catch (FileNotFoundException ex) {
        System.out.println("Ficheiro não encontrado");
    }catch (IOException ex) {
        System.out.println("Erro ao escrever no ficheiro");
    }
}

}
