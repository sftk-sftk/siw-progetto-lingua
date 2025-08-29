/*
package it.uniroma3.siw.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(OAuth2AuthenticationSuccessHandler.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyService companyService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Log that the handler is triggered
        logger.info("OAuth2AuthenticationSuccessHandler triggered");

        // Extract OIDC user information in order to fill the User's attributes
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        String email = oidcUser.getEmail();
        String givenName = oidcUser.getGivenName();
        String familyName = oidcUser.getFamilyName();

        logger.info("User authenticated with OAuth2 provider. Email: {}, Given Name: {}, Family Name: {}", email, givenName, familyName);

        /* Check if user already exists in the database this is the DEFAULT USERNAME PATTERN assigned to
         * users who don't register with standard procedure *
        String username = "USERNAMEof" + email; // Ensure the username is formatted correctly
        Credentials credentials = credentialsService.findByUsername(username).orElse(null);

        User user;
        if (credentials == null) {
            // Create new user and credentials if they don't exist
            user = new User();
            user.setName(givenName);
            user.setSurname(familyName);
            user.setEmail(email);


            // Find or create the 'UNKNOWN' company using the CompanyService method
            Company unknownCompany = companyService.findOrCreateCompanyByName("UNKNOWN");
            user.setCompany(unknownCompany);// Assign "UNKNOWN" as default

            // Set a default address
            Address address = new Address("Unknown");
            user.setAddress(address);

            userService.save(user);  // Persist the user

            credentials = new Credentials();
            credentials.setUsername(username);
            credentials.setPassword(passwordEncoder.encode("OIDC_USER"));  // Placeholder password
            credentials.setUser(user);
            credentials.setRole(Credentials.USER);  // Default role

            credentialsService.save(credentials);  // Save credentials
            logger.info("New user and credentials created for email: {}", email);
        } else {
            user = credentials.getUser();
            logger.info("Existing user found for email: {}", email);
        }

        // Cast the authentication to OAuth2AuthenticationToken to get the registration ID
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();

        // Assign roles and redirect
        String role = credentials.getRole();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        OAuth2AuthenticationToken newAuth = new OAuth2AuthenticationToken(oidcUser, authorities, authorizedClientRegistrationId);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        
        /* Se viene passato il ruolo di un utente USER, sarà certo che il suo ruolo sarà proprio quello di
         * USER e quindi è inutile questo controllo, almeno per ora, coi due utenti ben distinti *
        if (role.equals(Credentials.HOST)) {
            logger.info("Redirecting host to the admin dashboard");
            response.sendRedirect("/host/dashboard");
        } else if (role.equals(Credentials.EMPLOYEE)) {
        	logger.info("Redirecting employee to the employee dashboard");
        	response.sendRedirect("employeePage");
        } else if (role.equals(Credentials.INTERMEDIATE_HOST)) {
            logger.info("Redirecting host to the admin dashboard");
            response.sendRedirect("/intermediateHost/dashboard");
        } else {
            logger.info("Redirecting user to index");
            response.sendRedirect("/");
        }
    }
}*/