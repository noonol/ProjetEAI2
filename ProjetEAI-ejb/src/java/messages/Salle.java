/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

/**
 *
 * @author Rigal
 */
public class Salle {
    private int idSalle;
    private String nomSalle;
    private boolean isEstrade;
    private boolean isGrandeCuisine;
    private float tarifS;
    private int capaciteS;

    
    
    public Salle(int idSalle, String nomSalle, boolean isEstrade, boolean isGrandeCuisine, float tarifS, int capaciteS) {
        this.idSalle = idSalle;
        this.nomSalle = nomSalle;
        this.isEstrade = isEstrade;
        this.isGrandeCuisine = isGrandeCuisine;
        this.tarifS = tarifS;
        this.capaciteS = capaciteS;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public boolean isIsEstrade() {
        return isEstrade;
    }

    public void setIsEstrade(boolean isEstrade) {
        this.isEstrade = isEstrade;
    }

    public boolean isIsGrandeCuisine() {
        return isGrandeCuisine;
    }

    public void setIsGrandeCuisine(boolean isGrandeCuisine) {
        this.isGrandeCuisine = isGrandeCuisine;
    }

    public float getTarifS() {
        return tarifS;
    }

    public void setTarifS(float tarifS) {
        this.tarifS = tarifS;
    }
    
    public int getCapaciteS() {
        return capaciteS;
    }

    public void setCapaciteS(int capaciteS) {
        this.capaciteS = capaciteS;
    }

    @Override
    public String toString() {
        return "Salle{" + "idSalle=" + idSalle + ", nomSalle=" + nomSalle + ", isEstrade=" + isEstrade + ", isGrandeCuisine=" + isGrandeCuisine + ", tarifS=" + tarifS + ", capacite=" + capaciteS + '}';
    }
    
}
