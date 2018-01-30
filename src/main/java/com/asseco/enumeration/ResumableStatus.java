/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.enumeration;

/**
 *
 * @author kiril.micev
 */
public enum ResumableStatus {
    
    RESUMABLE_NEW, //nov 
    JOB_CANCELED,//otkazana
    JOB_STARTED,//zapocnata
    JOB_ERROR,//greska 
    JOB_STARTING,//schedulirana
    JOB_ENDED,//zavrsena (uspesno)

}
