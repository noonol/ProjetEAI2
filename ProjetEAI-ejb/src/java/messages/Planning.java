/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import java.util.Date;

/**
 *
 * @author Rigal
 */
public class Planning {
    private Date dateHeureDebut;
    private Date dateHeureFin;
    private Personne laPersonne;
    private Contrat leContrat;
    private Salle laSalle;

    public Planning(Date dateHeureDebut, Date dateHeureFin, Personne laPersonne, Contrat leContrat, Salle laSalle) {
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
        this.laPersonne = laPersonne;
        this.leContrat = leContrat;
        this.laSalle = laSalle;
    }

    public Personne getLaPersonne() {
        return laPersonne;
    }

    public void setLaPersonne(Personne laPersonne) {
        this.laPersonne = laPersonne;
    }

    public Contrat getLeContrat() {
        return leContrat;
    }

    public void setLeContrat(Contrat leContrat) {
        this.leContrat = leContrat;
    }

    public Salle getLaSalle() {
        return laSalle;
    }

    public void setLaSalle(Salle laSalle) {
        this.laSalle = laSalle;
    }
    
    

    public Date getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(Date dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    public Date getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(Date dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    @Override
    public String toString() {
        return "Planning{" + "dateHeureDebut=" + dateHeureDebut + ", dateHeureFin=" + dateHeureFin + '}';
    }
    
}
