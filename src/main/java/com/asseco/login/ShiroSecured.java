/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.login;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 *
 * @author kiril.micev
 */

@Inherited
@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShiroSecured {
    //
}
