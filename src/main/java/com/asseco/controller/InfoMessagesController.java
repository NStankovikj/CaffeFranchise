package com.asseco.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class InfoMessagesController implements Serializable {

    public void info(ActionEvent event){
        String param = (String) event.getComponent().getAttributes().get("param");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You got new message", param));
    }

    public void messageReceived(ActionEvent event){
        String param = (String) event.getComponent().getAttributes().get("param");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You got new message", param));
    }

}
