<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <style type="text/css">
        td {
            font-size: small;
            font-family:Arial;
            font-weight:normal;
        }
        .titulo {
            font-size: 14px;
            font-weight:bold;
            font-family:Arial;
        }
        .suptitulo {
            font-size: 16px;
            font-weight:bold;
            font-family:Arial;
        }
    </style>
    <title>Liberación de lotes</title>
</head>
<body>
<h1 class="titulo">Liberación de Lotes</h1>
<%        
		if (request.getUserPrincipal() != null) {
			try {
				response.getWriter().println("<p>Hello " + request.getUserPrincipal().getName());
			} catch (Exception e) {
				// Handle errors
				response.getWriter().print(e.getMessage());
			}
		} else {
			response.getWriter().print("Hello stranger!");
		}
		
%>	

	<FORM METHOD='POST' ACTION='HelloWorldServlet'>
		<table>
			<tr>
			<td>Producto:</td>
			<td><INPUT TYPE='TEXT' NAME='Producto' SIZE=30></td>
			</tr>
			<tr>
				<td>Lote: </td>
				<td><INPUT TYPE='TEXT' NAME='Lote' SIZE=30></td>
			</tr>
			<tr>
				<td>Comitente: </td>
				<td><INPUT TYPE='TEXT' NAME='Comitente' SIZE=30></td>
			</tr>
			<tr>
				<td><INPUT TYPE='SUBMIT' VALUE='Liberar'></td>	 
				<td><INPUT TYPE='RESET'></td>
			</tr>
		</table>
	</FORM>
</body>
</html>