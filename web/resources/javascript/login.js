function validateForm(event){
    event.preventDefault();
    validate(null);
}


var validate = function(data){
    var username = ($("#username").val()).length;
    var password = ($("#password").val()).length;
    var isValidatedFront = true;
    var isValidatedBack = true;
    var loginValidated = (username<=20 && username>=3)?true:false;
    var passwordValidated = (password<=15 && password>=5)?true:false;

    if(data===null) {
        $("#authError").html("");
        if (!loginValidated) {
            $("#usernameError").html("Login must be 3-20 characters long");
            isValidatedFront = false;
        } else {
            $("#usernameError").html("");
        }
        if (!passwordValidated) {
            $("#passwordError").html("Password must be 5-15 characters long");
            isValidatedFront = false;
        } else {
            $("#passwordError").html("");
        }
        if(isValidatedFront){
            validateWithServer();
        }
    }else if(data!==null){
        if(!data.authenticated){
            $("#authError").html("No matching combination for this user and password");
            isValidatedBack = false;
        }
        if(isValidatedBack){
            window.location.href = "/";
        }
    }
    console.log(data);
}

var validateWithServer = function(){
    $.ajax({
        type: "POST",
        url: "/login",
        data: $("#loginForm").serialize(),
        success: function(data) {
            validate(data);
        },
        error: function(data){
            console.log("fail");
        }
    });
}