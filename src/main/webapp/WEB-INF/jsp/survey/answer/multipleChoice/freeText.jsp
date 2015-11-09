<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:checkbox
        path="questionModels[${question.id}].results[${statusLoopAnswers.index}].answer.id"
        value="${answer.id}"/>
<form:input path="questionModels[${question.id}].results[${statusLoopAnswers.index}].freeText"
            class="form-control"/>