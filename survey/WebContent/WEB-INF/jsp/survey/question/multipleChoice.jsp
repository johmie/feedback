<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
    <c:when test="${answer.valueType == 'PREDEFINED'}">
        <jsp:include page="answer/multipleChoice/predefined.jsp"></jsp:include>
    </c:when>
    <c:when test="${answer.valueType == 'FREE_TEXT'}">
        <jsp:include page="answer/multipleChoice/freeText.jsp"></jsp:include>
    </c:when>
</c:choose>