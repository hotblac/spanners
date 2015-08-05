<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:out value="${SPRING_SECURITY_LAST_EXCEPTION}"/>

<form method="GET" action="<c:url value="/admin/impersonate"/>" class="form">
    <label for="usernameField">User name:</label>
    <input type="text" name="username" id="usernameField" class="input-block-level"/>
    <a href="<c:url value="/displaySpanners"/>" class="btn btn-default">Cancel</a>
    <input type="submit" class="btn btn-primary" value="Switch User" />
</form>
