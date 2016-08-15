<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="form" method="post" action="/signup" modelAttribute="signupForm">
	<h2 class="form-heading">Please Sign Up</h2>
	<form:errors path="" element="p" class="text-error" />
	<form:input path="name" class="input-block-level" placeholder="Username" />
	<form:errors path="name" element="p" class="text-error"/>
	<form:password path="password" class="input-block-level" placeholder="Password" />
	<form:errors path="password" element="p" class="text-error"/> 
	<button class="btn btn-large btn-primary" type="submit">Sign Up</button>
	<p class="form-text">Already have an account? <a href='<s:url value="/signin"/>'>Sign In</a></p>
</form:form>
