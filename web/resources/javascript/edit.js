function validateEdit(e){
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "/editContacts",
        data: $("#editContactForm").serialize(),
        success: function(data) {
            updated(data);
        },
        error: function(data){
            console.log("fail");
        }
    });

}


function updated(up){
    if(up.updated){
        window.location.href = "/contacts";
    }else{
        alert("Email already exists");
    }
}