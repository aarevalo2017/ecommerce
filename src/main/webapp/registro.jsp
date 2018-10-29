<%-- 
    Document   : registro
    Created on : 08-09-2018, 4:40:48
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <c:if test="${not empty error}">
      <script type="text/javascript">
        $(function () {
          new PNotify({
            title: 'Error',
            text: '${error}',
            type: 'error',
            styling: 'bootstrap3'
          });
        });
      </script>
    </c:if>
    <div id="content" class="col-sm-9">
      <h1>Registro de Cliente</h1>
      <p>Si ya tiene una cuenta, vaya al <a href="${pageContext.servletContext.contextPath}\login.jsp">Inicio de sesión</a>.</p>
      <form action="${pageContext.servletContext.contextPath}/servlet/cliente" method="post" class="form-horizontal">
        <input type="hidden" name="accion" value="create"/>
        <fieldset id="account">
          <legend>Datos personales</legend>
          <div class="form-group required">
            <label class="col-sm-2 control-label" for="nombre">Nombre</label>
            <div class="col-sm-10">
              <input type="text" name="nombre" value="${param.nombre}" placeholder="Nombre" id="nombre" class="form-control" />
            </div>
          </div>
          <div class="form-group required">
            <label class="col-sm-2 control-label" for="apellidos">Apellidos</label>
            <div class="col-sm-10">
              <input type="text" name="apellidos" value="${param.apellidos}" placeholder="Apellidos" id="apellidos" class="form-control" />
            </div>
          </div>
          <div class="form-group required">
            <label class="col-sm-2 control-label" for="email">E-mail</label>
            <div class="col-sm-10">
              <input type="email" name="email" value="${param.email}" placeholder="E-Mail" id="email" class="form-control" />
            </div>
          </div>
          <div class="form-group required">
            <label class="col-sm-2 control-label" for="telefono">Teléfono</label>
            <div class="col-sm-10">
              <input type="tel" name="telefono" value="${param.telefono}" placeholder="Teléfono" id="telefono" class="form-control" />
            </div>
          </div>
        </fieldset>
        <fieldset>
          <legend>Contraseña</legend>
          <div class="form-group required">
            <label class="col-sm-2 control-label" for="password">Contraseña</label>
            <div class="col-sm-10">
              <input type="password" name="password" value="${param.password}" placeholder="Contraseña" id="password" class="form-control" />
            </div>
          </div>
          <div class="form-group required">
            <label class="col-sm-2 control-label" for="password-confirm">Confirme contraseña</label>
            <div class="col-sm-10">
              <input type="password" name="passwordConfirm" value="${param.passwordConfirm}" placeholder="Confirme contraseña" id="password-confirm" class="form-control" />
            </div>
          </div>
        </fieldset>
        <fieldset>
          <legend>Ofertas</legend>
          <div class="form-group">
            <label class="col-sm-2 control-label">Recibir ofertas al mail</label>
            <div class="col-sm-10"> <label class="radio-inline">
                <input type="radio" name="recibeOfertas" value="1" />Si</label>
              <label class="radio-inline">
                <input type="radio" name="recibeOfertas" value="0" checked="checked" />No</label>
            </div>
          </div>
        </fieldset>
        <div class="buttons">
          <div class="pull-right">
            <input type="submit" value="Registrar" class="btn btn-primary" />
          </div>
        </div>
      </form>
    </div>
    <%@include file="WEB-INF/tags/_aside_cliente_.jsp" %>
  </jsp:attribute>
</pt:principal>
