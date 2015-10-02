<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="question">
    <h2>${question.title}</h2>
    <c:forEach items="${question.answers}" var="answer" varStatus="statusLoopAnswers">
        <c:choose>
            <c:when test="${question.type == 'SINGLE'}">
                <form:radiobutton
                    path="questionResults[${statusLoopQuestions.index}].results[0].answer.id"
                    value="${answer.id}"/> ${answer.title}<br/>
            </c:when>
            <c:when test="${question.type == 'MULTIPLE'}">
                <form:checkbox
                    path="questionResults[${statusLoopQuestions.index}].results[${statusLoopAnswers.index}].answer.id"
                    value="${answer.id}"/> ${answer.title}<br/>
            </c:when>
            <c:when test="${question.type == 'MATRIX'}">
                MATRIX
            </c:when>
        </c:choose>
    </c:forEach>
</div>