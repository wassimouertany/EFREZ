<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProductDAO"%>
<%@page import="models.Connexion"%>
<%@page import="models.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	User auth =(User) request.getSession().getAttribute("auth");
	if(auth!=null){
		request.setAttribute("auth", auth);
	}
	
	ProductDAO pd= new ProductDAO(Connexion.getConnection());
	List<Product> products = pd.getAllProducts();
	
	ArrayList<Cart> cart_list =  (ArrayList<Cart>) session.getAttribute("cart-list");
	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	}
%>    
<!DOCTYPE html>
<html>
<head>
<title>Bienvenu to shopping cart</title>
<%@include file="includes/head.jsp" %>

</head>
<body>
<%@include file="includes/navbar.jsp" %>
<style>
.card {
  width: 18rem;
  height: 100%;
   
}
.row {
  display: flex;
  flex-wrap: wrap;
}

.col-md-3 {
  flex-basis: 25%;
}

.card {
  width: 100%;
  height: 100%;
 
}

</style>

<div class="container">

	<div class="card-header my-3">Tous les produits
	</div>
	<div class="search">
	<form action="chercher-product" method="post">
	<input type="text" name="mot">
	<button type="submit" class="btn btn-outline-primary btn-sm" style="margin-bottom :7px;">Chercher</button>
	</form></div>
	<div class="row">
	<%
		if(!products.isEmpty()){
			for(Product p:products){%>
				<div class="col-md-3 my-3">
				<div class="card h-100" style="width: 18rem;">
	  				<img class="image" src="<%=p.getImage() %>" class="card-img-top" alt="...">
	  				<div class="card-body">
	    				<h5 class="card-title"><%=p.getName() %></h5>
	    				<h6 class="price">Prix :  <%= p.getPrice()  %>TND</h6>
	    				<h6 class="price">Categorie :  <%= p.getCategory() %></h6>
	    				<div class="mt-3 d-flex justify-content-between">
	    				<a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-dark">Ajouter à la carte</a>
	    				<a href="order-now?quantity=1&id=<%= p.getId() %>" class="btn btn-primary">Acheter</a>    				
	    				</div>
	  				</div>
				</div>
			</div>
			<%}
		}
	%>
		
	</div>
</div>
<div class="background"></div>


<%@include file="includes/footer.jsp" %>
</body>
</html>