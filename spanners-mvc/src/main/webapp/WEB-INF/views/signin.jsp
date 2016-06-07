<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<form class="form" action='<s:url value="/login"/>' method="post">
	<h2 class="form-heading">Please Sign In</h2>
	<c:if test="${not empty param['error']}">
  		<div class="alert alert-error">
  			Sign in error. Please try again.
  		</div>
  	</c:if>
	<input type="text" class="input-block-level" placeholder="User name" name="username" />
	<input type="password" class="input-block-level" placeholder="Password" name="password" />
	<button class="btn btn-large btn-primary" type="submit">Sign In</button>
</form>
