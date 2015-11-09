<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${answer.valueType == 'CHOICE'}">
        <jsp:include page="multipleChoice/choice.jsp"></jsp:include>
    </c:when>
    <c:when test="${answer.valueType == 'FREE_TEXT'}">
        <jsp:include page="multipleChoice/freeText.jsp"></jsp:include>
    </c:when>
</c:choose>
<br/>