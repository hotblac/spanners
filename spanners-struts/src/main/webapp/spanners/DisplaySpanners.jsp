<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Display Spanners</title>
    <link href="<s:url value="/css/examplecss"/>" rel="stylesheet" type="text/css"/>
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

            <tr>
                <td><s:property value="id"/></td>
                <td><s:property value="name"/></td>
                <td><s:property value="size"/></td>
                <td><s:a href="%{spannerDetail}">View</s:a></td>
                <td>***TODO***</td>
            </tr>
        </s:iterator>
    </table>
</body>
</html>
