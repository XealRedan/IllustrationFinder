<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 19/11/2015
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Illustration Finder</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.3.5/css/bootstrap.min.css'>
    <link rel='stylesheet' href='webjars/bootstrap/3.3.5/css/bootstrap-theme.min.css'>
    <script type='text/javascript' src='webjars/jquery/2.1.4/jquery.min.js'></script>
    <script type='text/javascript' src='webjars/bootstrap/3.3.5/js/bootstrap.min.js'></script>
  </head>
  <body>
    <div class="container">
        <h1>Illustration Finder</h1>
        <p class="lead">Easily find illustration pictures for your articles</p>
        <form role="form">
          <div class="form-group">
            <label for="input-url">URL</label>
            <input class="form-control" type="text" id="input-url" />
          </div>
          <button class="btn btn-default" type="submit">Find illustrations</button>
        </form>
    </div>
  </body>
</html>
