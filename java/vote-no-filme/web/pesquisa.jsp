<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vote no Filme</title>

</head>
<body>


<s:include value="/menu.jsp"></s:include>

<s:iterator value="listaRetorno">
	<s:iterator value="listaFilmes">
	  <p>
	  Id <label class="param"><s:property value="idFilme"/></label> 
	  nome filme <label class="param"><s:property value="nome"/></label>
	  </p>
	  <p>-------------------------------------------</p>
	</s:iterator>
</s:iterator>

</body>
</html>