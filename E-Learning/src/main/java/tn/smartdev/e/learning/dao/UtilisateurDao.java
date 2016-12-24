/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.smartdev.e.learning.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import tn.smartdev.e.learning.contrats.IUtilisateurDao;
import tn.smartdev.e.learning.entities.Utilisateur;


/**
 *
 * @author Hichem
 */
public class UtilisateurDao implements IUtilisateurDao{

    EntityManagerFactory emf;
    EntityManager em ;

    public UtilisateurDao(){ 
           emf = Persistence.createEntityManagerFactory( "e-learning");
           em = emf.createEntityManager();
    }
    
    @Override
    public Utilisateur authentifier(String nomutilisateur, String motpasse) {
        Utilisateur u ;
        Query query=em.createQuery("select u from Utilisateur u where u.nomutilisateur=:nomutilisateur and"
                + " u.motpasse=:motpasse");
        query.setParameter("nomutilisateur", nomutilisateur);
        query.setParameter("motpasse", motpasse);
        try {
              u = (Utilisateur) query.getSingleResult();
	      } catch (Exception e) {
	         u=null;
		}
        return  u;
    }

    @Override
    public boolean inscrire(Utilisateur u) {
        boolean result=false ;
        if(!isUserExists(u))
        {
           em.persist(u);
           result=true ;         
        }
        return result ;
    }

    @Override
    public boolean isUserExists(Utilisateur u) {
        Utilisateur utilisateur;
        Query query=em.createQuery("select u from Utilisateur u where u.nomutilisateur=:nomutilisateur and"
                + " u.motpasse=:motpasse and u.email=:email");
        query.setParameter("nomutilisateur", u.getNomutilisateur());
        query.setParameter("motpasse", u.getMotpasse());
        query.setParameter("email", u.getEmail());
        try {
              utilisateur = (Utilisateur) query.getSingleResult();
	      } catch (Exception e) {
	      utilisateur=null;
		}
        if(utilisateur!=null)
	 return true ;
         return false; 
    }
    
}
