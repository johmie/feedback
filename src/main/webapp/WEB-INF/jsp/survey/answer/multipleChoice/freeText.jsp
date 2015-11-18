<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="errors">
    <form:errors path="questionModels[${question.id}].results[${statusLoopAnswers.index}]"/>
</c:set>
<span
        <c:if test="${not empty errors}">
            class="has-error"
        </c:if>
        >
<form:checkbox
        path="questionModels[${question.id}].results[${statusLoopAnswers.index}].answer.id"
        value="${answer.id}"/>
<form:input
        path="questionModels[${question.id}].results[${statusLoopAnswers.index}].freeText"
        class="form-control"/>
<c:if test="${not empty errors}">
    <span class="control-label" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        ${errors}
    </span>
</c:if>
</span>