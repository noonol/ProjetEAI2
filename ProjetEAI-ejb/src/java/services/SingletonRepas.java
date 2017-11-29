/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.HashMap;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import messages.Contrat;
import messages.Repas;

/**
 *
 * @author Nolwenn PILLARD
 */
@Singleton
@LocalBean
public class SingletonRepas {

    private HashMap<Integer, Repas> lesRepas;

    public SingletonRepas() {
        lesRepas = new HashMap<>();
    }

    public HashMap<Integer, Repas> getRepas() {
        return lesRepas;
    }

    public Repas getRepas(Integer idRepas) {
        return lesRepas.get(idRepas);
    }

    public Repas getRepasDuContrat(Integer idContrat) {
        Repas MonRepas = null;
        for (int i = 0; i < lesRepas.size(); i++) {
            if (lesRepas.get(i).getIdContrat() == idContrat) {
                MonRepas = lesRepas.get(i);
            }
        }
        return MonRepas;
    }

    public Integer getSizeLesRepas() {
        return lesRepas.size();
    }

    void add(Repas r) {
        lesRepas.put(r.getIdContrat(), r);

    }

}
