<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="form" method="post" modelAttribute="signupForm">
	<h2 class="form-heading">Please Sign Up</h2>
	<div class="alert alert-warning">
		Account creation is currently unavailable. Please login using an existing account.
	</div>
	<form:errors path="" element="p" class="text-error" />
	<form:input path="name" class="input-block-level" placeholder="Username" disabled="true" />
	<form:errors path="name" element="p" class="text-error"/>
	<form:password path="password" class="input-block-level" placeholder="Password" disabled="true" />
	<form:errors path="password" element="p" class="text-error"/> 
	<button class="btn btn-large btn-primary disabled" type="submit">Sign Up</button>
	<p class="form-text">Already have an account? <a href='<s:url value="/signin"/>'>Sign In</a></p>
</form:form>
