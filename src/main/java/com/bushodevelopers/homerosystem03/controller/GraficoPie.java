/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.controller;

/**
 *
 * @author javie
 */
import com.bushodevelopers.homerosystem03.model.Incidente;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;
 
@ManagedBean
public class GraficoPie implements Serializable {
 
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    
    @EJB
    private com.bushodevelopers.homerosystem03.ejb.IncidenteFacade EJBIcidente;
    private List<Incidente> lista;
    private Incidente incidente;
 
    @PostConstruct
    public void init() {
        lista = EJBIcidente.findAll();//siempre primero el ejb
        createPieModels();
        
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
     
    private void createPieModels() {
        createPieModel1();
        createPieModel2();
    }
 
    private void createPieModel1() {
        //reconrremos la lista y comparamos
        int totalabiertos =0;
        int totalsolucionado =0;
        for (Incidente incidente1 : lista) {
            if (incidente1.getSolucion().getEstado().equals("Abierto")) {
                totalabiertos++;
            }
            else if (incidente1.getSolucion().getEstado().equals("Solucionado")) {
                totalsolucionado++;
            }
        }
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Abiertos", totalabiertos);
        pieModel1.set("Solucionados", totalsolucionado);
        
         pieModel1.setShowDataLabels(true);
        pieModel1.setTitle("Incidentes");
        pieModel1.setLegendPosition("w");
    }
     
    private void createPieModel2() {
        pieModel2 = new PieChartModel();
         
        pieModel2.set("Brand 1", 540);
        pieModel2.set("Brand 2", 325);
        pieModel2.set("Brand 3", 702);
        pieModel2.set("Brand 4", 421);
         
        pieModel2.setTitle("Custom Pie");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }
     
}
