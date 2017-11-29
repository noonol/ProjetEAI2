/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import enumeration.EnumCommunication;
import enumeration.EnumSecurite;
import enumeration.EnumDecoration;
import enumeration.EnumAnimation;
import enumeration.EtatContrat;
import enumeration.typePrestations;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Rigal
 */
public class Contrat implements Serializable {
    private int idContrat;
    private EnumAnimation animation;
    private EnumDecoration decoration;
    private EnumCommunication communication;
    private EnumSecurite securite;
    private float montantGlobal;
    private int nbPersonnes;
    private Client leClient;
    private EtatContrat etat;
    private Date dateHeureDebut;
    private Date dateHeureFin;
    private Salle salle;
    private typePrestations typePresta;
private boolean cocktailMaison;
   
    public boolean isCocktailMaison() {
        return cocktailMaison;
    }

    public void setCocktailMaison(boolean cocktailMaison) {
        this.cocktailMaison = cocktailMaison;
    }

    public Contrat(int idContrat, EnumAnimation animation, EnumDecoration decoration, EnumCommunication communication, EnumSecurite securite, float montantGlobal, int nbPersonnes, Client leClient, EtatContrat etat, Date dateHeureDebut, Date dateHeureFin, Salle salle, typePrestations typePresta, boolean cocktailMaison) {
        this.idContrat = idContrat;
        this.animation = animation;
        this.decoration = decoration;
        this.communication = communication;
        this.securite = securite;
        this.montantGlobal = montantGlobal;
        this.nbPersonnes = nbPersonnes;
        this.leClient = leClient;
        this.etat = etat;
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
        this.salle = salle;
        this.typePresta = typePresta;
        this.cocktailMaison = cocktailMaison;
    }

    @Override
    public String toString() {
        return "Contrat{" + "idContrat=" + idContrat + ", animation=" + animation + ", decoration=" + decoration + ", communication=" + communication + ", securite=" + securite + ", montantGlobal=" + montantGlobal + ", nbPersonnes=" + nbPersonnes + ", leClient=" + leClient + ", etat=" + etat + ", dateHeureDebut=" + dateHeureDebut + ", dateHeureFin=" + dateHeureFin + ", salle=" + salle + ", typePresta=" + typePresta + ", cocktailMaison=" + cocktailMaison + '}';
    }





    public EnumAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(EnumAnimation animation) {
        this.animation = animation;
    }
    
    
    
    
    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
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


    public EtatContrat getEtat() {
        return etat;
    }

    public int getIdContrat() {
        return idContrat;
    }

    public EnumDecoration getDecoration() {
        return decoration;
    }

    public EnumCommunication getCommunication() {
        return communication;
    }

    public EnumSecurite getSecurite() {
        return securite;
    }

    public float getMontantGlobal() {
        return montantGlobal;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Client getLeClient() {
        return leClient;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }
    
    public void setEtat(EtatContrat etat) {
        this.etat = etat;
    }

    public void setDecoration(EnumDecoration decoration) {
        this.decoration = decoration;
    }

    public void setCommunication(EnumCommunication communication) {
        this.communication = communication;
    }

    public void setSecurite(EnumSecurite securite) {
        this.securite = securite;
    }

    public void setMontantGlobal(float montantGlobal) {
        this.montantGlobal = montantGlobal;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public void setLeClient(Client leClient) {
        this.leClient = leClient;
    }

    public void setTypePresta(typePrestations typePresta) {
        this.typePresta = typePresta;
    }

    public typePrestations getTypePresta() {
        return typePresta;
    }

}
