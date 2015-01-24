function deleteContact(id){

    var dt = {operation: "del", id: id };
    console.log(id);

    $.ajax({
        type: 'post',
        url: '/contacts',
        data: dt,
        success: function(data) {
            $("[data-id="+id+"]").remove();
        },
        error: function(data) {
            alert('fail');
        }
    });

}

function createForm(){
    $("#newContactFormStart").hide();
    $("#newContactForm").css("display", "block");
}

function createContact(e){
    e.preventDefault();

    $("#newContactForm").css("display", "none");
    $("#newContactFormStart").show();

}
