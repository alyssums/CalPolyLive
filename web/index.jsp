<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Cal Poly Live</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <link rel="stylesheet" href="styles/jquery.css" type="text/css"/>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/form.js"></script>
    </head>
    <body>
        <header><a href="#">Cal Poly Live</a></header>
        <nav id = "mainNav">
            <nav id="currentUser"></nav>
            <nav id="login">
                <p>Log In</p>
                <div id="loginForm">
                    <form id="loginBox" method="post">
                        <label for="loginName">Username:</label>
                        <input type="text" name="loginName" id="loginName" class="text ui-widget-content ui-corner-all" />
                        
                        <label for="loginPassword">Password:</label>
                        <input type="password" name="loginPassword" id="loginPassword" class="text ui-widget-content ui-corner-all"/>
                         
                        <button id="logIn" type="submit">Log In</button>
                        
                        <input type="hidden" name="type" id="type" value="login" />
                    </form>
                </div>
            </nav>
            <nav id ="signUp">
                <p>Sign Up</p>
                <div id="signUpBox">
                    <form id ="newUserForm" method="post">
                        <label for ="newName">Username</label>
                        <input type="text" name="newName" id="newName" class="text ui-widget-content ui-corner-all">
                        <label for="email">Email</label>
                        <input type="email" name="email" id="email" class="text ui-widget-content ui-corner-all">
                        <label for="major">Major:</label>
                        <input type="text" name="major" id="major" class="text ui-widget-content ui-corner-all"/>      
                        <label for="newPassword">Password</label>
                        <input type="password" name="newPassword" id="newPassword" class="text ui-widget-content ui-corner-all">
                        <button id="submitNewUser">Submit</button>
                        <input type="hidden" name="type" id="type" value="account" /> 
                    </form>
                </div>
            </nav>
        </nav>
        <div id="commentError"></div>
        <div id="enterNewComment">
            <form id ="comment" method="post">
                <textarea id="newComment" name="newComment" onfocus="this.value='';">Type your comment here</textarea>
                <button id="submitComment">Submit</button>
                
                <input type="hidden" name="type" id="type" value="comment" />
            </form>
            <!--<button id="clearComment">Clear</button>-->
        </div>
        
        <div id="allComments">
            <c:forEach items="${previousComments}" varStatus="loop">
                <div class="commentBox">
                    ${previousComments[loop.index][2]}
                    <span id="userInfo">
                        -${previousComments[loop.index][1]}, ${previousComments[loop.index][3]}
                        <p id="timeStamp">
                            ${previousComments[loop.index][0]}
                        </p>
                    </span>
                </div>
            </c:forEach>
        </div>       
<script>
<% if (request.getAttribute("validCredentials") != null && request.getAttribute("validCredentials").toString() == "true"){ 
   // Object name = session.getAttribute("userName");
%>
        var currentUser = "<%=session.getAttribute("userName").toString() %>";
        $("#signUp").hide();
        $("#login p").empty().append("Log out");
        if (currentUser !== "") {
            $("#currentUser").empty().append("Welcome, " + currentUser + "!");
            $("#loginForm").empty().append(currentUser + " is signed in.");
            $("#loginForm").append("<form method='post'><button type='submit' id='signOut' style='margin-top:5px;'>Sign Out</button><input type='hidden' id='type' name='type' value='logout'/></form>");
                $("#signOut").button().on("click", function(){
                    //location.reload();
                });
            //Close login box
            $(this).toggleClass('close');
        } else if (currentUser === ""){
            $("#currentUser").empty();
        }
   <%
   }
%>  
<% if (session.getAttribute("userName") != null){ 
   // Object name = session.getAttribute("userName");
%>
    var currentUser = "<%=session.getAttribute("userName").toString() %>";
    $("#signUp").hide();
    $("#login p").empty().append("Log out");
    $("#currentUser").empty().append("Welcome, " + currentUser + "!");
    $("#loginForm").empty().append(currentUser + " is signed in.");
    $("#loginForm").append("<form method='post'><button type='submit' id='signOut' style='margin-top:5px;'>Sign Out</button><input type='hidden' id='type' name='type' value='logout'/></form>");
    $("#signOut").button().on("click", function(){
        location.reload();
    });
<%
   }
%> 
    </script>
    </body>
</html>

