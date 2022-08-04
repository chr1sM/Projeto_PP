/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conference;

import estg.ipp.pt.tp02_conferencesystem.interfaces.Room;
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
public class Rooms implements Room {

    private static int idCount = 0;
    private int id, numSeats;
    private String name;

    /**
     * Cria um construtor de Rooms
     * @param numSeats numero de lugares
     * @param name nome da Room
     */
    public Rooms(int numSeats, String name) {
        setId(idCount++);
        this.numSeats = numSeats;
        this.name = name;
    }

    /**
     * Id para dar auto incremente do id room
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
        return name;
    }

    /**
     * 
     * {'@inhertirdoc'}
     */
    @Override
    public int getNumberOfSeats() {
        return numSeats;
    }

    /**
     * Aprensetao uma toString da Room
     * @return 
     */
    @Override
    public String toString() {
        return "Rooms{" + "id=" + id + ", numSeats=" + numSeats + ", name=" + name + '}';
    }

    /**
     * Compara os nomes das Sala
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
        final Rooms other = (Rooms) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}
