/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.smartdev.e.learning.managesbeans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import tn.smartdev.e.learning.entities.Role;
import tn.smartdev.e.learning.entities.Utilisateur;
import tn.smartdev.e.learning.services.UtilisateurService;

/**
 *
 * @author Hichem
 */

@ManagedBean
@SessionScoped
public class UtilisateurBean {
    
    private String nomutlisateur="Nom utilisateur";
    private String motpasse="Mot passe";
    private String nom="Nom";
    private String prenom="Prenom";
    private String email="Email" ;
    private String nom_role;
    private UtilisateurService service ;
    private Utilisateur utilisateur ;
    private  HttpSession session;
    @PostConstruct
    public void init()
    {
      service=new UtilisateurService();  
    }
    
    public  String doauthentifier()
    {
       FacesContext facescontext=FacesContext.getCurrentInstance();
       session =(HttpSession) facescontext.getExternalContext().getSession(true);
       utilisateur=service.authentifier(nomutlisateur, motpasse);
       if(utilisateur!=null) 
       {
       session.setAttribute("membre",utilisateur);
       return "index?faces-redirect=true";
       }
       else
       {
       FacesContext.getCurrentInstance().addMessage("connexion", new FacesMessage("Erreur: Mot passe ou nom utilisateur incorrecte."));
       return null;
       }
    }
    public String doinscrire()
    {
      Utilisateur u=new Utilisateur();
      Role r=new Role();
      u.setNom(nom);
      u.setPrenom(prenom);
      u.setEmail(email);
      u.setNomutilisateur(nomutlisateur);
      u.setMotpasse(motpasse);
      r.setNom_role(nom_role);
      u.setRole(r);
      if(service.inscrire(u))
      {
        return "login?faces-redirect=true";  
      }
      else
      {
         return "inscrption?faces-redirect=true";
      }
      
    }
    public String doLogout()
    {
      session.invalidate();
      return "index?faces-redirect=true";
    }
    public String getNomutlisateur() {
        return nomutlisateur;
    }

    public void setNomutlisateur(String nomutlisateur) {
        this.nomutlisateur = nomutlisateur;
    }

    public String getMotpasse() {
        return motpasse;
    }

    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom_role() {
        return nom_role;
    }

    public void setNom_role(String nom_role) {
        this.nom_role = nom_role;
    }
    

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UtilisateurService getService() {
        return service;
    }

    public void setService(UtilisateurService service) {
        this.service = service;
    }
    
    
}
