/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import enumeration.EnumAnimation;
import enumeration.EnumCommunication;
import enumeration.EnumDecoration;
import enumeration.EnumSecurite;
import enumeration.typePrestations;
import exceptions.ExceptionTropicDejaUtilise;
import java.util.Date;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import messages.Client;
import messages.Salle;

/**
 *
 * @author Nolwenn PILLARD
 */
@WebService(serviceName = "ApplicationEmployes")
public class ApplicationEmployes {

    @EJB
    private services.ApplicationEmployes ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "creerContrat")
    public void creerContrat(@WebParam(name = "idContrat") int idContrat, @WebParam(name = "animation") EnumAnimation animation, @WebParam(name = "decoration") EnumDecoration decoration, @WebParam(name = "communication") EnumCommunication communication, @WebParam(name = "securite") EnumSecurite securite, @WebParam(name = "montantGlobal") float montantGlobal, @WebParam(name = "nbPersonnes") int nbPersonnes, @WebParam(name = "leClient") Client leClient, @WebParam(name = "debut") Date debut, @WebParam(name = "fin") Date fin, @WebParam(name = "mySalle") Salle mySalle, @WebParam(name = "type") typePrestations type, @WebParam(name = "cocktailMaison") Boolean cocktailMaison) throws ExceptionTropicDejaUtilise {
        ejbRef.creerContrat(idContrat, animation, decoration, communication, securite, montantGlobal, nbPersonnes, leClient, debut, fin, mySalle, type, cocktailMaison);
    }
    
}
