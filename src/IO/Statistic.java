/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IO;

import estg.ipp.pt.tp02_conferencesystem.io.interfaces.Statistics;

/**
 * Nome: José Paulo de Magalhães Rodrigues
 * Numero: 8170322
 * Turma: LSIRC
 * 
 * Nome: Christopher Pereira Meder
 * Numero: 8170022
 * Turma: LSIRC
 */
public class Statistic implements Statistics {
    
    private String session;
    double value;

    public Statistic(String name, double count) {
        this.session = name;
        this.value = count;

    }

    @Override
    public String getDescription() {
        return session;
    }

    @Override
    public double getValue() {
        return value;
    }
    
}
