/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import Enumeration.GrauEscolaridade;
import IO.Export;
import IO.Import;
import estg.ipp.pt.tp02_conferencesystem.exceptions.ConferenceException;
import estg.ipp.pt.tp02_conferencesystem.exceptions.ParticipantException;
import estg.ipp.pt.tp02_conferencesystem.exceptions.PresentationException;
import estg.ipp.pt.tp02_conferencesystem.exceptions.RoomException;
import estg.ipp.pt.tp02_conferencesystem.exceptions.SessionException;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Conference;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Participant;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Presentation;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Room;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Session;
import estg.ipp.pt.tp02_conferencesystem.io.interfaces.Exporter;
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
public class ManagementConference {

    private final int MAX = 5;
    private Participant[] participant;
    private Room[] room;
    private Session[] session;
    private Conference[] conference;
    private int nRooms, nParticipants, nSession, nConference;

    /**
     * Cria Construtor para Management das Conferencias
     */
    public ManagementConference() {
        this.nRooms = 0;
        this.nParticipants = 0;
        this.nSession = 0;
        this.nConference = 0;
        this.room = new Room[MAX];
        this.participant = new Participant[MAX];
        this.session = new Session[MAX];
        this.conference = new Conference[MAX];
    }
    
    
    //Expand Arrays
    /**
     * Da expand ao array de rooms
     */
    private void expandRooms() {
        Room[] temp = new Room[this.room.length + 5];

        for (int i = 0; i < this.room.length; i++) {
            temp[i] = this.room[i];
        }
        this.room = temp;
    }

    /**
     * Da expand ao array de Participantes
     */
    private void expandParticipants() {
        Participant[] temp = new Participant[this.participant.length + 5];

        for (int i = 0; i < this.participant.length; i++) {
            temp[i] = this.participant[i];
        }
        this.participant = temp;
    }
    
    /**
     * Da expand ao array de Sessoes
     */
    private void expandSessions() {
        Session[] temp = new Session[this.session.length + 5];

        for (int i = 0; i < this.session.length; i++) {
            temp[i] = this.session[i];
        }
        this.session = temp;
    }
    
    /**
     * Da expand ao array de Conferencias
     */
    private void expandConference() {
        Conference[] temp = new Conference[this.conference.length + 5];

        for (int i = 0; i < this.conference.length; i++) {
            temp[i] = this.conference[i];
        }
        this.conference = temp;
    }

    
    //Gets
    /**
     * Retorna Rooms
     * @return 
     */
    public Room[] getRooms() {
        return this.room;
    }

    /**
     * Retorna Participantes
     * @return 
     */
    public Participant[] getParticipants() {
        return this.participant;
    }

    /**
     * Retorna Conferencia
     * @return 
     */
    public Conference[] getConfrence() {
        return this.conference;
    }

    /**
     * Retorna Sessoes
     * @return 
     */
    public Session[] getSession() {
        return this.session;
    }


    //Lists
    /**
     * Encontra rooms com o mesmo nome
     * Retorna True sim, False nao
     * @param room recebe um room
     * @return 
     */
    private boolean findRooms(Room room) {

        for (Room r : this.room) {
            if (r != null) {
                if (((Rooms) r).equals(room)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Encontra participantes com o mesmo id
     * @param id id do Participante
     * @return 
     */
    private Participant findParticipant(int id) {

        for (Participant p : this.participant) {
            if (p != null) {
                if (((Participants) p).getId() == id) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Encontra conferencias com o mesmo nome
     * @param conference nome da Conferencia
     * @return 
     */
    private Conference findConference(String conference) {

        for (Conference c : this.conference) {
            if (c != null) {
                if (((Conferences) c).equals(conference)) {
                    return c;
                }
            }
        }
        return null;
    }
    
    /**
     * Lista os Horarios das Conferencias
     */
    public void listarHorario() {
        for (int i = 0; i < this.nConference; i++) {
            System.out.println(this.conference[i].getSchedule());
        }
    }

    //Creates
    /**
     * Cria um Room
     * @param lugares numero de lugares do room
     * @param nome nome do room
     * @throws RoomException 
     */
    public void createRoom(int lugares, String nome) throws RoomException {

        Room room = new Rooms(lugares, nome);
        if (this.room.length == this.nRooms) {
            expandRooms();
        }

        if (findRooms(room)) {
            System.out.println("true");
        } else {

            this.room[nRooms++] = room;
        }

    }

    /**
     * Cria um Participante do tipo Aluno
     * @param curso nome do curso
     * @param anoLetivo ano letivo do aluno
     * @param nome nome do aluno
     * @param bio descricao do aluno
     * @throws ParticipantException 
     */
    public void createParticipantA(String curso, String anoLetivo, String nome, String bio) throws ParticipantException {
        Participant part = new Aluno(curso, anoLetivo, nome, bio);

        if (this.participant.length == this.nParticipants) {
            expandParticipants();
        }

        this.participant[nParticipants++] = part;
    }

    /**
     * Cria um Participante do tipo Professor
     * @param nome nome do professor
     * @param bio descricao do professor
     * @param grau grau de escolaridade do professor
     * @param expert expertise do professor
     * @throws ParticipantException 
     */
    public void createParticipantP(String nome, String bio, GrauEscolaridade grau, String expert) throws ParticipantException {
        Participant part = new Professor(expert, nome, bio, grau);

        if (this.participant.length == this.nParticipants) {
            expandParticipants();
        }

        this.participant[nParticipants++] = part;
    }

    /**
     * Cria uma nova Sessao e adiciona um room a sessao
     * @param nomeSession nome da Sessao
     * @param tema tema da Sessao
     * @param idRoom id do Room
     * @param ano ano da sessao
     * @param mes mes da sessao
     * @param dia dia da sessao
     * @param horas horas da sessao
     * @param min minutos da sessao
     * @throws SessionException
     * @throws RoomException 
     */
    public void createSession(String nomeSession, String tema, int idRoom, int ano, int mes, int dia, int horas, int min) throws SessionException, RoomException {
        
        
        for (int i = 0; i < nRooms; i++) {
            if (this.room[i].getId() == idRoom) {
                Room r = this.room[i];
                if(this.session.length == this.nSession) expandSessions();
                Session session = new Sessions(nomeSession, tema, r, LocalDateTime.of(LocalDate.of(ano, mes, dia), LocalTime.of(horas, min)));
                this.session[this.nSession++] = session;
            }
        }
    }

    /**
     * Cria uma Conferencia
     * @param nomeConference nome da conferencia
     * @param field tema da conferencia
     * @param ano ano da conferencia
     * @throws ConferenceException 
     */
    public void createConference(String nomeConference, String field, int ano) throws ConferenceException {
        
        if(this.conference.length == this.nConference) expandConference();

        Conference conf = new Conferences(nomeConference, field, ano);

        this.conference[this.nConference++] = conf;
    }

    
    //Adds
    /**
     * Adiciona um Sessao a Conferencia
     * @param idSession id da Sessao
     * @param confName nome da Conferencia
     * @throws ConferenceException
     * @throws SessionException 
     */
    public void addSessionConf(int idSession, String confName) throws ConferenceException, SessionException {
        for (int i = 0; i < this.nConference; i++) {
            if (this.conference[i].getName().equals(confName)) {
                Conference c = this.conference[i];
                for (int y = 0; y < this.nSession; y++) {
                    if (this.session[y].getId() == idSession) {
                        Session s = this.session[y];
                        c.addSession(s);
                    }
                }
            }
        }
    }

    /**
     * Adiciona uma Apresentacao a Sessao
     * @param dur duracao da apresentacao
     * @param title titulo da apresentacao
     * @param idParticipant id do Participante
     * @param sessao id da Sessao
     * @throws SessionException
     * @throws PresentationException 
     */
    public void addSessionPresentation(int dur, String title, int idParticipant, int sessao) throws SessionException, PresentationException {

        Participant participantfind = findParticipant(idParticipant);

        Presentation presentationLocal = new Presentations(dur, title, participantfind);

        this.session[sessao].addPresentation(presentationLocal);

    }

    
    //Removes
    /**
     * Remove uma Sessao da Conferencia
     * @param idSession id da sessao
     * @throws ConferenceException
     * @throws SessionException 
     */
    public void rmSessionConf(int idSession) throws ConferenceException, SessionException {

        for (int i = 0; i < this.nConference; i++) {
            if (this.conference[i] != null) {
                Session[] temp = this.conference[i].getSessions();
                for (int a = 0; a < temp.length; a++) {
                    if (temp[a].getId() == idSession) {
                        this.conference[i].removeSession(temp[a].getId());
                    }
                }
            }
        }
    }

    /**
     * Remove uma Apresentacao da Sessao
     * @param id id da apresentacao
     * @throws SessionException
     * @throws PresentationException 
     */
    public void rmSessionApresentation(int id) throws SessionException, PresentationException {

        for (int i = 0; i < this.nSession; i++) {
            if (this.session[i] != null) {
                Presentation[] temp = this.session[i].getPresentations();
                for (int a = 0; a < temp.length; a++) {
                    if (temp[a].getId() == id) {
                        this.session[i].removePresentation(temp[a].getId());
                    }
                }
            }
        }

    }

    
    //Exports && Import
    /**
     * Gere um certificao para o Orador da Conferencia
     * @param name nome da conferencia
     * @throws ConferenceException 
     */
    public void generatSpeakerCert(String name) throws ConferenceException {

        Conference tempConf = findConference(name);

        if (tempConf != null) {
            for (int i = 0; i < this.nConference; i++) {
                if (this.conference[i].getName() == tempConf.getName()) {
                    this.conference[i].generateSpeakerCertificates("speaker");
                }
            }
        }
    }

    /**
     * Gere um Certificado para o participante da Conferencia
     * @param name nome da Conferencia
     * @throws ConferenceException 
     */
    public void generatParticipantCert(String name) throws ConferenceException {

        Conference tempConf = findConference(name);

        if (tempConf != null) {
            for (int i = 0; i < this.nConference; i++) {
                if (this.conference[i].getName() == tempConf.getName()) {
                    this.conference[i].generateParticipantCertificates("participant");
                }
            }
        }
    }
    
    /**
     * Exportar Dados
     * @param name nome da Conferencia
     * @throws IOException
     * @throws ConferenceException 
     */
    public void export(String name) throws IOException, ConferenceException {

        Conference tempConf = findConference(name);

        if (tempConf != null) {
            Exporter export = new Export(tempConf);
            export.export();
        }
    }

    /**
     * Importa Dados
     * @param name nome da Conferencia
     * @throws IOException
     * @throws ConferenceException 
     */
    public void importar(String name) throws IOException, ConferenceException {
        Conference tempConf = findConference(name);

        if (tempConf != null) {
            Import importer = new Import();

            importer.importData(tempConf, "conference.json");

        }
    }
    
    
    //Others
    /**
     * Faz o check in do participante na conferencia
     * @param idParticipant id do participante
     * @param nomeparti nome do participante
     * @throws ConferenceException
     * @throws ParticipantException 
     */
    public void checkIn(int idParticipant, String nomeparti) throws ConferenceException, ParticipantException {

        Participant tempParti = findParticipant(idParticipant);

        Conference tempConf = findConference(nomeparti);

        if (tempParti != null && tempConf != null) {

            for (int i = 0; i < this.nConference; i++) {
                if (this.conference[i].getName() == tempConf.getName()) {
                    this.conference[i].checkIn(tempParti);
                }
            }
        }

    }

    /**
     * Altera o Estado da Conferencia
     * @param nomeConf nome da Conferencia
     * @throws ConferenceException 
     */
    public void changeConfState(String nomeConf) throws ConferenceException {

        Conference tempConf = findConference(nomeConf);

        if (tempConf != null) {
            for (int i = 0; i < this.nConference; i++) {
                if (this.conference[i].getName() == tempConf.getName()) {
                    this.conference[i].changeState();
                }
            }
        }
    }
}
