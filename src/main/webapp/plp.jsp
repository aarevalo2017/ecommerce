<%-- 
    Document   : plp
    Created on : 03-10-2018, 19:02:01
    Author     : Alejandro
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-9 col-lg-9 col-md-9">
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
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;sort=p.sort_order&amp;order=ASC&amp;search=iphone" selected="selected">Default</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;sort=pd.name&amp;order=ASC&amp;search=iphone">Name (A - Z)</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;sort=pd.name&amp;order=DESC&amp;search=iphone">Name (Z - A)</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;sort=p.price&amp;order=ASC&amp;search=iphone">Price (Low &gt; High)</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;sort=p.price&amp;order=DESC&amp;search=iphone">Price (High &gt; Low)</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;sort=rating&amp;order=DESC&amp;search=iphone">Rating (Highest)</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;sort=rating&amp;order=ASC&amp;search=iphone">Rating (Lowest)</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;sort=p.model&amp;order=ASC&amp;search=iphone">Model (A - Z)</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;sort=p.model&amp;order=DESC&amp;search=iphone">Model (Z - A)</option>
            </select>
          </div>
        </div>
        <div class="col-md-3 col-xs-6">
          <div class="form-group input-group input-group-sm">
            <label class="input-group-addon" for="input-limit">Mostrar :</label>
            <select id="input-limit" class="form-control" onchange="location = this.value;">
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;search=iphone&amp;limit=15" selected="selected">15</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;search=iphone&amp;limit=25">25</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;search=iphone&amp;limit=50">50</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;search=iphone&amp;limit=75">75</option>
              <option value="https://demo.opencart.com/index.php?route=product/search&amp;search=iphone&amp;limit=100">100</option>
            </select>
          </div>
        </div>
      </div>
      <div class="row">
        <c:forEach items="${requestScope.productos}" var="producto">
          <c:set var = "categoria" value = "${producto.getProductoId().getCategoriaId().getNombre()}"/>
          <c:set var = "unidad_medida" value = "${producto.getProductoId().getUnidadMedidaId().getNombre()}"/>
          <c:set var = "nombre" value = "${producto.getProductoId().getNombre()}"/>
          <c:set var = "url_imagen" value = "${producto.getProductoId().getDescripcionHtml()}"/>
          <c:set var = "precio" value = "${producto.getPrecio()}"/>
          <c:set var = "productor" value = "${producto.getProductorId().getNombreFantasia()}"/>
          <c:set var = "id_producto" value = "${producto.getId()}"/>
          <div class="product-layout product-list col-xs-12 col-lg-3 col-md-3 col-sm-6">
            <!--<div class="product-layout product-list col-lg-12">-->
            <!--<form method="post" action="${pageContext.servletContext.contextPath}/servlet/carro">-->
            <input type="hidden" name="token" value="${sessionScope.token}"/>
            <input type="hidden" name="accion" value="agregarItemCarro"/>
            <input type="hidden" name="id_producto" value="${id_producto}"/>
            <div class="product-thumb">
              <div class="image">
                <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">
                  <img width="250" src="${url_imagen}" alt="${nombre}" title="${nombre}" class="img-responsive" />
                </a>
              </div>
              <div>
                <div class="caption text-center" style="min-height: 100px">
                  <h4>
                    <a href="#">${nombre}</a>
                  </h4>
                  <p>${nombre}</p>
                  <p>${productor}</p>
                </div>
                <div style="text-align: center">
                  <h3 class="price">
                    $&nbsp;<fmt:formatNumber value = "${precio}" type="number"/>
                    <small>por ${unidad_medida}</small>
                  </h3>
                  <button id="menos" data-id="${id_producto}" class="menos btn btn-link btn-sm">
                    <i class="fa fa-minus" aria-hidden="true"></i>
                  </button>
                  <input style="width: 40px; display: inline; text-align: center" type="text" name="cantidad" value="1" size="5" id="cant" class="cant-${id_producto} form-control">
                  <button id="mas" data-id="${id_producto}" class="mas btn btn-link btn-sm">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                  </button>
                </div>
                <div style="padding: 10px">
                  <!--<button type="submit" id="btn_carro" class="btn btn-warning btn-lg btn-block">Agregar al Carro</button>-->
                  <button type="button" onclick="micarro.agregar(this, ${id_producto}, '${pageContext.session.id}');" class="btn btn-warning btn-lg btn-block">Agregar al Carro</button>
                </div>
              </div>
            </div>
            <!--</form>-->
          </div>
        </c:forEach>
      </div>
      <div class="row">
        <div class="col-sm-6 text-left"></div>
        <div class="col-sm-6 text-right">Showing 1 to 1 of 1 (1 Pages)</div>
      </div>
    </div>
    <script>
      $(document).ready(function () {
        $('.mas').click(function (e) {
          e.preventDefault();
          var id = $(this).attr('data-id');
          var cant = $(this).parents('div').find('.cant-' + id).val();
          cant++;
          if (cant > 10)
            return;
          $(this).parents('div').find('.cant-' + id).val(cant);
        });
        //Botón menos
        $('.menos').click(function (e) {
          e.preventDefault();
          var id = $(this).attr('data-id');
          var cant = $(this).parents('div').find('.cant-' + id).val();
          cant--;
          if (cant < 1)
            return;
          $(this).parents('div').find('.cant-' + id).val(cant);
        });
//        $('#btn_carro').click(function () {
//          alert('agregado al carro');
//        });
      });
    </script>
  </jsp:attribute>
</pt:principal>