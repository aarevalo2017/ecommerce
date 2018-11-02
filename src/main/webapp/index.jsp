<%-- 
    Document   : index
    Created on : 08-09-2018, 4:10:23
    Author     : Alejandro
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="basePath" value="${pageContext.servletContext.contextPath}"></c:set>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-12" style="min-height: 400px;">
      <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
          <div class="item active">
            <img src="http://www.agrogood.cl/image/cache/catalog/slide1-1140x380.JPG" alt="Los Angeles">
          </div>
          <div class="item">
            <img src="http://www.ecosiente.com/public/image/Banners_Ecosiente01.jpg" alt="Chicago">
          </div>
          <div class="item">
            <img src="http://www.mercadosmunicipales.es/Mercados/Mercados/images/banner_lapaz.jpg" alt="New York">
          </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>      
    </div>
    <div class="row">
      <c:forEach items="${requestScope.productos}" var="producto">
        <c:set var = "nombre" value = "${producto.getProductoId().getNombre()}"/>
        <c:set var = "url_imagen" value = "${basePath}${producto.getProductoId().getDescripcionHtml()}"/>
        <c:set var = "precio_minimo" value = "${producto.getPrecioMinimo()}"/>
        <c:set var = "precio_maximo" value = "${producto.getPrecioMaximo()}"/>
        <c:set var = "stock" value = "${producto.getStockDisponible()}"/>
        <c:set var = "id_producto" value = "${producto.getProductoId().getId()}"/>
        <c:set var = "productores" value = "${producto.getProductores()}"/>
        <c:set var = "unidad_medida" value = "${producto.getProductoId().getUnidadMedidaId().getNombre()}"/>
        <div class="product-layout product-grid col-xs-12 col-lg-3 col-md-3 col-sm-6">
          <div class="product-thumb">
            <div class="image">
              <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">
                <img width="200" src="${url_imagen}" alt="${nombre}" title="${nombre}" class="img-responsive" />
                <!--<img width="200" src="${pageContext.servletContext.contextPath}/servlet/foto?id=${id_producto}" alt="${nombre}" title="${nombre}" class="img-responsive" />-->
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
  </jsp:attribute>
</pt:principal>
