<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="org.imie.factory.BaseConcreteFactory"%>
<%@page import="org.imie.service.interfaces.ICursusService"%>
<%@page import="org.imie.service.CursusService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.imie.DTO.CursusDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet type=text/css href=./css/cssReset.css>

	<!-- lien vers la biblio jquery  -->
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./listecursus.js"></script>
<link rel=stylesheet type=text/css href=./css/Style.css>
<title>Liste de cursus</title>
</head>
<body>
	<div class="conteneur">
		
		<button id="openerajout">ajouter un cursus</button>
		<div id="tableaucursus" class=tableau>
			<%
				ICursusService cursusService = BaseConcreteFactory.getInstance()
						.createCursusService(null);
				List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
				cursusDTOs = cursusService.findAll();
				Integer i = 1;
				for (CursusDTO cursusDTO : cursusDTOs) {
			%>
			<div id="lignetableaucursus<%=i%>" class="ligneTableauCursus">
				<div class="celluleTableau largeur100 ">
					<%=i%></div>
				<div class="celluleTableau largeur350 "><%=cursusDTO.getLibelle()%></div>

			</div>

			<div id="contenu<%=i%>" class="contenu">
				<!-- lien à modifier  -->
				<button id="openermodif<%=i%>">modifier</button> <a
					href="./AccueilServletClass">supprimer</a>
			</div>
			<%
				i++;
				}
			%>
		</div>
	</div>

	<div id="ajouterdialog" title="ajouter">
	<form id="formajoutcursus" method="post"
			action="./CreateServletClass">
				<!-- lien à modifier  -->
		<fieldset>
			<legend>Ajouter un cursus</legend>
			Libellé*:<input type="text" name="libelle" maxlength="15"></input>
		</fieldset>
		<br />
		 <input type="submit" value="ajouter" />
		</form>
	</div>
	
	
	<div id="modifierdialog" title="modifier">
	<form id="formmodifcursus" method="post"
			action="./CreateServletClass">
				<!-- lien à modifier  -->
		<fieldset>
			<legend>modifier un cursus</legend>
			Libellé*:<input type="text" name="libelle" maxlength="15"></input>
		</fieldset>
		<br />
		 <input type="submit" value="modifier" />
		</form>
	</div>
</body>
</html>