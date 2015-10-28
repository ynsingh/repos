
$(document).ready(function() {
    $(".material_change").click(function() {
        var material_type = $(this).attr('value');
        if (material_type === 'descriptive') {
	    $(".type").show();
            $(".video").hide();
            $(".ppt").hide();
        }
        else if (material_type === 'video') {
	    $(".type").show();
            $(".ppt").hide();
            $(".descriptive").hide();
        }
        else {
	    $(".type").show();
            $(".video").hide();
            $(".descriptive").hide();
        }
    });
    $('#material_submit').validate({
        rules: {
            hardness: {
                required: true
            },
            type: {
                required: true
            },
            topic: {
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
            var url = '/admin/material_upload_subject_hod/';
            var options = {
                url: url,
                success: onSuccess_material_submit,
                error: onError_material_submit,
                type: "POST"
            };
            $('.loading_image').show();
            $('#material_submit').ajaxSubmit(options);
            return false;
        }
    });
    $(".delete").click(function() {
        var id = $(this).attr('id');
        data = {id: id
        };
        var url = "/admin/delete_material/";
        $.ajax(url, {
            data: data,
            type: "POST",
            success: onSuccess_delete,
            error: onError_delete
        });
        return(false);
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
var onSuccess_material_submit = function(data) {
        data = JSON.parse(data);
        if (!data.success) {
            alert(data.errorThrown);
        }
        else {
            alert(data.successMsg);
            window.location.reload();
        }
    };
    var onError_material_submit = function(data) {
        alert("Something went wrong");
    };
    var onSuccess_delete = function(material_data) {
        obj = JSON.parse(material_data);
        if (obj.response)
            window.location.reload();
    };
    var onError_delete = function(data) {

        alert(data);
    };
