/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.typeBouteille;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import messages.Bouteille;
import messages.Personnel;
import messages.Repas;

/**
 *
 * @author Nolwenn PILLARD
 */
@Stateless
@LocalBean
public class SingletonBouteille {

    private HashMap<Integer, Bouteille> lesBouteilles;

    public SingletonBouteille() {
        lesBouteilles = new HashMap<>();
        Bouteille bout1 = new Bouteille(1, 12, 5.6f, typeBouteille.eauPlate);
        Bouteille bout2 = new Bouteille(2, 12, 3.6f, typeBouteille.eauGazeuse);
        Bouteille bout3 = new Bouteille(3, 12, 4.6f, typeBouteille.apero1);
        Bouteille bout4 = new Bouteille(4, 12, 6.6f, typeBouteille.apero2);
        Bouteille bout5 = new Bouteille(5, 12, 2.6f, typeBouteille.apero3);
        Bouteille bout6 = new Bouteille(6, 12, 1.6f, typeBouteille.jusMultiFruits);
        Bouteille bout7 = new Bouteille(7, 12, 2.6f, typeBouteille.vins);
        this.lesBouteilles.put(bout1.getIdBouteille(), bout1);
        this.lesBouteilles.put(bout2.getIdBouteille(), bout2);
        this.lesBouteilles.put(bout3.getIdBouteille(), bout3);
        this.lesBouteilles.put(bout4.getIdBouteille(), bout4);
        this.lesBouteilles.put(bout5.getIdBouteille(), bout5);
        this.lesBouteilles.put(bout6.getIdBouteille(), bout6);
        this.lesBouteilles.put(bout7.getIdBouteille(), bout7);
    }

    public HashMap<Integer, Bouteille> getBouteilles() {
        return lesBouteilles;
    }

    public Bouteille getBouteille(Integer idBouteille) {
        return lesBouteilles.get(idBouteille);
    }

    public Integer getSizeLesBouteilles() {
        return lesBouteilles.size();
    }

    void add(Bouteille r) {
        lesBouteilles.put(r.getIdBouteille(), r);
    }

    public int getStockBouteille(typeBouteille type) {
        int value = 0;
        for (int i = 1; i <= getSizeLesBouteilles(); i++) {
            Bouteille bout = getBouteille(i);
            if (bout.getTypeB().equals(type)) { // TODO : regarder si EQUAL marche
                value = bout.getNbStockB();
            }
        }
        return value;
    }
    
        public int getIdBouteille(typeBouteille type) {
        int value = 0;
        for (int i = 1; i <= getSizeLesBouteilles(); i++) {
            Bouteille bout = getBouteille(i);
            if (bout.getTypeB().equals(type)) { // TODO : regarder si EQUAL marche
                value = bout.getIdBouteille();
            }
        }
        return value;
    }

    public float getPrixBouteille(typeBouteille type) {
        float value = 0;
        for (int i = 1; i <= getSizeLesBouteilles(); i++) {
            Bouteille bout = getBouteille(i);
            if (bout.getTypeB().equals(type)) { // TODO : regarder si EQUAL marche
                value = bout.getPrixB();
            }
        }
        return value;
    }

    public void decrementerStockBouteille(typeBouteille type, int nb) {
        for (int i = 1; i <= getSizeLesBouteilles(); i++) {
            Bouteille bout = getBouteille(i);
            if (bout.getTypeB().equals(type)) { // TODO : regarder si EQUAL marche
                bout.setNbStockB(bout.getNbStockB() - nb);
            }
        }
    }
}
