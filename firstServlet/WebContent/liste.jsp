<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="org.imie.DTO.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type=text/css href=./css/cssReset.css>

<!-- lien vers la biblio jquery  -->
 <link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./liste.js"></script>

<link rel=stylesheet type=text/css href=./css/Style.css>
 
	<title>liste utilisateur</title>
</head>
<body>
<div class="conteneur">
<a href=./CreateServletClass>ajouter un utilisateur</a><br />

	<div id="tableau" class=tableau>
		<%
			List<UserDTO> listuser = (List<UserDTO>) session
					.getAttribute("listuser");
			Integer i = 1;
			for (UserDTO userDTO : listuser) {
		%>
		
		<div id="lignetableau<%=i%>" class="ligneTableau">
		
			<a href=./AccueilServletClass?ligne=<%=i%> title="plus d'info">
				<div class="celluleTableau largeur100">
					<%=i%></div>
				<div class="celluleTableau largeur350"><%=userDTO.getNom()%></div>
				<div class="celluleTableau largeur350"><%=userDTO.getPrenom()%></div>
				<div class="celluleTableau largeur100"><%=userDTO.getAge()%>ans</div>
			</a>
		</div>
		<div id="contenu<%=i%>"class="contenu">
		
		</div>
		<%
			i++;
			}
		%>
	</div>
	</div>
</body>
</html>