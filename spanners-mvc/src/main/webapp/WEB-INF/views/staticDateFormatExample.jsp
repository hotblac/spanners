<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h1>Static DateFormat example</h1>
<p>This page demonstrates the problems with using static instances of DateFormat / SimpleDateFormat. As these classes are not synchronized, using them in multiple simultaneous sessions can sometimes result in error. These errors will usually not be seen when manually testing the page and are only discovered under load.</p>

<c:if test="${not empty parsedDate}">
    <div class="alert alert-success">
          <p>You entered <fmt:formatDate value="${parsedDate}" type="date"/></p>
    </div>
</c:if>

<form:form modelAttribute="dateForm" class="form">
      <form:label path="dateString">Date (dd/MM/yyyy):</form:label>
      <form:input path="dateString" class="input-block-level"/>
      <input type="submit" class="btn btn-primary" value="Submit" />
</form:form>