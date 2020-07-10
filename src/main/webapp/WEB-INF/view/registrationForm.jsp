<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<!-- Style -->
			<style>
			.login-form {
			    width: 340px;
			    margin: 50px auto;
			  	font-size: 15px;
			}
			.login-form form {
			    margin-bottom: 15px;
			    background: #f7f7f7;
			    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
			    padding: 30px;
			}
			.login-form h2 {
			    margin: 0 0 15px;
			}
			.form-control, .btn {
			    min-height: 38px;
			    border-radius: 2px;
			}
			.btn {        
			    font-size: 15px;
			    font-weight: bold;
			}
			</style>
			
<title>Login</title>
</head>
<body>
	<br>
	<div class="container">
	
		<div class="text-center text-warning">
			<!-- Check for registration error -->
				<c:if test="${registrationError != null}">
					<div class="alert alert-danger col-xs-offset-1 col-xs-10">
						${registrationError}
					</div>
				</c:if>
		</div>
		
		<!-- Registration Form -->
		<div class="login-form">
		    <form:form action="${pageContext.request.contextPath}/register/processRegistrationForm" method="POST" modelAttribute="crmUser">
		        <h2 class="text-center">Log In</h2>       
		        <div class="form-group">
		        	<span class="form-group-addon glyphicon glyphicon-user"><i class="glyphicon glyphicon-user"></i></span>
		            <input type="text" class="form-control" placeholder="Username" name="username" required="required">
		        </div>
		        <div class="form-group">
		        	<span class="form-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
		            <input type="password" class="form-control" placeholder="Password" name="password" required="required">
		        </div>
		        <div class="form-group">
		            <button type="submit" class="btn btn-primary btn-block">Register</button>
		            <a href="${pageContext.request.contextPath}/" class="btn btn-link btn-block">Log In</a>
		        </div>
		                
		    </form:form>
		    
		</div>
	</div>	

</body>
</html>