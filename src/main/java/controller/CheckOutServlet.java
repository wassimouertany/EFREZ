package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import models.Cart;
import models.Connexion;
import models.Order;
import models.User;


@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			
			//retrieve all cart products
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			//user authentification
			User auth = (User) request.getSession().getAttribute("auth");
			
			//check auth and card list
			if(cart_list != null && auth != null) {
				for(Cart c:cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setuId(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					OrderDAO orderDao = new OrderDAO(Connexion.getConnection());
					boolean result = orderDao.insertOrder(order);
					if(!result) break;
				}
				
				cart_list.clear();
				response.sendRedirect("cart.jsp");
			}else {
				if(auth==null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
