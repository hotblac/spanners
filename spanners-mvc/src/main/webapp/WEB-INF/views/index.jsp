<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="hero-unit">
	<h1><s:message code="view.index.title" /></h1>
	<p>Welcome to the Spanners Spring MVC demo application!</p>
	<p>
		<a href='<s:url value="/displaySpanners" />' class="btn btn-primary btn-large"> Sign In </a>
	</p>
</div>