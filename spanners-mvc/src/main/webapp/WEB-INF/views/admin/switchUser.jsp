<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute="switchUserForm" class="form">
    <form:label path="userName">User name:</form:label>
    <form:input path="userName" class="input-block-level"/>
    <form:errors path="userName" element="p" class="text-error"/>
    <a href="<c:url value="/displaySpanners"/>" class="btn btn-default">Cancel</a>
    <input type="submit" class="btn btn-primary" value="Switch User" />
</form:form>
