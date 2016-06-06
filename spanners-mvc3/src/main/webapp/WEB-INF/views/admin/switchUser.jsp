<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Display an error message if we've returned to this page after an attempt to switch user --%>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <div class="alert alert-danger fade in">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        Cannot switch to user: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION}"/>
    </div>
</c:if>

<form method="GET" action="<c:url value="/admin/impersonate"/>" class="form">
    <label for="usernameField">User name:</label>
    <input type="text" name="username" id="usernameField" class="input-block-level"/>
    <a href="<c:url value="/displaySpanners"/>" class="btn btn-default">Cancel</a>
    <input type="submit" class="btn btn-primary" value="Switch User" />
</form>
