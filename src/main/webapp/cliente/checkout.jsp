<%-- 
    Document   : checkout
    Created on : 15-10-2018, 17:42:23
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-12">
      <c:set var="detalleCarroList" value="${sessionScope.CARRO_COMPRA.getDetalleCarroList()}"></c:set>
      <c:set var="cliente" value="${sessionScope.cliente}"></c:set>
      <c:if test="${detalleCarroList.size() == 0}">
        <h1>Tu carro se encuentra vacío...</h1>
      </c:if>
      <c:if test="${detalleCarroList.size() > 0}">
        <h1>Confirme su Pedido</h1>

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
                      <img width="50" src="${url_imagen}" alt="${nombre}" title="${nombre}" class="img-thumbnail"/>
                    </a>
                  </td>
                  <td class="text-left" style="vertical-align: middle;">
                    <a href="${pageContext.servletContext.contextPath}/servlet/producto?accion=pdp&idp=${id_producto}">
                      ${nombre}
                    </a>
                  </td>
                  <td class="text-center" style="vertical-align: middle;">
                    ${unidad_medida}
                  </td>
                  <td class="text-center" style="vertical-align: middle;">
                    ${cantidad}
                  </td>
                  <td class="text-right" style="vertical-align: middle;">
                    $ <fmt:formatNumber type="number">${precio}</fmt:formatNumber>
                    </td>
                    <td class="text-right" style="vertical-align: middle;">
                      $ <fmt:formatNumber type="number">${precio * cantidad}</fmt:formatNumber>
                    </td>
                  </tr>
              </c:forEach>
              <c:set var="neto" value="${total / 1.19}"></c:set>
              <c:set var="iva" value="${total - neto}"></c:set>
              </tbody>
            </table>
          </div>
          <br>
          <!--detalle total-->
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

          <div class="panel-group" id="accordion">
            <!--medio de pago-->
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a href="#collapse-coupon" class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" aria-expanded="false">
                    Medio de Pago <i class="fa fa-caret-down"></i>
                  </a>
                </h4>
              </div>
              <div id="collapse-coupon" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
                <div class="panel-body text-center">
                  <label>
                    <input type="radio" name="pago" value="webpay">
                    <img width="300" src="${pageContext.servletContext.contextPath}/img/webpay.png"/>
                </label>
              </div>
            </div>
          </div>

          <!--dirección de envío-->
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4 class="panel-title">
                <a href="#collapse-shipping" class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" aria-expanded="false">
                  Dirección de Envío <i class="fa fa-caret-down"></i>
                </a>
              </h4>
            </div>
            <div id="collapse-shipping" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
              <div class="panel-body">
                <h3>Seleccione una dirección, o agregue una nueva.</h3>
                <%--<c:forEach items="${cliente.getDireccionList()}" var="direccion">--%>
                <div id="mis-direcciones" class="form-group required">
                  <!--                    <div class="radio">
                                        <label>
                                          <input type="radio" name="optradio">
                  
                </label>
              </div>-->
                </div>
                <%--</c:forEach>--%>
                <button data-toggle="modal" data-target="#modal-direccion" type="button" id="agregar-direccion" data-loading-text="Cargando..." class="btn btn-primary">Agregar</button>
              </div>
            </div>
          </div>

          <div class="panel panel-default">
            <div class="panel-heading">
              <h4 class="panel-title"><a href="#collapse-voucher" data-toggle="collapse" data-parent="#accordion" class="accordion-toggle collapsed" aria-expanded="false">Use Gift Certificate <i class="fa fa-caret-down"></i></a></h4>
            </div>
            <div id="collapse-voucher" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
              <div class="panel-body">
                <label class="col-sm-2 control-label" for="input-voucher">Enter your gift certificate code here</label>
                <div class="input-group">
                  <input type="text" name="voucher" value="" placeholder="Enter your gift certificate code here" id="input-voucher" class="form-control">
                  <span class="input-group-btn">
                    <input type="submit" value="Apply Gift Certificate" id="button-voucher" data-loading-text="Loading..." class="btn btn-primary">
                  </span> </div>
              </div>
            </div>
          </div>
        </div>                  

        <!--botones-->
        <div class="buttons clearfix">
          <div class="pull-left">
            <a href="${pageContext.servletContext.contextPath}" class="btn btn-warning">Continuar comprando</a>
          </div>
          <form action="${pageContext.servletContext.contextPath}/webpay" method="post">
            <input type="hidden" name="action" value="webpayNormalInit"/>
            <div class="pull-right">
              <input class="btn btn-primary btn-inverse" type="submit" value="Pagar"/>
            </div>
          </form>
        </div>
      </c:if>
    </div>
    <!--modal direccion-->
    <div id="modal-direccion" class="modal fade" role="dialog">
      <div class="modal-dialog modal-sm">
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Nueva Dirección</h4>
          </div>
          <form action="/ecommerce/servlet/cliente" method="post" id="add-direccion">
            <div class="modal-body">
              <input type="hidden" name="accion" value="agregarDireccion"/>
              <div class="form-group">
                <label for="region">Región:</label>
                <select class="form-control" id="region">
                </select>
              </div>
              <div class="form-group">
                <label for="comuna">Comuna:</label>
                <select class="form-control" id="comuna" name="comuna">
                </select>
              </div>
              <div class="form-group">
                <label for="direccion">Dirección:</label>
                <input type="text" class="form-control" id="direccion" name="direccion">
              </div>
              <div class="form-group">
                <label for="cod-postal">Código Postal:</label>
                <input type="text" class="form-control" id="cod-postal" name="cod-postal">
              </div>
              <div class="form-group">
                <label for="alias">Alias:</label>
                <input type="text" class="form-control" id="alias" name="alias">
              </div>
            </div>
            <div class="modal-footer">
              <button type="submit" class="btn btn-primary">Agregar</button>
              <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <script>
      $(document).ready(function () {
        
        $('#add-direccion').on('submit', function(e){
//          e.preventDefault();
          console.log('submit');
        });

        $.getJSON("/ecommerce/servlet/cliente?accion=obtenerDirecciones&idc=" + ${cliente.getId()}, function (data) {
          var direcciones = [];
          var comuna = "";
          $.each(data, function (k, v) {
            console.log(v);
            direcciones.push("<div class='radio'><label><input type='radio' name='optradio' value='" + v.id + "'>" + v.alias + " - " + v.direccion + " - " + v.comuna + "</label></div>");
          });
          $("#mis-direcciones").html(direcciones);
        });

        $.getJSON("https://apis.digital.gob.cl/dpa/regiones", function (data) {
          var regiones = [];
          $.each(data, function (k, r) {
            regiones.push("<option value='" + r.codigo + "'>" + r.nombre + "</option>");
          });
          $("#region").html(regiones);
          $("#region").trigger("change");
        });

        $("#region").on("change", function () {
          $("#comuna").attr('disabled', true);
          $.getJSON("https://apis.digital.gob.cl/dpa/regiones/" + $(this).val() + "/comunas", function (data) {
            var comunas = [];
            $.each(data, function (k, c) {
              comunas.push("<option value='" + c.codigo + "'>" + c.nombre + "</option>");
            });
            $("#comuna").html(comunas);
            $("#comuna").prop("disabled", false);
          });
        });
      });
    </script>
  </jsp:attribute>
</pt:principal>
