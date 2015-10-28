$(document).ready(function() {
    $('#admin_login').validate({
        rules: {
            email: {
                email: true,
                required: true
            },
            password: {
                required: true
            },
            type: {
                required: true
            }
        },
        errorPlacement: function(label, element) {
            label.insertAfter(element);
        },
        highlight: function(label) {
            $(label).closest('.control-group').addClass('error');
            $(label).closest('.control-group').removeClass('success');
        },
        unhighlight: function(label) {
            $(label).closest('.control-group').addClass('success');
            $(label).closest('.control-group').removeClass('error');
        },
        submitHandler: function() {
            var url = '/admin/login';
            var data = $('#admin_login').serialize();              
             $.ajax({
                data: data,
                url: url,
                success: onSuccess, 
                error: onError,
                type: "POST"
              
            });
            $('.loading_image').show();
            return false;
        }
    });
    var onSuccess = function(data) {
        data = JSON.parse(data);
        onload=validator()
        if (!data.success) {
            alert(data.errorThrown);
        }
        else {            
            window.location.href = data.success_page;
        }
    };
    var onError = function(data) {
        alert("chaap");
    };
});
