function validateEdit(e){
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "/editContacts",
        data: $("#editContactForm").serialize(),
        success: function(data) {
            console.log(data);
        },
        error: function(data){
            console.log("fail");
        }
    });

}



function validateEmail(email){
    var re = /\S+@\S+\.\S+/;
    return re.test(email);
}

function validatePhone(phone){
    var re = /[0-9]{9}/;
    return re.test(phone);
}

function validateBirth(dob){
    var re = /^(\d{4})-(\d{2})-(\d{2})$/;
    return re.test(dob);
}

function validateContact(data){
    var name = $("#name").val().length;
    var surname = $("#surname").val().length;
    var email = $("#email").val();
    var phone = $("#phone").val();
    var dob = $("#birth").val();
    var isValidatedFront = true;

    var nameValid = (name>=2 && name<=20);
    var surnameValid = (surname>=2 && surname<=30);
    var emailValid = validateEmail(email);
    var phoneValid = (phone.length===10)?validatePhone(phone):false;
    var dobValid = (dob.length===10)?validateBirth(dob):false;

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
            $("#emailError").html("Email format must match name@name.name");
            isValidatedFront = false;
        }else{
            $("#emailError").html("");
        }
        if(!phoneValid){
            $("#phoneError").html("Phone number length must be equal to 10");
            isValidatedFront = false;
        }else{
            $("#phoneError").html("");
        }
        if(!dobValid){
            $("#birthError").html("Date format must match yyyy-mm-dd");
            isValidatedFront = false;
        }else{
            $("#birthError").html("");
        }

        if(isValidatedFront){
            validateWithServer();
        }

    }else if(data!==null){
        if(data.emailExists){
            $("#emailError").html("Email already exists");
        }else if(!data.emailExists) {
            $("#emailError").html("");

            if(data.succ===0){
                alert("Unexpected error occured");
            }else{

            }

        }
    }
}



function validateWithServer(){

    $.ajax({
        type: "POST",
        url: "/editContacts",
        data: $("#contactForm").serialize(),
        success: function(data) {
            validateContact(data);
        },
        error: function(data){
            console.log("fail");
        }
    });

}