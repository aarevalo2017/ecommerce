<%-- 
    Document   : wpend
    Created on : 03-10-2018, 0:47:11
    Author     : Alejandro
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-9">
      <h1>Transacion Finalizada</h1>
      <br>
      <table class="table table-bordered">
        <tr>
          <td>Código Autorización</td>
          <td>
            <p id="authorizationCode"></p>
          </td>
        </tr>
        <tr>
          <td>Orden de Compra</td>
          <td>
            <p id="buyOrder"></p>
          </td>
        </tr>
        <tr>
          <td>Monto CLP $</td>
          <td>
            <p id="authorizedAmount"></p>
          </td>
        </tr>
        <tr>
          <td>Número de Tarjeta</td>
          <td>
            <p id="cardNumber"></p>
          </td>
        </tr>
        <tr>
          <td>Forma de Pago</td>
          <td>
            <p id="paymentTypeCode"></p>
          </td>
        </tr>
        <tr>
          <td>Número de Cuotas</td>
          <td>
            <p id="sharesNumber"></p>
          </td>
        </tr>
        <tr>
          <td>Fecha y Hora de Transacción</td>
          <td>
            <p id="transactionDate"></p>
          </td>
        </tr>
      </table>
      <script>
        $('#authorizationCode').html(localStorage.getItem('authorizationCode'));
        $('#authorizedAmount').html(formatNumber(localStorage.getItem('authorizedAmount')));
        $('#buyOrder').html(localStorage.getItem('buyOrder'));
        $('#cardNumber').html('**** **** **** ' + localStorage.getItem('cardNumber'));
        $('#paymentTypeCode').html(localStorage.getItem('paymentTypeCode') == 'VC' ? 'Crédito' : 'Débito');
        $('#sharesNumber').html(localStorage.getItem('sharesNumber'));
        $('#transactionDate').html(localStorage.getItem('transactionDate'));
        localStorage.clear();
      </script>
    </div>
  </jsp:attribute>
</pt:principal>

