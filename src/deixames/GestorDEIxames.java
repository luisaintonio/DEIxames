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
    
    //leitura de um ficheiro de textp que contem toda a informaçao sobre Pessoas
    public void loadPessoas(String nome_fich) throws FileNotFoundException{
        FileReader frd = new FileReader(new File(nome_fich));
        ListPessoas = new ArrayList<Pessoa>();
        
        
        
    }
    
}
