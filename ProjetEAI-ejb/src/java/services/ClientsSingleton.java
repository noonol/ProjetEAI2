/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.EnumCommunication;
import enumeration.EnumDecoration;
import enumeration.EnumSecurite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;

import messages.Client;
import messages.Contrat;

/**
 *
 * @author Nolwenn PILLARD
 */
@Singleton
@LocalBean
public class ClientsSingleton {

    private HashMap<Integer, Client> clients = new HashMap<>();

    public ClientsSingleton() {
        clients = new HashMap<>();
        // creation des articles
        
        Client c1 = new Client(1, "T.Desprats", "23 rue des coquelicots, 31530 Lévignac, Colocation 6b", "mdp");
        Client c2 = new Client(2, "C.Teyssié", "23 rue des coquelicots, 31530 Lévignac, Colocation 6b", "mdp");
        clients.put(c1.getIdClient(), c1);
        clients.put(c2.getIdClient(), c2);
    }

    public Collection<Client> getClients() {
        return clients.values();
    }
    
    public Client getClient(Integer idClt) {
        return clients.get(idClt);
    }

}
