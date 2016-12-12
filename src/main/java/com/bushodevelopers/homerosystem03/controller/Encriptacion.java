/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bushodevelopers.homerosystem03.controller;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author javie
 */
public class Encriptacion {

    public String encrypt(String texto) {

        String key = "ahksjdhgfyeudoksjdh";
        String textoEncriptado = DigestUtils.sha512Hex(texto + key);

        return textoEncriptado;
    }

}
