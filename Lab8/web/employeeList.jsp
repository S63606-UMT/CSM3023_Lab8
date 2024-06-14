<%-- 
    Document   : EmployeeList
    Created on : 14 Jun 2024, 11:07:22 am
    Author     : saifu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Employee Management Application</title>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                <div>
                    <a href="" class="navbar-brand"> Employee Management App </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Employees</a></li>
                </ul>
            </nav>
        </header>
        <br> 
        <div class="row">
            
            <div class="container">
                <h3 class="text-center">List of Employees</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Employee</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Position</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="employee" items="${listEmployee}">
                            <tr>
                                <td>
                                    <c:out value="${employee.id}" />
                                </td>
                                <td>
                                    <c:out value="${employee.name}" />
                                </td>
                                <td>
                                    <c:out value="${employee.email}" />
                                </td>
                                <td>
                                    <c:out value="${employee.position}" />
                                </td>
                                <td>
                                    <a href="edit?id=<c:out value='${employee.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="delete?id=<c:out value='${employee.id}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
