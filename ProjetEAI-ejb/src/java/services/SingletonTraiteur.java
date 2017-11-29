/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import messages.Client;
import messages.Repas;
import messages.Traiteur;

/**
 *
 * @author Nolwenn PILLARD
 */
@Stateless
@LocalBean
public class SingletonTraiteur {

    private HashMap<Integer, Traiteur> traiteurs;

    public SingletonTraiteur() {
        traiteurs = new HashMap<>();

        Traiteur c1 = new Traiteur(1, "Dupont", "05.45.45.45.45", "1 rue des roses, 31000 Toulouse");
        Traiteur c2 = new Traiteur(2, "Durand", "05.50.50.45.45", "2 rue des pigeons, 31000 Toulouse");
        traiteurs.put(c1.getIdTraiteur(), c1);
        traiteurs.put(c2.getIdTraiteur(), c2);
    }

    public Collection<Traiteur> getTraiteurs() {
        return traiteurs.values();
    }

    public Traiteur getTraiteur(Integer idTrait) {
        return traiteurs.get(idTrait);
    }
}
