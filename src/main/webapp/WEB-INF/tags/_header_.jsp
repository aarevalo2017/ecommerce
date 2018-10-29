<%-- 
    Document   : _header_
    Created on : 04-09-2018, 21:49:09
    Author     : Alejandro
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <div id="logo"> 
          <h1>
            <a href="${pageContext.servletContext.contextPath}/index">La Vega Online</a>
          </h1>
        </div>
      </div>
      <form method="POST" action="${pageContext.servletContext.contextPath}/servlet/producto">
        <div class="col-sm-5">
          <div id="search" class="input-group">
            <input type="hidden" name="accion" value="buscarProducto"/>
            <input required type="text" name="criterio" value="" placeholder="Buscar Frutas y Verduras" class="form-control input-lg" />
            <span class="input-group-btn">
              <button type="submit" class="btn btn-default btn-lg"><i class="fa fa-search"></i></button>
            </span>
          </div>
        </div>
      </form>
      <div class="col-sm-3">
        <div id="cart" class="btn-group btn-block">
          <button type="button" data-toggle="dropdown" data-loading-text="Cargando..." class="btn btn-inverse btn-block btn-lg dropdown-toggle">
            <i class="fa fa-shopping-cart"></i>
            <span id="cart-total">${cant_carro} producto(s) - $0</span>
          </button>
            <ul class="dropdown-menu pull-right">
              <li>
                <p class="text-center">Tu carro se encuentra vacío!</p>
              </li>
            </ul>
        </div>
      </div>
    </div>
  </div>
</header>
