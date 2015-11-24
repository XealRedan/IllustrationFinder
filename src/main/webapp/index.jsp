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
<%@ page import="com.illustrationfinder.process.post.IPostProcessor" %>
<%@ page import="com.illustrationfinder.process.searchengine.google.GoogleSearchEngine" %>
<%@ page import="com.illustrationfinder.process.post.HtmlPostProcessor" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.awt.image.BufferedImageOp" %>
<%@ page import="com.illustrationfinder.process.image.BufferedImageProcessor" %>
<%@ page import="com.illustrationfinder.process.image.IImageProcessor" %>
<%@ page import="java.awt.Dimension" %>
<%@ page import="com.illustrationfinder.IllustrationFinder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="javax.xml.bind.DatatypeConverter" %>
<%@ page import="org.apache.commons.validator.UrlValidator" %>

<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 19/11/2015
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final String url = request.getParameter("url");
    int width, height;

    // Read the width from parameters
    try {
        if(request.getParameter("preferred-width") != null)
            width = Math.max(0, Math.min(1024, Integer.parseInt(request.getParameter("preferred-width"))));
        else
            width = 512;
    } catch (NumberFormatException e) {
        width = 512;
    }

    // Read the height from parameters
    try {
        if(request.getParameter("preferred-height") != null)
            height = Math.max(0, Math.min(1024, Integer.parseInt(request.getParameter("preferred-height"))));
        else
            height = 512;
    } catch (NumberFormatException e) {
        height = 512;
    }

    // Validate URL
    boolean isUrlValid = false;
    final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
    if(url != null && urlValidator.isValid(url)) {
        isUrlValid = true;
    }
%>
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
        <%
            if(url != null && !isUrlValid) {
                %>
                <div class="form-group has-error has-feedback">
                    <label for="input-url">URL</label>
                    <input class="form-control" type="text" id="input-url" name="url" value="<%=url%>"/>
                    <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                </div>
                <%
            } else {
                %>
                <div class="form-group">
                    <label for="input-url">URL</label>
                    <input class="form-control" type="text" id="input-url" name="url"/>
                </div>
                <%
            }
        %>
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
        <%
            if (url != null && isUrlValid) {
                final IPostProcessor postProcessor = new HtmlPostProcessor();
                final GoogleSearchEngine searchEngine = new GoogleSearchEngine();
                final IImageProcessor<BufferedImage, BufferedImageOp> imageProcessor = new BufferedImageProcessor();

                imageProcessor.setPreferredSize(new Dimension(width, height));

                final IllustrationFinder illustrationFinder = new IllustrationFinder();
                illustrationFinder.setPostProcessor(postProcessor);
                illustrationFinder.setSearchEngine(searchEngine);
                illustrationFinder.setImageProcessor(imageProcessor);

                final List<BufferedImage> images = illustrationFinder.getImages(new URL(url));

                for (BufferedImage image : images) {
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "png", baos);
                    baos.flush();
                    final byte[] imageInByteArray = baos.toByteArray();
                    baos.close();
                    final String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);
        %>
                    <div class="col-sm-6">
                        <img class="img-responsive img-rounded" src="data:image/png;base64, <%=b64%>" alt="Picture" />
                    </div>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
