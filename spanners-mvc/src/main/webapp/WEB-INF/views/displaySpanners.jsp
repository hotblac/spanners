<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="username">
    <security:authentication property="principal.username" />
</c:set>

<p>Hello <c:out value="${principal.username}" />! Welcome to the Spanners Spring MVC demo application!</p>

<table class="table table-striped table-bordered table-condensed">
    <tr>
        <th>Name</th>
        <th>Size</th>
        <th>Owner</th>
        <th>View</th>
        <th>Edit</th>
        <th>Remove</th>
    </tr>
    <c:forEach items="${spanners}" var="spanner">

        <c:url value="/detailSpanner" var="detailSpannerUrl">
            <c:param  name="id" value="${spanner.id}"/>
        </c:url>
        <c:url value="/editSpanner" var="editSpannerUrl">
            <c:param  name="id" value="${spanner.id}"/>
        </c:url>
        <c:url value="/deleteSpanner" var="deleteSpannerUrl">
            <c:param  name="id" value="${spanner.id}"/>
        </c:url>
        <tr>
            <td><c:out value="${spanner.name}"/></td>
            <td><c:out value="${spanner.size}"/></td>
            <td><c:out value="${spanner.owner}"/></td>
            <td><a href="${detailSpannerUrl}">View</a></td>
            <c:choose>
                <c:when test="${spanner.owner eq username}">
                    <td><a href="${editSpannerUrl}">Edit</a></td>
                    <td><a href="${deleteSpannerUrl}">Delete</a></td>
                </c:when>
                <c:otherwise>
                    <td>Edit</td>
                    <td>Delete</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>

<a class="btn btn-primary" href="<c:url value="/addSpanner"/>">Create new spanner</a>

