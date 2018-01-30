/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.controller;

import com.asseco.common.LocaleBean;
import com.asseco.common.MainBean;
import com.asseco.dao.AttachmentDAO;
import com.asseco.dao.UserDAO;
import com.asseco.dao.UserRequestRegistrationDAO;
import com.asseco.dao.CbCountryDAO;
import com.asseco.lookups.CbCountry;
import com.asseco.model.Attachment;
import com.asseco.model.Grad;
import com.asseco.model.UserRequestRegistration;
import com.asseco.start.AppProperties;
import com.asseco.util.Encryption;
import com.asseco.validator.EMAILValidator;
import com.asseco.validator.TYPEValidator;
import nl.captcha.Captcha;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author darko.aleksovski
 */
@ViewScoped
@Named(value = "registrationController")
public class RegistrationController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserDAO userDAO;
    @EJB
    private CbCountryDAO cbCountryDAO;
    @EJB
    private UserRequestRegistrationDAO userRequestRegistrationDAO;
    private Attachment attachment;
    private List<Attachment> attachmentsList;
    private UploadedFile file;
    private String fileContent;

    @EJB
    AttachmentDAO attachmentDAO;
    @Inject
    private MainBean mainBean;
    @Inject
    private LocaleBean localeBean;

    private UserRequestRegistration userRequestRegistration;
    private String passvord;
    private String confPassvord;
    private String captchaInput;

    private List<Grad> listGradovi;
    private List<CbCountry> listDrzavi;

    private String verifyURL;

    @PostConstruct
    public void init() {

        verifyURL = mainBean.getAppURL() + "/pages/login/verifyRegistration.xhtml?verifyCode=";

        createNewObject();

        listDrzavi = cbCountryDAO.findAll();
        attachmentsList=new ArrayList<>();
    }

    public void createNewObject() {
        userRequestRegistration = new UserRequestRegistration();
        passvord = null;
        confPassvord = null;
        captchaInput = null;
    }

    public void updateGradovi() {
        if (userRequestRegistration.getCountryCompany() != null) {
            listGradovi = userRequestRegistration.getCountryCompany().getGradovi();
        } else {
            listGradovi = null;
        }
    }

    public void save() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        Captcha secretcaptcha = (Captcha) session.getAttribute(Captcha.NAME);

        EMAILValidator emailValidator = new EMAILValidator();
        Encryption encryption = new Encryption();
        TYPEValidator typeValidator = new TYPEValidator();

        if (checkRequiredField(userRequestRegistration)) {
            if (secretcaptcha.isCorrect(captchaInput)) {
                if (passvord.equals(confPassvord)) {
                    if (emailValidator.checkEMAIL(userRequestRegistration.getEmail())) {
                        if (userDAO.checkEmailIsFree(userRequestRegistration.getEmail(), null) && userRequestRegistrationDAO.checkEmailIsFree(userRequestRegistration.getEmail())) {
                            if (userDAO.checkUsernameIsFree(userRequestRegistration.getUsername(), null) && userRequestRegistrationDAO.checkUsernameIsFree(userRequestRegistration.getUsername())) {
                                if (attachmentsList.isEmpty()) {
                                    userRequestRegistration.setDocuments(attachmentsList);
                                }

                                userRequestRegistration.setPassword(encryption.cryptReverseWithSha256(passvord));
                                userRequestRegistration.setVerify_email(false);
                                String rndId = encryption.getRandomAlphaNum(10);
                                Date cDate = new Date();
                                String vctmp = rndId + Math.abs(userRequestRegistration.hashCode()) + cDate.getTime();
                                userRequestRegistration.setVerify_code(vctmp);

                                userRequestRegistrationDAO.create(userRequestRegistration);

                                String messageSend = htmlMessage(userRequestRegistration, vctmp);

                                createNewObject();
                                RequestContext context = RequestContext.getCurrentInstance();
                                context.execute("PF('dlgInfo').show();");
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("usernameIsUsed"), ""));
                            }
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("emailIsUsed"), ""));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("requiredMessageEmail"), ""));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("registration.notEqualPass"), ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("captcha.wrong"), ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, localeBean.getText("requiredMessageAll"), ""));
        }
    }

    public boolean checkRequiredField(UserRequestRegistration urr) {

        TYPEValidator typeValidator = new TYPEValidator();

        if (typeValidator.isEmptyString(urr.getNazivKompanija())) {
            return false;
        }
        if (typeValidator.isEmptyString(urr.getMaticenBrojKompanija())) {
            return false;
        }
        if (typeValidator.isEmptyString(urr.getEdbKompanija())) {
            return false;
        }
        if (typeValidator.isEmptyString(urr.getAdresaSedisteKompanija())) {
            return false;
        }

        if (urr.getCountryCompany() == null) {
            return false;
        }
        if (typeValidator.isEmptyString(urr.getUsername())) {
            return false;
        }
        if (typeValidator.isEmptyString(urr.getEmail())) {
            return false;
        }
        if (typeValidator.isEmptyString(urr.getTelefon())) {
            return false;
        }
        if (typeValidator.isEmptyString(urr.getIme())) {
            return false;
        }
        if (typeValidator.isEmptyString(urr.getPrezime())) {
            return false;
        }
        if (typeValidator.isEmptyString(passvord)) {
            return false;
        }
        if (typeValidator.isEmptyString(confPassvord)) {
            return false;
        }

        return true;
    }

    public String htmlMessage(UserRequestRegistration urr, String vctmp) {
        String messageSend = "<html>\n"
                + "<body>\n"
                + "<br/>\n"
                + "<p style=\"font-family:Verdana; font-size:12px; font-weight: normal; margin-left: 0.5cm;\">"
                + localeBean.getText("email.greeting") + " "
                + urr.getIme() + " " + urr.getPrezime() + ", "
                + "</p>\n"
                + "<div align=\"justify\"> <p style=\"display:block; font-family:Verdana; font-size:12px; border-color:black; width: 21cm; height: 29.7cm; margin: 0.5cm;\">\n"
                + localeBean.getText("email.regNewConfirmation") + "<br /><br />\n"
                + "<a href=\"" + verifyURL + vctmp + "\"target=\"_blank\">" + localeBean.getText("email.confirmationLink") + "</a>" + "<br /><br /> \n"
                + localeBean.getText("email.using.thankYou") + "<br/>\n"
                + localeBean.getText("contact.signature") + "</p></div>\n"
                + "</body>\n"
                + "</html>\n"
                + "</body>\n"
                + "</html>";

        return messageSend;
    }

    public void handleAttachmentsUploadLiteratureReference(FileUploadEvent event) {
        file = event.getFile();
        fileContent = file.getFileName();

        byte data[] = file.getContents();

        String filename = file.getFileName();
        String newfilename = String.valueOf(System.currentTimeMillis()) + "-" + file.getFileName();

        Path filepath = Paths.get(AppProperties.FILE_SYSTEM_PATH + "/attachments/" + newfilename);
        File uploads = new File(AppProperties.FILE_SYSTEM_PATH + "/attachments/");
        File newfile = new File(uploads, newfilename);
        try {
            InputStream input = file.getInputstream();
            Files.copy(input, newfile.toPath());
            attachment = new Attachment();
            attachment.setName(newfilename);
            attachment.setPath(filepath.toString());
            attachment.setUserRequestRegistrationID(userRequestRegistration);
            if (attachmentsList == null) {
                attachmentsList = new ArrayList<>();
            }
            attachmentsList.add(attachment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserDocuments(Attachment attachment) {
        userRequestRegistration.getDocuments().remove(attachment);
    }

    public UserRequestRegistration getUserRequestRegistration() {
        return userRequestRegistration;
    }

    public void setUserRequestRegistration(UserRequestRegistration userRequestRegistration) {
        this.userRequestRegistration = userRequestRegistration;
    }

    public String getPassvord() {
        return passvord;
    }

    public void setPassvord(String passvord) {
        this.passvord = passvord;
    }

    public String getConfPassvord() {
        return confPassvord;
    }

    public void setConfPassvord(String confPassvord) {
        this.confPassvord = confPassvord;
    }

    public String getCaptchaInput() {
        return captchaInput;
    }

    public void setCaptchaInput(String captchaInput) {
        this.captchaInput = captchaInput;
    }

    public List<Grad> getListGradovi() {
        return listGradovi;
    }

    public List<CbCountry> getListDrzavi() {
        return listDrzavi;
    }

    public void setListDrzavi(List<CbCountry> listDrzavi) {
        this.listDrzavi = listDrzavi;
    }

    public List<Attachment> getAttachmentsList() {
        return attachmentsList;
    }

    public void setAttachmentsList(List<Attachment> attachmentsList) {
        this.attachmentsList = attachmentsList;
    }
}
