package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import models.Connexion;
import models.Product;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/add-product")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String prix = request.getParameter("price");
		String image = request.getParameter("image");
		
		Double price = Double.parseDouble(prix);
		String resultat;
		Product product = new Product();
		product.setName(name);
		product.setCategory(category);
		product.setPrice(price);
		product.setImage(image);
		
		ProductDAO productDao = new ProductDAO(Connexion.getConnection());
		Product r = productDao.save(product);
		
		if(r!=null) {
	    	resultat="Ajout avec succés";
	    	request.setAttribute("resultat", resultat);
	    	request.getRequestDispatcher("addProduct.jsp").forward(request, response);	
	    }
	    else {
	    	resultat="Ajout eroné !";
	    	request.setAttribute("resultat", resultat);
	    	request.getRequestDispatcher("addProduct.jsp").forward(request, response);	
	    }
	}

}
