<%-- 
    Document   : info
    Created on : 24-09-2018, 23:37:04
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <c:if test="${not empty mensaje}">
      <div class="container">
        <div class="${class} alert-dismissible col-sm-12 container">
          <!--<i class="fa fa-check"></i>-->
          ${mensaje}
        </div>
      </div>
    </c:if>
    <div id="content" class="col-sm-9">
    </div>
  </jsp:attribute>
</pt:principal>
