<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Aplikasi Virtual Account</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<%= request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
    <link href="<%= request.getContextPath() %>css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="css/twitter-bootstrap/js/html5shiv.js"></script>
    <![endif]-->

  <decorator:head></decorator:head>
    
     </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Aplikasi VAC</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              Logged in as <a href="#" class="navbar-link">Username</a>
            </p>
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
              <li><a href="<%= request.getContextPath() %>/j_spring_security_logout">Logout</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Master</li>
              <li><a href="<%= request.getContextPath() %>/spring/master/user/list">User</a></li>
              <li><a href="#">Role</a></li>
              <li><a href="#">Permission</a></li>
              <li class="nav-header">Transaksi</li>
              <li><a href="#">Pembuatan Account</a></li>
              <li class="nav-header">Laporan</li>
              <li><a href="#">Rekap Transaksi</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">          
          <div class="row-fluid">
              <!-- content start di sini -->

              <decorator:body></decorator:body>
              
              <!-- content finish di sini -->
          </div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Company 2013</p>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%= request.getContextPath() %>/js/jquery.min.js"></script>
    <script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>

  </body>
</html>
