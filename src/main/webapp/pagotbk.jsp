<%-- 
    Document   : pagotbk
    Created on : 02-10-2018, 22:47:37
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js" type="text/javascript"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <title>JSP Page</title>
  </head>
  <body>
    <h1>Confirme Datos de Pago</h1>
    <form action="${requestScope.url}" method="post" id="enviar_pago">
      <input type="hidden" name="token_ws" value="${requestScope.token}"/>
      <input type="hidden" value="${requestScope.idSession}"/>
      <input type="hidden" value="${requestScope.buyOrder}"/>
      <input type="hidden" value="${requestScope.amount}"/>
      <input type="hidden" value="${requestScope.urlReturn}"/>
      <input type="hidden" value="${requestScope.urlFinal}"/>
      <table>
        <tr>
          <td>Id Sesión</td>
          <td>${requestScope.idSession}</td>
        </tr>
        <tr>
          <td>Orden de compra</td>
          <td>${requestScope.buyOrder}</td>
        </tr>
        <tr>
          <td>Total</td>
          <td>${requestScope.amount}</td>
        </tr>
        <tr>
          <td>URL Retorno</td>
          <td>${requestScope.urlReturn}</td>
        </tr>
        <tr>
          <td>URL Final</td>
          <td>${requestScope.urlFinal}</td>
        </tr>
<!--        <tr>
          <td></td>
          <td><input type="submit" value="Pagar"/></td>
        </tr>-->
      </table>
    </form>
        <script>
          $(document).ready(function () {
            setTimeout(function () {
              $('#enviar_pago').submit();
            }, 10);
          });
        </script>
  </body>
</html>
