/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

/**
 *
 * @author Nolwenn PILLARD
 */
public class Traiteur {
    
    private int idTraiteur;
    private String nomTraiteur;
    private String numTel;
    private String adresse;

    public Traiteur(int idTraiteur, String nomTraiteur, String numTel, String adresse) {
        this.idTraiteur = idTraiteur;
        this.nomTraiteur = nomTraiteur;
        this.numTel = numTel;
        this.adresse = adresse;
    }

    public int getIdTraiteur() {
        return idTraiteur;
    }

    public String getNomTraiteur() {
        return nomTraiteur;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setIdTraiteur(int idTraiteur) {
        this.idTraiteur = idTraiteur;
    }

    public void setNomTraiteur(String nomTraiteur) {
        this.nomTraiteur = nomTraiteur;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Traiteur{" + "idTraiteur=" + idTraiteur + ", nomTraiteur=" + nomTraiteur + ", numTel=" + numTel + ", adresse=" + adresse + '}';
    }
   
}
