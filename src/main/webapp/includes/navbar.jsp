
   <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">EFREZ </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i> <span class="badge text-bg-danger">${cart_list.size()}</span></a>
        </li>
<%
				if (auth != null) {
					if (auth.getEmail().equals("admin@gmail.com")) {
				%>
				 <li class="nav-item">
          <a class="nav-link" href="products.jsp">Admin Produits</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="addProduct.jsp">Ajouter Produit</a>
        </li>
        <% }
					%>
				<li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="Log-out"><i class="fa-solid fa-right-from-bracket"></i></a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login <i class="fa-solid fa-user"></i></a></li>
				<%
				}
				%>
        
        
         
      </ul>
      
    </div>
  </div>
</nav>
