$(document).ready(function(){
    $("#other_reg").hide();
    $('.bool-slider .inset .control').click(function() {
        if (!$(this).parent().parent().hasClass('enabled')) {
            if ($(this).parent().parent().hasClass('true')) {
                 $("#student_registration").hide();
                 $("#other_reg").show();                 
                 $(this).parent().parent().addClass('false').removeClass('true');
            } else {
                $("#other_reg").hide();
                $("#student_registration").show();
                $(this).parent().parent().addClass('true').removeClass('false');
            }
        }
    });
    $("#email").focusout(function() {
        var email = $("#email").val();
        data = {
            email: email
        };
        var url = "/eguru/user_valid";
        $.ajax(url, {
            data: data,
            type: "POST",
            success: onSuccess,
            error: onError
        });
    });
    return(false);
});
var onError = function(data) {
    alert(data);
    alert("failed");
};
var onSuccess = function(data)
{
    if (data == 0)
        alert("this Email already exists.. try another");
};


