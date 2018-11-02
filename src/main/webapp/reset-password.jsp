<%-- 
    Document   : reset-password
    Created on : 08-09-2018, 4:41:05
    Author     : Alejandro
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-9">
      <h1>�Olvid� su contrase�a?</h1>
      <p>Ingrese el correo electr�nico asociado a su cuenta, al presionar el bot�n enviar le ser� enviado un enlace para reestablecer su contrase�a.</p>
      <form action="${pageContext.servletContext.contextPath}/servlet/cliente" method="post" class="form-horizontal">
        <input type="hidden" name="accion" value="recuperarPassword"/>
        <fieldset>
          <legend>Su correo electr�nico</legend>
          <div class="form-group required">
            <label class="col-sm-2 control-label" for="input-email">Correo electr�nico</label>
            <div class="col-sm-10">
              <input type="text" name="email" value="" placeholder="Correo electr�nico" id="email" class="form-control">
            </div>
          </div>
        </fieldset>
        <div class="buttons clearfix">
          <div class="pull-left"><a href="${pageContext.servletContext.contextPath}\login.jsp" class="btn btn-default">Volver</a></div>
          <div class="pull-right">
            <input type="submit" value="Enviar" class="btn btn-primary">
          </div>
        </div>
      </form>
    </div>
    <%@include file="WEB-INF/tags/_aside_cliente_.jsp" %>
  </jsp:attribute>
</pt:principal>
