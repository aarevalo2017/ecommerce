<%-- 
    Document   : _aside_cliente_
    Created on : 04-09-2018, 22:17:28
    Author     : Alejandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside id="column-right" class="col-sm-3 hidden-xs pull-right">
  <div class="list-group">
    <c:choose>
      <c:when test="${not empty pageContext.request.userPrincipal}">
        <a href="${pageContext.servletContext.contextPath}/servlet/cliente?accion=logout" class="list-group-item text-danger">Cerrar sesión</a>
      </c:when>
      <c:otherwise>
        <a href="${pageContext.servletContext.contextPath}/login.jsp" class="list-group-item">Inicio de sesión</a>
        <a href="${pageContext.servletContext.contextPath}/reset-password.jsp" class="list-group-item">Olvidé mi Contraseña</a>
        <a href="${pageContext.servletContext.contextPath}/registro.jsp" class="list-group-item">Registro</a>
      </c:otherwise>
    </c:choose>
    <a href="${pageContext.servletContext.contextPath}/cliente/mi-cuenta.jsp" class="list-group-item">Mi Cuenta</a>
    <a href="${pageContext.servletContext.contextPath}/cliente/mis-compras.jsp" class="list-group-item">Mis Compras</a>
  </div>
</aside>
