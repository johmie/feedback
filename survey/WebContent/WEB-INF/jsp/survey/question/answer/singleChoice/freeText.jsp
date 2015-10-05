<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:radiobutton
    path="questions[${statusLoopQuestions.index}].results[0].answer.id"
    value="${answer.id}"/>
<form:input path="questions[${statusLoopQuestions.index}].results[0].freeText" /><br/>