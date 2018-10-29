<%-- 
    Document   : _menu_superior_
    Created on : 04-09-2018, 21:52:43
    Author     : Alejandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.servletContext.contextPath}"></c:set>
<nav id="menu" class="navbar">
  <div class="navbar-header"><span id="category" class="visible-xs">Categorías</span>
    <button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      <i class="fa fa-bars"></i>
    </button>
  </div>
  <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav">
      <c:forEach items="${sessionScope.categorias}" var="cat">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">${cat.getNombre()}</a>
          <div class="dropdown-menu">
            <div class="dropdown-inner"> 
              <c:set var="cont" value="1"></c:set>
                <ul class="list-unstyled">
                <c:forEach items="${cat.getProductoList()}" var="p">
                  <li style="width: 210px">
                    <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${p.getId()}">
                      <img width="32" height="32" src="${basePath}${p.getDescripcionHtml()}"/>
                      <!--<img width="32" height="32" src="${pageContext.servletContext.contextPath}/servlet/foto?id=${p.getId()}"/>-->
                      &nbsp;&nbsp;${p.getNombre()}
                    </a>
                  </li>
                  <c:if test="${cont % 10 == 0}">
                  </ul>
                  <ul class="list-unstyled">
                  </c:if>
                  <c:set var="cont" value="${cont = cont + 1}"></c:set>
                </c:forEach>
              </ul>
            </div>
            <a href="#" class="see-all">Ver todo ${cat.getNombre()}</a> </div>
        </li>
      </c:forEach>
    </ul>
  </div>
</nav>
