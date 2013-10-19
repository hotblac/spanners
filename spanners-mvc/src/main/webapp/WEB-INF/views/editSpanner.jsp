<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<p>

    <h1>Edit Spanner</h1>

    <form:form modelAttribute="spanner">
        <form:hidden path="owner"/>
        <form:label path="name">Spanner name:</form:label>
        <form:input path="name" class="input-block-level"/>
        <form:label path="size">Spanner size:</form:label>
        <form:input path="size" class="input-block-level"/>
        <input type="submit" value="Submit" />
    </form:form>

     <p><a href="<c:url value="/displaySpanners"/>">Back</a></p>

</p>