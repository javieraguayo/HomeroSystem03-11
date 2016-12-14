/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.controller;

import com.bushodevelopers.homerosystem03.model.Incidente;
import com.bushodevelopers.homerosystem03.model.Servidor;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
 
@ManagedBean
public class ChartView implements Serializable {
 
   
    private CartesianChartModel combinedModel;
 @EJB
    private com.bushodevelopers.homerosystem03.ejb.IncidenteFacade EJBIcidente;
    private List<Incidente> lista;
    private Incidente incidente;
    
     @EJB
    private com.bushodevelopers.homerosystem03.ejb.ServidorFacade EJBservidor;
    private List<Servidor> listaservidor;
    private Servidor servidor;
    
    
@PostConstruct
    public void init() {
        lista = EJBIcidente.findAll();
        listaservidor = EJBservidor.findAll();
        createCombinedModel();
    }
 
    public CartesianChartModel getCombinedModel() {
        return combinedModel;
    }
     
    private void createCombinedModel() {
        combinedModel = new BarChartModel();
        
        int largo = lista.size();
        int largoservidores = listaservidor.size();
        BarChartSeries boys = new BarChartSeries();
        boys.setLabel("Incidentes");
 
        boys.set("2004", 30);
        boys.set("2005", 37);
        boys.set("2006", 45);
        boys.set("2007", 60);
        boys.set("2008", largo);
 
        LineChartSeries girls = new LineChartSeries();
        girls.setLabel("Servidores");
 
        girls.set("2004", 2);
        girls.set("2005", 3);
        girls.set("2006", 5);
        girls.set("2007", 7);
        girls.set("2008", largoservidores);
 
        combinedModel.addSeries(boys);
        combinedModel.addSeries(girls);
         
       
        combinedModel.setLegendPosition("ne");
        combinedModel.setMouseoverHighlight(false);
        combinedModel.setShowDatatip(false);
        combinedModel.setShowPointLabels(true);
        combinedModel.setAnimate(true);
        Axis yAxis = combinedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(90);
    }
     
}