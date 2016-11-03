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
import javax.persistence.Table;


@Entity
@Table(name = "Servidor_Apk")
public class Servidor_Apk implements Serializable{
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_serv_apk;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "version")
    private String version;

    public int getId_serv_apk() {
        return id_serv_apk;
    }

    public void setId_serv_apk(int id_serv_apk) {
        this.id_serv_apk = id_serv_apk;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id_serv_apk;
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
        final Servidor_Apk other = (Servidor_Apk) obj;
        if (this.id_serv_apk != other.id_serv_apk) {
            return false;
        }
        return true;
    }

    
   
    
}
