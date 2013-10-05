<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<p>
	Hello <security:authentication property="principal.username" />! Welcome to the Spanners Spring MVC demo application!

	<table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Size</th>
            <th>Owner</th>
            <th>View</th>
            <th>Edit</th>
            <th>Remove</th>
        </tr>
        <c:forEach items="${spanners}" var="spanner">
            <tr>
                <td><c:out value="${spanner.id}"/></td>
                <td><c:out value="${spanner.name}"/></td>
                <td><c:out value="${spanner.size}"/></td>
                <td><c:out value="${spanner.owner}"/></td>
                <td>TODO</td>
                <td>TODO</td>
                <td>TODO</td>
            </tr>
        </c:forEach>
    </table>

</p>