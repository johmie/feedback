<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:choose>
    <c:when test="${answer.type == 'PREDEFINED'}">
        <form:checkbox
            path="resultLists[${statusLoopQuestions.index}].results[${statusLoopAnswers.index}].answer.id"
            value="${answer.id}"/>
        ${answer.title}<br/>
    </c:when>
    <c:when test="${answer.type == 'FREE_TEXT'}">
        <form:checkbox
            path="resultLists[${statusLoopQuestions.index}].results[${statusLoopAnswers.index}].answer.id"
            value="${answer.id}"/>
        <form:input path="resultLists[${statusLoopQuestions.index}].results[${statusLoopAnswers.index}].freeText" /><br/>
    </c:when>
</c:choose>