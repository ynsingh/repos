
$(document).ready(function() {
    $("#upload").hide();
    $("#upload_call").click(function() {
        $("#upload").show();
    });
    $('#material_edit').validate({
        rules: {
            hardness: {
                required: true
            },
            type: {
                required: true
            },
            topic: {
                required: true
            },
            user_file: {
                required: true
            },
            link: {
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
            var url = '/admin/material_for_edit_submit/';
            var options = {
                url: url,
                success: onSuccess_material_submit,
                error: onError_material_submit,
                type: "POST"
            };
            $('.loading_image').show();
            $('#material_edit').ajaxSubmit(options);
            return false;
        }
    });
    autocomplete_topic(data_autocomplete);
});
var autocomplete_topic = function(data_autocomplete) {
    $("#topic").autocomplete({
        source: data_autocomplete,
        minLength: 1,
        open: function(event, ui) {
            $(".ui-autocomplete").css("z-index", 1000);
        }
    });
};
var onSuccess_material_submit = function(material_data) {
    obj = JSON.parse(material_data);
    if (obj.response) {
        alert("Successfully Uploaded");
        window.location.href = "/admin/profile_subject_hod";
    }
};
var onError_material_submit = function(data) {
    alert("chaap");
};
