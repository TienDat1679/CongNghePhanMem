<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Form</title>
</head>
<body>
    <h2>Employee Form</h2>

    <!-- Check if the employee has an ID (existing employee for update) -->
    <form action="<c:choose>
                     <c:when test='${employee.id != null}'>
                         update
                     </c:when>
                     <c:otherwise>
                         insert
                     </c:otherwise>
                  </c:choose>" 
          method="post">

        <!-- Hidden ID field -->
        <input type="hidden" name="id" value="${employee.id}"/>

        <!-- Employee information fields -->
        Name: <input type="text" name="name" value="${employee.name}"/><br/>
        Gender: <input type="text" name="gender" value="${employee.gender}"/><br/>
        Email: <input type="email" name="email" value="${employee.email}"/><br/>

        <input type="submit" value="Save"/>
    </form>
</body>
</html>

