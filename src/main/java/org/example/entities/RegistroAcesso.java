package org.example.entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroAcesso {
    private int id;
    private Date data;
    private String dispositivo;
    private String navegador;
    private String localizacaoGeografica;
    private Time hora;
    private List<ContaCliente> clientes;

    public RegistroAcesso() {
        clientes = new ArrayList<>();
    }

    public RegistroAcesso(int id, Date data, String dispositivo, String navegador, String localizacaoGeografica,
                          Time hora) {
        this.id = id;
        this.data = data;
        this.dispositivo = dispositivo;
        this.navegador = navegador;
        this.localizacaoGeografica = localizacaoGeografica;
        this.hora = hora;
        clientes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    public String getLocalizacaoGeografica() {
        return localizacaoGeografica;
    }

    public void setLocalizacaoGeografica(String localizacaoGeografica) {
        this.localizacaoGeografica = localizacaoGeografica;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public List<ContaCliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<ContaCliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "RegistroAcesso{" +
                "id=" + id +
                ", data=" + data +
                ", dispositivo='" + dispositivo + '\'' +
                ", navegador='" + navegador + '\'' +
                ", localizacaoGeografica='" + localizacaoGeografica + '\'' +
                ", hora=" + hora +
                ", clientes=" + clientes +
                '}';
    }
}
