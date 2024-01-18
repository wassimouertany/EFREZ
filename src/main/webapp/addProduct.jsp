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
	
    String R=(String) request.getAttribute("resultat"); 
	

%>    
<!DOCTYPE html>
<html>
<head>
<title>Ajouter un produit</title>
<%@include file="includes/head.jsp" %>
<style>


label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="number"] {
  width: 100%;
  padding: 5px;
  margin-bottom: 10px;
}

button[type="submit"] {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: #fff;
  border: none;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #45a049;
}
</style>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<%if (R != null) {%>

	<script>alert("<%= R%>");</script>
	<%} %>
<div class="container">
	<div class="card-header my-3">Ajouter un produit
	</div>
	
	<form action="add-product" method="post">
		<label>Nom :</label>
		<input type="text" name="name"> <br><br>
		<label>Catégorie :</label>
		<input type="text" name="category"> <br><br>
		<label>Prix :</label>
		<input type="text" name="price"> <br><br>
		<label>Image</label>
		<input type="text" name="image"> <br><br>
		<button type="submit">Ajouter</button>
	</form>
				
	
	
	
	
    
		
	</div>
<div class="background"></div>
<%@include file="includes/footer.jsp" %>
</body>
</html>