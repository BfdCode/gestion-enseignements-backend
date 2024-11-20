package uasz.sn.authentification.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import uasz.sn.authentification.modeles.Role;
import uasz.sn.authentification.modeles.Utilisateur;
import uasz.sn.authentification.repositories.RoleRepository;
import uasz.sn.authentification.repositories.UtilisateurRepository;

@Service
@Transactional
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Utilisateur ajouter_Utilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Role ajouter_Role(Role role) {
        return roleRepository.save(role);
    }
    
    // public void ajouter_UtilisateurRoles(Utilisateur utilisateur, Role role) {
    //     Optional<Utilisateur> userOpt = utilisateurRepository.findUtilisateurByUsername(utilisateur.getUsername());
    //     Role profil = roleRepository.findRoleByRole(role.getRole());
    //     Utilisateur user = userOpt.get();
    //     user.getRoles().add(profil);
    // }

    public Utilisateur ajouter_UtilisateurRoles(Utilisateur utilisateur, Role role) {
        Optional<Utilisateur> userOpt = utilisateurRepository.findUtilisateurByUsername(utilisateur.getUsername());
        Role profil = roleRepository.findRoleByRole(role.getRole());
        Utilisateur user = userOpt.get();
        user.getRoles().add(profil);
        return utilisateurRepository.save(user);
    }

    public Optional<Utilisateur> rechercher_Utilisateur(String username){
        return utilisateurRepository.findUtilisateurByUsername(username);
    }
}
