/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conference;

import estg.ipp.pt.tp02_conferencesystem.enumerations.PresentationState;
import estg.ipp.pt.tp02_conferencesystem.exceptions.SessionException;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Participant;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Presentation;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Room;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Session;
import java.time.Duration;
import java.time.LocalDateTime;
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
public class Sessions implements Session{

    private static int idCount = 0;
    private static final int MAX = 5;
    private int id, numPresentations, presentationDur;
    private LocalDateTime startSession, endSession;
    private String nameSession, theme;
    private Room room;
    private Presentation[] presentations;

    /**
     * Cria o Construtor da Sessao
     * @param nameSession nome da Sessao
     * @param theme Tema da Sessao
     * @param room sala a ser adicionada a Sessao
     * @param startSession inicio da Sessao
     */
    public Sessions(String nameSession, String theme, Room room, LocalDateTime startSession) {
        setId(idCount++);
        this.startSession = startSession;
        this.nameSession = nameSession;
        this.theme = theme;
        this.room = room;
        this.presentationDur = 30;
        this.presentations = new Presentation[MAX];
    }

    /**
     * Auto incrementa o id da sessao
     * @param id 
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Encontra Apresentacoes se os titulos forem iguais
     * @param prsntn titulo da apresentacao
     * @return 
     */
    private int findPresentation(Presentation prsntn) {
        int pos = -1;

        for (int i = 0; i < numPresentations; i++) {
            if (((Presentations)this.presentations[i]).equals(prsntn)) {
                pos = i;
            }
        }
        return pos;
    }
    
    /**
     * Encontra Apresentacoes pelo id
     * @param id id da apresentacao
     * @return 
     */
    private boolean findPresentationID(int id) {
        
        for (int i = 0; i < numPresentations; i++) {
            if (this.presentations[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Expande o array de Apresentacoes
     * @return 
     */
    private Presentation[] expandArray() {
        Presentation[] tempArray = new Presentation[this.presentations.length * 2];

        for (int i = 0; i < this.presentations.length; i++) {
            tempArray[i] = this.presentations[i];
        }

        return tempArray;
    }

    /**
     * Retorna o fim da sessao
     * @return 
     */
    public LocalDateTime endSession() {
        int temp = 0;

        for (int i = 0; i < numPresentations; i++) {
            temp = temp + this.presentations[i].getDuration();
        }

        endSession = startSession.plusMinutes(temp + 10);

        return endSession;
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
        return nameSession;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public int getDuration() {
        int temp = 0, duration = 0;

        for (int i = 0; i < numPresentations; i++) {
            temp = temp + this.presentations[i].getDuration();
        }

        LocalDateTime endSession = startSession.plusMinutes(temp);

        Duration dur = Duration.between(startSession, endSession);

        int total = (int) (dur.toSecondsPart() / 60);

        duration = total + temp + 10;

        return duration;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public int getMaxDurationPerPresentation() {
        return presentationDur;
    }

    /**
     * 
     * {'@inhertirdoc'} 
     */
    @Override
    public LocalDateTime getStartTime() {
        return startSession;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public String getSessionTheme() {
        return theme;
    }

    /**
     * 
     * {'@inhertirdoc'} 
     */
    @Override
    public Room getRoom() {
        return room;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public boolean addPresentation(Presentation prsntn) throws SessionException {
        int pos = findPresentation(prsntn);

        if (prsntn == null) {
            throw new SessionException("Parametro null");
        }
        
        if(prsntn.getDuration() > this.getMaxDurationPerPresentation()){
            throw new SessionException("Demasiado tempo por apresentacao");
        }
        if (numPresentations == this.presentations.length) {
            presentations = expandArray();
        }
        if (pos != -1) {
            return false;
        }

        this.presentations[numPresentations++] = prsntn;
        return true;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public void removePresentation(int i) throws SessionException {
        
        if(!findPresentationID(id)){
            throw new SessionException("Presentacao nao existe");
        }
        
        for (int pos = 0; pos < this.numPresentations - 1; pos++) {
            if (this.presentations[pos].getId() == i) {
                this.presentations[pos] = this.presentations[pos + 1];
            }
        }
        this.presentations[--this.numPresentations] = null;

    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Presentation getPresentation(int i) throws SessionException {
        
        if(!findPresentationID(id)){
            throw new SessionException("Presentacao nao existe");
        }
        return this.presentations[i];
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Presentation[] getPresentations() {
        int total = 0, countPresentations = 0;
        for (int i = 0; i <= numPresentations; i++) {
            if (this.presentations[i] instanceof Presentation) {
                total++;
            }
        }
        Presentation[] temp = new Presentation[total];
        for (int i = 0; i < numPresentations; i++) {
            if (this.presentations[i] instanceof Presentation) {
                temp[countPresentations++] = this.presentations[i];
            }
        }
        return temp;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public boolean isStarted() {
        if (this.getPresentations().equals(PresentationState.NOT_PRESENTED) || this.getPresentations().equals(PresentationState.PRESENTED)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Participant[] getAllPresenters() {
     
        Participant[] temp = new Participant[5];
        for (int i = 0; i <this.presentations.length; i++) {
            if(this.presentations[i] != null){
                temp[i] = this.presentations[i].getPresenter();
            }
        }
        return temp;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public int getNumberOfPresentations() {
        return numPresentations;
    }

    /**
     * Aprenseta uma toString de Sessoes
     * @return 
     */
    @Override
    public String toString() {
        String s = "";
        for (Presentation i : presentations) {
            if (i != null) {
                s += i.toString();
            }
        }
        return "\n\tSessao " + this.getId() + ": " + this.getSessionTheme() + " (Sala " + room.getId() + ": " + this.getStartTime().toLocalTime() + " - " + this.endSession().toLocalTime() + ")" + s;
    }

    /**
     * Compara os temas das sessoes
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sessions other = (Sessions) obj;
        if (!Objects.equals(this.theme, other.theme)) {
            return false;
        }
        return true;
    }
    
    
}
