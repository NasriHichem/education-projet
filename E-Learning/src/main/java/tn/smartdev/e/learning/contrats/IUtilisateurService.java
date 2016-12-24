/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.smartdev.e.learning.contrats;

import tn.smartdev.e.learning.entities.Utilisateur;

/**
 *
 * @author Hichem
 */
public interface IUtilisateurService {
    
    public Utilisateur authentifier(String nomutilisateur,String motpasse);
    public boolean inscrire(Utilisateur u);
}
