
package com.asseco.login;

import com.asseco.util.Encryption;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 *
 * @author kiril.micev
 */
public class MalmedCredentialsMatcher extends SimpleCredentialsMatcher  {

    @Override 
    public boolean doCredentialsMatch ( AuthenticationToken token, AuthenticationInfo info) {
        String tokenCredentials = charArrayToString(token.getCredentials());        
        Encryption encryption = new Encryption ();
        String encryptedToken = encryption.cryptReverseWithSha256(tokenCredentials);

        String accountCredentials = charArrayToString(info.getCredentials());
        return accountCredentials.equals(encryptedToken);
    }

    private  String charArrayToString ( Object credentials) {
         return  new  String ((char []) credentials);
    }
}
