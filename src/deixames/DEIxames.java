
package deixames;

import java.io.IOException;

public class DEIxames {

    public static void main(String[] args) throws IOException {
        // onde vai ficar o menu ou eventualmente a interface grafica
        GestorDEIxames g=new GestorDEIxames();
        g.loadPessoas("pessoas.txt");
    }
    
}
