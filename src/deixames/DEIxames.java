
package deixames;

import java.io.IOException;
import java.util.Date;

public class DEIxames {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // onde vai ficar o menu ou eventualmente a interface grafica
        GestorDEIxames g=new GestorDEIxames();
        g.loadPessoas("ficheiros\\pessoas.txt");
        
        //g.curso();//cria e escreve ficheiro de objetos
        g.loadCursos("ficheiros\\cursos.dat"); //le ficheiros de objectos
        
        //g.novoPessoa(1);
        //g.criaExame();
        //g.savePessoas("ficheiros\\pessoas.txt");
        //g.saveCursos("ficehiros\\cursos.dat");
       
       
    }
    
}
