/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conference;

import estg.ipp.pt.tp02_conferencesystem.interfaces.Participant;

/**
 * Nome: José Paulo de Magalhães Rodrigues
 * Numero: 8170322
 * Turma: LSIRC
 * 
 * Nome: Christopher Pereira Meder
 * Numero: 8170022
 * Turma: LSIRC
 */
public abstract class Participants implements Participant {

    private static int idCount = 0;
    private int id;
    private String nameParticipants, bio;

    /**
     * Cria o Construtor dos Participantes
     * @param nameParticipants nome dos participantes
     * @param bio cria a descricao dos Participantes
     */
    public Participants(String nameParticipants, String bio) {
        setId(idCount++);
        this.nameParticipants = nameParticipants;
        this.bio = bio;
    }

    /**
     * Auto incrementa os ids dos Participantes
     * @param id 
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public String getName() {
        return nameParticipants;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public String getBio() {
        return bio;
    }

    /**
     * Compara os ids dos Participantes
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Participants)) {
            return false;
        }
        final Participants other = (Participants) obj;
        return this.id == other.id;
    }

    /**
     * Retorna a toString dos Participantes
     * @return 
     */
    @Override
    public String toString() {
        return "Participants:\n" + "ID: " + id + ", Participant Name: " + nameParticipants + ", Bio: " + bio;
    }
    
    
}
