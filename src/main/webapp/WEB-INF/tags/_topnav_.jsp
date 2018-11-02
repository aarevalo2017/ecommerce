<%-- 
    Document   : _topnav_
    Created on : 04-09-2018, 22:10:56
    Author     : Alejandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="top">
  <div class="container"><div class="pull-left">
    </div>
    <div id="top-links" class="nav pull-right">
      <ul class="list-inline">
        <li>
          <a href="${pageContext.servletContext.contextPath}/contacto.jsp">
            <i class="fa fa-phone"></i>
          </a> 
          <span class="hidden-xs hidden-sm hidden-md">+56991623740</span>
        </li>
        <li class="dropdown">
          <a href="#" title="My Account" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-user"></i>
            <span class="hidden-xs hidden-sm hidden-md">Mi Cuenta</span>
            <span class="caret"></span>
          </a>
          <ul class="dropdown-menu dropdown-menu-right">
            <c:choose>
                <c:when test="${not empty pageContext.request.userPrincipal}">
                    <li><a href="${pageContext.servletContext.contextPath}/cliente/mi-cuenta.jsp">Mi Cuenta</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/cliente/mis-compras.jsp">Mis Compras</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/servlet/cliente?accion=logout">Cerrar Sesión</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.servletContext.contextPath}/login.jsp">Iniciar Sesión</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/registro.jsp">Registro</a></li>
                    <li><a href="${pageContext.servletContext.contextPath}/reset-password.jsp">Olvidé mi contraseña</a></li>
                </c:otherwise>
            </c:choose>
            <c:if test="${not empty pageContext.request.userPrincipal}">
                <!--<c:out value="${pageContext.request.userPrincipal.name}" />-->
            </c:if>
            <c:if test="${not empty pageContext.request.userPrincipal}">
            </c:if>
          </ul>
        </li>
        <li>
          <a href="${pageContext.servletContext.contextPath}/carro/carro.jsp" title="Shopping Cart">
            <i class="fa fa-shopping-cart"></i>
            <span class="hidden-xs hidden-sm hidden-md">Carro de compras</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.servletContext.contextPath}/cliente/pago.jsp" title="Checkout">
            <i class="fa fa-dollar-sign"></i>
            <span class="hidden-xs hidden-sm hidden-md">Pagar</span>
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>
