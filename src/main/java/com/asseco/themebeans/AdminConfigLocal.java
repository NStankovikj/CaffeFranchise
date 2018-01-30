/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.themebeans;

import com.github.adminfaces.template.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author atanasij.josifoski
 */
@Named
@ApplicationScoped
public class AdminConfigLocal implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(AdminConfigLocal.class);
    private Properties adminConfigFile;
    private Properties userConfigFile;
    private String loginPage;
    private String indexPage;
    private String errorPage;
    private String dateFormat;
    private String templatePath;
    private Integer breadCrumbMaxSize;
    private boolean renderMessages;
    private boolean renderAjaxStatus;
    private boolean disableFilter;
    private boolean enableRipple;
    private boolean renderBreadCrumb;
    private boolean enableSlideMenu;
    private String rippleElements;
    private String skin;
    private boolean autoShowNavbar;

    public AdminConfigLocal() {
    }

    @PostConstruct
    public void init() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        adminConfigFile = new Properties();
        userConfigFile = new Properties();
        Throwable localThrowable6;
        try {
            InputStream is = cl.getResourceAsStream("admin-config.properties");
            localThrowable6 = null;
            try {
                userConfigFile.load(is);
            } catch (Throwable localThrowable1) {
                localThrowable6 = localThrowable1;
                throw localThrowable1;
            } finally {
                if (is != null) {
                    if (localThrowable6 != null) {
                        try {
                            is.close();
                        } catch (Throwable localThrowable2) {
                            localThrowable6.addSuppressed(localThrowable2);
                        }
                    } else {
                        is.close();
                    }
                }
            }
        } catch (Exception ex) {
            log.warn("Could not load user defined admin template properties.", ex);
        }
        try {
            InputStream isDefault = cl.getResourceAsStream("config/admin-config.properties");
            localThrowable6 = null;
            try {
                adminConfigFile.load(isDefault);
            } catch (Throwable localThrowable4) {
                localThrowable6 = localThrowable4;
                throw localThrowable4;
            } finally {
                if (isDefault != null) {
                    if (localThrowable6 != null) {
                        try {
                            isDefault.close();
                        } catch (Throwable localThrowable5) {
                            localThrowable6.addSuppressed(localThrowable5);
                        }
                    } else {
                        isDefault.close();
                    }
                }
            }
        } catch (Exception ex) {
            log.error("Could not load admin template default properties.", ex);
        }

        loadDefaults();
    }

    protected void loadDefaults() {
        loginPage = getProperty("admin.loginPage");
        errorPage = getProperty("admin.errorPage");
        indexPage = getProperty("admin.indexPage");
        dateFormat = getProperty("admin.dateFormat");
        templatePath = getProperty("admin.templatePath");
        breadCrumbMaxSize = Integer.valueOf(Integer.parseInt(getProperty("admin.breadcrumbSize")));
        renderMessages = Boolean.parseBoolean(getProperty("admin.renderMessages"));
        renderAjaxStatus = Boolean.parseBoolean(getProperty("admin.renderAjaxStatus"));
        disableFilter = Boolean.parseBoolean(getProperty("admin.disableFilter"));
        enableRipple = Boolean.parseBoolean(getProperty("admin.enableRipple"));
        renderBreadCrumb = Boolean.parseBoolean(getProperty("admin.renderBreadCrumb"));
        rippleElements = getProperty("admin.rippleElements");
        enableSlideMenu = Boolean.parseBoolean(getProperty("admin.enableSlideMenu"));
        skin = getProperty("admin.skin");
        autoShowNavbar = Boolean.parseBoolean(getProperty("admin.autoShowNavbar"));
    }

    private String getProperty(String property) {
        return Assert.has(userConfigFile.getProperty(property)) ? userConfigFile.getProperty(property) : adminConfigFile.getProperty(property);
    }

    public String getLoginPage() {
        return loginPage;
    }

    public String getIndexPage() {
        return indexPage;
    }

    public String getErrorPage() {
        return errorPage;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

    public boolean isDisableFilter() {
        return disableFilter;
    }

    public void setDisableFilter(boolean disableFilter) {
        this.disableFilter = disableFilter;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public Integer getBreadCrumbMaxSize() {
        return breadCrumbMaxSize;
    }

    public void setBreadCrumbMaxSize(Integer breadCrumbMaxSize) {
        this.breadCrumbMaxSize = breadCrumbMaxSize;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public boolean isRenderMessages() {
        return renderMessages;
    }

    public void setRenderMessages(boolean renderMessages) {
        this.renderMessages = renderMessages;
    }

    public boolean isRenderAjaxStatus() {
        return renderAjaxStatus;
    }

    public void setRenderAjaxStatus(boolean renderAjaxStatus) {
        this.renderAjaxStatus = renderAjaxStatus;
    }

    public boolean isEnableRipple() {
        return enableRipple;
    }

    public void setEnableRipple(boolean enableRipple) {
        this.enableRipple = enableRipple;
    }

    public boolean isRenderBreadCrumb() {
        return renderBreadCrumb;
    }

    public void setRenderBreadCrumb(boolean renderBreadCrumb) {
        this.renderBreadCrumb = renderBreadCrumb;
    }

    public String getRippleElements() {
        return rippleElements;
    }

    public void setRippleElements(String rippleElements) {
        this.rippleElements = rippleElements;
    }

    public boolean isEnableSlideMenu() {
        return enableSlideMenu;
    }

    public void setEnableSlideMenu(boolean enableSlideMenu) {
        this.enableSlideMenu = enableSlideMenu;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public boolean isAutoShowNavbar() {
        return autoShowNavbar;
    }

    public void setAutoShowNavbar(boolean autoShowNavbar) {
        this.autoShowNavbar = autoShowNavbar;
    }
}
