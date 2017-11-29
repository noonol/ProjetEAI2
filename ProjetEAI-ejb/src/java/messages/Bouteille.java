/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import enumeration.typeBouteille;

/**
 *
 * @author Nolwenn PILLARD
 */
public class Bouteille {

    public Bouteille(int idBouteille, int nbStockB, float prixB, typeBouteille typeB) {
        this.idBouteille = idBouteille;
        this.nbStockB = nbStockB;
        this.prixB = prixB;
        this.typeB = typeB;
    }

    public int getIdBouteille() {
        return idBouteille;
    }

    public int getNbStockB() {
        return nbStockB;
    }

    public float getPrixB() {
        return prixB;
    }

    public typeBouteille getTypeB() {
        return typeB;
    }

    public void setIdBouteille(int idBouteille) {
        this.idBouteille = idBouteille;
    }

    public void setNbStockB(int nbStockB) {
        this.nbStockB = nbStockB;
    }

    public void setPrixB(float prixB) {
        this.prixB = prixB;
    }

    public void setTypeB(typeBouteille typeB) {
        this.typeB = typeB;
    }

    @Override
    public String toString() {
        return "Bouteille{" + "idBouteille=" + idBouteille + ", nbStockB=" + nbStockB + ", prixB=" + prixB + ", typeB=" + typeB + '}';
    }
    private int idBouteille;
    private int nbStockB;
    private float prixB;
    private typeBouteille typeB;
}
