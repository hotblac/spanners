<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<p>Hello <security:authentication property="principal.username" />! Welcome to the Spanners Spring MVC demo application!</p>

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
            <td><a data-toggle="modal" href="${editSpannerUrl}" data-target="#editSpannerModal">Edit</a></td>
            <td><a href="${deleteSpannerUrl}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<a href="<c:url value="/addSpanner"/>">Create new spanner</a>

<div class="modal fade" id="editSpannerModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Edit spanner</h4>
      </div>
      <div class="modal-body">
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
