<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Spanner detail</title>
    <link href="<s:url value="/styles/simple.css"/>" rel="stylesheet" type="text/css"/>
</head>

<body>
    <h1>Spanner</h1>
    <p>Spanner id: <s:property value="spanner.id"/></p>
    <p>Spanner name: <s:property value="spanner.name"/></p>
    <p>Spanner size: <s:property value="spanner.size"/></p>

    <p><a href="<s:url action="displaySpanners"/>">Back</a></p>
</body>
</html>
