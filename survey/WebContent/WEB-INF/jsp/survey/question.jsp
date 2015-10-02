<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="question">
    <h2>${question.title}</h2>
    <c:forEach items="${question.answers}" var="answer" varStatus="status">
        <c:choose>
            <c:when test="${question.type == 'SINGLE'}">
                <form:radiobutton
                    path="resultSets[${question.id}].results[0].answer.id"
                    value="${answer.id}"/>${answer.title}<br/>
            </c:when>
            <c:when test="${question.type == 'MULTIPLE'}">
                <form:checkbox
                    path="resultSets[${question.id}].results[${status.index}].answer.id"
                    value="${answer.id}"/>${answer.title}<br/>
            </c:when>
        </c:choose>
    </c:forEach>
</div>