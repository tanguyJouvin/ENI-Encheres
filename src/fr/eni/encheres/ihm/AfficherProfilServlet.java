package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class AfficherProfilServlet
 */
@WebServlet("/Profil")
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/*
	 * @Author : Valentin
	 * 
	 * V�rifie si le parametre pseudo d�signe un utilisateur existant
	 * Si il n'existe pas, afficher la page de son profil
	 * Sinon page 404 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			session = request.getSession();
			response.sendRedirect("Accueil");
		}else {
			session = request.getSession();
		
			UtilisateurManager mgr = new UtilisateurManager();
			Utilisateur utilDemande = null;
			String utilCourant = (String) session.getAttribute("pseudo");
			boolean memeProfil;
		
			try {
				utilDemande = mgr.selectByPseudo(request.getParameter("pseudo"));
				if(utilDemande == null){
					if(utilCourant.isEmpty()) {
						request.getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);
					} else 
						utilDemande = mgr.selectByPseudo(utilCourant);
					
				}
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if((utilDemande.getPseudo()).equals(utilCourant)){
				memeProfil = true;
			}else
				memeProfil = false;
				
			request.setAttribute("pseudo", utilDemande.getPseudo());
			request.setAttribute("nom", utilDemande.getNom());
			request.setAttribute("prenom", utilDemande.getPrenom());
			request.setAttribute("email", utilDemande.getEmail());
			request.setAttribute("telephone", utilDemande.getTelephone());
			request.setAttribute("rue", utilDemande.getRue());
			request.setAttribute("codePostal", utilDemande.getCodePostal());
			request.setAttribute("ville", utilDemande.getVille());
			request.setAttribute("credit", utilDemande.getCredit());
			
			request.setAttribute("memeProfil", memeProfil);
			request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("annuler") != null) {
			response.sendRedirect("ListeEncheres");
		}else {
			request.getRequestDispatcher("/WEB-INF/pages/ModifierProfil.jsp").forward(request, response);
	}
	}
}
