/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.typeMP;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import messages.Matiere1ere;
import messages.Prevision_MatMP;
import messages.prevision_Bouteille;

/**
 *
 * @author Nolwenn PILLARD
 */
@Stateless
@LocalBean
public class SingletonPrevMP {

     private static int compteur;
    
    private HashMap<Integer, Prevision_MatMP> lesPrevMP;

    public SingletonPrevMP() {
        lesPrevMP = new HashMap<>();
        compteur = 0;
    }

    public HashMap<Integer, Prevision_MatMP> getLesPrevBouteille() {
        return lesPrevMP;
    }


    public Integer getSizeLesRelesPrevBouteillepas() {
        return lesPrevMP.size();
    }

    void add(Prevision_MatMP r) {
        compteur = compteur + 1;
        lesPrevMP.put(compteur, r);
    }
}
