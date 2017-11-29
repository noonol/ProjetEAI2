/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.ExceptionAucuneSalleTrouvee;
import enumeration.EtatContrat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;
import messages.Contrat;
import messages.Salle;
import messages.Planning;

/**
 *
 * @author Rigal
 */
@Singleton
@LocalBean
public class GestionSalle {
    private final ArrayList<Salle>lesSalles = new ArrayList<>();
    
    @EJB
    PlanningSingleton plannings;
    
    @EJB
    SalleSingleton salles;
    
    @Resource(lookup = "jms/TopicContrat")
    private Topic topic;
    @Inject
    private JMSContext context;
     @Resource(lookup = "jms/montantPrevuFile")
    private Queue file;
    /*@Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) message;
                 Object obj = om.getObject();
                 if (obj instanceof Contrat) {
                     Contrat c = (Contrat) obj;
                     Salle s = (Salle) obj;
                     if (c.getEtat().equals(EtatContrat.gestion_salle)){
                         int idS = s.getIdSalle();
                         
                         System.out.println("Salle réservée !");
                     }
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(GestionProjet.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }//fin onMessage*/
    
    public String reserverSalle(Contrat unContrat) throws ExceptionAucuneSalleTrouvee{
        //Dans Planning
            //si je trouve un creneau libre correspondant à celui du contrat
                //alors je mets à jour le planning en mettant le créneau comme occupé
                // et j'envoie confirmationRéservation à la gestion de projets
            //sinon je retourne une erreur "aucun créneau correspondant n'est dispo
        Message m = context.createConsumer(topic).receive();
        String nomSalle = "";
        if (m instanceof ObjectMessage) {
            try {
                ObjectMessage om = (ObjectMessage) m;
                Object obj = om.getObject();
                if (obj instanceof Contrat) {
                    unContrat = (Contrat) obj;
                    Salle sallechoisie = null;
                    for (int i = 0; i < salles.getLesSalles().size(); i++) {
                        Salle s = salles.getLesSalles().get(i);
                        if (s.getCapaciteS() >= unContrat.getNbPersonnes()) {
                            boolean salleOccupee = false;
                            for (Planning p : plannings.getPlanning()) {
                                if (p.getDateHeureDebut().compareTo(unContrat.getDateHeureDebut()) >= 0 && p.getDateHeureFin().compareTo(unContrat.getDateHeureFin()) <= 0) {
                                    salleOccupee = true;
                                }
                            }
                            //si la salle est ok, on réserve le créneau
                            if (!salleOccupee) {
                                unContrat.setSalle(s);
                                ObjectMessage omToSend = context.createObjectMessage(unContrat);
                                context.createProducer().send(topic, omToSend);
                                
                                // Nolwenn !!! :) Ici on envois le montant à la file que gestion de projet récupérera
                                omToSend = context.createObjectMessage(s.getTarifS());
                                context.createProducer().send(file, omToSend);
                                nomSalle = s.getNomSalle();
                                ///////////////////////////////////////////////////////////////////////////////////
                                
                                sallechoisie = s;
                            }
                        }

                    }
                    //si aucune salle n'est trouvée, on lance une exception
                        if (sallechoisie!=null) {
                        } else {
                            throw new ExceptionAucuneSalleTrouvee();
                        }
                }
            } catch (JMSException ex) {
                Logger.getLogger(GestionProjet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
       return nomSalle;  
    }
  
    
}
