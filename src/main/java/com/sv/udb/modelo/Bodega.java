/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.sql.Date;

/**
 *
 * @author bernardo
 */
public class Bodega {
    private int codiBode;
    private int codPiez;
    private int codiProv;
    private int cant;
    private Date fech;

    public Bodega() {
    }

    public Bodega(int codiBode, int codPiez, int codiProv, int cant, Date fech) {
        this.codiBode = codiBode;
        this.codPiez = codPiez;
        this.codiProv = codiProv;
        this.cant = cant;
        this.fech = fech;
    }

    public int getCodiBode() {
        return codiBode;
    }

    public void setCodiBode(int codiBode) {
        this.codiBode = codiBode;
    }

    public int getCodPiez() {
        return codPiez;
    }

    public void setCodPiez(int codPiez) {
        this.codPiez = codPiez;
    }

    public int getCodiProv() {
        return codiProv;
    }

    public void setCodiProv(int codiProv) {
        this.codiProv = codiProv;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public Date getFech() {
        return fech;
    }

    public void setFech(Date fech) {
        this.fech = fech;
    }

    @Override
    public String toString() {
        return String.valueOf(this.codiBode);
    }  
    
}
