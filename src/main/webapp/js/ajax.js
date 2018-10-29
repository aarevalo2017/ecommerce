
function llamadaAjax(url, frm) {
  $.ajax({
    type: 'post',
    data: frm.serialize(),
    url: url,
    success: function (data) {
      data = $.parseJSON(data);
      console.log(data);
      new PNotify({
        title: data.msg,
        text: data.msg,
        type: 'success',
        styling: 'bootstrap3',
        delay: 3000,
        after_close: function () {
          location.reload();
        }
      });
    },
    error: function (jqXHR, textStatus, errorThrown) {
      new PNotify({
        title: 'ups !!!',
        text: 'Algo salió mal :-(',
        type: 'error',
        styling: 'bootstrap3'
      });
    }
  });
}

$(document).ready(function () {
//  alert('asdf');
//  new PNotify({
//    title: 'ups !!!',
//    text: 'Algo salió mal :-(',
//    type: 'success',
//    styling: 'bootstrap3'
//  });
});

