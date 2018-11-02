<%-- 
    Document   : principal
    Created on : 04-09-2018, 21:30:03
    Author     : Alejandro
--%>

<%--<%@tag language="java" description="put the tag description here" pageEncoding="UTF-8"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="titulo" required="true" rtexprvalue="true"%>
<%@attribute name="contenido" fragment="true"%>
<!DOCTYPE html>
<html>
  <%@include file="_head_.jsp" %>
  <body>
    <%@include file="_topnav_.jsp" %>
    <%@include file="_header_.jsp" %>
    <div class="container">
      <%@include file="_menu_superior_.jsp" %>
    </div>
    <div class="container">
      <%--<%@include file="_breadcrumb_.jsp" %>--%>
      <div class="row">
        <jsp:invoke fragment="contenido"></jsp:invoke>
        <%--<%@include file="_aside_cliente_.jsp" %>--%>
      </div>
    </div>
    <%@include file="_footer_.jsp" %>
    <script>
        recargarMiniCarro();
    </script>
  </body>
</html>