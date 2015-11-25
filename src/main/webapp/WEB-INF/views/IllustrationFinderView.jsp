<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  #%L
  Illustration finder
  %%
  Copyright (C) 2015 Alexandre Lombard
  %%
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
       http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  #L%
  --%>

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

    <form role="form" method="get">
        <c:choose>
            <c:when test="${isUrlValid != null && isUrlValid == false}">
                <div class="form-group has-error has-feedback">
                    <label for="input-url">URL</label>
                    <input class="form-control" type="text" id="input-url" name="url" value="${pUrl}"/>
                    <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                </div>
            </c:when>
            <c:when test="${isUrlValid == null || isUrlValid == true}">
                <div class="form-group">
                    <label for="input-url">URL</label>
                    <input class="form-control" type="text" id="input-url" name="url"/>
                </div>
            </c:when>
        </c:choose>

        <div class="form-group">
            <label for="preferred-width">Width</label>
            <input class="form-control" type="text" id="preferred-width" name="preferred-width" value="512" />
            <label for="preferred-height">Height</label>
            <input class="form-control" type="text" id="preferred-height" name="preferred-height" value="512" />
        </div>
        <button class="btn btn-default" type="submit">Find illustrations</button>
    </form>
</div>
<div class="container">
    <div class="row">
        <c:if test="${images != null}">
            <c:forEach var="image" items="${images}">
                <div class="col-sm-6">
                    <img class="img-responsive img-rounded" src="data:image/png;base64, ${image}" alt="Picture" />
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>
</html>
