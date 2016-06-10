<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<p>
	<h1>${status} Error</h1>
    <div id='created'>${timestamp}</div>
    <div>There was an unexpected error (type=${error}, status=${status}).</div>
    <div>${message}</div>
</p>