/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import enumeration.typePrestations;
import java.util.Objects;

/**
 *
 * @author Nolwenn PILLARD
 */
public class Repas {

    private int idRepas;
    private typePrestations typePrestation;
    private float montantTraiteur;
    private float montantMP;
    private float montantBouteilles;
    private Traiteur traiteur;
    private int idContrat;
    private Boolean cocktailMaison;

    public void setMontantMP(float montantMP) {
        this.montantMP = montantMP;
    }

    public void setMontantBouteilles(float montantBouteilles) {
        this.montantBouteilles = montantBouteilles;
    }

    public float getMontantMP() {
        return montantMP;
    }

    public float getMontantBouteilles() {
        return montantBouteilles;
    }

    @Override
    public String toString() {
        return "Repas{" + "idRepas=" + idRepas + ", typePrestation=" + typePrestation + ", montantTraiteur=" + montantTraiteur + ", montantMP=" + montantMP + ", montantBouteilles=" + montantBouteilles + ", traiteur=" + traiteur + ", idContrat=" + idContrat + ", cocktailMaison=" + cocktailMaison + '}';
    }

    public Repas(int idRepas, typePrestations typePrestation, float montantTraiteur, float montantMP, float montantBouteilles, Traiteur traiteur, int idContrat, Boolean cocktailMaison) {
        this.idRepas = idRepas;
        this.typePrestation = typePrestation;
        this.montantTraiteur = montantTraiteur;
        this.montantMP = montantMP;
        this.montantBouteilles = montantBouteilles;
        this.traiteur = traiteur;
        this.idContrat = idContrat;
        this.cocktailMaison = cocktailMaison;
    }
    
        public Repas(int idRepas, typePrestations typePrestation, float montantTraiteur, Traiteur traiteur, int idContrat, Boolean cocktailMaison) {
        this.idRepas = idRepas;
        this.typePrestation = typePrestation;
        this.montantTraiteur = montantTraiteur;
        this.traiteur = traiteur;
        this.idContrat = idContrat;
        this.cocktailMaison = cocktailMaison;
    }

    public Boolean getCocktailMaison() {
        return cocktailMaison;
    }

    public void setCocktailMaison(Boolean cocktailMaison) {
        this.cocktailMaison = cocktailMaison;
    }
    
    public int getIdRepas() {
        return idRepas;
    }

    public typePrestations getTypePrestation() {
        return typePrestation;
    }

    public float getMontantTraiteur() {
        return montantTraiteur;
    }

    public Traiteur getTraiteur() {
        return traiteur;
    }

    public int getIdContrat() {
        return idContrat;
    }

    public void setIdRepas(int idRepas) {
        this.idRepas = idRepas;
    }

    public void setTypePrestation(typePrestations typePrestation) {
        this.typePrestation = typePrestation;
    }

    public void setMontantTraiteur(float montantTraiteur) {
        this.montantTraiteur = montantTraiteur;
    }

    public void setTraiteur(Traiteur traiteur) {
        this.traiteur = traiteur;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }
}
