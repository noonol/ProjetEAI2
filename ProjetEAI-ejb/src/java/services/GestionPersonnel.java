/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.EnumTypePersonne;
import exceptions.ExceptionPersonnelNonTrouve;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import messages.Contrat;
import messages.Personnel;
import messages.Planning;

/**
 *
 * @author Marine
 */
@Singleton
@LocalBean
public class GestionPersonnel   {
    @Resource(lookup = "jms/TopicContrat")
    private Topic topic;
    @Inject
    private JMSContext context;
    
    @EJB
    PersonnelSingleton personnel;    
  
    @EJB
    PlanningSingleton planning;  
    
    public float prévoirPersonnel(EnumTypePersonne type, int effectif) throws ExceptionPersonnelNonTrouve {
        float retour = 0.0f;
        Message m = context.createConsumer(topic).receive();
        //Récupération du contrat
        if (m instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) m;
                 Object obj = om.getObject();
                 if (obj instanceof Contrat) {
                     
                     Contrat c = (Contrat) obj;
                     int e = effectif;
                     for (int i= 0; i < personnel.getPersonnel().size()&&e!=0;i++){
                        Personnel p = personnel.getPersonnel().get(i);
                         if(p.getType().equals(type)){
                             boolean personnePasDispo = false;
                             for(Planning pa : planning.getPlanning()){
                                 //Si la personne est occuée
                                 if(pa.getLaPersonne().equals(p)
                                         && c.getDateHeureDebut().compareTo(pa.getDateHeureDebut()) >= 0
                                         && c.getDateHeureFin().compareTo(pa.getDateHeureFin()) <= 0){
                                    personnePasDispo = true; 
                                    }
                             }
                             //si la personne est libre, on la reserve sur le creneaux
                             if(!personnePasDispo && e!=0){
                                 planning.addPlanning(new Planning(c.getDateHeureDebut(), c.getDateHeureFin(), p, c, c.getSalle()));
                                 retour +=p.getTarif();
                                 e=-1;
                             }
                         }
                     }
                     //Si personne n'est libre on lance une exception
                     if (e!=0){
                         throw new ExceptionPersonnelNonTrouve();
                     }
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(GestionProjet.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    return retour;
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
