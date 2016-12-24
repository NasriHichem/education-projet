/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.smartdev.e.learning.services;

import tn.smartdev.e.learning.contrats.IUtilisateurService;
import tn.smartdev.e.learning.dao.UtilisateurDao;
import tn.smartdev.e.learning.entities.Utilisateur;

/**
 *
 * @author Hichem
 */
public class UtilisateurService implements IUtilisateurService{
    
    private UtilisateurDao udao ;
    
    public UtilisateurService()
    {

        udao=new UtilisateurDao();
    } 

    @Override
    public Utilisateur authentifier(String nomutilisateur, String motpasse) {
       return udao.authentifier(nomutilisateur, motpasse);
    }

    @Override
    public boolean inscrire(Utilisateur u) {
        return udao.inscrire(u);
    }
    
    
}
