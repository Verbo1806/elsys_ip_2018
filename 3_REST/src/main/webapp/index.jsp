<%--
  Created by IntelliJ IDEA.
  User: milko.mitropolitsky
  Date: 11/29/17
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>File Upload Example - howtodoinjava.com</h1>

  <form action="/api/match/upload" method="post" enctype="multipart/form-data">

    <p>Select a file : <input type="file" name="file" size="45" accept="text/csv" /></p>
    <input type="submit" value="Upload CSV" />

  </form>
  </body>
</html>
