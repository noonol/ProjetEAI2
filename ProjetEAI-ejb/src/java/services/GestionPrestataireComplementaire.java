/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.EnumSecurite;
import enumeration.EnumTypePersonne;
import enumeration.EnumAnimation;
import enumeration.EnumCommunication;
import enumeration.EnumDecoration;
import enumeration.EtatContrat;
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
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;
import messages.Contrat;
import messages.Planning;
import messages.Prestataire;
/**
 *
 * @author Marine
 */
@Singleton
@LocalBean
public class GestionPrestataireComplementaire implements MessageListener   {
private final float montantSAGEM = 306.5f; 
    @Resource(lookup = "jms/TopicContrat")
    private Topic topic;
    @EJB
    GestionPersonnel gestionPersonnel;
    @EJB
    PrestataireSingleton prestataires;
    @EJB
    PlanningSingleton plannings;
    @Inject    
    private JMSContext context;
    @Resource(lookup = "jms/montantPrevuFile")
    private Queue file;
    @Override
    public void onMessage(Message message) {
      
      if (message instanceof ObjectMessage) {
            try {
                ObjectMessage om = (ObjectMessage) message;
                Object obj = om.getObject();
                if (obj instanceof Contrat) {
                    Contrat c = (Contrat) obj;
                    if (c.getEtat().equals(EtatContrat.gestion_prestataire_complementaire_creer)) {
                        float montantSup = 0.0f;
                        int effectif = 0 ;
                        //Partie sécurité ///////////////////////////////////////////////////////////////
                        if(c.getSecurite().equals(EnumSecurite.accesSalle)
                                ||c.getSecurite().equals(EnumSecurite.gardiennageParking)){
                                if (c.getNbPersonnes()>0 &&c.getNbPersonnes()<50){
                                    effectif = 1;
                                }else if (c.getNbPersonnes()>50 &&c.getNbPersonnes()<100){
                                    effectif = 2;
                                }else if (c.getNbPersonnes()>150){
                                    effectif = 3;
                                } 
                        } else if (c.getSecurite().equals(EnumSecurite.salleParking)){
                                 if (c.getNbPersonnes()>0 &&c.getNbPersonnes()<50){
                                    effectif = 2;
                                } else if (c.getNbPersonnes()>50 &&c.getNbPersonnes()<100){
                                    effectif = 4;
                                } else if (c.getNbPersonnes()>150){
                                    effectif = 6;
                                } 
                        }
                        try {
                          //  c.setEtat(EtatContrat.gestion_personnel_creer);
                            ObjectMessage o = context.createObjectMessage(c);
                            context.createProducer().send(topic, o);
                            montantSup += gestionPersonnel.prévoirPersonnel(EnumTypePersonne.agentSecurite, effectif);    
                        } catch (ExceptionPersonnelNonTrouve ex) {
                                try {
                                    throw new ExceptionPersonnelNonTrouve();
                                } catch (ExceptionPersonnelNonTrouve ex1) {
                                    Logger.getLogger(GestionPrestataireComplementaire.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                        }
                        //DJ ou DJ animateur ou ornement basique ou photo ou video ///////////////////////////////////////////////////////////////
                        if (c.getAnimation().equals(EnumAnimation.Disco)){
                            // c.setEtat(EtatContrat.gestion_personnel_creer);
                            ObjectMessage o = context.createObjectMessage(c);
                            context.createProducer().send(topic, o);
                            try {
                                montantSup+=gestionPersonnel.prévoirPersonnel(EnumTypePersonne.DJ, 1);
                                montantSup += montantSAGEM;
                            } catch (ExceptionPersonnelNonTrouve ex) {
                                try {
                                    throw new ExceptionPersonnelNonTrouve();
                                } catch (ExceptionPersonnelNonTrouve ex1) {
                                    Logger.getLogger(GestionPrestataireComplementaire.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        }
                        if (c.getAnimation().equals(EnumAnimation.DiscoAnimation)){
                            // c.setEtat(EtatContrat.gestion_personnel_creer);
                            ObjectMessage o = context.createObjectMessage(c);
                            context.createProducer().send(topic, o);
                            try {
                                montantSup+=gestionPersonnel.prévoirPersonnel(EnumTypePersonne.DJanimateur, 1);
                                 montantSup += montantSAGEM;
                            } catch (ExceptionPersonnelNonTrouve ex) {
                                 try {
                                    throw new ExceptionPersonnelNonTrouve();
                                } catch (ExceptionPersonnelNonTrouve ex1) {
                                    Logger.getLogger(GestionPrestataireComplementaire.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        }
                        if (c.getDecoration().equals(EnumDecoration.simple)){
                            // c.setEtat(EtatContrat.gestion_personnel_creer);
                            ObjectMessage o = context.createObjectMessage(c);
                            context.createProducer().send(topic, o);
                            try {
                                montantSup+=gestionPersonnel.prévoirPersonnel(EnumTypePersonne.fleuriste, 1);
                                 montantSup += montantSAGEM;
                            } catch (ExceptionPersonnelNonTrouve ex) {
                                try {
                                    throw new ExceptionPersonnelNonTrouve();
                                } catch (ExceptionPersonnelNonTrouve ex1) {
                                    Logger.getLogger(GestionPrestataireComplementaire.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        }
                        if (c.getCommunication().equals(EnumCommunication.photos)|| c.getCommunication().equals(EnumCommunication.videos)||c.getCommunication().equals(EnumCommunication.photosVideos)){
                            // c.setEtat(EtatContrat.gestion_personnel_creer);
                            ObjectMessage o = context.createObjectMessage(c);
                            context.createProducer().send(topic, o);
                            
                            try {
                                montantSup+=gestionPersonnel.prévoirPersonnel(EnumTypePersonne.photographeVideaste, 1);
                            } catch (ExceptionPersonnelNonTrouve ex) {
                                 try {
                                    throw new ExceptionPersonnelNonTrouve();
                                } catch (ExceptionPersonnelNonTrouve ex1) {
                                    Logger.getLogger(GestionPrestataireComplementaire.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                                 montantSup += montantSAGEM;
                        }
                        //Orchestre ou Groupe
                        if (c.getAnimation().equals(EnumAnimation.Orchestre)){
                            boolean personneDispo = true;
                            boolean personneTrouvee = false;
                            for ( Prestataire p : prestataires.getPrestataire()){
                                 personneDispo = true;
                                if( p.getType().equals(EnumTypePersonne.orchestre) && !personneTrouvee ){
                                    for(Planning pa : plannings.getPlanning() ){
                                        if(pa.getLaPersonne().equals(p)
                                         && c.getDateHeureDebut().compareTo(pa.getDateHeureDebut()) >= 0
                                         && c.getDateHeureFin().compareTo(pa.getDateHeureFin()) <= 0){
                                        personneDispo = false; 
                                    }
                                    }
                                }
                                if(personneDispo && !personneTrouvee ){
                                    personneTrouvee = true;
                                    plannings.addPlanning(new Planning(c.getDateHeureDebut(), c.getDateHeureFin(), p, c, c.getSalle()));
                                    montantSup +=p.getTarif();
                                }
                            }
                            if (!personneTrouvee){
                                try {
                                    throw new ExceptionPersonnelNonTrouve();
                                } catch (ExceptionPersonnelNonTrouve ex1) {
                                    Logger.getLogger(GestionPrestataireComplementaire.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                            
                        }
                        if (c.getAnimation().equals(EnumAnimation.GroupeMusical)){
                            boolean personneDispo = true;
                            boolean personneTrouvee = false;
                            for ( Prestataire p : prestataires.getPrestataire()){
                                 personneDispo = true;
                                if( p.getType().equals(EnumTypePersonne.groupeDeMusique) && !personneTrouvee ){
                                    for(Planning pa : plannings.getPlanning() ){
                                        if(pa.getLaPersonne().equals(p)
                                         && c.getDateHeureDebut().compareTo(pa.getDateHeureDebut()) >= 0
                                         && c.getDateHeureFin().compareTo(pa.getDateHeureFin()) <= 0){
                                        personneDispo = false; 
                                    }
                                    }
                                }
                                if(personneDispo && !personneTrouvee ){
                                    personneTrouvee = true;
                                    plannings.addPlanning(new Planning(c.getDateHeureDebut(), c.getDateHeureFin(), p, c, c.getSalle()));
                                    montantSup +=p.getTarif();
                                }
                            }
                            if (!personneTrouvee){
                                try {
                                    throw new ExceptionPersonnelNonTrouve();
                                } catch (ExceptionPersonnelNonTrouve ex1) {
                                    Logger.getLogger(GestionPrestataireComplementaire.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        }
                        //fleuriste 
                        if (c.getDecoration().equals(EnumDecoration.florale)){
                            boolean personneDispo = true;
                            boolean personneTrouvee = false;
                            for ( Prestataire p : prestataires.getPrestataire()){
                                 personneDispo = true;
                                if( p.getType().equals(EnumTypePersonne.fleuriste) && !personneTrouvee ){
                                    for(Planning pa : plannings.getPlanning() ){
                                        if(pa.getLaPersonne().equals(p)
                                         && c.getDateHeureDebut().compareTo(pa.getDateHeureDebut()) >= 0
                                         && c.getDateHeureFin().compareTo(pa.getDateHeureFin()) <= 0){
                                        personneDispo = false; 
                                    }
                                    }
                                }
                                if(personneDispo && !personneTrouvee ){
                                    personneTrouvee = true;
                                    plannings.addPlanning(new Planning(c.getDateHeureDebut(), c.getDateHeureFin(), p, c, c.getSalle()));
                                    montantSup +=p.getTarif();
                                }
                            }
                            if (!personneTrouvee){
                                try {
                                    throw new ExceptionPersonnelNonTrouve();
                                } catch (ExceptionPersonnelNonTrouve ex1) {
                                    Logger.getLogger(GestionPrestataireComplementaire.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        }
                    c.setEtat(enumeration.EtatContrat.validé);
                    ObjectMessage o = context.createObjectMessage(c);
                    context.createProducer().send(topic, o);  
                     o = context.createObjectMessage(montantSup);
                    context.createProducer().send(file, o);  
                    }
                }
            } catch (JMSException ex) {
            }
        
      }    
           
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
