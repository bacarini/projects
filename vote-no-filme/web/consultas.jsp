<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultas</title>
</head>
<body>
<s:include value="/menu.jsp"></s:include>

<s:iterator value="listaRetorno">
<table>
	<s:if test="%{tipoListaRetorno=='listaUsuario'}">
		<s:iterator value="listaUsuario">
		
		<tr>
			
				<td><a href="/vote-no-filme/obterVotoPorUsuario?idUsuario=<s:property value="idUsuario"/>">
					<s:property value="nomeUsuario" />
					</a>
				</td>
				<td><s:property value="email" /></td>
			
		</tr>
		</s:iterator>
	</s:if>
	<s:elseif test="%{tipoListaRetorno=='listaFilmes'}">
		<s:iterator value="listaFilmes">
		<tr>
			<td><s:property value="idFilme" /></td>
			<td><s:property value="nome" /></td>
		</tr>
		</s:iterator>		
	</s:elseif>
	<s:elseif test="%{tipoListaRetorno=='listaPresquisa'}">
		<s:iterator value="listaPresquisa">
		<tr>
			<td><s:property value="nomeUsuario" /></td>
			<td><s:property value="nomeFilme" /></td>
		</tr>
		</s:iterator>		
	</s:elseif>	
	<s:elseif test="%{tipoListaRetorno=='listaRaking'}">
		<s:iterator value="listaRaking">
		<tr>
			<td><s:property value="numeroVotos" /></td>
			<td><s:property value="nomeFilme" /></td>
		</tr>
		</s:iterator>		
	</s:elseif>
</table>
</s:iterator>
</body>
</html>