<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Display Spanners</title>
    <link href="<s:url value="/styles/simple.css"/>" rel="stylesheet" type="text/css"/>
</head>

<body>
    <h1>Display Spanners</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Size</th>
            <th>View</th>
            <th>Remove</th>
        </tr>
        <s:iterator value="spanners" var="spanner">

            <s:url var="spannerDetail" action="spannerDetail">
                <s:param name="id" value="id"/>
            </s:url>
            <s:url var="deleteSpanner" action="deleteSpanner">
                <s:param name="id" value="id"/>
            </s:url>

            <tr>
                <td><s:property value="id"/></td>
                <td><s:property value="name"/></td>
                <td><s:property value="size"/></td>
                <td><s:a href="%{spannerDetail}">View</s:a></td>
                <td><s:a href="%{deleteSpanner}">Delete</s:a></td>
            </tr>
        </s:iterator>
    </table>

    <p><a href="<s:url action="createSpanner"/>">Create spanner</a></p>
</body>
</html>
