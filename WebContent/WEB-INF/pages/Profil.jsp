<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<title>Profil de ${pseudo}</title>
</head>
<body>
	

	<p class="h3 display-3 text-center">Mon profil</p>

	<div class="container text-center bg-dark  text-white rounded">

		<div class="form-group">
			<label>Prénom</label> <label class="form-control">${prenom}</label>
		</div>


		<div class="form-group">
			<label>Code Postal</label> <label class="form-control">${codePostal}</label>
		</div>



		<div class="form-group">
			<label>Ville</label> <label class="form-control">${ville}</label>
		</div>


		<!-- Si le pseudo en session et le pseudo affiché sont les mêmes -->
		<c:if test="${memeProfil == true }">

			<div class="form-group">
				<label>Pseudo</label> <label class="form-control">${pseudo}</label>
			</div>


			<div class="form-group">
				<label>Nom</label> <label class="form-control">${nom}</label>
			</div>




			<div class="form-group">
				<label>Email</label> <label class="form-control">${email}</label>
			</div>

			<div class="form-group">
				<label>Téléphone</label> <label class="form-control">${telephone}</label>
			</div>


			<div class="form-group">
				<label>Rue</label> <label class="form-control">${rue}</label>
			</div>


			<div class="form-group ">
				<label>Crédit</label> <label>${credit}</label>
			</div>

			<div class="form-group row mb-5">
				<div class="col-6">
					<form action="/ENI-Encheres/Profil" method="post">
						<button type="submit" class="btn btn-success">Modifier</button>
					</form>
				</div>
	
				<div class="col-6">
					<form action="/ENI-Encheres/Profil" method="post">
						<button name="annuler" type="submit" class="btn btn-danger">Retour</button>
					</form>
				</div>
				</div>
		</c:if>
	</div>

</body>
</html>