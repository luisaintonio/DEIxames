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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fabio
 */
public class GestorDEIxames {
    
    String[] salas = {"C5.1","C5.2","C5.3","G4.1","G4.2","G4.3","G4.4","E6.1","E6.2","E6.3"};
    private ArrayList<Pessoa> listPessoas;
    private ArrayList<Curso> listCursos;
    private ArrayList<Exame> listExames;
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

    
    public void loadCursos(String nome_fich) throws IOException, ClassNotFoundException{
        try{
            ObjectInputStream iS = new ObjectInputStream(new FileInputStream(nome_fich));
            listCursos = (ArrayList<Curso>)iS.readObject();
//            for(Curso c: listCursos) System.out.println(c);
            
        }catch (FileNotFoundException e){
            System.out.println("Erro ao abrir o ficheiro "+nome_fich);
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Erro ao ler do ficheiro "+nome_fich);
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada em "+nome_fich);
            e.printStackTrace();
        }
    }
    public void loadExames(String nome_fich) throws IOException, ClassNotFoundException{
        try{
            ObjectInputStream iS = new ObjectInputStream(new FileInputStream(nome_fich));
            listExames = (ArrayList<Exame>)iS.readObject();
//            for (Exame d:listExames)
//                System.out.println(d);
        }catch (FileNotFoundException e){
            System.out.println("Erro ao abrir o ficheiro "+nome_fich);
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Erro ao ler do ficheiro "+nome_fich);
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada em "+nome_fich);
            e.printStackTrace();
        }
    }
    
    public Pessoa novoPessoa(int tipo){
        //criar novo Pessoa e associar a ListPessoas
        //tipo 1 para aluno, 2 para docente, 3 para nao docente
        
        /*criar um limite de tentativas que quando excedido termina a função e devolve null
        apresentar tambme varios tipos de regime e opção outroe que permite introduzir outra opção*/
        
        Pessoa novo;
        switch(tipo){
            case 1:
                //novo Aluno
                System.out.println("NOVO ALUNO:\n");
                
                Scanner sc = new Scanner(System.in);
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Numero de Estudante (10 digitos):");

                int check = 0, num =0;
                do{
                    check=0;
                    num = sc.nextInt();
                    if(!verificaNum(num,10)){
                        check = 1;
                        System.out.println("O numero introduzido não é valido!"); 
                    }else if(verificaNum(num,10) && procuraPessoa_num(num) != null){
                        check = 1;
                        System.out.println("O numero introduzido ja existe!");
                    }    
                }while(check!=0);
                System.out.print("Ano de matricula: ");
                int anoMt = 0;
                do{
                    check = 0;
                    anoMt = sc.nextInt();
                    if(!verificaNum(anoMt,4) || anoMt > 2016){
                        check = 1;
                        System.out.println("Ano introduzido invalido, lembre-se que estamos em 2016.");     
                    }
                }while(check != 0);
                System.out.println("Cursos: ");
                listCursos.forEach((c) -> {System.out.println("     "+c.getNome());});
                System.out.print("\nDigite o nome do curso que pertende: ");
                String nomeCurso = new String();
                Curso curso;
                do{
                    check=0;
                    nomeCurso = sc.nextLine();
                    if ((curso = procuraCurso(nomeCurso)) == null){
                        check = 1;
                        System.out.println("Opção invalidada, Tente de novo.");
                    }
                }while(check !=0);
                
                System.out.print("Regime: ");
                String regime = sc.nextLine();

                novo = new Aluno(nome,email,num,nomeCurso,anoMt,regime);
                curso.addAluno((Aluno) novo);
                break;
//            case 2:
//                //novo Docente
//                break;
//            case 3:
//                //NDocente
//                break;
            default:
                System.out.println("Erro na função novoAluno(int tipo) : Pessoa");
                return null;
        }
        listPessoas.add(novo);
        return novo;
    }
    public Curso procuraCurso(String nC){
        //procura um curso atraves no nome do curso passado por parametro
        for(Curso c: listCursos){
            if (Objects.equals(nC, c.getNome()))
                return c;
        }
        return null;
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
                }
        }return null;
    }
    
    public void criaExame(){
        //metodo para criar exame e adicionalo a lista de exames
        System.out.println("NOVO EXAME\n\nEscolha o tipo de exame que pretende crair:");
        System.out.print("     1) Exame Época Normal\n     2) Exame Época de Recurso\n     3) Exames Época Especial\n Opção: ");
        Exame novo = null;
        Scanner sc = new Scanner(System.in);
        int check=0;
        int op = 0;
        do{
            check=0;
            op = sc.nextInt();
            if (op!=1 && op!=2 && op!=3){
                check = 1;
                System.out.println("Opção invalida. Tente de novo.");
            }
        }while(check!=0);
        
        //codigo que pede informações sobre o exame
        System.out.println("\nCursos: ");
        listCursos.forEach((c) -> {System.out.println("     "+c.getNome());});
        String nomeCurso = new String();
        Curso curso;
        sc.nextLine(); /*limpar buffer do scanner*/
        do{
            System.out.print("\nDigite o nome do curso que pertende: ");
            check=0;
            nomeCurso = sc.nextLine();
            if ((curso = procuraCurso(nomeCurso)) == null){
                check = 1;
                System.out.println("Opção invalida. Tente de novo.");
            }
        }while(check !=0);
        
        System.out.println("\nCurso: "+curso.getNome());
        
        //imprime o nome de todas as disciplinas
        (curso.getListDiscp()).forEach((discp) -> {System.out.println("    "+discp.getNome());});
        Disciplina d;
        String nomeDiscp = new String();
        do{
            System.out.print("\nEscolha a disciplina para a qual pretende criar um novo exame: ");
            check = 0;
            nomeDiscp = sc.nextLine();
            if((d=curso.procuraDisp(nomeDiscp)) == null){
                check=1;
                System.out.println("Opção invalida. Tente de novo.");
            }
        }while(check!=0);
        
        //pedir data e hora do exame
        String strData = new String();
        Date data;
        do{
            System.out.print("\nData e duracao (dd/mm/aaaa hh:mm): ");
            check=0;
            strData = sc.nextLine();
            if((data=leData(strData)) == null){
                check = 1;
                System.out.println("Erro ao ler a data.");
            }
        }while(check!=0);  

        System.out.print("\nDuração da prova (minutos): ");
        int duracao = sc.nextInt();
        
        System.out.println("Salas:");
        //apresentamos apenas as salas disponiveis tendo em conta a hora de realização e a 
        for(String sala: salas){
            if(!verificaSala(sala, data, duracao))
                System.out.println("    - "+sala);
        }
        String sala;
         do{
            System.out.print("\nEscolha a sala: ");
            check=1;
            sala = sc.next();
            for(String s: salas){
                 if(Objects.equals(s, sala)){
                    check = 0;
                }
            } 
            if(check == 1)
                System.out.println("Sala invalida. Tente de novo.");
        }while(check!=0);  
        
        Docente dResp = d.getDocResp();
        
        switch(op){
            case 1:
                //exame normal
                novo = new ExNormal(d,data,duracao,sala,dResp);
                
                break;
            case 2:
                //epoca recurso
                novo = new ExRecurso(d,data,duracao,sala,dResp);
                break;
            case 3:
                //epoca especial
                novo = new ExEspecial(d,data,duracao,sala,dResp);
                break;     
        }
        //associar vigilantes e funcionarios
        System.out.println("\nASSOCIAR VIGILANTES\n");
        
        
        
        //confirmar
        System.out.println("\nTem a certeza que pretende criar o seguinte novo exame: (< S - sim > < N - nao >)  "); 
        System.out.println("\n\t"+novo+"\n");
        String sConf;
        do{
            System.out.print("Opção: ");
            check=0;
            sConf = sc.next();
            if ((!Objects.equals(sConf, "S")) && (!Objects.equals(sConf, "N"))){
                check = 1;
                System.out.println("Opcao invalida. Tente de novo.");
            }
        }while(check!=0);
        if(Objects.equals(sConf, "S")){
            listExames.add(novo);
            System.out.println("\tExame criado com sucesso.");
        }
        else
            System.out.println("\tCrição de exame cancelado.");
    }
    public Date leData(String d){
        //converte uma string com a data num determinado formato num objeto date
        try {
            Date data = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(d);
            return data;
        } catch (ParseException ex) {
            //Logger.getLogger(GestorDEIxames.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public boolean verificaSala(String s,Date iniD, int dur){
        Calendar cal = Calendar.getInstance();
        
        //FIM DO NOVO EXAME
        cal.setTime(iniD);
        cal.add(Calendar.MINUTE, dur);
        Date fimD = cal.getTime();
        
        for(Exame ex: listExames){
            if ((ex.data.getYear() == iniD.getYear()) && (ex.data.getMonth() == iniD.getMonth()) && (ex.data.getDate() == iniD.getDate())){
                 if(Objects.equals(ex.Sala, s)){
                    //FIM DO EXAME NO MESMO DIA E MESMA SALA
                    cal.setTime(ex.data);
                    cal.add(Calendar.MINUTE, ex.duracao);
                    Date fimEx = cal.getTime();
                    
                    if (overlapTime(iniD, fimD, ex.data, fimEx)){
                        return true; //sala ocupada
                    }
                }
            }  
        }
        return false;
    }
    
    public boolean overlapTime(Date ini1, Date fim1, Date ini2, Date fim2){
        //comparar se 2 intervalos de tempo se intercetam
        //date1.compareTo(date2) < 0 date1 antes date2, senão depois
        if((ini1.compareTo(fim2) < 0) && (fim1.compareTo(ini2) > 0)){
            return true;
        }else
            return false;
    } 
      
    
    public void saveCursos(String nome_fich) throws IOException{
        try {
            FileOutputStream os = new FileOutputStream("ficheiros\\cursos.dat");
            try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
                oos.writeObject(listCursos);
            }
        }catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado (cursos.dat)");
        }catch (IOException ex) {
            System.out.println("Erro ao escrever no ficheiro (cursos.dat)");
        }
    }
    
    public void saveExames(String nome_fich) throws IOException{
        try {
            FileOutputStream os = new FileOutputStream("ficheiros\\exames.dat");
            try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
                oos.writeObject(listExames);
            }
        }catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado (exames.dat)");
        }catch (IOException ex) {
            System.out.println("Erro ao escrever no ficheiro (exames.dat(");
        }       
    }

    public void savePessoas(String nome_fich) throws IOException{
        //guardar num ficheiro de texto linha a linha cada elemento do array de pessoas FUNÇÃO: STRINGJOINER
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
                    //porque o StringJoiner nao é uma String
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

//    //criar apenas algumas disciplinas para atribuir aos cursos e guardar num ficheiro
//    public void curso(){
//    Docente d1 = (Docente) listPessoas.get(15);
//    Docente d2 = (Docente) listPessoas.get(12);
//    Docente d3 = (Docente) listPessoas.get(13);
//    Docente d4 = (Docente) listPessoas.get(14);
//    Docente d5 = (Docente) listPessoas.get(16);
//    Docente d6 = (Docente) listPessoas.get(17);
//    Docente d7 = (Docente) listPessoas.get(18);
//    Docente d8 = (Docente) listPessoas.get(19);
//    Docente[] docts = new Docente[3];
//    Disciplina i1 = new Disciplina("Multimedia",d1,docts);
//    Disciplina i2 = new Disciplina("Programacao Orientada a Objetps",d2,docts);
//    Disciplina i3 = new Disciplina("Sistemas Operativos",d3,docts);
//    Disciplina i4 = new Disciplina("Engenharia de Software",d4,docts);
//    
//    Disciplina i5 = new Disciplina("Multimedia",d1,docts);
//    Disciplina i6 = new Disciplina("Programacao Orientada a Objetos",d2,docts);
//    Disciplina i7 = new Disciplina("Sistemas Operativos",d3,docts);
//    Disciplina i8 = new Disciplina("Engenharia de Software",d4,docts);
//
//    Curso enginf = new Curso("Engenharia Informatica",3,"Licenciatura");
//    Curso desmul = new Curso("Design e Multimedia",3,"Licenciatura");
//
//
//    enginf.addDiscp(i1);
//    enginf.addDiscp(i2);
//    enginf.addDiscp(i3);
//    enginf.addDiscp(i4);
//    
//    desmul.addDiscp(i5);
//    desmul.addDiscp(i6);
//    desmul.addDiscp(i7);
//    desmul.addDiscp(i8);
//    
//    listCursos = new ArrayList<Curso>();
//    listCursos.add(enginf);
//    listCursos.add(desmul);
//    
//    //adicionar alunos a partir do array
//    for(Pessoa p: listPessoas){
//        if (p instanceof Aluno){
//            String nC = ((Aluno) p).getCurso();
//            Curso c = procuraCurso(nC);
//            c.addAluno((Aluno) p);
//        }
//    }
////    i1.printAlunos();
////    //imorimir cursos
////    listCursos.forEach((c) -> {System.out.println(c);});
//    try {
//        FileOutputStream os = new FileOutputStream("ficheiros\\cursos.dat");
//        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
//            oos.writeObject(listCursos);
//        }
//    }catch (FileNotFoundException ex) {
//        System.out.println("Ficheiro não encontrado");
//    }catch (IOException ex) {
//        System.out.println("Erro ao escrever no ficheiro");
//    }
//}
//
//        //so para inicializar o ficheiro dos exames
//    public void exame(){
//        listExames = new ArrayList<Exame>();
//        try {
//        FileOutputStream os = new FileOutputStream("ficheiros\\exames.dat");
//            try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
//                oos.writeObject(listExames);}
//        }catch (FileNotFoundException ex) {
//            System.out.println("Ficheiro não encontrado");
//        }catch (IOException ex) {
//            System.out.println("Erro ao escrever no ficheiro");
//        }
//    }
}
