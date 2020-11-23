package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.ihm.model.ConnexionForm;


/**
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/ModificationProfil")
public class ModifierProfilServlet extends HttpServlet {


	UtilisateurManager mgr = new UtilisateurManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session == null) {
			session = request.getSession();
			response.sendRedirect("Accueil");
		}else {
			session = request.getSession();
			Utilisateur utilDemande = null;
			
			try {
				utilDemande = mgr.selectByPseudo((String) session.getAttribute("pseudo"));
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Méthode
			request.setAttribute("pseudo", utilDemande.getPseudo());
			request.setAttribute("prenom", utilDemande.getPrenom());
			request.setAttribute("nom", utilDemande.getNom());
			request.setAttribute("email", utilDemande.getEmail());
			request.setAttribute("telephone", utilDemande.getTelephone());
			request.setAttribute("rue", utilDemande.getRue());
			request.setAttribute("codePostal", utilDemande.getCodePostal());
			request.setAttribute("ville", utilDemande.getVille());
			
			request.getRequestDispatcher("/WEB-INF/pages/ModifierProfil.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		// Bouton Annuler
		if (request.getParameter("annuler") != null) {
			response.sendRedirect("ListeEncheres");
		}
		
		//Bouton Enregistrer
		if (request.getParameter("enregistrer") != null) {
			Utilisateur UserCurrent = null;
			// TODO v�rifier les donn�es de la requ�te (factorisation Inscription ?)
			
			ArrayList<String> entries = new ArrayList<String>();
			
			entries.add("pseudo");
			entries.add("nom");
			entries.add("prenom");
			entries.add("email");
			entries.add("telephone");
			entries.add("rue");
			entries.add("codePostal");
			entries.add("ville");
			entries.add("mdp");
			entries.add("confirmMdp");
			
			
			List<String> paramUser = ConnexionForm.checkForm(request, entries);
			
			try {
				UserCurrent = mgr.selectByPseudo((String) session.getAttribute("pseudo"));
			} catch (BLLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String id = String.valueOf(UserCurrent.getId());
			paramUser.add(0, id);  
			
			Utilisateur user = new Utilisateur(paramUser);
			try {
				mgr.updateById(user);
				request.setAttribute("messageUpdate", "Les modifications ont bien �t� enregistr�es");
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("memeProfil", true);
			session.setAttribute("pseudo", user.getPseudo());
			request.setAttribute("pseudo", user.getPseudo());
			request.setAttribute("nom", user.getNom());
			request.setAttribute("prenom", user.getPrenom());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("telephone", user.getTelephone());
			request.setAttribute("rue", user.getRue());
			request.setAttribute("codePostal", user.getCodePostal());
			request.setAttribute("ville", user.getVille());
			request.setAttribute("credit", user.getCredit());
			request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp").forward(request, response);
		}
		
		// Bouton Supprimer
		if (request.getParameter("supprimer") != null) {
			try {
				Utilisateur user = mgr.selectByPseudo((String) session.getAttribute("pseudo"));
				mgr.deleteById(user.getId());
			} catch (BLLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("Accueil");
		}
	}

}
