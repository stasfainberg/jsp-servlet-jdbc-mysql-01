<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
	<title>User Management Application</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
</head>
<body>
	
      
      
	<header>
	    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
	        <div>
	            <a href="https://www.stasfainberg.com" class="navbar-brand"> User Management App </a>
	        </div>
	
	        <ul class="navbar-nav">
	            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                <li><a href="<%=request.getContextPath()%>/" class="nav-link">Back</a></li>
	        </ul>
	    </nav>
	</header>      
	
	
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Users</h3>
	        <hr>	
			
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
			</div>
			<br>
			
			<table class="table table-bordered">
				<thead>
					<tr>
	                    <th>ID</th>
	                    <th>Name</th>
	                    <th>Email</th>
	                    <th>Country</th>
	                    <th>Actions</th>					
					</tr>
				</thead>
					<c:forEach var="user" items="${listUser}">
						<tr>
							<td>
								<c:out value="${user.id}"></c:out>
							</td>
							<td>
                                <c:out value="${user.name}" />
                            </td>
                            <td>
                                <c:out value="${user.email}" />
                            </td>
                            <td>
                                <c:out value="${user.country}" />
                            </td>
                            <td>
                            	<a href="edit?id=<c:out value='${user.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; 
                            	<a href="delete?id=<c:out value='${user.id}' />">Delete</a>
                            </td>
						</tr>
					</c:forEach>
				<tbody>
				</tbody>
			</table>
			
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/" class="btn btn-danger">Get me Back</a>
			</div>
		</div>
		
		
			
		
	</div>
	
	
	
	
</body>
</html>