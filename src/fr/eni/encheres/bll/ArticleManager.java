package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO articleDAO;

	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}

	/**
	 * @author Samy-Lee
	 * @return List<Article>
	 * @throws BLLException
	 * Selectionne tout les articles avec Utilisateur et cat�gorie
	 */
	public List<Article> SelectAllArticlesAvecUtilisateurEtCategorie(int utilisateurId, int categorieId) throws BLLException {
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.SelectAllArticlesAvecUtilisateurEtCategorie(utilisateurId, categorieId);
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllArticlesAvecUtilisateurEtCategorie");
			throw new BLLException();
		}
		return listeArticle;

	}
	/**
	 * m�thode d'insertion d'article en bdd
	 * @author tjouvin
	 * @throws BLLException 
	 * @throws SQLException 
	 * @return article
	 */
	public void  insertArticle ( Article article, int utilisateurId, int categorieId) throws BLLException, SQLException {
		try {
			System.out.println("l� ! bll" + article);
			 articleDAO.insertArticle ( article, utilisateurId, categorieId );

		} catch (DALException e) {
			System.out.println("erreur lors de l'insertion de l'article");
			throw new BLLException();
		}
	}
	/**
	 * @author Samy-Lee
	 * @return List<Categorie>
	 * @throws BLLException
	 * Selectionne toutes les cat�gories
	 */
	public List<Categorie> SelectAllCategories() throws BLLException {
		
		List<Categorie> listeCategorie = null;
		
		try {
			listeCategorie = articleDAO.SelectAllCategories();
		} catch (DALException e) {
			System.out.println("Erreur manager SelectAllCategorie");
			throw new BLLException();
		}
		return listeCategorie;	
		
	}
	

	/**
	 * @author Samy-Lee
	 * @return List<Article>
	 * @throws BLLException
	 * Selectionne tout les articles avec Utilisateur et cat�gorie
	 */
	public List<Article> SelectAllArticles() throws BLLException {
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.selectAllArticles();
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllArticles");
			throw new BLLException();
		}
		return listeArticle;

	}
	
	public List<Article> selectAllByEtatVente(int etatVente) throws BLLException{
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.selectAllByEtatVente(etatVente);
		} catch (DALException e) {
			System.out.println("erreur manager selectAllByEtatVente");
			throw new BLLException();
		}
		return listeArticle;
	}
	
	public List<Article> selectAllByEtatVenteUtilisateur(int etatVente, int idUtilisateur) throws BLLException{
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.selectAllByEtatVenteUtilisateur(etatVente, idUtilisateur);
		} catch (DALException e) {
			System.out.println("erreur manager selectAllByEtatVenteUtilisateur");
			throw new BLLException();
		}
		return listeArticle;
	}
	
	public List<Article> selectAllByEtatVenteGagne(int etatVente, int idUtilisateur) throws BLLException{
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.selectAllByEtatVenteGagne(etatVente, idUtilisateur);
		} catch (DALException e) {
			System.out.println("erreur manager selectAllByEtatVenteGagne");
			throw new BLLException();
		}
		return listeArticle;
	}
	
	public List<Article> SelectAllEncheresByEtat(int idUtilisateur, int etatVente) throws BLLException{
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.SelectAllEncheresByEtat(idUtilisateur, etatVente);
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllEncheresByEtat");
			throw new BLLException();
		}
		return listeArticle;
	}

	
	public Article selectArticleByID(int idArticle) {
		Article article = null;
		try {
			article = articleDAO.SelectEnchereById(idArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;

	}
}
