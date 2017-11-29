/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.EnumTypePersonne;
import java.util.ArrayList;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import messages.Personnel;
import messages.Prestataire;

/**
 *
 * @author Marine
 */
@Singleton
@LocalBean
public class PrestataireSingleton {
    
private final ArrayList<Prestataire> lesPrestataires ;

    public PrestataireSingleton() {
        this.lesPrestataires = new ArrayList<Prestataire>();
        this.lesPrestataires.add(new Prestataire(1, "Madama la Fleur", EnumTypePersonne.fleuriste, 40, "06 99 66 31 55","FVIHRE8565","11 rue de la Fleur 31200 Toulouse"));
        this.lesPrestataires.add(new Prestataire(2, "The Beatles", EnumTypePersonne.groupeDeMusique, 50, "08 88 56 31 99","87GEG85RH7","Liverpool"));
        this.lesPrestataires.add(new Prestataire(3, "La cinquième symphonie", EnumTypePersonne.orchestre, 60, "05 54 56 51 68","FREG8786RZ8788","Poucharamet"));
        this.lesPrestataires.add(new Prestataire(4, "Slipknot", EnumTypePersonne.groupeDeMusique, 56, "06 96 25 48 86","JOJ8789RO868","En enfer"));
        this.lesPrestataires.add(new Prestataire(5, "David Guetta", EnumTypePersonne.groupeDeMusique, 60, "08 64 84 66 48","ÖG96898EOJ","Dans mon jet privé"));
        this.lesPrestataires.add(new Prestataire(7, "Amélie Poulain", EnumTypePersonne.fleuriste, 39, "07 64 84 56 48","OJ868+6OJOV","Paris"));
        this.lesPrestataires.add(new Prestataire(8, "Les gourgandines anonymes de Toulouse", EnumTypePersonne.orchestre, 65, "06 88 87 76 49","M865678J856","Canal du Midi, Toulouse"));
        
    }

    public ArrayList< Prestataire> getPrestataire() {
        return lesPrestataires;
    }
    
    public void addPersonnel( Prestataire p) {
        lesPrestataires.add(p);
    }
     public void removePersonnel( Prestataire p) {
        lesPrestataires.remove(p);
    }  
}
