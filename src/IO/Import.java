/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IO;

import estg.ipp.pt.tp02_conferencesystem.interfaces.Conference;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Nome: José Paulo de Magalhães Rodrigues
 * Numero: 8170322
 * Turma: LSIRC
 * 
 * Nome: Christopher Pereira Meder
 * Numero: 8170022
 * Turma: LSIRC
 */
public class Import {

    public Import() {
    }

    public void importData(Conference conference, String file) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(file);
        int i;
        System.out.println("Formato JSON importado com sucesso do ficheiro: " + file);
        while ((i = fr.read()) != -1) {
            System.out.print((char) i);
        }
        fr.close();
    }
}
