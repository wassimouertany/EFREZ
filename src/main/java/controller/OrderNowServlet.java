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

/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter outPrint = response.getWriter()){
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			
			User auth = (User) request.getSession().getAttribute("auth");
				if(auth != null) {
					String productId = request.getParameter("id");
					int productQuantity = Integer.parseInt(request.getParameter("quantity"));
					if(productQuantity <=0) {
						productQuantity = 1;
					}
					
					Order orderModel = new Order();
					orderModel.setId(Integer.parseInt(productId));
					orderModel.setuId(auth.getId());
					orderModel.setQuantity(productQuantity);
					orderModel.setDate(formatter.format(date));
					
					OrderDAO orderDao = new OrderDAO(Connexion.getConnection());
					boolean result = orderDao.insertOrder(orderModel);
					
					if(result) {
						ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
						if(cart_list != null) {
							for(Cart c:cart_list) {
								if(c.getId() == Integer.parseInt(productId)) {
									cart_list.remove(cart_list.indexOf(c));
									break;
								}
							}
							
						}
						response.sendRedirect("orders.jsp");
					}else {
						outPrint.print("order failed");
					}
				}else {
					response.sendRedirect("login.jsp");
				}
			
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
