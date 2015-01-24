/**
 * Created by krzysiek on 24.01.15.
 */
function validateForm(event){
    event.preventDefault();
    validate(null);

}

var validate = function(data){
    var username = ($("#username").val()).length;
    var password = ($("#password").val()).length;
    var passwordValue = ($("#password").val());
    var passwordMatch = ($("#password2").val());
    var isValidatedFront = true;
    var isValidatedBack = true;
    var loginValidated = (username<=20 && username>=3)?true:false;
    var passwordValidated = (password<=15 && password>=5)?true:false;
    var passwordMatchValidated = (passwordMatch.length<=20 && passwordMatch.length>=3 && passwordMatch===passwordValue)?true:false;

    if(data===null){
        if(!loginValidated){
            $("#usernameError").html("Login must be 3-20 characters long");
            isValidatedFront = false;
        }else{
            $("#usernameError").html("");
        }
        if(!passwordValidated){
            $("#passwordError").html("Password must be 5-15 characters long");
            isValidatedFront = false;
        }else{
            $("#passwordError").html("");
        }
        if(!passwordMatchValidated){
            $("#passwordError2").html("Passwords must match");
            isValidatedFront = false;
        }else{
            $("#passwordError2").html("");
        }
        if(isValidatedFront){
            validateWithServer();
        }
    }else if(data!==null) {
        if(data.exists){
            $("#usernameError").html("User already exists");
            isValidatedBack = false;
        }
        if(!data.passl){
            $("#usernameError").html("Login must be 3-20 characters long");
            isValidatedBack = false;
        }
        if(!data.loginl){
            $("#passwordError").html("Password must be 5-15 characters long");
            isValidatedBack = false;
        }
        if(isValidatedBack){
            window.location.href = "/login";
        }
    }

}

var validateWithServer = function(){
    $.ajax({
        type: "POST",
        url: "/register",
        data: $("#registerForm").serialize(),
        success: function(data) {
            validate(data);
        },
        error: function(data){
            console.log("fail");
        }
    });
}