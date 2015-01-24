/**
 * Created by krzysiek on 24.01.15.
 */
function validateForm(event){

    event.preventDefault();
    var username = ($("#username").val()).length;
    var usernameValidated = (username<3 || username>20)?false:true;

    if(username<3 || username>20){
        console.log("Too small " + ($("#username").val()).length);
        $("#usernameError").html("Must be >3 and <20");
    }else{
        $("#usernameError").html("");
    }

    checkIfUserExists();
}
var validate = function(data){
    console.log(data);
}

var checkIfUserExists = function(){
    $.ajax({
        type: "POST",
        url: "/login",
        data: $("#loginForm").serialize(),
        success: function(data) {
            validate(data.exists);
        },
        error: function(data){
            console.log("fail");
        }
    });

}