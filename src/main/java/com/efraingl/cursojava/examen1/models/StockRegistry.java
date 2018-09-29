package com.efraingl.cursojava.examen1.models;

import java.math.BigDecimal;
import java.util.Date;

public class StockRegistry {

    private Date fecha;

    private BigDecimal cierre;

    private BigDecimal apertura;


    public StockRegistry(Date fecha, BigDecimal cierre, BigDecimal apertura) {
        this.fecha = fecha;
        this.cierre = cierre;
        this.apertura = apertura;
    }

    public StockRegistry() {
        this.fecha = null;
        this.cierre = null;
        this.apertura = null;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getCierre() {
        return cierre;
    }

    public void setCierre(BigDecimal cierre) {
        this.cierre = cierre;
    }

    public BigDecimal getApertura() {
        return apertura;
    }

    public void setApertura(BigDecimal apertura) {
        this.apertura = apertura;
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("fecha=").append(fecha);
        sb.append(", cierre=").append(cierre);
        sb.append(", apertura=").append(apertura);
        sb.append("}\n");

        return sb.toString();
    }
}
