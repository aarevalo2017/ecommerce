//  alert('asdf');

//  function pruebaplp(){
//    console.log(654);
//  }
const BASE_URL = '/ecommerce/servlet/';

function formatNumber(numero) {
  return numero.toString().replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, '$1.');
}
var carro = {
  'agregar': function (btn, id_producto, csrf_token) {
    $(btn).attr("disabled", true);
    var cantidad = $(btn).parents('div').find('.cant-' + id_producto).val();
    $.ajax({
      url: 'carro',
      type: 'post',
      data: 'action=agregar&csrf_token=' + csrf_token + '&id_producto=' + id_producto + '&cantidad=' + (typeof (cantidad) != 'undefined' ? cantidad : 1),
      dataType: 'json',
      beforeSend: function () {
        $('#cart > button').button('loading');
      },
      complete: function () {
        $(btn).removeAttr("disabled");
        $('#cart > button').button('reset');
      },
      success: function (json) {
        $('.alert-dismissible, .text-danger').remove();
        if (json['success']) {
          $('#content').parent().before('<div class="alert alert-success alert-dismissible"><i class="fa fa-check-circle"></i> ' + json['success'] + ' <button type="button" class="close" data-dismiss="alert">&times;</button></div>');
          setTimeout(function () {
//                    $('#cart > button').html('<span id="cart-total"><i class="fa fa-shopping-cart"></i> ' + json['total'] + '</span>');
          }, 100);
//                  $('#cart > ul').load('index.php?route=common/cart/info ul li');
//                  $('#cart > ul').load('carro');
          recargarMiniCarro();
          $('#cart').addClass('open');
        }
      },
      error: function (xhr, ajaxOptions, thrownError) {
        alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
      }
    });
  },

  'quitar': function (item_id) {
//    alert(id_producto);
    $.ajax({
      url: BASE_URL + 'carro',
      type: 'post',
      data: "action=eliminar&item_id=" + item_id,
      dataType: 'json',
      beforeSend: function () {
      },
      complete: function () {
        recargarMiniCarro();
      },
      success: function (json) {
        new PNotify({
//          title: '',
          text: json.ok,
          type: 'success',
          styling: 'bootstrap3'
        });
        console.log(json);
      },
      error: function (xhr, ajaxOptions, thrownError) {
        alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
      }
    });
  }

};

function recargarMiniCarro() {
  $.ajax({
    url: BASE_URL + 'carro',
    type: 'get',
    dataType: 'json',
    success: function (data, textStatus, jqXHR) {
      if (data.length == 0) {
        var carroVacioHtml = '<li><p class="text-center">Tu carro se encuentra vacío!</p></li>';
        $('#cart > button').html('<span id="cart-total"><i class="fa fa-shopping-cart"></i> 0 producto(s) $ 0</span>');
        $('#cart > ul').html(carroVacioHtml);
        return;
      }
      var carroHtml = "<li>";
      carroHtml += "<table class='table table-striped'><tbody>";
      var total = 0;
      var neto = 0;
      var iva = 0;
      $.each(data, function (i, item) {
        carroHtml += "<tr>";
        carroHtml += "<td class='text-center'><a href='" + item.url_pdp + "'><img width='50' src='/ecommerce" + item.url_imagen + "' alt='" + item.nombre + "' title='" + item.nombre + "' class='img-thumbnail'/></a></td>";
        carroHtml += "<td class='text-left'><a href='" + item.url_pdp + "'>" + item.nombre + "</a></td>";
        carroHtml += "<td class='text-right'>" + item.cantidad + "</td>";
        carroHtml += "<td class='text-left'>" + item.medida + "</td>";
        carroHtml += "<td class='text-right'>$</td>";
        totalItem = (item.precio * item.cantidad)
        carroHtml += "<td class='text-right'>" + formatNumber(totalItem) + "</td>";
        carroHtml += "<td class='text-center'><button type='button' onclick='carro.quitar(" + item.id + ");' title='Quitar' class='btn btn-danger btn-xs'><i class='fa fa-times'></i></button></td>";
        carroHtml += "</tr>";
        total += (item.cantidad * item.precio);
      });
      carroHtml += "</tbody></table></li>";
      neto = Math.round(total / 1.19);
      iva = total - neto;
      carroHtml += "<li>";
      carroHtml += "<div>";
      carroHtml += "<table class='table table-bordered'>";
      carroHtml += "<tr>";
      carroHtml += "<td class='text-right'><strong>Neto</strong></td>";
      carroHtml += "<td class='text-right' style='vertical-align: middle;'>$ " + formatNumber(neto) + "</td>";
      carroHtml += "</tr>";
      carroHtml += "<tr>";
      carroHtml += "<td class='text-right'><strong>IVA (19%)</strong></td>";
      carroHtml += "<td class='text-right'>$ " + formatNumber(iva) + "</td>";
      carroHtml += "</tr>";
      carroHtml += "<tr>";
      carroHtml += "<td class='text-right'><strong>Total</strong></td>";
      carroHtml += "<td class='text-right'>$ " + formatNumber(total) + "</td>";
      carroHtml += "</tr>";
      carroHtml += "</table>";
      carroHtml += "<p class='text-right'><a href='/ecommerce/carro/carro.jsp'><strong><i class='fa fa-shopping-cart'></i> Ver Carro</strong></a>";
      carroHtml += "&nbsp;&nbsp;&nbsp;<a href='/ecommerce/cliente/checkout.jsp'><strong><i class='fa fa-share'></i> Confirmar Pedido</strong></a></p>";
      carroHtml += "</div>";
      carroHtml += "</li>";
      $('#cart > ul').html(carroHtml);
      $('html, body').animate({scrollTop: 0}, 'slow');
      $('#cart > button').html('<span id="cart-total"><i class="fa fa-shopping-cart"></i> ' + data.length + ' producto(s) $ ' + formatNumber(total) + '</span>');
    },
    error: function (jqXHR, textStatus, errorThrown) {
      console.log(errorThrown + "\r\n" + jqXHR.statusText + "\r\n" + jqXHR.responseText);
    }
  });
}