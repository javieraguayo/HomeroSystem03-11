/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.controller;

import com.bushodevelopers.homerosystem03.ejb.LenguajeFacade;
import com.bushodevelopers.homerosystem03.model.Lenguaje;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javafx.scene.chart.PieChart;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class ChartView implements Serializable {

    private LineChartModel areaModel;
    @EJB
    private com.bushodevelopers.homerosystem03.ejb.LenguajeFacade EJBlenguaje;
    private List<Lenguaje> lista;
    private Lenguaje lenguaje;

    @PostConstruct
    public void init() {
        lista = EJBlenguaje.findAll();
        createAreaModel();
    }

    public LineChartModel getAreaModel() {
        return areaModel;
    }

    private void createAreaModel() {
        areaModel = new LineChartModel();
        
        int largo = lista.size();

        LineChartSeries boys = new LineChartSeries();
        boys.setFill(true);
        boys.setLabel("Boys");
        boys.set("2004", largo);
        boys.set("2005", 20);
        boys.set("2006", largo);
        boys.set("2007", largo);
        boys.set("2008", largo);

        LineChartSeries girls = new LineChartSeries();
        girls.setFill(true);
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);

        areaModel.addSeries(boys);
        areaModel.addSeries(girls);

        areaModel.setTitle("Area Chart");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Years");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(300);

    }
    
    
}
