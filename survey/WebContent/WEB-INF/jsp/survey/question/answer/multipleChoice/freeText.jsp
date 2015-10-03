<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:checkbox
    path="resultLists[${statusLoopQuestions.index}].results[${statusLoopAnswers.index}].answer.id"
    value="${answer.id}"/>
<form:input path="resultLists[${statusLoopQuestions.index}].results[${statusLoopAnswers.index}].freeText" /><br/>