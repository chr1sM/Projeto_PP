/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conference;

import estg.ipp.pt.tp02_conferencesystem.enumerations.PresentationState;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Participant;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Presentation;
import java.util.Objects;

/**
 * Nome: José Paulo de Magalhães Rodrigues
 * Numero: 8170322
 * Turma: LSIRC
 * 
 * Nome: Christopher Pereira Meder
 * Numero: 8170022
 * Turma: LSIRC
 */
public class Presentations implements Presentation {

    private static int idCount = 0;
    private int id, presentDuration;
    private String title;
    private PresentationState presentationState;
    private Participant participant;

    public Presentations(int presentDuration, String title, Participant participant) {
        setId(idCount++);
        this.presentDuration = presentDuration;
        this.title = title;
        this.presentationState = PresentationState.NOT_PRESENTED;
        this.participant = participant ;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getDuration() {
        return presentDuration;
    }

    @Override
    public PresentationState getPresentationState() {
        return presentationState;
    }

    @Override
    public void setPresented() {
        this.presentationState = PresentationState.PRESENTED;
    }

    @Override
    public Participant getPresenter() {
        return participant;
    }

    @Override
    public String toString() {
        return "\n\t\t" + title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Presentations other = (Presentations) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
    
    
}
