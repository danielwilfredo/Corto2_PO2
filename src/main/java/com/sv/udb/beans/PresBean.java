/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.beans;

import com.sv.udb.controladores.PrestamosFacade;
import com.sv.udb.controladores.PrestamosFacadeLocal;
import com.sv.udb.modelos.Prestamos;
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
@Named(value = "presBean")
@ViewScoped
public class PresBean implements Serializable {
    
    
       @EJB
    private PrestamosFacadeLocal presFacade;
       
    private boolean guardando;
    private Prestamos objePres;
    private List<Prestamos> listPres;
       

    /**
     * Creates a new instance of PresBean
     */
    public PresBean() {
    }

    public PrestamosFacadeLocal getPresFacade() {
        return presFacade;
    }

    public void setPresFacade(PrestamosFacadeLocal presFacade) {
        this.presFacade = presFacade;
    }

    public boolean isGuardando() {
        return guardando;
    }

    public void setGuardando(boolean guardando) {
        this.guardando = guardando;
    }

    public Prestamos getObjePres() {
        return objePres;
    }

    public void setObjePres(Prestamos objePres) {
        this.objePres = objePres;
    }

    public List<Prestamos> getListPres() {
        return listPres;
    }

    public void setListPres(List<Prestamos> listPres) {
        this.listPres = listPres;
    }
    
    
   //Se ejecuta despues de que la página carga
    @PostConstruct
    public void init()
    {
        this.objePres = new Prestamos();
        this.listPres = this.presFacade.findAll();
    }
    
    
    public void nuev()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.objePres = new Prestamos();
        this.guardando = true;
        ctx.execute("$('#modaFormPres').modal('show')");
    }
    
     public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        Map<String, String> mapaPrms = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int codi = Integer.parseInt(mapaPrms.get("codiPres"));
        this.objePres = this.presFacade.find(codi);
        this.guardando = false;
        ctx.execute("$('#modaFormPres').modal('show')");
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objePres.setFechPres(new java.util.Date());
            this.presFacade.create(this.objePres);
            this.listPres.add(this.objePres);
            this.objePres = new Prestamos();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void edit()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.presFacade.edit(this.objePres);
            this.setItem(this.objePres);
            this.objePres = new Prestamos();
            this.guardando = true;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No se modificó')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.presFacade.remove(this.objePres);
            this.listPres.remove(this.objePres);
            this.objePres = new Prestamos();
            this.guardando = true;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No se eliminó')");
        }
        finally
        {
            
        }
    }
    
    private void setItem(Prestamos item)
    {
        int itemIndex = this.listPres.indexOf(item);
            if (itemIndex != -1) {
            this.listPres.set(itemIndex, item);
        }
    }
}