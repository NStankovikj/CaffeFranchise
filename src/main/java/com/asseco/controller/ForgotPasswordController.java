/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller;

import com.asseco.common.LocaleBean;
import com.asseco.dao.UserDAO;
import com.asseco.model.User;
import com.asseco.util.Encryption;
import com.asseco.validator.EMAILValidator;
import com.asseco.validator.TYPEValidator;
import nl.captcha.Captcha;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 *
 * @author darko.aleksovski
 */
@ViewScoped
@Named(value = "forgotPasswordController")
public class ForgotPasswordController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserDAO userDAO;

    @Inject
    private LocaleBean localeBean;


    private String select = "username";
    private String inputUE;
    private String captchaInput;

    @PostConstruct
    public void init() {

    }

    public void changepass() {

        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        Captcha secretcaptcha = (Captcha) session.getAttribute(Captcha.NAME);

        Encryption encryption = new Encryption();
        TYPEValidator typeValidator = new TYPEValidator();

        if (!typeValidator.isEmptyString(inputUE)) {
            if (secretcaptcha.isCorrect(captchaInput)) {

                //username
                if (select.equals("username")) {
                    User user = userDAO.getActiveUserByUsername(inputUE);
                    if (user != null) {

                        String pass = encryption.getRandomAlphaNum(8);
                        user.setPassword(encryption.cryptReverseWithSha256(pass));
                        userDAO.edit(user);

                        String messageSend = htmlMessage(user,pass);
//                        mailSender.setSubject(localeBean.getText("emailSendSubjectForgotPassword"));
//                        mailSender.sendMail(user.getC2h().getC2x2(), messageSend);

                        RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('dlgInfo').show();");
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, localeBean.getText("forgotPassword.usernameNotFound")+" "+inputUE, ""));
                    }
                }

                //e-mail
                if (select.equals("mailAddress")) {
                    EMAILValidator emailValidator = new EMAILValidator();
                    if (emailValidator.checkEMAIL(inputUE)) {
                        User user = userDAO.getActiveUserByEmail(inputUE);
                        if (user != null) {

                            String pass = encryption.getRandomAlphaNum(8);
                            user.setPassword(encryption.cryptReverseWithSha256(pass));
                            userDAO.edit(user);

                            String messageSend = htmlMessage(user,pass);
//                            mailSender.setSubject(localeBean.getText("emailSendSubjectForgotPassword"));
//                            mailSender.sendMail(user.getC2h().getC2x2(), messageSend);

                            RequestContext context = RequestContext.getCurrentInstance();
                            context.execute("PF('dlgInfo').show();");
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, localeBean.getText("forgotPassword.emailNotFound")+" "+inputUE, ""));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, localeBean.getText("requiredMessageEmail"), ""));
                    }
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, localeBean.getText("captcha.wrong"), ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, localeBean.getText("forgotPassword.usernameEmailRequiredMessage"), ""));
        }
        inputUE = "";
        captchaInput = "";
    }

    public String htmlMessage(User user, String pass) {
        String messageSend = "<html>\n"
                + "<body>\n"
                + "<br/>\n"
                + "<p style=\"font-family:Verdana; font-size:12px; font-weight: normal; margin-left: 0.5cm;\">"
                + localeBean.getText("email.greeting") + " "
                + "</p>\n"
                + "<div align=\"justify\"> <p style=\"display:block; font-family:Verdana; font-size:12px; border-color:black; width: 21cm; height: 29.7cm; margin: 0.5cm;\">\n"
                + localeBean.getText("email.infoPswText") + "<br /><br />\n"
                + localeBean.getText("email.username") + ": <i>" + user.getUsername() + "</i><br/>\n"
                + localeBean.getText("email.password") + ": <i>" + pass + "</i><br/><br/>\n"
                + localeBean.getText("email.using.thankYou") + "<br/>\n"
                + localeBean.getText("contact.signature") + "</p></div>\n"
                + "</body>\n"
                + "</html>\n"
                + "</body>\n"
                + "</html>";
        
        return messageSend;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getInputUE() {
        return inputUE;
    }

    public void setInputUE(String inputUE) {
        this.inputUE = inputUE;
    }

    public String getCaptchaInput() {
        return captchaInput;
    }

    public void setCaptchaInput(String captchaInput) {
        this.captchaInput = captchaInput;
    }

}
