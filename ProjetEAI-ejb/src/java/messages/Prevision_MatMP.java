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
public class Prevision_MatMP {
    
    private int nbPrevuB;
    private int idrepas;
    private int idMp;

    public Prevision_MatMP(int nbPrevuB, int idRepas, int idMp) {
        this.nbPrevuB = nbPrevuB;
        this.idrepas = idRepas;
        this.idMp = idMp;
    }

    public int getNbPrevuB() {
        return nbPrevuB;
    }

    public int getidRepas() {
        return idrepas;
    }

    public int getidMp() {
        return idMp;
    }

    public void setNbPrevuB(int nbPrevuB) {
        this.nbPrevuB = nbPrevuB;
    }

    public void setidRepas(int idrepas) {
        this.idrepas = idrepas;
    }

    public void setidMp(int idMp) {
        this.idMp = idMp;
    }

    @Override
    public String toString() {
        return "Prevision_MatMP{" + "nbPrevuB=" + nbPrevuB + ", repas=" + idrepas + ", matiere 1ère=" + idMp + '}';
    }

}
