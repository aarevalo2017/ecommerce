<%-- 
    Document   : contacto
    Created on : 08-09-2018, 4:39:36
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-9">
      <h1>Contacto</h1>
      <form method="POST" action="test">
        <input type="text" name="msg"/>
        <input class="btn btn-primary" type="submit" value="Enviar"/>
      </form>
    </div>
  </jsp:attribute>
</pt:principal>
