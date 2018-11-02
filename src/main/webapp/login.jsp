<%-- 
    Document   : login
    Created on : 23-06-2018, 12:42:13
    Author     : Alejandro
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <c:if test="${not empty param.retry}">
      <div class="container">
        <div class="alert alert-danger alert-dismissible col-sm-12 container">
          <i class="fa fa-exclamation-circle"></i>
          Atenci�n: No existe coincidencia para direcci�n de E-Mail y/o Contrase�a.
          <c:if test="${not empty sessionScope.ATTR_INTENTO_LOGIN}">
            <br>
            <strong>Le quedan ${3 - sessionScope.ATTR_INTENTO_LOGIN} intentos disponibles.</strong>
          </c:if>
        </div>
      </div>
    </c:if>
    <div id="content" class="col-sm-9">
      <div class="row">
        <div class="col-sm-6">
          <div class="well">
            <h2>Nuevo Cliente</h2>
            <p><strong>Registre su cuenta</strong></p>
            <p class="text-justify">Al crear su cuenta de usuario podr� comprar de una manera mas rapida, revisar sus pedidos y el estado de sus ordenes adem�s de recibir ofertas y promociones.</p>
            <a href="${pageContext.servletContext.contextPath}/registro.jsp" class="btn btn-primary">Continuar</a></div>
        </div>
        <div class="col-sm-6">
          <div class="well">
            <h2>Cliente Registrado</h2>
            <p><strong>Soy un cliente registrado</strong></p>
            <!--<form action="j_security_check" method="post">-->
            <form action="${pageContext.servletContext.contextPath}/login" method="post">
              <div class="form-group">
                <label class="control-label" for="input-email">Correo electr�nico</label>
                <input required type="text" name="j_username" value="" placeholder="" id="input-email" class="form-control" />
              </div>
              <div class="form-group">
                <label class="control-label" for="input-password">Contrase�a</label>
                <input required type="password" name="j_password" value="" placeholder="Contrase�a" id="input-password" class="form-control" />
                <a href="${pageContext.servletContext.contextPath}/reset-password.jsp">Olvid� mi contrase�a</a>
              </div>
              <input type="submit" value="Iniciar Sesi�n" class="btn btn-primary" />
            </form>
          </div>
        </div>
      </div>
    </div>
    <%@include file="WEB-INF/tags/_aside_cliente_.jsp" %>
  </jsp:attribute>
</pt:principal>