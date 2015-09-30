<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
Page title: ${page.title}
<c:forEach var="question" items="${page.questions}">
${question.title}
</c:forEach>