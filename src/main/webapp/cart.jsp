<%@page import="java.util.List"%>
<%@page import="dao.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Connexion"%>
<%@page import="models.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% User auth = (User) request.getSession().getAttribute("auth");
	if(auth != null){
		request.setAttribute("auth", auth); 
	}
	
	ArrayList<Cart> cart_list =  (ArrayList<Cart>) session.getAttribute("cart-list");
	List<Cart> cartProduct = null;
	if(cart_list != null){
		ProductDAO pDao = new ProductDAO(Connexion.getConnection());
		cartProduct = pDao.getCartProducts(cart_list);
		double total = pDao.getTotalPrice(cart_list);
		request.setAttribute("cart_list", cart_list);
		request.setAttribute("total", total);
	}
%>  
<!DOCTYPE html>
<html>
<head>

<title>Les pages de cartes</title>
<%@include file="includes/head.jsp" %>
<style type="text/css">
	.table tbody td{
		vertical-align: middle;
		
	}	
	.btn-incre, .btn-decre{
		box-shadow:none;
		font-size :25px;
	}
</style>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
	<div class="d-flex py-3"> 
		<h3>Prix Total : ${(total>0)?total:0} TND</h3> <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a></div>
	<table class="table table-light">
	<thead>
		<tr>
			<th scope="col">Nom</th>
			<th scope="col">Catégorie</th>
			<th scope="col">Prix</th>
			<th scope="col" style="text-align:center">Acheter Maintenant</th>
			<th scope="col"></th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody>
	<% if(cart_list != null){
		for(Cart c:cartProduct){%>
			<tr>
			<td><%= c.getName() %></td>
			<td><%= c.getCategory() %></td>
			<td><%= c.getPrice() %> TND</td>
			<td>
				<form action="order-now" method="post" class="form-inline">
					<input type="hidden" name ="id" value="<%= c.getId() %>" class="form-input">
					<div class="form-group d-flex justify-content-between">
						<a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%= c.getId()%>"><i class="fas fa-plus-square"></i></a>
						<input type="text" name="quantity" class="form-control w-50" value="<%= c.getQuantity() %>" readonly>
						<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%= c.getId()%>"><i class="fas fa-minus-square"></i></a>
						
					</div>
					<td><button type="submit" class="btn btn-primary btn-sm">Acheter</button></td>
				</form>
			</td>
			<td><a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%= c.getId()%>">Annuler</a></td>
		</tr>	
		<% 
		}
		
	}	
		%>
	
		
	</tbody>
	</table>	
</div>
<div class="background"></div>
<%@include file="includes/footer.jsp" %>
</body>
</html>