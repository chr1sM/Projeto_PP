/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IO;

import Conference.Sessions;
import estg.ipp.pt.tp02_conferencesystem.interfaces.Conference;
import estg.ipp.pt.tp02_conferencesystem.io.interfaces.Exporter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONArray;
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
public class Export implements Exporter {

    public Conference conferences;

    private static final String CONFERENCEFILE = "conference.json";

    public Export(Conference conferences) {
        this.conferences = conferences;
    }

    @Override
    public String export() throws IOException {

        try ( FileWriter file = new FileWriter(CONFERENCEFILE)) {
            file.write(getConferenceJSON().toString());
        }
        return "Done";

    }

    private JSONObject getConferenceJSON() throws IOException {
        JSONArray conferenceList = new JSONArray();
        JSONObject conferenceObj = new JSONObject();

        for (int i = 0; i < conferences.getSessions().length; i++) {
            if (conferences.getSessions()[i] != null) {
                JSONObject conf = new JSONObject();

                conf.put("name", conferences.getName());
                conf.put("state", conferences.getState());
                conf.put("field", conferences.getField());
                conf.put("year", conferences.getYear());

                conf.put("id", conferences.getSessions()[i].getId());
                conf.put("name_session", conferences.getSessions()[i].getName());
                conf.put("theme", conferences.getSessions()[i].getSessionTheme());
                conf.put("start", conferences.getSessions()[i].getStartTime());
                conf.put("end", ((Sessions) conferences.getSessions()[i]).endSession());

                for (int j = 0; j < conferences.getSessions()[i].getPresentations().length; j++) {
                    if (conferences.getSessions()[i].getPresentations()[j] != null) {
                        conf.put("name_presentation", conferences.getSessions()[i].getPresentations()[j].getTitle());
                        conf.put("room", conferences.getSessions()[i].getRoom());
                        conf.put("state_presentation", conferences.getSessions()[i].getPresentations()[j].getPresentationState());
                        conf.put("duration", conferences.getSessions()[i].getPresentations()[j].getDuration());
                        conf.put("presenter_id", conferences.getSessions()[i].getPresentations()[j].getPresenter().getId());
                        conf.put("presenter_name", conferences.getSessions()[i].getPresentations()[j].getPresenter().getName());
                        conf.put("presenter_bio", conferences.getSessions()[i].getPresentations()[j].getPresenter().getBio());

                    }
                }
                conferenceList.add(conf);
            }
        }
        conferenceObj.put("conferencias", conferenceList);

        return conferenceObj;
    }
}
