<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProductDAO"%>
<%@page import="models.Connexion"%>
<%@page import="models.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	User auth =(User) request.getSession().getAttribute("auth");
    boolean isAdmin = auth != null && auth.getEmail().equals("admin@gmail.com");
    if (!isAdmin) {
        response.sendRedirect("login.jsp");
    }
	
	ProductDAO pd= new ProductDAO(Connexion.getConnection());
	List<Product> products = pd.getAllProducts();
	
	String R=(String) request.getAttribute("resultat"); 
	
	
%>    
<!DOCTYPE html>
<html>
<head>
<title>Bienvenu to shopping cart</title>
<%@include file="includes/head.jsp" %>

</head>
<body>
<style>

</style>
<%@include file="includes/navbar.jsp" %>

<%if (R != null) {%>

	<script>alert("<%= R%>");</script>
	<%} %>
<div class="container">
	<div class="card-header my-3">Tous les produits
	</div>
	
	
				<table id="example" class="table table-striped table-light" style="width:100%">
        <thead>
        <tr>
            <th>Identifiant</th>
            <th>Nom</th>
            <th>Catégorie</th>
            <th>Prix</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
		if(!products.isEmpty()){
			for(Product p:products){%>
        
            <tr>
                <td><%=p.getId() %></td>
                <td><%=p.getName() %></td>
                <td><%=p.getCategory() %></td>
                <td><%=p.getPrice() %></td>
                <td><a class="btn btn-sm btn-danger" href="remove-product?id=<%= p.getId()%>">Supprimer</a>
                	<a class="btn btn-sm btn-primary" href="editProduct.jsp?id=<%= p.getId()%>">Modifier</a>
                </td>
       
      
			<%}
		}
	%>
	
	  </tbody>

    </table> 
	
	
	
	
    
		
	</div>
<div class="background"></div>
<%@include file="includes/footer.jsp" %>
</body>
</html>