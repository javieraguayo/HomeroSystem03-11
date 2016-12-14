/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.controller;

import com.bushodevelopers.homerosystem03.model.Incidente;
import com.bushodevelopers.homerosystem03.model.Sistema;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
 
@ManagedBean
public class GraficoLineal implements Serializable {
 
    private LineChartModel lineModel1;
    private LineChartModel lineModel2;
    
    @EJB
    private com.bushodevelopers.homerosystem03.ejb.IncidenteFacade EJBIcidente;
    private List<Incidente> lista;
    private Incidente incidente;
    
     @EJB
    private com.bushodevelopers.homerosystem03.ejb.SistemaFacade EJBSistema;
    private List<Sistema> listaSistema;
    private Sistema sistema;
     
    @PostConstruct
    public void init() {
       listaSistema = EJBSistema.findAll();
       lista = EJBIcidente.findAll();
        createLineModels();
    }
 
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
     
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        lineModel2 = initCategoryModel();
        lineModel2.setAnimate(true);
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Años"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(90);
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
     
    private LineChartModel initCategoryModel() {
        
            int largo = lista.size();
            int largoSistema = listaSistema.size();
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Incidentes");
        boys.set("2012", 30);
        boys.set("2013", 37);
        boys.set("2014", 45);
        boys.set("2015", 60);
        boys.set("2016", largo);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Sistemas");
        girls.set("2012", 10);
        girls.set("2013", 12);
        girls.set("2014", 14);
        girls.set("2015", 16);
        girls.set("2016", largoSistema);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
 
}
