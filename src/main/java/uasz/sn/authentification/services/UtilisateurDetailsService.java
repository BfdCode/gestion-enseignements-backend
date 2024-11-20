package uasz.sn.authentification.services;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uasz.sn.authentification.modeles.Role;
import uasz.sn.authentification.modeles.Utilisateur;
import uasz.sn.authentification.repositories.UtilisateurRepository;

@Service
public class UtilisateurDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findUtilisateurByUsername(username);

        UserBuilder builder = null;
		if (utilisateur.isPresent()) {
			Utilisateur currentUser = utilisateur.get();
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(currentUser.getPassword());
			// builder.roles(currentUser.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new));
			builder.roles(Optional.ofNullable(currentUser.getRoles())
                .orElse(Collections.emptyList())
                .stream()
                .map(Role::getRole)
                .toArray(String[]::new));
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		
		return builder.build();	 
    }
            // Encien corps de la fonction LoadUserByUsername()
        // 
        // String[] roles = utilisateur.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);
        //                  OU BIEN
        // String[] roles = Optional.ofNullable(utilisateur.getRoles())
        //     .orElse(Collections.emptyList())
        //     .stream()
        //     .map(Role::getRole)
        //     .toArray(String[]::new);

        // UserDetails userDetails = 
        //     org.springframework.security.core.userdetails.User.builder()
        //         .username(utilisateur.getUsername())
        //         .password(utilisateur.getPassword())
        //         .roles(roles)
        //         .build();

        // return userDetails;

}
