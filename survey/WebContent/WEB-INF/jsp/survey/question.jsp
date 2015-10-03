<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="question">
    <h2>${question.title}</h2>
    <c:forEach items="${question.answers}" var="answer" varStatus="statusLoopAnswers">
        <c:set var="answer" value="${answer}" scope="request"/>
        <c:set var="statusLoopAnswers" value="${statusLoopAnswers}" scope="request"/>
        <c:choose>
            <c:when test="${question.type == 'SINGLE_CHOICE'}">
                <jsp:include page="question/singleChoice.jsp"></jsp:include>
            </c:when>
            <c:when test="${question.type == 'MULTIPLE_CHOICE'}">
                <jsp:include page="question/multipleChoice.jsp"></jsp:include>
            </c:when>
            <c:when test="${question.type == 'MATRIX'}">
                MATRIX
            </c:when>
        </c:choose>
    </c:forEach>
</div>