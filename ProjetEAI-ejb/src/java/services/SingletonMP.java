/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.typeBouteille;
import enumeration.typeMP;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import messages.Bouteille;
import messages.Matiere1ere;
import messages.Repas;

/**
 *
 * @author Nolwenn PILLARD
 */
@Stateless
@LocalBean
public class SingletonMP {

    private HashMap<Integer, Matiere1ere> lesMP;

    public SingletonMP() {
        lesMP = new HashMap<>();
        Matiere1ere bout1 = new Matiere1ere(1,20, 2.3f, typeMP.canapé);
        Matiere1ere bout2 = new Matiere1ere(2,24, 5.3f, typeMP.verrine);
        Matiere1ere bout3 = new Matiere1ere(3,22, 3.3f, typeMP.platFroid);
        this.lesMP.put(bout1.getIdMP(), bout1);
        this.lesMP.put(bout2.getIdMP(), bout2);
        this.lesMP.put(bout3.getIdMP(), bout3);
    }

    public HashMap<Integer, Matiere1ere> getMP() {
        return lesMP;
    }

    public Matiere1ere getMP(Integer idMP) {
        return lesMP.get(idMP);
    }
    
    public Integer getSizeLesMPs() {
        return lesMP.size();
    }

    void add(Matiere1ere r) {
        lesMP.put(r.getIdMP(), r);
    }
    
     public int getStockMp(typeMP type) {
        int value = 0;
        for (int i = 1; i <= getSizeLesMPs(); i++) {
            Matiere1ere mp = getMP(i);
            if (mp.getTypeMp().equals(type)) { // TODO : regarder si EQUAL marche
                value = mp.getNbStockMp();
            }
        }
        return value;
    }
    
    public int getIdMp(typeMP type) {
        int value = 0;
        for (int i = 1; i <= getSizeLesMPs(); i++) {
            Matiere1ere mp = getMP(i);
            if (mp.getTypeMp().equals(type)) { // TODO : regarder si EQUAL marche
                value = mp.getIdMP();
            }
        }
        return value;
    }

    public float getPrixMp(typeMP type) {
        float value = 0;
        for (int i = 1; i <= getSizeLesMPs(); i++) {
            Matiere1ere mp = getMP(i);
            if (mp.getTypeMp().equals(type)) { // TODO : regarder si EQUAL marche
                value = mp.getPrixM();
            }
        }
        return value;
    }

    public void decrementerStockMP(typeMP type, int nb) {
        for (int i = 1; i <= getSizeLesMPs(); i++) {
            Matiere1ere MP = getMP(i);
            if (MP.getTypeMp().equals(type)) { // TODO : regarder si EQUAL marche
                MP.setNbStock(MP.getNbStockMp()- nb);
            }
        }
    }
}
