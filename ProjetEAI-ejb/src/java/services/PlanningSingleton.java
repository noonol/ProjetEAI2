/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import messages.Client;
import messages.Planning;

/**
 *
 * @author Marine
 */
@Singleton
@LocalBean
public class PlanningSingleton {
    private final ArrayList<Planning> lesPlannings;

    public PlanningSingleton() {
        lesPlannings = new ArrayList<Planning>();
    }

    public ArrayList< Planning> getPlanning() {
        return lesPlannings;
    }
    
    public void addPlanning( Planning p) {
        lesPlannings.add(p);
    }
     public void removePlanning( Planning p) {
        lesPlannings.remove(p);
    }

}
