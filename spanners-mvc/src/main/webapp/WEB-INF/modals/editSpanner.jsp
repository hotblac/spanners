<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute="spanner" id="editSpannerForm">
    <form:label path="name">Spanner name:</form:label>
    <form:input path="name" class="input-block-level"/>
    <form:label path="size">Spanner size:</form:label>
    <form:input path="size" class="input-block-level"/>

    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <input type="submit" class="btn btn-primary" value="Save changes" />
</form:form>
