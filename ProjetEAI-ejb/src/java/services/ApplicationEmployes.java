/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.EnumAnimation;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

import messages.Client;
import messages.Contrat;
import enumeration.EnumCommunication;
import enumeration.EnumDecoration;
import enumeration.EnumSecurite;
import enumeration.EtatContrat;
import enumeration.typePrestations;
import exceptions.ExceptionTropicDejaUtilise;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import messages.Salle;

//import javax.ejb.Stateless;
/**
 *
 * @author Nolwenn PILLARD
 */
@Singleton
@LocalBean
public class ApplicationEmployes {

    @Resource(lookup = "jms/TopicContrat")
    private Topic topic;
    @Inject
    private JMSContext context;

    @EJB
    ClientsSingleton clients;

    @EJB
    ContratsSingleton contrats;

    @EJB
    SalleSingleton salles;

    public void creerContrat(int idContrat, EnumAnimation animation, EnumDecoration decoration, EnumCommunication communication, EnumSecurite securite, float montantGlobal, int nbPersonnes, Client leClient, Date debut, Date fin, Salle mySalle, typePrestations type, Boolean cocktailMaison) throws ExceptionTropicDejaUtilise {
        // On test qu'on n'a pas déjà un contrat en cours de traitement dans le Topic, avant d'en traiter un autre
        Message m = context.createConsumer(topic).receive();
        boolean stop = false;
        try {
            ObjectMessage om = (ObjectMessage) m;
            Object obj = om.getObject();
            if (obj instanceof Contrat) {
                Contrat cmd = (Contrat) obj;
                if (!cmd.getEtat().equals(EtatContrat.validé) || !cmd.getEtat().equals(EtatContrat.annulé)) {
                    throw new ExceptionTropicDejaUtilise();
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(GestionProjet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Initialisation des listes de contrats
        // On crée un contrat pour en avoir un dans la liste de contrat 
        Date dateHeureDebut = new Date("20170101200000");
        Date dateHeureFin = new Date("20170102200000");
        Contrat c1 = new Contrat(1, EnumAnimation.Disco, EnumDecoration.simple, EnumCommunication.videos, EnumSecurite.accesSalle, 456.70f, 70, clients.getClient(1), EtatContrat.initialise, dateHeureDebut, dateHeureFin, salles.getSalle(1), typePrestations.assis, true);
        contrats.add(c1);

        // On ajoute le contrat que l'on veux créer dans la liste des contrats
        Contrat c = new Contrat(idContrat, animation, decoration, communication, securite, montantGlobal, nbPersonnes, leClient, EtatContrat.initialise, debut, fin, mySalle, type, cocktailMaison);
        c.setEtat(EtatContrat.gestion_projet);
        ObjectMessage om = context.createObjectMessage(c);
        context.createProducer().send(topic, om);
        // salle.reserver();
        contrats.add(c);

    }

}
