<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:radiobutton
    path="questionModels[${question.id}].results[0].answer.id"
    value="${answer.id}"/>
${answer.title}<br/>