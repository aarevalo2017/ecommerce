<%-- 
    Document   : carro
    Created on : 08-09-2018, 4:50:49
    Author     : Alejandro
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-9">
      <c:set var="detalleCarroList" value="${sessionScope.CARRO_COMPRA.getDetalleCarroList()}"></c:set>
      <c:if test="${detalleCarroList.size() == 0}">
        <h1>Tu carro se encuentra vacío...</h1>
      </c:if>
      <c:if test="${detalleCarroList.size() > 0}">
        <!--<h1>Shopping Cart&nbsp;(10.00kg)</h1>-->
        <form action="#" method="post" enctype="multipart/form-data">
          <div class="table-responsive">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <td class="text-center">Imagen</td>
                  <td class="text-left">Producto</td>
                  <td class="text-left">U. Medida</td>
                  <td class="text-left">Cantidad</td>
                  <td class="text-right">Precio</td>
                  <td class="text-right">Total</td>
                </tr>
              </thead>
              <tbody>
                <c:set var="total" value="0"></c:set>
                <c:forEach items="${detalleCarroList}" var="item">
                  <c:set var="nombre" value="${item.getProductoProductorId().getProductoId().getNombre()}"></c:set>
                  <c:set var="url_imagen" value="${item.getProductoProductorId().getProductoId().getDescripcionHtml()}"></c:set>
                  <c:set var="unidad_medida" value="${item.getProductoProductorId().getProductoId().getUnidadMedidaId().getNombre()}"></c:set>
                  <c:set var="precio" value="${item.getProductoProductorId().getPrecio()}"></c:set>
                  <c:set var="cantidad" value="${item.getCantidad()}"></c:set>
                  <c:set var="total" value="${total + (cantidad * precio)}"></c:set>
                  <c:set var="id_producto" value="${item.getProductoProductorId().getProductoId().getId()}"></c:set>
                    <tr>
                      <td class="text-center">
                        <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">
                        <img width="50" src="${url_imagen}" alt="iPhone" title="iPhone" class="img-thumbnail">
                      </a>
                    </td>
                    <td class="text-left" style="vertical-align: middle;">
                      <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">
                        ${nombre}
                      </a>
                    </td>
                    <td class="text-left" style="vertical-align: middle;">${unidad_medida}</td>
                    <td class="text-left" style="vertical-align: middle;"><div class="input-group btn-block" style="max-width: 200px;">
                        <input type="text" name="quantity[178161]" value="${cantidad}" size="1" class="form-control">
                        <span class="input-group-btn">
                          <button type="button" data-toggle="tooltip" title="" class="btn btn-primary" onclick="alert('Función no implementada.')"  data-original-title="Update"><i class="fa fa-refresh"></i></button>
                          <button type="button" data-toggle="tooltip" title="" class="btn btn-danger" onclick="alert('Función no implementada.')" data-original-title="Remove"><i class="fa fa-times-circle"></i></button>
                        </span></div></td>
                    <td class="text-right" style="vertical-align: middle;">$ <fmt:formatNumber type="number">${precio}</fmt:formatNumber></td>
                    <td class="text-right" style="vertical-align: middle;">$ <fmt:formatNumber type="number">${precio * cantidad}</fmt:formatNumber></td>
                    </tr>
                </c:forEach>
                <c:set var="neto" value="${total / 1.19}"></c:set>
                <c:set var="iva" value="${total - neto}"></c:set>
                </tbody>
              </table>
            </div>
          </form>
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
            <!--<form action="${pageContext.servletContext.contextPath}/webpay" method="post">-->
          <!--<input type="hidden" name="action" value="webpayNormalInit"/>-->
          <div class="pull-right">
            <a href="${pageContext.servletContext.contextPath}/cliente/checkout.jsp" class="btn btn-success">Confirmar Pedido</a>
            <!--<input class="btn btn-primary btn-inverse" type="submit" value="Confirmar Pedido"/>-->
          </div>
          <!--</form>-->
        </div>
      </c:if>
    </div>
    <%@include file="../WEB-INF/tags/_aside_cliente_.jsp" %>
  </jsp:attribute>
</pt:principal>
