<%-- 
    Document   : carro
    Created on : 08-09-2018, 4:50:49
    Author     : Alejandro
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<c:set var="basePath" value="${pageContext.servletContext.contextPath}"></c:set>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-9">
      <c:set var="detalleCarroList" value="${sessionScope.CARRO_COMPRA.getDetalleCarroList()}"></c:set>
      <c:if test="${detalleCarroList.size() == 0}">
        <h2>Tu carro se encuentra vacío...</h2>
      </c:if>
      <c:if test="${detalleCarroList.size() > 0}">
        <i class="fa fa-info-circle fa-10x" aria-hidden="true" data-toggle="tooltip" title="Ayudame"></i>
        <h2>Carro de Compras</h2>
        <div class="table-responsive">
          <table class="table table-bordered">
            <thead>
              <tr>
                <td class="text-center">Imagen</td>
                <td class="text-left">Producto</td>
                <td class="text-center">U. Medida</td>
                <td class="text-center">Cantidad</td>
                <td class="text-right">Precio</td>
                <td class="text-right">Total</td>
                <td class="text-center">Quitar Producto</td>
              </tr>
            </thead>
            <tbody>
              <c:set var="total" value="0"></c:set>
              <c:set var="cont" value="0"></c:set>
              <c:forEach items="${detalleCarroList}" var="item">
                <c:set var="nombre" value="${item.getProductoProductorId().getProductoId().getNombre()}"></c:set>
                <c:set var="url_imagen" value="${item.getProductoProductorId().getProductoId().getDescripcionHtml()}"></c:set>
                <c:set var="unidad_medida" value="${item.getProductoProductorId().getProductoId().getUnidadMedidaId().getNombre()}"></c:set>
                <c:set var="precio" value="${item.getProductoProductorId().getPrecio()}"></c:set>
                <c:set var="cantidad" value="${item.getCantidad()}"></c:set>
                <c:set var="total" value="${total + (cantidad * precio)}"></c:set>
                <c:set var="id_producto" value="${item.getProductoProductorId().getProductoId().getId()}"></c:set>
                <c:set var="id" value="${item.getProductoProductorId().getId()}"></c:set>
                <c:set var="stock" value="${item.getProductoProductorId().getCantidad()}"></c:set>
                  <tr>
                    <td class="text-center">
                      <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">
                      <img width="50" src="${basePath}${url_imagen}" alt="${nombre}" title="${nombre}" class="img-thumbnail">
                    </a>
                  </td>
                  <td class="text-center" style="vertical-align: middle;">
                    <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">
                      ${nombre}
                    </a>
                  </td>
                  <td class="text-center" style="vertical-align: middle;">${unidad_medida}</td>
                  <td class="text-center" style="vertical-align: middle;">
                    <button data-id="${id}" class="menos btn btn-link btn-xs" data-toggle="tooltip" title="Disminuir Cantidad">
                      <i class="fa fa-minus" aria-hidden="true"></i>
                    </button>
                    <input disabled="" class="cant-${id} form-control input-sm" style="width: 40px; display: inline; text-align: center" type="text" name="cantidad" value="${cantidad}" size="2" id="cant"/>
                    <button data-stock="${stock}" data-id="${id}" class="mas btn btn-link btn-xs" data-toggle="tooltip" title="Aumentar Cantidad">
                      <i class="fa fa-plus" aria-hidden="true"></i>
                    </button>
                  </td>
                  <td class="text-right" style="vertical-align: middle;">$ <fmt:formatNumber type="number">${precio}</fmt:formatNumber></td>
                  <td class="text-right" style="vertical-align: middle;">$ <fmt:formatNumber type="number">${precio * cantidad}</fmt:formatNumber></td>
                    <td class="text-center" style="vertical-align: middle;">
                      <button onclick="carro.quitar(${cont});location.reload();" class="btn btn-xs btn-danger" data-toggle="tooltip" title="Quitar ${nombre}">
                      <i class="fa fa-times" aria-hidden="true"></i>
                    </button>
                  </td>
                </tr>
                <c:set var="cont" value="${cont + 1}"></c:set>
              </c:forEach>
              <c:set var="neto" value="${total / 1.19}"></c:set>
              <c:set var="iva" value="${total - neto}"></c:set>
              </tbody>
            </table>
          </div>
          <br>
          <div class="row">
            <div class="col-sm-4 col-sm-offset-8">
              <table class="table table-bordered">
                <tbody>
                  <tr>
                    <td class="text-right"><strong>Neto</strong></td>
                    <td class="text-right">
                      $ <fmt:formatNumber value="${neto}" type="number" maxFractionDigits="0"></fmt:formatNumber>
                    </td>
                  </tr>
                  <tr>
                    <td class="text-right"><strong>IVA (19%):</strong></td>
                    <td class="text-right">
                      $ <fmt:formatNumber value="${iva}" type="number" maxFractionDigits="0"></fmt:formatNumber>
                    </td>
                  </tr>
                  <tr>
                    <td class="text-right"><strong>Total:</strong></td>
                    <td class="text-right">$ <fmt:formatNumber value="${total}" type="number" maxFractionDigits="0"></fmt:formatNumber></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="buttons clearfix">
            <div class="pull-left">
              <a href="${pageContext.servletContext.contextPath}" class="btn btn-warning">Continuar comprando</a>
          </div>
          <div class="pull-right">
            <a data-toggle="tooltip" title="Pasar a pagar" href="${pageContext.servletContext.contextPath}/cliente/checkout.jsp" class="btn btn-success">Confirmar Pedido</a>
          </div>
        </div>
      </c:if>
    </div>
    <script>
      $(document).ready(function () {
        //Botón más
        $('.mas').click(function (e) {
          e.preventDefault();
          var id = $(this).attr('data-id');
          var stock = $(this).attr('data-stock');
          var cant = $(this).parents('div').find('.cant-' + id).val();
          cant++;
          if (cant > stock)
            return;
          carro.actualizar($(this), id, 1);
        });
        //Botón menos
        $('.menos').click(function (e) {
          e.preventDefault();
          var id = $(this).attr('data-id');
          var cant = $(this).parents('div').find('.cant-' + id).val();
          cant--;
          if (cant < 1)
            return;
          carro.actualizar($(this), id, -1);
        });
      });
    </script>
    <%@include file="../WEB-INF/tags/_aside_cliente_.jsp" %>
  </jsp:attribute>
</pt:principal>
