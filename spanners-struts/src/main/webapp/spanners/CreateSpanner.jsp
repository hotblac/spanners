<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Create Spanner</title>
</head>

<body>
    <h1>Create Spanner</h1>
    <s:if test="%{message != null}">

        <div class="message">
            <!-- This is XSS safe -->
            <s:property value="message"/>
            <br/>

            <!-- This is not XSS safe
            <s:property value="message" escape="false"/>
            <br/>
            -->

            <!-- This is not XSS safe
            <%= request.getAttribute("message") %>
            <br/>
            -->
        </div>
    </s:if>

    <s:form action="createSpanner">
        <s:textfield name="spanner.name" label="Name"/>
        <s:textfield name="spanner.size" label="Size"/>
        <s:submit/>
    </s:form>

    <p><a href="<s:url action="displaySpanners"/>">Back</a></p>

</body>
</html>
