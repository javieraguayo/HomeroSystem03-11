/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.controller;

import com.bushodevelopers.homerosystem03.model.Incidente;
import com.bushodevelopers.homerosystem03.model.Lenguaje;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.MeterGaugeChartModel;

@ManagedBean
public class GraficoGaugeView implements Serializable{
    
     private MeterGaugeChartModel meterGaugeModel1;
    private MeterGaugeChartModel meterGaugeModel2;
    
    @EJB
    private com.bushodevelopers.homerosystem03.ejb.IncidenteFacade EJBIcidente;
    private List<Incidente> lista;
    private Incidente incidente;
 
    @PostConstruct
    public void init() {
        lista = EJBIcidente.findAll();
        createMeterGaugeModels();
    }
     
    public MeterGaugeChartModel getMeterGaugeModel1() {
        return meterGaugeModel1;
    }
     
    public MeterGaugeChartModel getMeterGaugeModel2() {
        return meterGaugeModel2;
    }
 
    private MeterGaugeChartModel initMeterGaugeModel() {
        int largo = lista.size();
        List<Number> intervals = new ArrayList<Number>(){{
            add(20);
            add(40);
            add(60);
            add(80);
            
        }};
         
        return new MeterGaugeChartModel(largo, intervals);
    }
 
    private void createMeterGaugeModels() {
        meterGaugeModel1 = initMeterGaugeModel();
        
        meterGaugeModel1.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
        meterGaugeModel1.setGaugeLabel("Zona");
         
        meterGaugeModel2 = initMeterGaugeModel();
        meterGaugeModel2.setTitle("Custom Options");
        meterGaugeModel2.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
        meterGaugeModel2.setGaugeLabel("km/h");
        meterGaugeModel2.setGaugeLabelPosition("bottom");
        meterGaugeModel2.setShowTickLabels(false);
        meterGaugeModel2.setLabelHeightAdjust(110);
        meterGaugeModel2.setIntervalOuterRadius(100);
    }
 
}
    

