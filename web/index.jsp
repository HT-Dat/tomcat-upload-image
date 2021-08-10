<%-- 
    Document   : index
    Created on : May 22, 2021, 1:54:51 AM
    Author     : FAMILY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="post" action="multiPartServlet" enctype="multipart/form-data">
            Choose a file: <input type="file" name="multiPartServlet" />
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>
