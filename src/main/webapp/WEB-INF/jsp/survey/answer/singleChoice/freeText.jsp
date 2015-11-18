<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="errors">
    <form:errors path="questionModels[${question.id}].results[0]"/>
</c:set>
<span
        <c:if test="${not empty errors}">
            class="has-error"
        </c:if>
        >
<form:radiobutton
        path="questionModels[${question.id}].results[0].answer.id"
        value="${answer.id}"/>
<form:input
        path="questionModels[${question.id}].results[0].freeText"
        class="form-control"/>
<c:if test="${not empty errors}">
    <span class="control-label" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        ${errors}
    </span>
</c:if>
</span>