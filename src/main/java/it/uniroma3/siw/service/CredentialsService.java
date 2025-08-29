package it.uniroma3.siw.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.controller.AuthenticationController;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
    @Autowired
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected CredentialsRepository credentialsRepository;
	
	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}
		
    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        credentials.setRole(Credentials.UTENTE_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }

	public void save(Credentials credentials) {
		this.credentialsRepository.save(credentials);
	}

	public Optional<Credentials> getAuthenticatedUserCredentials() {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            Credentials credentials = null;

            // Handle UserDetails or OIDC user
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                logger.info("Attempting to retrieve credentials for UserDetails with username: {}", username);
                credentials = credentialsRepository.findByUsername(username).orElse(null);
            } else if (principal instanceof DefaultOidcUser) {
                String email = ((DefaultOidcUser) principal).getEmail();
                logger.info("Attempting to retrieve credentials for OIDC user with username: {}", email);
                credentials = credentialsRepository.findByEmail("USERNAMEof" + email).orElse(null);
            }

            if (credentials != null) {
                logger.info("Credentials found for user.");
            } else {
                logger.warn("Credentials not found for the authenticated user.");
            }

            return Optional.ofNullable(credentials);
        } else {
            logger.warn("No authenticated user found.");
        }

        return Optional.empty();
	}
}
