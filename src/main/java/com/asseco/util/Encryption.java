/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.util;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.util.Random;

/**
 *
 * @author darko.aleksovski
 */
public class Encryption {

    public String cryptReverseWithSha256(String pass) {
        String reverseToken = StringUtils.reverse(pass);
        return new Sha256Hash(reverseToken).toString();
    }
    
    public String getRandomAlphaNum(int size) {
        String returnValue = "";

        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size - 1; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        returnValue = sb.toString();
        return returnValue;
    }
}
