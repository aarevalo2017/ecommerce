<%-- 
    Document   : _header_
    Created on : 04-09-2018, 21:49:09
    Author     : Alejandro
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <div id="logo"> 
          <h1>
            <a href="#">La Vega Online</a>
          </h1>
        </div>
      </div>
      <form method="POST" action="${pageContext.servletContext.contextPath}/servlet/producto">
        <div class="col-sm-5">
          <div id="search" class="input-group">
            <input type="hidden" name="accion" value="buscarProducto"/>
            <input required type="text" name="criterio" value="" placeholder="Buscar Frutas y Verduras" class="form-control input-lg" />
            <span class="input-group-btn">
              <button type="submit" class="btn btn-default btn-lg"><i class="fa fa-search"></i></button>
            </span>
          </div>
        </div>
      </form>
      <div class="col-sm-3">
        <div id="cart" class="btn-group btn-block">
          <button type="button" data-toggle="dropdown" data-loading-text="Loading..." class="btn btn-inverse btn-block btn-lg dropdown-toggle">
            <i class="fa fa-shopping-cart"></i>
            <c:if test="${empty sessionScope.CARRO_COMPRA}">
              <c:set var="cant_carro" value="0"></c:set>
            </c:if>
            <c:if test="${not empty sessionScope.CARRO_COMPRA}">
              <c:set var="cant_carro" value="${sessionScope.CARRO_COMPRA.getDetalleCarroList().size()}"></c:set>
            </c:if>
            <span id="cart-total">${cant_carro} producto(s) - $0</span>
          </button>
          <%--<c:if test="${empty sessionScope.CARRO_COMPRA}">--%>
            <ul class="dropdown-menu pull-right">
              <li>
                <p class="text-center">Tu carro se encuentra vacío!</p>
              </li>
            </ul>
          <%--</c:if>--%>
          <!--<c:if test="${not empty sessionScope.CARRO_COMPRA}">
            <ul class="dropdown-menu pull-right">
              <li>
                <table class="table table-striped">
                  <tbody>
                    <c:set var="total" value="0"></c:set>
                    <c:set var="carro" value="${sessionScope.CARRO_COMPRA.getDetalleCarroList()}"></c:set>
                    <c:forEach items="${carro}" var="item">
                      <c:set var="nombre" value="${item.getProductoProductorId().getProductoId().getNombre()}"></c:set>
                      <c:set var="url_imagen" value="${item.getProductoProductorId().getProductoId().getDescripcionHtml()}"></c:set>
                      <c:set var="unidad_medida" value="${item.getProductoProductorId().getProductoId().getUnidadMedidaId().getNombre()}"></c:set>
                      <c:set var="precio" value="${item.getProductoProductorId().getPrecio()}"></c:set>
                      <c:set var="cantidad" value="${item.getCantidad()}"></c:set>
                      <c:set var="total" value="${total + (cantidad * precio)}"></c:set>
                      <c:set var="id_producto" value="${item.getProductoProductorId().getId()}"></c:set>
                      <c:set var="neto" value="${total / 1.19}"></c:set>
                      <c:set var="iva" value="${total - neto}"></c:set>
                        <tr>
                          <td class="text-center">
                            <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">
                            <img width="50" src="${url_imagen}" alt="${nombre}" title="${nombre}" class="img-thumbnail">
                          </a>
                        </td>
                        <td class="text-left">
                          <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">${nombre}</a>
                        </td>
                        <td class="text-right">x ${cantidad}</td>
                        <td class="text-right">
                          $ <fmt:formatNumber value="${precio}" type="number"></fmt:formatNumber>
                          </td>
                          <td class="text-center">
                            <button type="button" onclick="cart.remove('178688');" title="Remove" class="btn btn-danger btn-xs">
                              <i class="fa fa-times"></i>
                            </button>
                          </td>
                        </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </li>
              <li>
                <div>
                  <table class="table table-bordered">
                    <tbody>
                      <tr>
                        <td class="text-right"><strong>Neto</strong></td>
                        <td class="text-right">
                          $ <fmt:formatNumber value="${neto}" type="number" maxFractionDigits="0"></fmt:formatNumber>
                          </td>
                        </tr>
                        <tr>
                          <td class="text-right"><strong>IVA (19%)</strong></td>
                          <td class="text-right">
                            $ <fmt:formatNumber value="${iva}" type="number" maxFractionDigits="0"></fmt:formatNumber>
                          </td>
                        </tr>
                        <tr>
                          <td class="text-right"><strong>Total</strong></td>
                          <td class="text-right">$ ${total}</td>
                      </tr>
                    </tbody>
                  </table>
                  <p class="text-right">
                    <a href="${pageContext.servletContext.contextPath}/carro/carro.jsp">
                      <strong><i class="fa fa-shopping-cart"></i> Ver Carro</strong>
                    </a>&nbsp;&nbsp;&nbsp;<a href="#"><strong><i class="fa fa-share"></i> Pagar</strong></a>
                  </p>
                </div>
              </li>
            </ul>
          </c:if>-->
        </div>
      </div>
    </div>
  </div>
</header>
