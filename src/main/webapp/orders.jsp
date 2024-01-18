<%@page import="java.util.List"%>
<%@page import="dao.OrderDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Connexion"%>
<%@page import="models.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% User auth = (User) request.getSession().getAttribute("auth");
List<Order> orders = null;
		if (auth != null) {
		    request.setAttribute("auth", auth);
		    OrderDAO orderDao  = new OrderDAO(Connexion.getConnection());
			orders = orderDao.userOrders(auth.getId());
	}
	else{
		response.sendRedirect("login.jsp");
	}
	  
	ArrayList<Cart> cart_list =  (ArrayList<Cart>) session.getAttribute("cart-list");
	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	}
	
%>  
<!DOCTYPE html>
<html>
<head>
<title>Orders</title>
<%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
	
	<div class="container">
		<div class="card-header my-3">Tous les ordres</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Nom</th>
					<th scope="col">Categorie</th>
					<th scope="col">Quantité</th>
					<th scope="col">Prix</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<%
					if(orders != null){
						for(Order o:orders){%>
							<tr>
							<%System.out.println(o.getName()); %>
								<td><%= o.getDate() %></td>
								<td><%= o.getName() %></td>
								<td><%= o.getCategory() %></td>
								<td><%= o.getQuantity() %></td>
								<td><%= o.getPrice() %></td>
								<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%= o.getOrderId()%>">Annuler</a></td>
							<tr>
						<% }
					}
				%>
			</tbody>
		</table>
	</div>
	
	
	
	
	
	
	
	
	<div class="background"></div>
	
	
	
	
	

<%@include file="includes/footer.jsp" %>
</body>
</html>