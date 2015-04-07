$(document).ready(function() {
     var rules = {
          newName: {
            required: true
          },
          major: {
            required: true,
            minlength: 3
          },
          newPassword: {
            required: true,
            minlength: 6
          },
          email: {
            email: true,
            required: true
          }    
        };
    var messages = {
            newName: "Please enter a username",
            major: "Please enter your major (example: CIS)",
            newPassword: {
                required: "Please enter a password (must be at least 6 characters)",
                minlength: "Password must be at least 6 characters"
            },
            email: "Please enter a valid email"
        };

    //Drop down login box
    $('#login p').click(function() {
        $('#loginForm').slideToggle(300);
        $(this).toggleClass('close');
    });
    
    //Drop down sign up box
    $('#signUp p').click(function() {
        $('#signUpBox').slideToggle(300);
        $(this).toggleClass('close');
    });
    
    //Validate login
    $("#loginBox").validate({
       rules: {
           loginName:{
               required: true
           },
           loginPassword:{
               required: true
           }
       },
       messages: {
           loginName: "Username required for login",
           loginPassword: "Valid password required for login"
       },
       submitHandler: function(form){
            form = $("#loginBox");
            var name = $("#loginName");
            var password = $("#loginPassword");
            var getType = $("#type");
           $.ajax({
               type:"post",
               contentType: "application/json; charset=utf-8",
               url: "CalPolyLive",
               data: { loginName: name, loginPassword: password, type: getType},
               dataType: 'json',
                success: function(data){
                    //form.submit();
                    JSON.parse(data);
                },
                error: function(){
                    $("#loginBox").append("Username/Password Invalid");
                }
            }); 
       } 
    });
    
    //Validate new user input on registration
  $("#newUserForm").validate({
        //onkeyup: true,
        rules: rules,
        messages: messages,
        submitHandler: function(form){
            form = $("#newUserForm");
            var name = $("#newName").val();
            var password = $("#newPassword").val();
            var newEmail = $("#email").val();
            var newMajor = $("#major").val();
            var getType = $("#type");
             $.ajax({
                type:"post",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                url: "CalPolyLive",
               data: { newName: name, newPassword: password, email: newEmail, major: newMajor, type: getType},
               success: function(data){
                    JSON.parse(data);
                },
                error: function(){
                    console.log("error occured");
                }
            }); 
        }
    }); 
    
    //Potential new user button???
    $("#submitNewUser").button().on("click", function(){
        $("#newUserForm").submit();    
    });
    
    //Get user input on log in click
    $("#logIn").button().on("click", function() {
        //event.preventDefault();
        $("#loginBox").submit();
        //helloUser();
        console.log($("#loginBox").valid());
    });

    //Submit a new comment
    $("#submitComment").button().on("click", function(){        
        var comment = $("#newComment").val();
        var getType = $("#type").val();
        if (comment === ''){
            alert("Please enter a comment");
            return false;
        } else {
            $.ajax({
                type:"POST",
                url: "CalPolyLive",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: {type: getType, newComment: comment },
                 success: function(data){
                     //e.preventDefault();
                    //$("#newComment").html(data);
                    JSON.parse(data);
                 },
                 error: function(){
                    console.log("yup error");
                 }

            });
        }
    });
});

