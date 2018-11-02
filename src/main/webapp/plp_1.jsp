<%-- 
    Document   : plp
    Created on : 03-10-2018, 19:02:01
    Author     : Alejandro
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<c:set var="basePath" value="${pageContext.servletContext.contextPath}"></c:set>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <!--<div id="content" class="col-sm-9 col-lg-9 col-md-9">-->
    <div id="content">
      <h1>Búsqueda de - ${param.criterio}</h1>
      <label class="control-label" for="input-search">Criterio de búsqueda</label>
      <div class="row">
        <form method="POST" action="${pageContext.servletContext.contextPath}/servlet/producto">
          <input type="hidden" name="accion" value="buscarProducto"/>
          <div class="col-lg-4 col-sm-6 col-xs-9">
            <input required="" type="text" name="criterio" value="${param.criterio}" placeholder="Que desea buscar" id="input-search" class="form-control" />
          </div>
          <input type="submit" value="Buscar" id="button-search" class="btn btn-primary" />
        </form>
      </div>
      <h2>Productos encontrados : ${requestScope.productos.size()}</h2>
      <div class="row">
        <div class="col-md-2 col-sm-6 hidden-xs">
          <div class="btn-group btn-group-sm">
            <button type="button" id="list-view" class="btn btn-default" data-toggle="tooltip" title="Lista"><i class="fa fa-th-list"></i></button>
            <button type="button" id="grid-view" class="btn btn-default" data-toggle="tooltip" title="Grilla"><i class="fa fa-th"></i></button>
          </div>
        </div>
        <div class="col-md-4 col-xs-6">
          <div class="form-group input-group input-group-sm">
            <label class="input-group-addon" for="input-sort">Ordenar por:</label>
            <select id="input-sort" class="form-control" onchange="location = this.value;">
            </select>
          </div>
        </div>
        <div class="col-md-3 col-xs-6">
          <div class="form-group input-group input-group-sm">
            <label class="input-group-addon" for="input-limit">Mostrar :</label>
            <select id="input-limit" class="form-control" onchange="location = this.value;">
            </select>
          </div>
        </div>
      </div>
      <div class="row">
        <c:forEach items="${requestScope.productos}" var="producto">
          <c:set var = "nombre" value = "${producto.getProductoId().getNombre()}"/>
          <c:set var = "url_imagen" value = "${producto.getProductoId().getDescripcionHtml()}"/>
          <c:set var = "precio_minimo" value = "${producto.getPrecioMinimo()}"/>
          <c:set var = "precio_maximo" value = "${producto.getPrecioMaximo()}"/>
          <c:set var = "stock" value = "${producto.getStockDisponible()}"/>
          <c:set var = "id_producto" value = "${producto.getProductoId().getId()}"/>
          <c:set var = "productores" value = "${producto.getProductores()}"/>
          <c:set var = "unidad_medida" value = "${producto.getProductoId().getUnidadMedidaId().getNombre()}"/>
          
          <div class="product-layout product-list col-xs-12 col-lg-3 col-md-3 col-sm-6">
<!--            <input type="hidden" name="token" value="${sessionScope.token}"/>
            <input type="hidden" name="accion" value="agregarItemCarro"/>
            <input type="hidden" name="id_producto" value="${id_producto}"/>-->
            <div class="product-thumb">
              <div class="image">
                <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">
                  <img width="200" src="${basePath}${url_imagen}" alt="${nombre}" title="${nombre}" class="img-responsive" />
                </a>
              </div>
              <div>
                <div class="caption text-center" style="min-height: 80px">
                  <h4>
                    <a href="#">${nombre}</a>
                  </h4>
                  <p>${nombre}</p>
                  <p>Productores disponibles : ${productores}</p>
                </div>
                <div style="text-align: center">
                  <h2>Precio</h2>
                  <h3 class="price">
                    $&nbsp;<fmt:formatNumber value = "${precio_minimo}" type="number"/>
                    <c:if test="${productores > 1}">
                      -&nbsp;<fmt:formatNumber value = "${precio_maximo}" type="number"/>
                    </c:if>
                  </h3>
                  <p>Por ${unidad_medida}</p>
                </div>
                <br>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
      <div class="row">
        <div class="col-sm-6 text-left"></div>
        <div class="col-sm-6 text-right">Mostrando 1 de 1 (1 Página(s))</div>
      </div>
    </div>
  </jsp:attribute>
</pt:principal>