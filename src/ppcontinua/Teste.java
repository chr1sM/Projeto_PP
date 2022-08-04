/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ppcontinua;

import Conference.Aluno;
import Conference.Conferences;
import Conference.Presentations;
import Conference.Professor;
import Conference.Rooms;
import Conference.Sessions;
import Enumeration.GrauEscolaridade;
import IO.Export;
import IO.Import;
import IO.Statistic;
import estg.ipp.pt.tp02_conferencesystem.exceptions.ConferenceException;
import estg.ipp.pt.tp02_conferencesystem.exceptions.SessionException;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Conference;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Participant;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Presentation;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Room;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Session;
import estg.ipp.pt.tp02_conferencesystem.io.interfaces.Statistics;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
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
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SessionException, ConferenceException {

        Room r1 = new Rooms(50, "Sala 1");
        Room r2 = new Rooms(50, "Sala 2");

        Participant p1 = new Aluno("LSIRC", "21/22", "Chris", "Top player");
        Participant p2 = new Professor("Hacking", "joao", "Cranio", GrauEscolaridade.Superior);
        Participant p3 = new Professor("Hackin2", "pedro", "Cranio", GrauEscolaridade.Superior);
        Participant p4 = new Professor("Hacking3", "antonio", "Cranio", GrauEscolaridade.Superior);
        Participant p5 = new Professor("Markting", "Ze", "gigi", GrauEscolaridade.Basico);

        Presentation present1 = new Presentations(20, "Ciberseguranca", p1);
        Presentation present2 = new Presentations(20, "Ciberseguranca2", p3);
        Presentation present3 = new Presentations(20, "Ciberseguranca3", p1);
        Presentation present4 = new Presentations(20, "Ciberseguranca4", p4);
        Presentation present5 = new Presentations(20, "Ciberseguranca5", p4);

        Session s1 = new Sessions("Informatica1", "Hacking", r1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 40)));
        Session s2 = new Sessions("Informatica2", "IoT", r2, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 00)));

        //System.out.println(s1.getStartTime());
        s1.addPresentation(present1);
        s1.addPresentation(present4);
        s1.addPresentation(present3);

        s2.addPresentation(present2);
        s1.addPresentation(present5);

        //System.out.println(((Sessions)s1).endSession());
        //System.out.println("ola");
        //Participant[] temp =  s1.getAllPresenters();
        //System.out.println(temp);
        //for (int i = 0; i <temp.length; i++) {
        //   if(temp[i] != null){
        //      System.out.println(temp[i].getId());
        //}
        //}
        //Participant[] temp2 =  s2.getAllPresenters();
        //for (int i = 0; i <temp2.length; i++) {
        //System.out.println("ola");
        //  if(temp2[i] != null){
        //    System.out.println(temp2[i].getId());
        // }
        //}
        //System.out.println("Duration: " + s1.getDuration() + " min");
        //s1.removePresentation(4);
        //System.out.println(s1);
        Conference c1 = new Conferences("Teste", "Tecnologia", 2022);

        c1.addSession(s1);
        c1.addSession(s2);

        System.out.println(Arrays.toString(c1.getSpeakerParticipants()));
        //c1.changeState();

        //c1.checkIn(p2);
        //c1.checkIn(p1);
        //c1.checkIn(p3);
        //c1.checkIn(p4);
        //c1.checkIn(p5);
        //System.out.println(Arrays.toString(c1.getSpeakerParticipants()));
        //c1.addSession(s2);
        //System.out.println(c1.getSchedule());
        //c1.changeState();
        //System.out.println("State: " + c1.getState());
        c1.changeState();
        c1.checkIn(p2);
        c1.checkIn(p1);
        c1.checkIn(p3);
        c1.checkIn(p4);
        c1.checkIn(p5);

        /*
        Export exporter = new Export(c1);

        try {
            exporter.export();
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }

        Import importer = new Import();
        try {
            importer.importData(c1, "conference.json");
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        //Participant[] temp3 =  c1.getSpeakerParticipants();
        //for (int i = 0; i <temp3.length; i++) {
        //    if(temp3[i] != null){
        //        System.out.println(temp3[i].getName());
        //   }
        //}
        //System.out.println(Arrays.toString(c1.getRoomSessions(0, LocalDateTime.of(LocalDate.now() , LocalTime.of(10, 40)) , LocalDateTime.of(LocalDate.now() , LocalTime.of(14 , 30))) ));
        System.out.println(Arrays.toString(c1.getRooms()));
        c1.changeState();
        c1.generateSpeakerCertificates("speaker");
        c1.generateParticipantCertificates("participant");

        Statistics[] temp = c1.getNumberOfSessionsByRoom();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    System.out.println("Description: "+temp[i].getDescription()+"Value: "+temp[i].getValue());
            }
        }
            
        Statistics[] temp2 = c1.getNumberOfParticipantsBySession();
            for (int i = 0; i < temp2.length; i++) {
                if (temp2[i] != null) {
                    System.out.println("Description: "+temp2[i].getDescription()+"Value: "+temp2[i].getValue());
            }
        }

    }
}
