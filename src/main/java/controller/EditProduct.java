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
 * Servlet implementation class EditProduct
 */
@WebServlet("/edit-product")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String prix = request.getParameter("price");
		String image = request.getParameter("image");
		
		Double price = Double.parseDouble(prix);
		String resultat;
		Product product = new Product();
		product.setId(Integer.parseInt(id));
		product.setName(name);
		product.setCategory(category);
		product.setPrice(price);
		product.setImage(image);
		
		ProductDAO productDao = new ProductDAO(Connexion.getConnection());
		Product r = productDao.update(product);
		
		if(r!=null) {
	    	resultat="Modification avec succés";
	    	request.setAttribute("resultat", resultat);
	    	request.getRequestDispatcher("products.jsp").forward(request, response);	
	    }
	    else {
	    	resultat="Modification eronée !";
	    	request.setAttribute("resultat", resultat);
	    	request.getRequestDispatcher("products.jsp").forward(request, response);	
	    }
		
	}

}
