var data_autocomplete;
$(document).ready(function() {
    $('#subject_selection').validate({
        rules: {
            subject: {
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
        }
    });
    $('#subject').focusout(function() {
        var sub = $(this).val();
        var url = '/student/subject_selection/';
        data = {sub: sub};
        $.ajax(url, {
            data: data,
            type: "POST",
            success: onSuccess_select_subject,
            error: onError_select_subject
        });
        return false;
    });

    var onSuccess_select_subject = function(data) {
        data_autocomplete = JSON.parse(data);
        if (data_autocomplete)
            autocomplete_topic(data_autocomplete);
        else {
            alert("There are no topics in this subject");
            window.location.reload();
        }

    };
    var onError_select_subject = function(data) {
        alert("opi");
    };
    var autocomplete_topic = function(data_autocomplete) {
        $("#topic").autocomplete({
            source: data_autocomplete,
            minLength: 1,
            open: function(event, ui) {
                $(".ui-autocomplete").css("z-index", 1000);
            }
        });
    };
    $("#topic").focusout(function() {
        var flag = 0;
        var topic = $(this).val();
	if(topic!=''){
            for (var key in data_autocomplete) {
                if (data_autocomplete[key].label === topic) {
                    flag = 1;
                }
            }
            if (flag === 0) {
                alert("There is no material for this topic");
                window.location.reload();
            }
        }
    });
});
