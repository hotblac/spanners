package org.dontpanic.spanners.springmvc.saml;

import org.junit.Before;
import org.junit.Test;
import org.opensaml.saml2.core.NameID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.saml.SAMLCredential;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for SimpleSAMLUserDetailsService
 * User: Stevie
 * Date: 05/05/14
 */
public class SimpleSAMLUserDetailsServiceTest {

    private static final List<String> ROLES = Arrays.asList("ROLE1", "ROLE2");
    private static final String USER_NAME = "USER_NAME";

    private SimpleSAMLUserDetailsService service = new SimpleSAMLUserDetailsService();

    @Before
    public void onSetUp() {
        service.setRoles(ROLES);
    }


    @Test
    public void testUserDetails() {

        // Use service to get userDetails from SAML credential
        SAMLCredential credential = stubCredential(USER_NAME);
        Object userDetails = service.loadUserBySAML(credential);

        assertNotNull("userDetails is null", userDetails);
        assertThat("userDetails is incorrect type", userDetails, instanceOf(UserDetails.class));

        Collection<? extends GrantedAuthority> gas = ((UserDetails)userDetails).getAuthorities();
        assertNotNull("No granted authorities", gas);
        assertEquals(ROLES.size(), gas.size());
        for (GrantedAuthority ga : gas) {
            assertNotNull("GrantedAuthority is null");
            assertThat(ga.getAuthority(), isIn(ROLES));
        }

    }


    private SAMLCredential stubCredential(String username) {
        SAMLCredential credential = mock(SAMLCredential.class);
        NameID nameId = mock(NameID.class);

        when(nameId.getValue()).thenReturn(username);
        when(credential.getNameID()).thenReturn(nameId);

        return credential;
    }

}
