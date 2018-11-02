<%-- 
  Document   : head
  Created on : 04-09-2018, 21:34:28
  Author     : Alejandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <!--<meta charset="UTF-8" />-->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <script src="${pageContext.servletContext.contextPath}/js/jquery.min.js" type="text/javascript"></script>
  <link href="<c:url value="/css/bootstrap.min.css"></c:url>" rel="stylesheet" media="screen" />
  <!--<link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen" />-->
  <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" crossorigin="anonymous">
  <!--<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />-->
  <!--<link href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css" />-->
  <link type="text/css" href="${pageContext.servletContext.contextPath}/css/stylesheet.css" rel="stylesheet"/>
  <script src="${pageContext.servletContext.contextPath}/js/common.js" type="text/javascript"></script>
  <!-- PNotify -->
  <script src="${pageContext.servletContext.contextPath}/js/pnotify.js"></script>
  <link href="${pageContext.servletContext.contextPath}/css/pnotify.css" rel="stylesheet">
  <script src="${pageContext.servletContext.contextPath}/js/ajax.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/custom.js"></script>
  <title>${titulo}</title>
</head>
