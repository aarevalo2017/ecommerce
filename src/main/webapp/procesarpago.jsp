<%-- 
    Document   : procesarpago
    Created on : 02-10-2018, 23:50:41
    Author     : Alejandro
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pt" tagdir="/WEB-INF/tags/" %>
<pt:principal titulo="Feria Web">
  <jsp:attribute name="contenido">
    <div id="content" class="col-sm-9">
      <h2>Estamos procesando su pago <i class="fa fa-spinner fa-spin"></i></h2>
<!--      <h4>accountingDate : ${requestScope.accountingDate}</h4>
      <h4>cardExpirationDate : ${requestScope.cardExpirationDate}</h4>
      <h4>cardNumber : ${requestScope.cardNumber}</h4>
      <h4>authorizationCode : ${requestScope.authorizationCode}</h4>
      <h4>authorizedAmount : ${requestScope.authorizedAmount}</h4>
      <h4>buyOrder : ${requestScope.buyOrder}</h4>
      <h4>paymentTypeCode : ${requestScope.paymentTypeCode}</h4>
      <h4>sharesNumber : ${requestScope.sharesNumber}</h4>
      <h4>commerceCode : ${requestScope.commerceCode}</h4>
      <h4>responseCode : ${requestScope.responseCode}</h4>
      <h4>sessionId : ${requestScope.sessionId}</h4>
      <h4>transactionDate : ${requestScope.transactionDate}</h4>
      <h4>urlRedirection : ${requestScope.urlRedirection}</h4>
      <h4>vci : ${requestScope.vci}</h4>-->
      <form action="${requestScope.url}" method="post" id="procesarpago">
        <input type="hidden" name="token_ws" value="${requestScope.token}">
      </form>
      <script>
        window.localStorage.clear();
        localStorage.setItem("cardNumber", "${requestScope.cardNumber}");
        localStorage.setItem("authorizationCode", "${requestScope.authorizationCode}");
        localStorage.setItem("authorizedAmount", "${requestScope.authorizedAmount}");
        localStorage.setItem("buyOrder", "${requestScope.buyOrder}");
        localStorage.setItem("paymentTypeCode", "${requestScope.paymentTypeCode}");
        localStorage.setItem("sharesNumber", "${requestScope.sharesNumber}");
        localStorage.setItem("transactionDate", "${requestScope.transactionDate}");
        $(document).ready(function () {
          setTimeout(function () {
          $('#procesarpago').submit();
          }, 3000);
        });
      </script>
    </div>
  </jsp:attribute>
</pt:principal>



