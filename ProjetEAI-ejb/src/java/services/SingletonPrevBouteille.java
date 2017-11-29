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
import messages.prevision_Bouteille;

/**
 *
 * @author Nolwenn PILLARD
 */
@Stateless
@LocalBean
public class SingletonPrevBouteille {

    private static int compteur;
    
    private HashMap<Integer, prevision_Bouteille> lesPrevBouteille;

    public SingletonPrevBouteille() {
        lesPrevBouteille = new HashMap<>();
        compteur = 0;
    }

    public HashMap<Integer, prevision_Bouteille> getLesPrevBouteille() {
        return lesPrevBouteille;
    }


    public Integer getSizeLesRelesPrevBouteillepas() {
        return lesPrevBouteille.size();
    }

    void add(prevision_Bouteille r) {
        compteur = compteur + 1;
        lesPrevBouteille.put(compteur, r);
    }
}
