
var listen = function(){
    $("[data-id]").unbind("click");
    $("[data-id]").click(function () {
        var id = $(this).data("id");
        var isNone = ($("[data-hid=" + id + "]").css("display") == "none");

        if (isNone) {
            $("[data-hid=" + id + "]").css("display", "block");
        } else {
            $("[data-hid=" + id + "]").css("display", "none");
        }
    });
}

$(document).ready(function () {
    listen();
});

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
    var name = $("#name").val();
    var namel = name.length;
    var surname = $("#surname").val();
    var surnamel = surname.length;
    var email = $("#email").val();
    var phone = $("#phone").val();
    var dob = $("#birth").val();
    var isValidatedFront = true;

    var nameValid = (namel>=2 && namel<=20);
    var surnameValid = (surnamel>=2 && surnamel<=30);
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
                $("#contacts").append("<div data-id= " + data.contactID + " id=contact>" +
                    "<div id=\"contactID\">" +
                    name + " " + surname +
                    " </div>" +
                    "<div data-hid=\"" + data.contactID + "\" id=\"contactHidden\" style=\"display: none;\"> "  +
                        dob +
                    "</div>" + formT(data.contactID, name, surname, email, phone, dob) +

                    "</div>");
            }
            listen();
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

var formT = function(id, imie, nazwisko, email, telefon, dob){

    var left = "<div id=\"formControl\"><form action=\"editContacts\" method=\"GET\">";
    var c = "<input type=\"hidden\" name=\"contactId\" value=\"" + id +"\"/>";
    var i = "<input type=\"hidden\" name=\"imie\" value=\"" + imie +"\"/>";
    var n ="<input type=\"hidden\" name=\"nazwisko\" value=\"" + nazwisko + "\"/>";
    var e = "<input type=\"hidden\" name=\"email\" value=\"" + email + "\"/>";
    var t ="<input type=\"hidden\" name=\"telefon\" value=\""+ telefon +"\"/>";
    var d = "<input type=\"hidden\" name=\"dob\" value=\""+ dob +"\"/>";
    var right = "<input type=\"submit\" value=\"Edit\">" + "</form>";

    var bl = "<button onclick=\"deleteContact(";
    var br = ")>Delete</button></div>"

    return left+c+i+n+e+t+d+right+bl+id+br;
}

var editFormL = "<div id=\"formControl\"> <form action=\"/editContacts\" method=\"GET\">" +
"<input type=\"hidden\" name=\"contactId\" value=\"${c.getOsobaId()}\"/>" +
"<input type=\"hidden\" name=\"imie\" value=\"${c.getImie()}\"/>" +
"<input type=\"hidden\" name=\"nazwisko\" value=\"${c.getNazwisko()}\"/>" +
"<input type=\"hidden\" name=\"email\" value=\"${c.getEmail()}\"/>" +
"<input type=\"hidden\" name=\"telefon\" value=\"${c.getTelefon()}\"/>" +
"<input type=\"hidden\" name=\"dob\" value=\"${c.getDob()}\"/>" +
"<input type=\"submit\" value=\"Edit\">" +
"</form>"+
"<button onclick=\"deleteContact(";

var editFormR = ")\">Delete</button> </div>";