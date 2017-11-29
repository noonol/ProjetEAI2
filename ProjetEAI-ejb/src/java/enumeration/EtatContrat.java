/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumeration;

/**
 *
 * @author Marine
 */
public enum EtatContrat { // l'etat correspond à le gestion qui l'envoie ex pour créer un contrat : GestionProjet -> GestionRestauration : etat : gestion_projet_creer
    initialise,
    //quand l'état par gestion, c'est à dire qu'il est utilisé par un bean en particulier
    gestion_projet,
    gestion_projet_creer,
    gestion_projet_annuler,
    gestion_restauration_creer,
    gestion_restauration_annuler,
    gestion_salle,
    gestion_personnel,
    gestion_personnel_creer,
    gestion_personnel_annuler,
    gestion_prestataire_complementaire_annuler,
    gestion_prestataire_complementaire_creer,
    annulé,
    validé
   
}
