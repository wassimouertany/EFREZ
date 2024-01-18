package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductDAO;
import models.Connexion;

import models.Product;

/**
 * Servlet implementation class ChercherProduct
 */
@WebServlet("/chercher-product")
public class ChercherProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mot = request.getParameter("mot");
		ProductDAO productDao= new ProductDAO(Connexion.getConnection());

		
			
		List<Product> lesProduits = productDao.chercherParMC(mot);
		System.out.print(lesProduits.toString());
		request.setAttribute("produits", lesProduits);
		request.getRequestDispatcher("productSearch.jsp").forward(request, response);
		
		
		
	}
	}


