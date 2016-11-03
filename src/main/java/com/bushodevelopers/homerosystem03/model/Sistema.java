/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author javie
 */
@Entity
@Table(name = "Sistema")
public class Sistema implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sistema;
    
    @ManyToOne
    @JoinColumn(name = "Servidor_Apk_id_serv_apk")
    private Servidor_Apk servidor_Apk;
   
    @ManyToOne
    @JoinColumn(name = "Servidor_id_servidor")
    private Servidor servidor;
    
    @ManyToOne
    @JoinColumn(name = "Base_Datos_id_bd")
    private Base_Datos base_datos;
    
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "version")
    private String version;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "Lenguaje_id_lenguaje")
    private Lenguaje lenguaje;

    public int getId_sistema() {
        return id_sistema;
    }

    public void setId_sistema(int id_sistema) {
        this.id_sistema = id_sistema;
    }

    public Servidor_Apk getServidor_Apk() {
        return servidor_Apk;
    }

    public void setServidor_Apk(Servidor_Apk servidor_Apk) {
        this.servidor_Apk = servidor_Apk;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Base_Datos getBase_datos() {
        return base_datos;
    }

    public void setBase_datos(Base_Datos base_datos) {
        this.base_datos = base_datos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id_sistema;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sistema other = (Sistema) obj;
        if (this.id_sistema != other.id_sistema) {
            return false;
        }
        return true;
    }
    
    
}
