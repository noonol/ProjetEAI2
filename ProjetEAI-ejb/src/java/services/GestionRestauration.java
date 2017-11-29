/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import enumeration.EnumTypePersonne;
import enumeration.EtatContrat;
import enumeration.typePrestations;
import enumeration.typeBouteille;
import enumeration.typeMP;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;
import messages.Contrat;
import messages.Personnel;
import messages.Prevision_MatMP;
import messages.Repas;
import messages.Traiteur;
import messages.prevision_Bouteille;

/**
 *
 * @author Nolwenn PILLARD
 */
@Singleton
@LocalBean
public class GestionRestauration implements MessageListener {

    @Resource(lookup = "jms/TopicContrat")
    private Topic topic;
    @Inject
    private JMSContext context;
    @Resource(lookup = "jms/montantPrevuFile")
    private Queue file;

    @EJB
    ContratsSingleton lesContrats;

    @EJB
    SingletonTraiteur lesTraiteurs;

    @EJB
    SingletonRepas lesRepas;

    @EJB
    SingletonBouteille lesBouteilles;

    @EJB
    SingletonMP lesMPs;

    @EJB
    SingletonPrevMP lesPrevMPs;

    @EJB
    SingletonPrevBouteille lesPrevBouteilles;

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage om = (ObjectMessage) message;
                Object obj = om.getObject();
                if (obj instanceof Contrat) {
                    Contrat monContrat = (Contrat) obj;
                    if (monContrat.getEtat().equals(EtatContrat.gestion_projet_creer)) {
                        GererRestauration(monContrat);
                        System.out.println("Contrat arrivé dans la gestionRestauration pour création de contrat !");
                        ObjectMessage omToSend = context.createObjectMessage(monContrat);
                        context.createProducer().send(topic, omToSend);
                        System.out.println("Contrat traité dans GestionRestauration et renvoye le montant pour Gestion de projet!");
                    }
                }
            } catch (JMSException ex) {
                Logger.getLogger(GestionProjet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void GererRestauration(Contrat monContrat) {
        float montantPrevu = 0;
        float montantTraiteur = 0;
        float montantBouteilles = 0;
        float montantMP = 0;

        if (monContrat.getTypePresta() == typePrestations.assis) {
            // Prévoir traiteur
            montantTraiteur = PrevoirTraiteurMontant(monContrat.getNbPersonnes(), monContrat.getDateHeureDebut(), monContrat.getDateHeureFin());
            // Set montant repas = montant traiteur + 15%
            montantTraiteur = montantTraiteur + ((montantTraiteur * 15) / 100);

            // Pour la gestion du personnel : 
            //  TODO NOL   montantPrevu= montantPrevu + PrévoirPersonnel(EnumTypePersonne.personnelDeService, monContrat.getIdContrat(), 2);
            ObjectMessage om = context.createObjectMessage(monContrat);
            context.createProducer().send(topic, om);
        } else {
            // Calcul du nombre nécessaire de bouteilles et matieres premieres
            Integer nbBouteillesNecessaires = CalculNbBouteilles(monContrat.getDateHeureDebut(), monContrat.getDateHeureFin(), monContrat.getNbPersonnes());
            Integer nbMpNecessaires = CalculNbMP(monContrat.getDateHeureDebut(), monContrat.getDateHeureFin(), monContrat.getNbPersonnes());
            // On crée le repas 
            Repas monRepas = new Repas(lesRepas.getSizeLesRepas() + 1, monContrat.getTypePresta(), montantTraiteur, lesTraiteurs.getTraiteur(1), monContrat.getIdContrat(), monContrat.isCocktailMaison());
            lesRepas.add(monRepas);
            if (monRepas.getCocktailMaison() == true) {
                //   TODO NOL    PrévoirPersonnel(EnumTypePersonne.preparateurCocktail, monContrat.getIdContrat(), 2);
                ObjectMessage om2 = context.createObjectMessage(monContrat);
                context.createProducer().send(topic, om2);
            }
            if (monRepas.getTypePrestation() == typePrestations.cocktail) {
                // réserve les bouteilles
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.eauPlate, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.eauGazeuse, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.apero1, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.apero2, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.apero3, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.jusMultiFruits, nbBouteillesNecessaires, monRepas.getIdRepas());
                // réserve les matières premieres
                montantMP = montantMP + PrevoirLesMPsDeType(typeMP.canapé, nbMpNecessaires, monRepas.getIdRepas());
                montantMP = montantMP + PrevoirLesMPsDeType(typeMP.verrine, nbMpNecessaires, monRepas.getIdRepas());
            }
            if (monRepas.getTypePrestation() == typePrestations.lunch) {
                // réserve les bouteilles
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.eauPlate, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.eauGazeuse, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.apero1, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.apero2, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.apero3, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.jusMultiFruits, nbBouteillesNecessaires, monRepas.getIdRepas());
                montantBouteilles = montantBouteilles + PrevoirLesBouteillesDeType(typeBouteille.vins, nbBouteillesNecessaires, monRepas.getIdRepas());
                // réserve les matières premieres
                montantMP = montantMP + PrevoirLesMPsDeType(typeMP.platFroid, nbMpNecessaires, monRepas.getIdRepas());
            }

            monRepas.setMontantTraiteur(montantPrevu);
            monRepas.setMontantBouteilles(montantBouteilles);
            monRepas.setMontantMP(montantMP);
            montantPrevu = montantPrevu + montantTraiteur + montantMP + montantBouteilles;
            // TODO  montant a contrat/gestion de projet 
            ObjectMessage omToSend = context.createObjectMessage(monContrat);
            context.createProducer().send(topic, omToSend);

            // Nolwenn !!! :) Ici on envois le montant à la file que gestion de projet récupérera
            omToSend = context.createObjectMessage(montantPrevu);
            context.createProducer().send(file, omToSend);
        }
        // TODO laisser ou pas ?
        monContrat.setEtat(EtatContrat.gestion_restauration_creer);
    }

    // S'occupe de prévoir les bouteilles. Si pas assez un message dit qu'il faut commander XXX bouteilles
    public float PrevoirLesBouteillesDeType(typeBouteille typeBout, int nbBouteillesNecessaires, int idRepas) {
        int bouteilleToCommand = RegarderSiAssezBout(typeBout, nbBouteillesNecessaires);
        if (bouteilleToCommand != 0) {
            // pas assez en stock :  il faut commander TODO : regarder si on affiche ici ou aillerus
            System.out.println("Il faut commander : " + bouteilleToCommand + " bouteilles de type : " + typeBout);
        }
        return CreerPrevisionBouteille(typeBout, nbBouteillesNecessaires, idRepas);
    }

    // Decremente le stock disponible de Bouteille + creer une prévision de Bouteille associée au repas, retourne le montant des Bouteille
    public float CreerPrevisionBouteille(typeBouteille typeB, int nb, int idRepas) {
        lesBouteilles.decrementerStockBouteille(typeB, nb);
        lesPrevBouteilles.add(new prevision_Bouteille(nb, idRepas, lesBouteilles.getIdBouteille(typeB)));
        float montant = lesBouteilles.getPrixBouteille(typeB) * nb;
        return montant;
    }

    // regarde s'il y a assez de bouteilles disponibles
    public int RegarderSiAssezBout(typeBouteille typeB, int nb) {
        int nbStock = lesBouteilles.getStockBouteille(typeB);
        if (nb <= nbStock) {
            //ok
            return 0;
        } else {
            //NOK 
            return nb - nbStock;
        }
    }

    // S'occupe de prévoir les matières premieres. Si pas assez un message dit qu'il faut commander XXX MP
    public float PrevoirLesMPsDeType(typeMP typeMP, int nbMPNecessaires, int idRepas) {
        int MPToCommand = RegarderSiAssezMP(typeMP, nbMPNecessaires);
        if (MPToCommand != 0) {
            // pas assez en stock :  il faut commander TODO : regarder si on affiche ici ou aillerus

            System.out.println("Il faut commander : " + MPToCommand + " matières 1ere de type : " + typeMP);
        }
        return CreerPrevisionMP(typeMP, nbMPNecessaires, idRepas);
    }

    // Decremente le stock disponible de MP + creer une prévision de MP associée au repas, retourne le montant des MP
    public float CreerPrevisionMP(typeMP typeMP, int nb, int idRepas) {
        lesMPs.decrementerStockMP(typeMP, nb);
        lesPrevMPs.add(new Prevision_MatMP(nb, idRepas, lesMPs.getIdMp(typeMP)));
        float montant = lesMPs.getPrixMp(typeMP) * nb;
        return montant;
    }

    // regarde s'il y a assez de matieres premieres disponibles
    public int RegarderSiAssezMP(typeMP typeMP, int nb) {
        int nbStock = lesMPs.getStockMp(typeMP);
        if (nb <= nbStock) {
            //ok
            return 0;
        } else {
            //NOK 
            return nb - nbStock;
        }
    }

    // Calcul du montant du traiteur
    public float PrevoirTraiteurMontant(Integer NbPers, Date dtHeureDebut, Date dtHeureFin) {
        float montantPrevu = 0;
        long nbJours = Math.abs(dtHeureDebut.getTime() - dtHeureFin.getTime());
        // 20 e par jour et par personnes
        montantPrevu = (nbJours * 20) * NbPers;
        return montantPrevu;
    }

    // Calcul du nomobre de lesBouteilles dont on a besoin
    public int CalculNbBouteilles(Date date1, Date date2, Integer nbPersonnes) {
        Integer res = 0;
        Long diff = diffDateApprox(date1, date2);

        Integer nbBoutPrUnePersPrDemiJour = 1;
        // Une demi journée
        if (diff < 12) {
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 1;
        } else if (diff < 24) { // une journée 
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 2;
        } else if (diff < 36) { // 1 jour + 1 demi
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 3;
        } else if (diff < 48) { // 2 jours
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 4;
        } else if (diff < 60) { // 2 jours + 1 demi jours
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 5;
        } else if (diff < 72) { // 3 jours 
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 6;
        } else if (diff >= 72) { // plus de 3 jours 
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 7;
        }
        return res;
    }

    // Calcul du nomobre de lesBouteilles dont on a besoin
    public int CalculNbMP(Date date1, Date date2, Integer nbPersonnes) {
        Integer res = 0;
        Long diff = diffDateApprox(date1, date2);

        Integer nbBoutPrUnePersPrDemiJour = 3;
        // Une demi journée
        if (diff < 12) {
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 1;
        } else if (diff < 24) { // une journée 
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 2;
        } else if (diff < 36) { // 1 jour + 1 demi
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 3;
        } else if (diff < 48) { // 2 jours
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 4;
        } else if (diff < 60) { // 2 jours + 1 demi jours
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 5;
        } else if (diff < 72) { // 3 jours 
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 6;
        } else if (diff >= 72) { // plus de 3 jours 
            res = nbBoutPrUnePersPrDemiJour * nbPersonnes * 7;
        }
        return res;
    }

    // retourne la différnce entre 2 dates sous forme de chaine de caractères
    public Long diffDateApprox(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        long days = 0;

        while (diff > 1000) {
            diff = diff - 1000;
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
            }

            if (minutes == 60) {
                minutes = 0;
                hours++;
            }

            if (hours == 24) {
                hours = 0;
                days++;
            }
        }

        Long inter = days * 24 * +hours;
        return inter;
    }
}
