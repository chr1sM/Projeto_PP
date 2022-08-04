/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conference;

import Enumeration.GrauEscolaridade;

/**
 * Nome: José Paulo de Magalhães Rodrigues
 * Numero: 8170322
 * Turma: LSIRC
 * 
 * Nome: Christopher Pereira Meder
 * Numero: 8170022
 * Turma: LSIRC
 */
public class Professor extends Participants{

    private String expert;
    private GrauEscolaridade grau;

    /**
     * Cria um Construtor do Professor
     * @param expert
     * @param nameParticipants
     * @param bio
     * @param grau 
     */
    public Professor(String expert, String nameParticipants, String bio, GrauEscolaridade grau) {
        super(nameParticipants, bio);
        this.grau = grau;
        this.expert = expert;
    }

    /**
     * Retorna a expertise do professor
     * @return 
     */
    public String getExpert() {
        return expert;
    }

    /**
     * Da set a expertise do Professor
     * @param expert 
     */
    public void setExpert(String expert) {
        this.expert = expert;
    }

    /**
     * Retorna o grau de escolaridade do professor
     * @return 
     */
    public GrauEscolaridade getGrau() {
        return grau;
    }

    /**
     * Da set ao grau de escolaridade do professor
     * @param grau 
     */
    public void setGrau(GrauEscolaridade grau) {
        this.grau = grau;
    }

    /**
     * Retorna uma toString do professor
     * @return 
     */
    @Override
    public String toString() {
        return super.toString() + "Professor:\n" + "Expert: " + expert + ", Grau: " + grau;
    }
}
