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
public class prevision_Bouteille {
    
    private int nbPrevuB;
    private int idRepas;
    private int idBouteille;

    public prevision_Bouteille(int nbPrevuB, int idRepas, int idBouteille) {
        this.nbPrevuB = nbPrevuB;
        this.idRepas = idRepas;
        this.idBouteille = idBouteille;
    }

    public int getNbPrevuB() {
        return nbPrevuB;
    }

    public int getidRepas() {
        return idRepas;
    }

    public int getidBouteille() {
        return idBouteille;
    }

    public void setNbPrevuB(int nbPrevuB) {
        this.nbPrevuB = nbPrevuB;
    }

    public void setidRepas(int idrepas) {
        this.idRepas = idrepas;
    }

    public void setidBouteille(int idbouteille) {
        this.idBouteille = idbouteille;
    }

    @Override
    public String toString() {
        return "prevision_Bouteille{" + "nbPrevuB=" + nbPrevuB + ", repas=" + idRepas + ", bouteille=" + idBouteille + '}';
    }

}
