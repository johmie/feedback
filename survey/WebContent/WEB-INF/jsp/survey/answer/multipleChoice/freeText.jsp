<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:checkbox
    path="questions[${statusLoopQuestions.index}].results[${statusLoopAnswers.index}].answer.id"
    value="${answer.id}"/>
<form:input path="questions[${statusLoopQuestions.index}].results[${statusLoopAnswers.index}].freeText" /><br/>