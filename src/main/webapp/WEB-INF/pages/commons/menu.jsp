<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<nav class="blue">
	<div class="nav-wrapper container">
		<a href="home" class="brand-logo">Topaidi</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="home">Idées</a></li>
			<li><a href="ranking">Classement</a></li>
			<c:choose>
	      	<c:when test="${isConnected == true}">
	      		<li><a href="idea?action=create">Proposer</a></li>
	        	<li><a href="signIn?action=exit">Déconnexion</a></li>
	    	</c:when>    
	    	<c:otherwise>
	        	<li><a href="signIn?action=signOn">Connexion</a></li>
		    </c:otherwise>
		</c:choose>
		</ul>
	</div>
</nav>
