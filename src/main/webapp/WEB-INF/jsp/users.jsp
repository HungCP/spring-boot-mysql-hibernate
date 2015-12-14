<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">

    <style>
      table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
      }
      th, td {
        padding: 5px;
        text-align: left;
      }
    </style>

    <title>List of Users</title>
  </head>

  <body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/user/create">Create a new user</a></li>
      </ul>
    </nav>

    <h1>List of Users</h1>

    <table style="width:45%">
      <thead>
      <tr>
        <th>E-mail</th>
        <th>Role</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${users}" var="user" varStatus="status">
        <tr>
          <td><a href="/user/${user.id}">${user.email}</a></td>
          <td>${user.role.text}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

  </body>
</html>