<%-- 
    Document   : pdp
    Created on : 03-10-2018, 16:20:35
    Author     : Alejandro
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <c:set var="basePath" value="${pageContext.servletContext.contextPath}"></c:set>
    <c:set var="producto" value="${requestScope.producto}"></c:set>
    <c:set var="productores" value="${requestScope.productores}"></c:set>
    <c:set var="nombre" value="${producto.getNombre()}"></c:set>
    <c:set var="url_imagen" value="${producto.getDescripcionHtml()}"></c:set>
    <c:set var="unidad_medida" value="${producto.getUnidadMedidaId().getNombre()}"></c:set>
    <c:set var="categoria" value="${producto.getCategoriaId().getNombre()}"></c:set>
    <c:set var="impuesto" value="${producto.getImpuestoId()}"></c:set>
      <script>
        var arrLatLng = [];
      </script>
      <div id="content">
        <!--foto y mapa-->
        <div class="row">
          <!--FOTO-->
          <div class="col-sm-4">
            <ul class="thumbnails">
              <li>
                <a class="" href="${basePath}${url_imagen}" title="iPhone">
                <img width="300" src="${basePath}${url_imagen}" title="iPhone" alt="iPhone">
              </a>
            </li>
            <li class="image-additional">
              <a class="thumbnail" href="${basePath}${url_imagen}" title="iPhone">
                <img src="${basePath}${url_imagen}" title="iPhone" alt="iPhone">
              </a>
            </li>
          </ul>
        </div>
        <!--MAPA-->
        <div class="col-md-8" id="map" style="height: 398px;"></div>
      </div>
      <!--fin foto y mapa-->
      <hr>
      <!--descripción y tabla productores-->
      <div class="row">
        <!--DESCRIPCION-->
        <div class="col-lg-4">
          <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-description" data-toggle="tab">Descripción</a></li>
            <li><a href="#tab-review" data-toggle="tab">Comentarios (0)</a></li>
          </ul>
          <div class="tab-content">
            <div class="tab-pane active" id="tab-description">
              <!--<p class="intro">${producto.getId()}</p>-->
              <h1>${nombre}</h1>
              <ul class="list-unstyled">
                <li>Productores: ${productores.size()}</li>
                <li>Código de producto: ${producto.getId()}</li>
              </ul>
              <div id="product"> <div class="form-group">
                  <br>
                </div>
              </div>
              <div class="rating">
                <p> 
                  <span class="fa fa-stack">
                    <i class="fa fa-star-o fa-stack-1x"></i>
                  </span>
                  <span class="fa fa-stack">
                    <i class="fa fa-star-o fa-stack-1x"></i>
                  </span> 
                  <span class="fa fa-stack">
                    <i class="fa fa-star-o fa-stack-1x"></i>
                  </span> 
                  <span class="fa fa-stack">
                    <i class="fa fa-star-o fa-stack-1x"></i>
                  </span> 
                  <span class="fa fa-stack">
                    <i class="fa fa-star-o fa-stack-1x"></i>
                  </span>
                  <a href="" onclick="$('a[href=\'#tab-review\']').trigger('click'); return false;">0 comentarios</a> / <a href="" onclick="$('a[href=\'#tab-review\']').trigger('click'); return false;">Escriba un comentario</a>
                </p>
                <hr>
              </div>


            </div>
            <div class="tab-pane" id="tab-review">
              <form class="form-horizontal" id="form-review">
                <div id="review"><p>No hay comentarios acerca de este producto.</p>
                </div>
                <h2>Escriba un comentario</h2>
                <div class="form-group required">
                  <div class="col-sm-12">
                    <label class="control-label" for="input-name">Nombre</label>
                    <input type="text" name="name" value="" id="input-name" class="form-control">
                  </div>
                </div>
                <div class="form-group required">
                  <div class="col-sm-12">
                    <label class="control-label" for="input-review">Comentario</label>
                    <textarea name="text" rows="5" id="input-review" class="form-control"></textarea>
                  </div>
                </div>
                <div class="form-group required">
                  <div class="col-sm-12">
                    <label class="control-label">Calificación</label>
                    &nbsp;&nbsp;&nbsp; Malo&nbsp;
                    <input type="radio" name="rating" value="1">
                    &nbsp;
                    <input type="radio" name="rating" value="2">
                    &nbsp;
                    <input type="radio" name="rating" value="3">
                    &nbsp;
                    <input type="radio" name="rating" value="4">
                    &nbsp;
                    <input type="radio" name="rating" value="5">
                    &nbsp;Bueno</div>
                </div>
                <div class="buttons clearfix">
                  <div class="pull-right">
                    <button type="button" id="button-review" data-loading-text="Loading..." class="btn btn-primary">Enviar</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
        <!--TABLA PRODUCTORES-->
        <div class="col-sm-8">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><i class="fa fa-list"></i> Productores disponibles</h3>
            </div>
            <div class="panel-body">
              <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                  <thead>
                    <tr>
                      <!--<td style="width: 1px;" class="text-center"><input type="checkbox" onclick="$('input[name*=\'selected\']').prop('checked', this.checked);"></td>-->
                      <td class="text-center bg-info">Productor</td>
                      <td class="text-center bg-info">Stock Disponible</td>
                      <td class="text-center bg-info">Precio</td>
                      <td class="text-center bg-info">Tiene Despacho</td>
                      <td class="text-center bg-info">Agregar al Carro</td>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${productores}" var="productor">
                      <c:set var="id" value="${productor.getId()}"></c:set>
                      <c:set var="precio" value="${productor.getPrecio()}"></c:set>
                      <c:set var="stock" value="${productor.getCantidad()}"></c:set>
                      <c:set var="pro" value="${productor.getProductorId()}"></c:set>
                      <c:set var="tiene_despacho" value="${productor.getTieneDespacho()}"/>
                      <c:set var="lat" value="${pro.getLatitud()}"/>
                      <c:set var="lng" value="${pro.getLongitud()}"/>
                    <script>
                      arrLatLng.push({
                        lat: ${lat},
                        lng: ${lng},
                        producto: {
                          nombre: '${pro.getNombreFantasia()}',
                          stock: ${stock},
                          precio: ${precio},
                          despacho: ${tiene_despacho}
                        }
                      });
                    </script>
                    <tr>
                      <td class="text-center" style="vertical-align: middle;"> 
                        ${pro.getNombreFantasia()}
                      </td>
                      <td class="text-center" style="vertical-align: middle;"><span class="label label-success">${stock}</span></td>
                      <td class="text-center" style="vertical-align: middle;">$ <fmt:formatNumber type="number" >${precio}</fmt:formatNumber></td>
                      <td class="text-center" style="vertical-align: middle;"><c:if test="${tiene_despacho == 1}">Si</c:if><c:if test="${tiene_despacho == 0}">No</c:if></td>
                        <td class="text-center" style="width: 300px; vertical-align: middle;">
                            <button data-id="${id}" class="menos btn btn-link btn-xs">
                          <i class="fa fa-minus" aria-hidden="true"></i>
                        </button>
                        <input class="cant-${id} form-control input-sm" style="width: 40px; display: inline; text-align: center" type="text" name="cantidad" value="1" size="5" id="cant"/>
                        <button data-stock="${stock}" data-id="${id}" class="mas btn btn-link btn-xs">
                          <i class="fa fa-plus" aria-hidden="true"></i>
                        </button>
                        <button type="button" onclick="carro.agregar(this, ${id}, '${pageContext.session.id}');" class="btn btn-xs btn-warning text-center">
                          Agregar
                          <i class="fa fa-cart-plus"></i>
                        </button>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <div class="row">
              </div>
            </div>
          </div>
        </div>      
      </div>
      <!--fin descripción y tabla productores-->

      <!--PRODUCTOS RELACIONADOS-->
      <!--      <h3>Related Products</h3>
            <div class="row"> <div class="col-xs-6 col-sm-3">
                <div class="product-thumb transition">
                  <div class="image"><a href="https://demo.opencart.com/index.php?route=product/product&amp;product_id=42"><img src="${url_imagen}" alt="Apple Cinema 30&quot;" title="Apple Cinema 30&quot;" class="img-responsive"></a></div>
                  <div class="caption">
                    <h4><a href="https://demo.opencart.com/index.php?route=product/product&amp;product_id=42">Apple Cinema 30"</a></h4>
                    <p>The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..</p>
                    <p class="price"> <span class="price-new">$110.00</span> <span class="price-old">$122.00</span> <span class="price-tax">Ex Tax: $90.00</span> </p>
                  </div>
                  <div class="button-group">
                    <button type="button" onclick="cart.add('42', '2');"><span class="hidden-xs hidden-sm hidden-md">Add to Cart</span> <i class="fa fa-shopping-cart"></i></button>
                    <button type="button" data-toggle="tooltip" title="" onclick="wishlist.add('42');" data-original-title="Add to Wish List"><i class="fa fa-heart"></i></button>
                    <button type="button" data-toggle="tooltip" title="" onclick="compare.add('42');" data-original-title="Compare this Product"><i class="fa fa-exchange"></i></button>
                  </div>
                </div>
              </div>
            </div>-->
    </div>
    <!--script google maps-->
    <script>
      var map, infoWindow, infoMiPosicion;

      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -33.437533, lng: -70.643707},
          zoom: 10
        });
        obtenerMiPosicion();
        for (var key in arrLatLng) {
          agregarMarcador(arrLatLng[key].lat, arrLatLng[key].lng, arrLatLng[key].producto);
        }
      }

      function agregarMarcador(lat, lng, producto) {
        var marker = new google.maps.Marker({
          position: {lat: lat, lng: lng},
          map: map,
          animation: google.maps.Animation.DROP,
          title: producto.nombre
        });
        var contentString = '<h4>' + producto.nombre + '</h4>' +
                'Stock : ' + producto.stock + '<br>' +
                'Precio : ' + producto.precio + '<br>' +
                'Despacho a domicilio : ' + (producto.despacho ? 'Si' : 'No');

        var infowindow = new google.maps.InfoWindow({
          content: contentString
        });
        marker.addListener('click', function () {
          infowindow.open(map, marker);
        });
      }

      function obtenerMiPosicion() {
        infoMiPosicion = new google.maps.InfoWindow;
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            var miPosMarker = new google.maps.Marker({
              position: pos,
              map: map,
              animation: google.maps.Animation.DROP,
              title: 'Mi ubicación.',
              icon: '../img/blue-dot.png'
            });
            map.setCenter(pos);
          }, function () {
//            handleLocationError(true, infoWindow, map.getCenter());
          });
        } else {
          console.log(false);
        }
      }
    </script>

    <!--script botones carro-->
    <script>
      $(document).ready(function () {
        $('.mas').click(function (e) {
          e.preventDefault();
          var id = $(this).attr('data-id');
          var stock = $(this).attr('data-stock');
          var cant = $(this).parents('div').find('.cant-' + id).val();
          cant++;
          if (cant > stock)
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
      });
    </script>

    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBLyOW8h-pwR__Xf3Ldg8Qto4HdwUUnF2E&callback=initMap"></script>
  </jsp:attribute>
</pt:principal>