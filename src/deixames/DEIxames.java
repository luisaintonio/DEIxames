
package deixames;

import java.io.IOException;

public class DEIxames {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // onde vai ficar o menu ou eventualmente a interface grafica
        GestorDEIxames g=new GestorDEIxames();
        g.loadPessoas("ficheiros\\pessoas.txt");
        g.curso();
        //g.novoPessoa(1);
        g.savePessoas("ficheiros\\pessoas.txt");
       
        //g.loadCursos("ficheiros\\cursos.dat");
    }
    
}
