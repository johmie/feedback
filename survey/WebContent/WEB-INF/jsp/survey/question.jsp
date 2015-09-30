<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="question">
    <h2>${question.title}</h2>
    <c:forEach var="answer" items="${question.answers}">
        <!--form:checkbox path="comingSoon" value="${answer.value}" /-->${answer.title}<br/>
    </c:forEach>
</div>