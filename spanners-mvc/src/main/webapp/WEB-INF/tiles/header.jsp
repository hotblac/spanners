<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
			</a> 
			<a class="brand" href="#">Spanners</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="active"><a href='<s:url value="/displaySpanners"></s:url>'>Home</a></li>
				</ul>					
				<ul class="nav pull-right">
					<security:authorize access="!isAuthenticated()">
						<li><a href='<s:url value="/signin"></s:url>'>Sign in</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_PREVIOUS_ADMINISTRATOR')">
						<li><a href='<s:url value="/admin/switchUser"></s:url>'>Switch User</a></li>
						</security:authorize>
						<li><a href='<s:url value="/logout"></s:url>'>Logout (<security:authentication property="principal.username"/>)</a></li>
					</security:authorize>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>