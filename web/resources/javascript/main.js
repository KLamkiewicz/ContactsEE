$(document).ready(function () {


    $("[data-id]").click(function () {
        var id = $(this).data("id");

        var isNone = ($("[data-hid=" + id +"]").css("display") == "none");

        if (isNone) {
            $("[data-hid=" + id +"]").css("display", "block");
        } else {
            $("[data-hid=" + id +"]").css("display", "none");
        }
    });

});