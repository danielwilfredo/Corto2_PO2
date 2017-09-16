/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.beans;

import com.sv.udb.controladores.UsuariosFacade;
import com.sv.udb.controladores.UsuariosFacadeLocal;
import com.sv.udb.modelos.Usuarios;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DanielWilfredo
 */
@Named(value = "usuariosBean")
@ViewScoped
public class UsuariosBean implements Serializable{
    
    
    @EJB
    private UsuariosFacadeLocal UsuaFacade;
    private boolean guardando;
    private Usuarios objeUsua;
    private List<Usuarios> listPres;

    /**
     * Creates a new instance of UsuariosBean
     */
    public UsuariosBean() {
    }

    public UsuariosFacadeLocal getUsuaFacade() {
        return UsuaFacade;
    }

    public boolean isGuardando() {
        return guardando;
    }

    public Usuarios getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(Usuarios objeUsua) {
        this.objeUsua = objeUsua;
    }

    public List<Usuarios> getListPres() {
        return listPres;
    }

    
        //Se ejecuta despues de que la página carga
    @PostConstruct
    public void init()
    {
        this.objeUsua = new Usuarios();
        this.listPres = this.UsuaFacade.findAll();
    }
    

    
    
    
    
}
