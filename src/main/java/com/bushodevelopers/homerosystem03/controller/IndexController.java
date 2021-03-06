/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.controller;




import com.bushodevelopers.homerosystem03.ejb.UsuarioFacadeLocal;
import com.bushodevelopers.homerosystem03.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javax.inject.Named;

/**
 *
 * @author javie
 */
@Named
@SessionScoped
public class IndexController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    @Inject //patron de diseño CDI Contexts and Dependency Injection
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @PostConstruct
    public void init(){
    
        //usuario = new Usuario();
    }
    
    public String iniciarSesion(){
        Usuario us;
        String redireccion= null;
        
        try {
            us=EJBUsuario.iniciarSesion(usuario);
            
            if (us!= null) {
                //alamcenar session jsf
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "/PaginaPrincipal/principal?faces-redirect=true";//faces-redirect=true //navegacion explicita
            }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Credenciales incorrectas", "Credenciales incorrectas"));
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error", "Error"));
        }
        return redireccion;
        
        
    }
    
    public void verificarSesion() {
        
        try {
            //accedo a la session del usuario
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../permisos.xhtml");
            }
        } catch (Exception e) {
            //log para guardar registro
        }
    }
    
    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
    }
    
    public String usuarioLogeado(){
        
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getUsuario(); 
    
    }
    
    public Integer rolLogeado(){
        
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getRol().getId_rol(); 
    
    }
}
