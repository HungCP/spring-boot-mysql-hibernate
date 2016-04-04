<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 04/04/2016
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/_header.jsp" />

<body>

<div class="container">

  <h1>Error Page</h1>

  <p>${exception.message}</p>
  <!-- Exception: ${exception.message}.
		  	<c:forEach items="${exception.stackTrace}" var="stackTrace">
				${stackTrace}
			</c:forEach>
	  	-->

</div>

<jsp:include page="fragments/_footer.jsp" />

</body>
</html>
