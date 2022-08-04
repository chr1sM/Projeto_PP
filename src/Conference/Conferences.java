/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conference;

import IO.Statistic;
import estg.ipp.pt.tp02_conferencesystem.enumerations.ConferenceState;
import estg.ipp.pt.tp02_conferencesystem.exceptions.ConferenceException;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Conference;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Participant;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Presentation;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Room;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Session;
import estg.ipp.pt.tp02_conferencesystem.io.interfaces.Statistics;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Objects;
import org.json.simple.JSONObject;

/**
 * Nome: José Paulo de Magalhães Rodrigues
 * Numero: 8170322
 * Turma: LSIRC
 * 
 * Nome: Christopher Pereira Meder
 * Numero: 8170022
 * Turma: LSIRC
 */
public class Conferences implements Conference {
    
    
    private static final int MAX = 5;
    private String conferenceName, field;
    private ConferenceState conferenceState;
    private int year, numParticipants, numSession;
    private Session[] sessions;
    private Participant[] participants;
    
    /**
     * Contrutor de Conderences
     * @param conferenceName Nome da conferencia
     * @param field Tema da conferencia
     * @param year  Ano da conferencia
     */
    public Conferences(String conferenceName, String field, int year) {
        this.conferenceName = conferenceName;
        this.numParticipants = 0;
        this.field = field;
        this.conferenceState = ConferenceState.ON_EDITING;
        this.year = year;
        this.sessions = new Session[MAX];
        this.participants = new Participant[MAX];
    }
    
    /**
     * Encontrar Sessões pelo tema
     * @param sn Argumento do tipo sessao
     * @return posicao se encontrar ou -1 se não
     */
    private int findSession(Session sn) {
        int pos = -1;

        for (int i = 0; i < numSession; i++) {
            if (((Sessions) this.sessions[i]).equals(sn)) {
                pos = i;
            }
        }
        return pos;
    }
    
    /**
     * Encontrar Sessao pelo ID
     * @param id id da sessão
     * @return true se encontrar false caso não
     */
    private boolean findSessionID(int id) {

        for (int i = 0; i < numSession; i++) {
            if (this.sessions[i].getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Encontrar Participante 
     * @param parti Argumento do tipo Participante
     * @return posicao se encontrar ou -1 se não
     */
    private int findParticipant(Participant parti) {
        int pos = -1;

        for (int i = 0; i < numParticipants; i++) {
            if (this.participants[i].getId() == parti.getId()) {
                pos = i;
            }
        }
        return pos;
    }
    
    /**
     * Função para retornar True ou False, caso encontre no array o room da sessão
     * @param array array temporario de rooms
     * @param room room X de sessao
     * @return true se encontrar false caso não
     */
    private boolean findRoomArray(Room[] array, Room room) {

        for (int i = 0; i < array.length; i++) {
            if (array[i].getId() == room.getId()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Função para retornar True ou False,caso encontre no array o participante
     * @param array array de participants
     * @param participant legth de participantes Speakers
     * @return true se encontrar false caso não
     */
    private boolean findParticipantArray(Participant[] array, Participant participant) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].getId() == participant.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Encontrar Room pelo ID
     * @param id id te room
     * @return true se encontrar false caso não
     */
    private boolean findRoom(int id) {

        for (int i = 0; i < numSession; i++) {
            if (this.sessions[i].getRoom().getId() == id) {
                return true;
            }
        }
        return false;

    }

    /**
     * Encontrar participant pelo id
     * @param id Participant Id
     * @return true se encontrar false caso não
     */
    private boolean findParticipantID(int id) {

        for (int i = 0; i < numParticipants; i++) {
            if (this.participants[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Expande o arrat de Sessions
     */
    private void expandSessions() {
        Session[] temp = new Session[this.sessions.length + 5];

        for (int i = 0; i < this.sessions.length; i++) {
            temp[i] = this.sessions[i];
        }
        this.sessions = temp;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public String getName() {
        return conferenceName;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public String getField() {
        return field;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public ConferenceState getState() {
        return conferenceState;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public void changeState() {
        if (conferenceState == ConferenceState.ON_EDITING) {
            conferenceState = ConferenceState.IN_PROGRESS;
        } else {
            conferenceState = ConferenceState.FINISHED;
        }
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public int getYear() {
        return year;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public boolean addSession(Session sn) throws ConferenceException {
        int pos = findSession(sn);

        if (sn == null) {
            throw new ConferenceException("Parametro null");
        }

        for (int i = 0; i < this.numSession; i++) {
            if (this.sessions[i].getRoom().getId() == sn.getRoom().getId() && (!((Sessions) sn).endSession().isAfter(this.sessions[i].getStartTime())) || sn.getStartTime().isBefore(((Sessions) this.sessions[i]).endSession())) {
                throw new ConferenceException("Nao pode colocar sessoes em intervalos de tempo que colidem que estão no mesmo room");
            }
        }

        Participant[] sntemp = sn.getAllPresenters();
        for (int i = 0; i < this.numSession; i++) {
            Participant[] temp = this.sessions[i].getAllPresenters();
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] != null) {
                    for (int a = 0; a < sntemp.length; a++) {
                        if (sntemp[a] != null) {
                            if (sntemp[a].getId() == temp[j].getId() && ((Sessions) sn).endSession().isAfter(this.sessions[i].getStartTime())) {
                                throw new ConferenceException("Mesmo apresentador em sessões diferentes com horarios incompatíveis");
                            }
                        }
                    }
                }
            }
        }

        Presentation[] sntempApr = sn.getPresentations();
        for (int i = 0; i < this.numSession; i++) {
            Presentation[] temp = this.sessions[i].getPresentations();
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] != null) {
                    for (int a = 0; a < sntempApr.length; a++) {
                        if (sntempApr[a] != null) {
                            if (sntempApr[a].equals(temp[j])) {
                                throw new ConferenceException("Essa Apresentação já existe numa sessão");
                            }
                        }
                    }
                }
            }
        }

        if (getState() != ConferenceState.ON_EDITING) {
            throw new ConferenceException("Conferencia não esta em modo edicao");
        }

        if (numSession == this.sessions.length) {
            expandSessions();
        }

        if (pos != -1) {
            return false;
        }

        this.sessions[numSession++] = sn;
        return true;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public void removeSession(int i) throws ConferenceException {

        if ((!findSessionID(i))) {
            throw new ConferenceException("O ID não existe");
        }

        if (conferenceState != ConferenceState.ON_EDITING) {
            throw new ConferenceException("A conferencia não esta em modo edicao");
        }

        for (int pos = 0; pos < this.numSession - 1; pos++) {
            if (this.sessions[pos].getId() == i) {
                this.sessions[pos] = this.sessions[pos + 1];
            }
        }
        this.sessions[--this.numSession] = null;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Session getSession(int i) throws ConferenceException {
        if (!findSessionID(i)) {
            throw new ConferenceException("ID não existe");
        }
        return this.sessions[i];
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Session[] getSessions() {
        int total = 0, countSessions = 0;
        for (int i = 0; i < numSession; i++) {
            if (this.sessions[i] instanceof Session) {
                total++;
            }
        }
        Session[] temp = new Session[total];
        for (int i = 0; i < numSession; i++) {
            if (this.sessions[i] instanceof Session) {
                temp[countSessions++] = this.sessions[i];
            }
        }
        return temp;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public void checkIn(Participant p) throws ConferenceException {
        int pos = findParticipant(p);

        if (p == null) {
            throw new ConferenceException("Participant null");
        }
        if (pos != -1) {
            throw new ConferenceException("Participante ja deu check in");
        }
        if (conferenceState == ConferenceState.IN_PROGRESS) {
            this.participants[numParticipants++] = p;
        } else {
            throw new ConferenceException("A conferencia não esta em progresso ainda");
        }
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Participant getParticipant(int i) throws ConferenceException {
        if (!findParticipantID(i)) {
            throw new ConferenceException("Participante não existe");
        }
        return this.participants[i];
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Participant[] getParticipants() {
        int total = 0, countParticipants = 0;
        for (int i = 0; i < numParticipants; i++) {
            if (this.participants[i] instanceof Participant) {
                total++;
            }
        }
        Participant[] temp = new Participant[total];
        for (int i = 0; i < numParticipants; i++) {
            if (this.participants[i] instanceof Participant) {
                temp[countParticipants++] = this.participants[i];
            }
        }
        return temp;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Participant[] getSpeakerParticipants() {

        int countParticipantsSpeaker = 0, count = 0;
        for (int i = 0; i < this.numSession; i++) {
            Participant[] temp = this.sessions[i].getAllPresenters();
            for (int a = 0; a < temp.length; a++) {
                if (temp[a] != null) {
                    countParticipantsSpeaker++;
                }
            }
        }

        Participant[] tempFinalParti = new Participant[countParticipantsSpeaker];

        for (int i = 0; i < this.numSession; i++) {
            Participant[] temp = this.sessions[i].getAllPresenters();
            for (int a = 0; a < temp.length; a++) {
                if (temp[a] != null) {
                    tempFinalParti[count++] = temp[a];
                }
            }

        }

        Participant[] tempFinalParti2 = new Participant[0];
        for (int a = 0; a < this.numSession; a++) {
            for (int b = 0; b < tempFinalParti.length; b++) {
                if (tempFinalParti[b] != null) {
                    if (!findParticipantArray(tempFinalParti2, tempFinalParti[b])) {
                        Participant[] temp = new Participant[tempFinalParti2.length + 1];
                        int i = 0;
                        for (int y = 0; y < tempFinalParti2.length; y++) {
                            temp[i] = tempFinalParti2[y];
                            i++;
                        }
                        temp[i] = tempFinalParti[b];
                        tempFinalParti2 = temp;
                    }
                }
            }
        }
        return tempFinalParti2;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Session[] getRoomSessions(int i, LocalDateTime ldt, LocalDateTime ldt1) throws ConferenceException {
        Session[] temp = new Session[numSession];

        for (int y = 0; y < numSession; y++) {
            if (findRoom(i)) {
                int temp2 = this.sessions[y].getDuration();
                LocalDateTime endSession = this.sessions[y].getStartTime().plusMinutes(temp2);
                if (ldt.isBefore(this.sessions[y].getStartTime()) && ldt1.isAfter(endSession) || ldt.equals(this.sessions[y].getStartTime()) || ldt1.equals(endSession)) {
                    if (conferenceState == ConferenceState.IN_PROGRESS) {
                        temp[y] = this.sessions[y];
                    } else {
                        throw new ConferenceException("A conderencia não está IN_PROGESS");
                    }
                } else {
                    throw new ConferenceException("Intervalo de tempo invalido");
                }
            } else {
                throw new ConferenceException("O id do Room é invalido");
            }
        }
        return temp;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Room[] getRooms() {

        Room[] tempRooms = new Room[0];
        for (int a = 0; a < this.numSession; a++) {
            if (this.sessions[a] != null) {
                if (!findRoomArray(tempRooms, this.sessions[a].getRoom())) {
                    Room[] temp = new Room[tempRooms.length + 1];
                    int i = 0;
                    for (int b = 0; b < tempRooms.length; b++) {
                        temp[i++] = tempRooms[b];
                    }
                    temp[i] = this.sessions[a].getRoom();
                    tempRooms = temp;
                }
            }
        }
        return tempRooms;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public void generateSpeakerCertificates(String string) throws ConferenceException {
        Participant[] tempSpeaker = getSpeakerParticipants();

        JSONObject json = new JSONObject();
        if (conferenceState == ConferenceState.FINISHED) {
            for (int i = 0; i < tempSpeaker.length; i++) {
                if (tempSpeaker[i] != null) {
                    json.put("Participant Name:", tempSpeaker[i].getName());
                    json.put("Conference Name:", getName());
                    json.put("Year:", getYear());
                    for (int k = 0; k < this.sessions.length; k++) {
                        if (this.sessions[k] != null) {
                            Presentation[] tempPresentations = this.sessions[k].getPresentations();
                            for (int a = 0; a < tempPresentations.length; a++) {
                                if (tempPresentations[a] != null && tempSpeaker[i].getId() == tempPresentations[a].getPresenter().getId()) {

                                    json.put("Presentation tite:", tempPresentations[a].getTitle());
                                }
                            }
                            json.put("Presentation date:", this.sessions[k].getStartTime());
                        }
                    }
                    try (PrintWriter out = new PrintWriter(new FileWriter(string + "\\" + tempSpeaker[i].getId()))) {
                        out.write(json.toString());
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        } else {
            throw new ConferenceException("Conferencia ainda não terminou");
        }
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public void generateParticipantCertificates(String string) throws ConferenceException {
        Participant[] tempParticipats = getParticipants();

        JSONObject json = new JSONObject();
        if (conferenceState == ConferenceState.FINISHED) {
            for (int i = 0; i < this.numParticipants; i++) {
                if (tempParticipats[i] != null) {
                    json.put("Participant Name:", tempParticipats[i].getName());
                    json.put("Conference Name:", getName());
                    json.put("Year:", getYear());
                    try (PrintWriter out = new PrintWriter(new FileWriter(string + "\\" + tempParticipats[i].getId()))) {
                        out.write(json.toString());
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }

            }
        } else {
            throw new ConferenceException("Conferencia ainda não terminou");
        }

    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public String getSchedule() {
        return toString();
    }
    
    /**
     * Metodo não esta 100% funcional tinha de criar um metodo na classe Sessions que destinguisse participante de orador para conseguir adicionar apenas participants,
     * assim coloca o leght the participants no seu todo
     * {'@inhertirdoc'}
     */
    @Override
    public Statistics[] getNumberOfParticipantsBySession() {
        
        Statistics[] statistics = new Statistic[this.numSession];
        int count=0;
        
        for(int i = 0; i<this.numSession;i++){
            Statistics tempStatic = new Statistic(this.sessions[i].getName(), this.numParticipants);
            statistics[count++] = tempStatic;
        }
        
        return statistics;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public Statistics[] getNumberOfSessionsByRoom() {
        
        Statistics[] statistics = new Statistic[this.getRooms().length];
        int count = 0;
        for(int a=0; a<this.getRooms().length;a++){
            int count2 = 0;
            for(int b= 0; b<this.numSession;b++){
                if (this.sessions[b].getRoom().getId() == this.getRooms()[a].getId()) {
                    count2++;
                }
            }
            Statistics st = new Statistic(this.getRooms()[a].getName(), count2);
            statistics[count++] = st;
        }

        return statistics;
    }
    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public String toString() {
        String s = "";
        for (Session i : sessions) {
            if (i != null) {
                s += i.toString();
            }
        }
        return "Conferencia " + conferenceName + ": Tema - " + field + s;
    }
    
    /**
     * 
     * {'@inhertirdoc'}
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
        final Conferences other = (Conferences) obj;
        if (!Objects.equals(this.conferenceName, other.conferenceName)) {
            return false;
        }
        return true;
    }

}
