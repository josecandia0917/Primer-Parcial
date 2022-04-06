<%@page import="com.emergentes.modelo.Almacen"%>
<%
    Almacen item = (Almacen) request.getAttribute("miAlmacen");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> <%= (item.getId() == 0) ? "Nuevo registro":"Editar registro" %> </h1>
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="<%= item.getId() %>" />
            <table border="1">
               
                    <tr>
                         <td>Descripcion</td> 
                         <td><input type="text" name="descripcion" value="<%= item.getDescripcion() %>" placeholder="Descripcion" /></td>
                    </tr>
                
                    <tr>
                        <td>Cantidad</td>
                        <td><input type="number" name="cantidad" value="<%= item.getCantidad() %>" placeholder="Cantidad"/></td>
                    </tr>
                    
                    <tr>
                        <td>Precio</td>
                        <td><input type="text" name="precio" value="<%= item.getPrecio() %>" placeholder="Precio" /></td>
                    </tr>
                    
                     <tr>
                        <td>Categoria</td>
                        <td><input type="text" name="categoria" value="<%= item.getCategoria() %>" placeholder="Categoria"/></td>
                    </tr>
                   
                    <tr>
                        <td></td>
                        <td><input type="submit"  value="Enviar" /></td>
                    </tr>
            </table>
        </form>
    </body>
</html>
