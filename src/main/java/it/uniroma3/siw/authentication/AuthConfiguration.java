package it.uniroma3.siw.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static it.uniroma3.siw.model.Credentials.ADMIN_ROLE;
import static it.uniroma3.siw.model.Credentials.UTENTE_ROLE;

//import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.authoritiesByUsernameQuery("SELECT username, role from credenziali WHERE username=?")
		.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?");
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.cors(cors -> cors.disable())
		.authorizeHttpRequests(requests -> requests
				// Pagine pubbliche e risorse statiche
				.requestMatchers(HttpMethod.GET,
						"/", "/index", "/register", "/login", "/areaRiservata/**", "/landingPage/**", "/favicon.ico",
						"/css/**", "/js/**", "/images/**"
						).permitAll()
				// Registrazione e login aperti a tutti (POST)
				.requestMatchers("/lingua/**","/lingua/cinese").permitAll()

				.requestMatchers(HttpMethod.POST, "/register", "/login").permitAll()
				// Area amministrativa solo per autore
				.requestMatchers("/admin/**").hasAuthority(ADMIN_ROLE)
				// Area amministrativa solo per giocatore
				.requestMatchers("/utente/**").hasAuthority(UTENTE_ROLE)
				// Tutte le altre richieste devono essere autenticate
				.anyRequest().authenticated()
				)
		.formLogin(login -> login
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/success", true)	//pagina da fare!
				.failureUrl("/login?error=true")
				)
		.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.clearAuthentication(true)
				.permitAll()
				);

		return httpSecurity.build();
	}

}