/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.websocket;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * @author kiril.micev
 */
public class PushMessage  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @SerializedName("callMethod")
    private String callMethod;
    
    @SerializedName("msq")
    private String message;

    public String getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(String callMethod) {
        this.callMethod = callMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
   
}
