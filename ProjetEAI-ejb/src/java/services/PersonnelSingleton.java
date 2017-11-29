/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.EnumTypePersonne;
import java.util.ArrayList;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import messages.Personnel;
import messages.Planning;

/**
 *
 * @author Marine
 */
@Singleton
@LocalBean
public class PersonnelSingleton {
    private final ArrayList<Personnel> lesPersonnels;

    public PersonnelSingleton() {
        this.lesPersonnels = new ArrayList<Personnel>();
        this.lesPersonnels.add(new Personnel(1, "Allan", EnumTypePersonne.cuisinier, 20, "06 99 66 31 55"));
        this.lesPersonnels.add(new Personnel(2, "Seb", EnumTypePersonne.agentSecurite, 30, "07 88 56 31 55"));
        this.lesPersonnels.add(new Personnel(3, "Gilles Z.", EnumTypePersonne.DJanimateur, 25, "05 64 56 51 68"));
        this.lesPersonnels.add(new Personnel(4, "Geneviève P.", EnumTypePersonne.decoratrice, 26, "06 96 25 48 75"));
        this.lesPersonnels.add(new Personnel(5, "Janaïs", EnumTypePersonne.personnelDeService, 18, "08 64 84 66 48"));
        this.lesPersonnels.add(new Personnel(7, "Madame Rigalade", EnumTypePersonne.personnelDeService, 25, "07 64 84 56 48"));
        this.lesPersonnels.add(new Personnel(8, "Gourgandine", EnumTypePersonne.personnelDeService, 15, "06 88 87 76 48"));
        this.lesPersonnels.add(new Personnel(6, "Gertrude", EnumTypePersonne.preparateurSalle, 18, "08 64 84 66 48"));
        this.lesPersonnels.add(new Personnel(9, "Vivien", EnumTypePersonne.preparateurSalle, 28, "06 64 54 56 98"));
        this.lesPersonnels.add(new Personnel(10, "Ky Ky", EnumTypePersonne.cuisinier, 30, "07 56 84 76 96"));
        this.lesPersonnels.add(new Personnel(11, "Arnaud", EnumTypePersonne.preparateurCocktail, 15, "07 56 84 76 96"));
        this.lesPersonnels.add(new Personnel(12, "Hodor", EnumTypePersonne.agentSecurite, 20, "07 86 94 25 69"));
        this.lesPersonnels.add(new Personnel(13, "Arnaud", EnumTypePersonne.preparateurCocktail, 18, "06 36 74 76 96"));
        this.lesPersonnels.add(new Personnel(14, "Le gars qui fait des cocktails dégueux", EnumTypePersonne.preparateurCocktail, 19, "06 36 74 76 96"));
        this.lesPersonnels.add(new Personnel(15, "Le gars qui est DJ mais ne veut pas animer", EnumTypePersonne.DJ, 28, "06 36 74 76 96"));
        this.lesPersonnels.add(new Personnel(16, "Norman fait des videos", EnumTypePersonne.photographeVideaste, 28, "06 56 75 76 96"));
        this.lesPersonnels.add(new Personnel(17, "Andy Warold", EnumTypePersonne.photographeVideaste, 38, "06 45 44 76 97"));
        this.lesPersonnels.add(new Personnel(18, "Hulk", EnumTypePersonne.agentSecurite, 30, "06 86 54 25 69"));
  
    }

    public ArrayList< Personnel> getPersonnel() {
        return lesPersonnels;
    }
    
    public void addPersonnel( Personnel p) {
        lesPersonnels.add(p);
    }
     public void removePersonnel( Personnel p) {
        lesPersonnels.remove(p);
    }
}
