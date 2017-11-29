/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import enumeration.typeMP;

/**
 *
 * @author Nolwenn PILLARD
 */
public class Matiere1ere {
    
    private int idMP;
    private int nbStock;
    private float prixMP;
    private typeMP typeMp;
    
    public Matiere1ere(int idMP, int nbStock, float prixMP, typeMP typeMp) {
        this.idMP = idMP;
        this.nbStock = nbStock;
        this.prixMP = prixMP;
        this.typeMp = typeMp;
    }

    public int getIdMP() {
        return idMP;
    }

    public int getNbStockMp() {
        return nbStock;
    }

    public float getPrixM() {
        return prixMP;
    }

    public typeMP getTypeMp() {
        return typeMp;
    }

    public void setIdMP(int idMP) {
        this.idMP = idMP;
    }

    public void setNbStock(int nbStock) {
        this.nbStock = nbStock;
    }

    public void setPrixMP(float prixMP) {
        this.prixMP = prixMP;
    }

    public void setTypeMp(typeMP typeMp) {
        this.typeMp = typeMp;
    }

    @Override
    public String toString() {
        return "Matiere1ere{" + "idMP=" + idMP + ", nbStock=" + nbStock + ", prixMP=" + prixMP + ", typeMp=" + typeMp + '}';
    }
    
}
