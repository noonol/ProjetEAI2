/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.EnumCommunication;
import enumeration.EnumDecoration;
import enumeration.EnumSecurite;
import enumeration.EtatContrat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import messages.Client;
import messages.Contrat;
import messages.Salle;

/**
 *
 * @author Nolwenn PILLARD
 */
@Singleton
@LocalBean

@DependsOn("ClientsSingleton")
public class ContratsSingleton {

    @EJB
    ClientsSingleton clients;

    private HashMap<Integer, Contrat> contrats;

    public ContratsSingleton() {
        contrats = new HashMap<>();
    }

    public HashMap<Integer, Contrat> getContrats() {
        return contrats;
    }

    public Contrat getContrat(Integer idContrat) {
        return contrats.get(idContrat);
    }

    void add(Contrat c) {
        contrats.put(c.getIdContrat(), c);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getSizeContrat() {
        return contrats.size();
    }
}
