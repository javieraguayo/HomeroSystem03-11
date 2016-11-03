/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.ejb;

import com.bushodevelopers.homerosystem03.model.Servidor_Apk;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author javie
 */
@Stateless
public class Servidor_ApkFacade extends AbstractFacade<Servidor_Apk> {

    @PersistenceContext(unitName = "com.bushoDevelopers_HomeroSystem03-11_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Servidor_ApkFacade() {
        super(Servidor_Apk.class);
    }
    
}
