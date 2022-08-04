/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ppcontinua;

import Conference.ManagementConference;
import Enumeration.GrauEscolaridade;
import estg.ipp.pt.tp02_conferencesystem.dashboards.Dashboard;
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
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Nome: José Paulo de Magalhães Rodrigues
 * Numero: 8170322
 * Turma: LSIRC
 * 
 * Nome: Christopher Pereira Meder
 * Numero: 8170022
 * Turma: LSIRC
 */
public class Menus {

    private Scanner scanner;
    private ManagementConference manage;
    private String[] ficheiro = {"conference"};

    public Menus() {
        this.scanner = new Scanner(System.in);
        this.manage = new ManagementConference();
    }

    private final static String START = "Pretende criar uma nova Conferencia ou importar dados \n"
            + "1 - Menu \n"
            + "2 - Importar Dados \n"
            + "3 - Dashboard \n"
            + "0 - Sair \n";

    private final static String MENUGLOBAL = "Menu Gestao\n"
            + "1 - Conferencia\n"
            + "2 - Sessao \n"
            + "3 - Participante \n"
            + "4 - Sala \n"
            + "0 - Sair \n";

    private final static String CONF = "Gestao de Conferencia \n"
            + "1 - Criar Conferencia\n"
            + "2 - Adicionar Sessão\n"
            + "3 - Remover Sessão\n"
            + "4 - Check In Participants"
            + "5 - Gerar Certificados \n"
            + "6 - Listar Horarios \n"
            + "7 - Alterar Estado da Conferencia \n"
            + "8 - Exportar Dados \n"
            + "0 - Sair";

    private final static String SESSAO = "Gestao de Sessoes \n"
            + "1 - Criar Sessao \n"
            + "2 - Adicionar Apresentação\n"
            + "3 - Remover Apresentação \n"
            + "4 - Listar Apresentações\n"
            + "5 - Duração da Sessão \n"
            + "0 - Sair \n";

    private final static String PARTICIPANT = "Gestao de Participantes \n"
            + "1 - Criar Participante \n"
            + "2 - Listar Participantes\n"
            + "0 - Sair \n";

    private final static String ROOM = "Gestao de Salas \n"
            + "1 - Criar Sala \n"
            + "2 - Listar Salas \n"
            + "0 - Sair \n";

    private final static String CERTS = "Gestao de Certificados \n"
            + "1 - Certificado de Orador \n"
            + "2 - Certificado de Participante \n"
            + "0 - Sair \n";

    //Menus
    public void menuMain() {
        boolean exit = false;
        while (!exit) {
            System.out.println(START);
            switch (scanner.next()) {
                case "1": {
                    menuGlobal();
                    exit = true;
                    break;
                }
                case "2": {
                    importar();
                    exit = true;
                    break;
                }
                case "3": {
                    Dashboard.render(ficheiro);
                    exit = true;
                    break;
                }
                case "0": {
                    exit = true;
                    break;
                }
                default: {
                    menuMain();
                }
            }
        }
    }

    private void menuGlobal() {
        boolean exit = false;
        while (!exit) {
            System.out.println(MENUGLOBAL);
            switch (scanner.next()) {
                case "1": {
                    menuConf();
                    break;
                }
                case "2": {
                    menuSessao();
                    break;
                }
                case "3": {
                    menuParti();
                    break;
                }
                case "4": {
                    menuRoom();
                    break;
                }
                case "0": {
                    exit = true;
                    break;
                }
                default: {
                    menuGlobal();
                }
            }
        }
    }

    private void menuConf() {
        boolean exit = false;
        while (!exit) {
            System.out.println(CONF);
            switch (scanner.next()) {
                case "1": {
                    addConference();
                    break;
                }
                case "2": {
                    addSessionConf();
                    break;
                }
                case "3": {
                    rmSessionConf();
                    break;
                }
                case "4": {
                    checkIn();
                    break;
                }
                case "5": {
                    menuCerts();
                    break;
                }
                case "6": {
                    manage.listarHorario();
                    break;
                }
                case "7": {
                    changeState();
                    break;
                }
                case "8": {
                    export();
                    break;
                }
                case "0": {
                    exit = true;
                    break;
                }
                default: {
                    menuConf();
                }
            }
        }
    }

    private void menuSessao() {
        boolean exit = false;
        while (!exit) {
            System.out.println(SESSAO);
            switch (scanner.next()) {
                case "1": {
                    addSession();
                    break;
                }
                case "2": {
                    addSessionPresentation();
                    break;
                }
                case "3": {
                    rmSessionPresentation();
                    break;
                }
                case "4": {
                    listPresentations();
                    break;
                }
                case "5": {
                    checkIn();
                    break;
                }
                case "0": {
                    exit = true;
                    break;
                }
                default: {
                    menuSessao();
                }
            }
        }
    }

    private void menuParti() {
        boolean exit = false;
        while (!exit) {
            System.out.println(PARTICIPANT);
            switch (scanner.next()) {
                case "1": {
                    addParticipant();
                    break;
                }
                case "2": {
                    listParticipants();
                    break;
                }
                case "0": {
                    exit = true;
                    break;
                }
                default: {
                    menuParti();
                }
            }
        }
    }

    private void menuRoom() {
        boolean exit = false;
        while (!exit) {
            System.out.println(ROOM);
            switch (scanner.next()) {
                case "1": {
                    addRoom();
                    break;
                }
                case "2": {
                    listRooms();
                    break;
                }
                case "0": {
                    exit = true;
                    break;
                }
                default: {
                    menuRoom();
                }
            }
        }
    }

    private void menuCerts() {
        boolean exit = false;
        while (!exit) {
            System.out.println(CERTS);
            switch (scanner.next()) {
                case "1": {
                    speakerCert();
                    break;
                }
                case "2": {
                    participantCert();
                    break;
                }
                case "0": {
                    exit = true;
                    break;
                }
                default: {
                    menuCerts();
                }
            }
        }
    }

    //Criar
    private String addRoom() {
        
        String nome;
        int capacidade;
        try {

            System.out.println("Introduza o nome da Sala:");
            nome = scanner.next();

            System.out.println("Capacidade da sala " + nome + ": ");
            capacidade = scanner.nextInt();

            manage.createRoom(capacidade, nome);
        } catch (RoomException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Sala Criada";
    }

    private String addParticipant() {
        String curso, anoLetivo, nome, bio, expert;
        int escolha, grau;

        System.out.println("Qual Participante deseja criar: ");
        System.out.println("1 - Aluno");
        System.out.println("2 - Professor");
        escolha = scanner.nextInt();
        if (escolha == 1) {
            try {
                
                System.out.println("Nome: ");
                nome = scanner.next();

                System.out.println("Ano Letivo: ");
                anoLetivo = scanner.next();

                System.out.println("Curso: ");
                curso = scanner.next();

                System.out.println("Descricao: ");
                bio = scanner.next();

            
                manage.createParticipantA(curso, anoLetivo, nome, bio);
            } catch (ParticipantException ex) {
                Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                System.out.println("Nome: ");
                nome = scanner.next();

                System.out.println("Expertise: ");
                expert = scanner.next();

                System.out.println("Descricao: ");
                bio = scanner.next();

                System.out.println("Qual e o seu grau de Escolaridade: ");
                System.out.println("1 - Basico");
                System.out.println("2 - Secundario");
                System.out.println("3 - Profissional");
                System.out.println("4 - Superior");
                grau = scanner.nextInt();

            
                if (grau == 1) {
                    manage.createParticipantP(nome, bio, GrauEscolaridade.Basico, expert);
                } else if (grau == 2) {
                    manage.createParticipantP(nome, bio, GrauEscolaridade.Secundario, expert);
                } else if (grau == 3) {
                    manage.createParticipantP(nome, bio, GrauEscolaridade.Profissional, expert);
                } else {
                    manage.createParticipantP(nome, bio, GrauEscolaridade.Superior, expert);
                }
            } catch (ParticipantException ex) {
                Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "Participante Criado";
    }

    private String addSessionPresentation() {
        int idParticipant, dur, sessao;
        String title;
        try {
            listParticipants();
            System.out.println("Introduza o ID do Apresentador: ");
            idParticipant = scanner.nextInt();

            System.out.println("Qual e o titulo da Apresentacao: ");
            title = scanner.next();

            System.out.println("Qual e a Duracao(15-30): ");
            dur = scanner.nextInt();

            listSesson();
            System.out.println("Qual a sessão: ");
            sessao = scanner.nextInt();

            manage.addSessionPresentation(dur, title, idParticipant, sessao);
        } catch (SessionException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PresentationException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Apresentacao Criada";
    }

    private String addConference() {
        String nomeConference, field;
        int ano;
        
        try {
            System.out.println("Nome da Conferencia: ");
            nomeConference = scanner.next();

            System.out.println("Tipo de Conferencia: ");
            field = scanner.next();

            System.out.println("Ano da Conferencia: ");
            ano = scanner.nextInt();

        
            manage.createConference(nomeConference, field, ano);
        } catch (ConferenceException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Conferencia Criada";
    }

    private String addSession() {
        String nomeSession, tema;
        int idRoom, ano, mes, dia, horas, min;
        
        try {
            System.out.println("Nome da Sessao: ");
            nomeSession = scanner.next();

            System.out.println("Tema: ");
            tema = scanner.next();

            listRoomsID();
            System.out.println("Em que sala e a Sessao: ");

            System.out.println("ID: ");
            idRoom = scanner.nextInt();

            System.out.println("Data: ");
            System.out.print("Dia: ");
            dia = scanner.nextInt();

            System.out.print("Mes: ");
            mes = scanner.nextInt();

            System.out.print("Ano: ");
            ano = scanner.nextInt();

            System.out.print("Horas: ");
            horas = scanner.nextInt();

            System.out.print("Min: ");
            min = scanner.nextInt();

        
            manage.createSession(nomeSession, tema, idRoom, ano, mes, dia, horas, min);
        } catch (SessionException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RoomException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Sessao Criada";
    }

    private String addSessionConf() {
        int idSession;
        String confName;
        try {
            System.out.println("Lista Sessoes:");
            listSesson();
            System.out.println("Qual Sessao quer adicionar a Conferencia:");
            System.out.println("ID da Sessao:");
            idSession = scanner.nextInt();

            System.out.println("Lista Conferencias:");
            listConf();

            System.out.println("Nome da Conferencia:");
            confName = scanner.next();

            manage.addSessionConf(idSession, confName);
        } catch (ConferenceException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SessionException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Sessao adicionada a Conferencia";

    }

    private String rmSessionPresentation() {
        int idPresentation;
        
        try {
            listPresentations();
            System.out.println("Qual a Apresentação que pretende remover: ");
            idPresentation = scanner.nextInt();

            manage.rmSessionApresentation(idPresentation);
        } catch (SessionException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PresentationException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private String rmSessionConf() {

        int idSession;
        try {
            
            listSesson();
            System.out.println("Qual a Sessao que pretende remover: ");
            idSession = scanner.nextInt();

        
            manage.rmSessionConf(idSession);
        } catch (ConferenceException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SessionException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private String checkIn() {
        int idParti;
        String nomeConference;
        
        try {
            listParticipants();
            System.out.println("Qual e o Participante que deseja dar CheckIn: ");
            System.out.println("ID: ");
            idParti = scanner.nextInt();

            System.out.println("Nome da Conferencia: ");
            nomeConference = scanner.next();

            manage.checkIn(idParti, nomeConference);
        } catch (ConferenceException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParticipantException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Check feito";
    }

    private void changeState() {
        String nomeConf;
        
        try {
            listConfState();

            System.out.println("A qual Conferencia pretende alterar o estado: ");
            nomeConf = scanner.next();

            manage.changeConfState(nomeConf);
        } catch (ConferenceException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void export() {
        String nome;
        
        try {
            System.out.println("Lista de Conferencias");

            listConf();
            System.out.println("Nome da Conferencia: ");
            nome = scanner.next();

            manage.export(nome);
        } catch (IOException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConferenceException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void importar() {
        String nome;
        
        try {
            System.out.println("Nome da Conferencia: ");
            nome = scanner.next();
        
            manage.importar(nome);
        } catch (IOException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConferenceException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Listar
    private void listRooms() {
        Room[] temp = this.manage.getRooms();
        for (int i = 0; i < this.manage.getRooms().length; i++) {
            if (this.manage.getRooms()[i] != null) {
                System.out.println(temp[i].toString());
            }
        }
    }

    private void listParticipants() {
        Participant[] temp = this.manage.getParticipants();
        for (int i = 0; i < this.manage.getParticipants().length; i++) {
            if (this.manage.getParticipants()[i] != null) {
                System.out.println(temp[i].toString());
            }
        }
    }

    private void listPresentations() {
        for (int i = 0; i < this.manage.getSession().length; i++) {
            if (this.manage.getSession()[i] != null) {
                Presentation[] temp = this.manage.getSession()[i].getPresentations();
                for (int a = 0; a < temp.length; a++) {
                    System.out.println("ID:" + temp[a].getId() + "Tema: " + temp[a].getTitle());
                }
            }
        }
    }

    private void listConf() {
        Conference[] temp = this.manage.getConfrence();
        for (int i = 0; i < this.manage.getConfrence().length; i++) {
            if (this.manage.getConfrence()[i] != null) {
                System.out.println(temp[i].toString());
            }
        }
    }

    private void listSesson() {
        Session[] temp = this.manage.getSession();
        for (int i = 0; i < this.manage.getSession().length; i++) {
            if (this.manage.getSession()[i] != null) {
                System.out.println(temp[i].toString());
            }
        }
    }

    private void listConfState() {
        Conference[] temp = this.manage.getConfrence();
        for (int i = 0; i < this.manage.getConfrence().length; i++) {
            if (this.manage.getConfrence()[i] != null) {
                System.out.println(temp[i].getName() + temp[i].getState());
            }
        }
    }

    private void listRoomsID() {
        Room[] temp = this.manage.getRooms();
        System.out.println("ID |");
        for (int i = 0; i < this.manage.getRooms().length; i++) {
            if (this.manage.getRooms()[i] != null) {
                System.out.println(temp[i].getId() + " - " + temp[i].getName());
            }
        }
    }

    private void speakerCert() {
        String nomeConference;
        
        try {
            listConf();
            System.out.println("Nome da Conferencia: ");
            nomeConference = scanner.next();

        
            manage.generatSpeakerCert(nomeConference);
        } catch (ConferenceException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void participantCert() {
        String nomeConference;
        
        try {
            listConf();
            System.out.println("Nome da Conferencia: ");
            nomeConference = scanner.next();

            manage.generatSpeakerCert(nomeConference);
        } catch (ConferenceException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
