/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.ejb;

import com.bushodevelopers.homerosystem03.controller.Encriptacion;
import com.bushodevelopers.homerosystem03.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author javie
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.bushoDevelopers_HomeroSystem03-11_war_1.0PU")
    private EntityManager em;
    
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    
    
    @Override
    public Usuario iniciarSesion(Usuario us) {
        Usuario usuario = null;
        
        //usuario.setPassword(new Encriptacion().encrypt(us.getPassword()));
        
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.usuario =?1 and u.password =?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getUsuario());
            query.setParameter(2, us.getPassword());
            List<Usuario> lista = query.getResultList();

            
            if (!lista.isEmpty()) {
                usuario = lista.get(0);

            }

        } catch (Exception e) {
            throw e;

        }
        return usuario;
    }
}
