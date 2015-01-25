function createForm(){
    $("#newContactFormStart").hide();
    $("#newContactForm").css("display", "block");
}

function deleteContact(id){

    var dt = {id: id };
    console.log(id);

    $.ajax({
        type: 'post',
        url: '/deleteContacts',
        data: dt,
        success: function(data) {
            $("[data-id="+id+"]").remove();
        },
        error: function(data) {
            alert('fail');
        }
    });

}

function validateEmail(email) {
    var re = /\S+@\S+\.\S+/;
    return re.test(email);
}

function validateContact(data){
    var name = $("#name").val().length;
    var surname = $("#surname").val().length;
    var email= $("#email").val();
    var isValidatedFront = true;

    var nameValid = (name>=2 && name<=20);
    var surnameValid = (surname>=2 && surname<=30);
    var emailValid = validateEmail(email);

    if(data===null){
        if(!nameValid){
            $("#nameError").html("Name must be 2-20 characters long");
            isValidatedFront = false;
        }else{
            $("#nameError").html("");
        }
        if(!surnameValid){
            $("#surnameError").html("Surname must be 2-30 characters long");
            isValidatedFront = false;
        }else{
            $("#surnameError").html("");
        }
        if(!emailValid){
            $("#emailError").html("Email not validated");
            isValidatedFront = false;
        }else{
            $("#emailError").html("");
        }
        if(isValidatedFront){
            validateWithServer();
        }

    }else if(data!==null){
        if(data.emailExists){
            $("#emailError").html("Email already exists");
        }else if(!data.emailExists) {
            $("#emailError").html("");

            $("#newContactForm").find('input:text, input:password, input:file, select, textarea').val('');
            $("#newContactForm").css("display", "none");
            $("#newContactFormStart").show();
        }
    }
}

function validateWithServer(){

    $.ajax({
        type: "POST",
        url: "/contacts",
        data: $("#contactForm").serialize(),
        success: function(data) {
            validateContact(data);
        },
        error: function(data){
            console.log("fail");
        }
    });

}

function createContact(e){
    e.preventDefault();
    validateContact(null);
}
