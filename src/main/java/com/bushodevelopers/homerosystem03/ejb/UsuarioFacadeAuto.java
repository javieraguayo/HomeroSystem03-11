/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.ejb;

import com.bushodevelopers.homerosystem03.model.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author javie
 */
@Stateless
public class UsuarioFacadeAuto extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.bushoDevelopers_HomeroSystem03-11_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacadeAuto() {
        super(Usuario.class);
    }
    
}
