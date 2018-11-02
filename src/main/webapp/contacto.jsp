<%-- 
    Document   : contacto
    Created on : 08-09-2018, 4:39:36
    Author     : Alejandro
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-9">
      <h1>Contacto</h1>
      <i class="fas fa-spinner fa-spin fa-2x"></i>
      <span data-toggle="tooltip" title="Aquí puede\n encontrar ayuda" class="fas fa-info-circle fa-2x"></span>
      <form method="POST" action="test" enctype="multipart/form-data">
        <!--<input type="text" name="msg"/>-->
        <input type="file" name="file"/>
        <input class="btn btn-primary" type="submit" value="Subir"/>
      </form>
    </div>
  </jsp:attribute>
</pt:principal>
