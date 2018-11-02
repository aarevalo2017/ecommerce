<%-- 
    Document   : checkout
    Created on : 15-10-2018, 17:42:23
    Author     : Alejandro
--%>

<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<c:set var="basePath" value="${pageContext.servletContext.contextPath}"></c:set>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-9">
      <c:set var="detalleCarroList" value="${sessionScope.CARRO_COMPRA.getDetalleCarroList()}"></c:set>
      <c:set var="cliente" value="${sessionScope.cliente}"></c:set>
      <c:if test="${detalleCarroList.size() == 0}">
        <h2>Tu carro se encuentra vacío...</h2>
      </c:if>
      <c:if test="${detalleCarroList.size() > 0}">
        <h2>Confirme su Pedido</h2>
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
                      <img width="50" src="${basePath}${url_imagen}" alt="${nombre}" title="${nombre}" class="img-thumbnail"/>
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
                    <img width="200" src="${basePath}/img/webpay.png"/>
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
                <div id="mis-direcciones" class="form-group required">
                  <h4>Cargando direcciones <i class="fas fa-spinner fa-spin"></i></h4>
                </div>
                <button data-toggle="modal" data-target="#modal-direccion" type="button" id="agregar-direccion" data-loading-text="Cargando..." class="btn btn-primary">Agregar</button>
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
                <input type="text" class="form-control" id="direccion" name="direccion" maxlength="100" required>
              </div>
              <div class="form-group">
                <label for="alias">Alias:</label>
                <input type="text" class="form-control" id="alias" name="alias" maxlength="50" required>
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

        $('#add-direccion').on('submit', function (e) {
          e.preventDefault();
          $.ajax({
            url: "/ecommerce/servlet/cliente",
            type: 'POST',
            data: $(this).serialize(),
            dataType: 'json',
            success: function (json) {
              if (json.success) {
                new PNotify({
                  title: 'Todo bien',
                  text: json.success,
                  type: 'success',
                  styling: 'bootstrap3'
                });
                recargarDirecciones();
                $('#add-direccion').trigger('reset')
                $('#modal-direccion').modal('hide');
              }
            },
            error: function (jqXHR, textStatus, errorThrown) {
              new PNotify({
                title: 'Ups Algo salió mal',
                text: errorThrown,
                type: 'danger',
                styling: 'bootstrap3'
              });
            }
          });
        });
        function recargarDirecciones() {
          $.getJSON("/ecommerce/servlet/cliente?accion=obtenerDirecciones&idc=${cliente.getId()}", function (data) {
            var direcciones = [];
            var comuna = "";
            $.each(data, function (k, v) {
              console.log(v);
              direcciones.push("<div class='radio'><label><input type='radio' name='optradio' value='" + v.id + "'>" + v.alias + " - " + v.direccion + " - " + v.comuna + "</label></div>");
            });
            $("#mis-direcciones").html(direcciones);
          });
        }
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
        recargarDirecciones();
      });
    </script>
    <%@include file="../WEB-INF/tags/_aside_cliente_.jsp" %>
  </jsp:attribute>
</pt:principal>
