package uasz.sn.authentification.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import uasz.sn.authentification.AuthEntryPoint;
import uasz.sn.authentification.AuthenticationFilter;
import uasz.sn.authentification.services.UtilisateurDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UtilisateurDetailsService utilisateurDetailsService;

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Autowired
    private AuthEntryPoint exceptionHandler;

    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
			throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception  {
		auth.userDetailsService(utilisateurDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
    	// Add this row

    	http.csrf().disable().cors().and()
    	.authorizeHttpRequests().anyRequest().permitAll();
    	
    	
		// http.csrf().disable()
		// 	.cors().and()
		// 	.sessionManagement()
		// 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		// 	.and()
		// 	.authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/login").permitAll()
		// 	.requestMatchers(HttpMethod.POST, "/api/**").permitAll()
		// 	.requestMatchers("/api/**").permitAll()
		// 	.requestMatchers("/apiDTO/**").permitAll()
		// 	.anyRequest().authenticated()   
		// 	.and()
		// 	.exceptionHandling().authenticationEntryPoint(exceptionHandler)
		// 	.and()
		// 	.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
		// 	.httpBasic(withDefaults());
		
	    return http.build();
    }

    // @Bean
	// CorsConfigurationSource corsConfigurationSource() {
	// 	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	// 	CorsConfiguration config = new CorsConfiguration();
	// 	config.setAllowedOrigins(Arrays.asList("*"));
	// 	config.setAllowedMethods(Arrays.asList("*"));
	// 	config.setAllowedHeaders(Arrays.asList("*"));
	// 	config.setAllowCredentials(false);
	// 	config.applyPermitDefaultValues();

	// 	source.registerCorsConfiguration("/**", config);
	// 	return source;
	// }
}







// @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeHttpRequests(
    //             (authorizeHttpRequests) -> 
    //                 authorizeHttpRequests 
    //                     .requestMatchers("/js/**", "/css/**").permitAll()
    //                     .requestMatchers("/login**", "/logout**").permitAll()
    //                     .requestMatchers("/h2/**").permitAll()
    //                     .anyRequest().authenticated()
    //         )
    //         .formLogin(
    //             (formLogin) ->
    //                 formLogin
    //                     .usernameParameter("username")
    //                     .passwordParameter("password")
    //                     .loginPage("/login")
    //                     .defaultSuccessUrl("/")
    //                     .successForwardUrl("/")
    //                     .permitAll()
    //         );

    //     return http.build();
    // }