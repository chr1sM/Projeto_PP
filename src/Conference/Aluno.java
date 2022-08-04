/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conference;

/**
 * Nome: José Paulo de Magalhães Rodrigues
 * Numero: 8170322
 * Turma: LSIRC
 * 
 * Nome: Christopher Pereira Meder
 * Numero: 8170022
 * Turma: LSIRC
 */
public class Aluno extends Participants{
    
    private String curso, anoLetivo;
    
    /**
     * Construtor do aluno
     * @param curso nome do curso aluno
     * @param anoLetivo ano letivo do aluno
     * @param nameParticipants  nome do participante (aluno) (super Participants)
     * @param bio bio do participante (aluno) (super Participants)
     */
    public Aluno(String curso, String anoLetivo, String nameParticipants, String bio) {
        super(nameParticipants, bio);
        this.curso = curso;
        this.anoLetivo = anoLetivo;
    }
    
    /**
     * Retorna o curso do aluno
     * @return curso do aluno
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Define o curso de aluno
     * @param curso curso de aluno
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    /**
     * Retorna AnoLetivo de aluno
     * @return Anoletivo de aluno
     */
    public String getAnoLetivo() {
        return anoLetivo;
    }
    
    /**
     * Define o anoletivo do aluno
     * @param anoLetivo ano letivo do aluno
     */
    public void setAnoLetivo(String anoLetivo) {
        this.anoLetivo = anoLetivo;
    }
    
    /**
     * To String de Aluno
     * @return A string dos parametros de aluno
     */
    @Override
    public String toString() {
        return super.toString() + "Aluno:\n" + "Curso: " + curso + ", Ano Letivo: " + anoLetivo;
    }
}
