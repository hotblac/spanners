package org.dontpanic.spanners.springmvc.saml;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Simplest implementation of SAMLUserDetailsService that just creates users with default roles.
 * User: Stevie
 * Date: 05/05/14
 */
public class SimpleSAMLUserDetailsService implements SAMLUserDetailsService {

    public static final String DUMMY_PASSWORD = "DUMMY_PASSWORD";
    private List<String> roles;

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
        String username = credential.getNameID().getValue();
        Collection<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            gas.add(new SimpleGrantedAuthority(role));
        }

        return new User(username, DUMMY_PASSWORD, gas);
    }
}
