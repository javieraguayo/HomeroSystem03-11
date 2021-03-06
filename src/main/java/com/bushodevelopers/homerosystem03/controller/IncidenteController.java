package com.bushodevelopers.homerosystem03.controller;

import com.bushodevelopers.homerosystem03.model.Incidente;
import com.bushodevelopers.homerosystem03.controller.util.JsfUtil;
import com.bushodevelopers.homerosystem03.controller.util.JsfUtil.PersistAction;
import com.bushodevelopers.homerosystem03.ejb.IncidenteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("incidenteController")
@SessionScoped
public class IncidenteController implements Serializable {

    
    @PersistenceContext(unitName = "com.bushoDevelopers_HomeroSystem03-11_war_1.0PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private com.bushodevelopers.homerosystem03.ejb.IncidenteFacade ejbFacade;
    private List<Incidente> items = null;
    private Incidente selected;

    public IncidenteController() {
    }

    public Incidente getSelected() {
        return selected;
    }

    public void setSelected(Incidente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IncidenteFacade getFacade() {
        return ejbFacade;
    }

    public Incidente prepareCreate() {
        selected = new Incidente();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IncidenteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IncidenteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IncidenteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Incidente> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Incidente getIncidente(int id) {
        return getFacade().find(id);
    }

    public List<Incidente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Incidente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public Incidente getUltimoIncidente(){
    
        Incidente incidente = null;
        String consulta;
        try {
            consulta = "FROM Incidente i WHERE i.solucion.id_solucion = 246 ORDER BY i.solucion.id_solucion DESC";
            Query query = em.createQuery(consulta);
            List<Incidente> lista = query.getResultList();
            if (!lista.isEmpty()) {
                incidente = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return incidente;

    }

    @FacesConverter(forClass = Incidente.class)
    public static class IncidenteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IncidenteController controller = (IncidenteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "incidenteController");
            return controller.getIncidente(getKey(value));
        }

        int getKey(String value) {
            int key;
            key = Integer.parseInt(value);
            return key;
        }

        String getStringKey(int value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Incidente) {
                Incidente o = (Incidente) object;
                return getStringKey(o.getId_incidente());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Incidente.class.getName()});
                return null;
            }
        }

    }

}
