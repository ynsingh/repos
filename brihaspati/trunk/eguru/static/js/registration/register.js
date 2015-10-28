$(document).ready(function() {
    $("#according_apply_for").hide();
    $("#subject").hide();
    $('#proceed').click(function() {
        var apply_for_value = $("#apply_for").val();
        var department = $("#department_for").val();
        var url = "/registration/all_subject_extract";
        var data = {department:department};
        if (apply_for_value !== '') {
            if (apply_for_value === 'sub_hod') {
                $("#subject").show();
            }
            if (department === '') {
                alert("Please fill your Subject or Department");
            }
            else {
                $("#according_apply_for").show();
                $("#proceed").hide();
                $.ajax({
                    data: data,
                    url: url,
                    success: onSuccess_subject_extract,
                    error: onError_subject_extract,
                    type: "POST"
                });
                $('.loading_image').show();
                return false;
            }
        }
        else
            alert("Please fill Apply for");

    });
    $('#others_registration').validate({
        rules: {
            f_name: {
                maxlength: 100,
                required: true
            },
            l_name: {
                maxlength: 100,
                required: true
            },
            email: {
                email: true,
                required: true
            },
            pass: {
                minlength: 6,
                required: true
            },
            conf_pass: {
                required: true,
                equalTo: '#pass_others'
            },
            mobile: {
                maxlength: 10,
                minlength: 10,
                number: true,
                required: true
            },
            designation: {
                maxlength: 100,
                required: true
            },
            apply_for: {
                required: true
            },
            subject: {
                required: true
            },
            research_papers_publication: {
                required: true
            },
            research_projects: {
                required: true
            },
            post_dectoral: {
                required: true
            },
            participation: {
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
            var url = '/registration/others_submit';
            var data = $('#others_registration').serialize();
            $.ajax({
                data: data,
                url: url,
                success: onSuccess_others,
                error: onError_others,
                type: "POST"
            });
            $('.loading_image').show();
            return false;
        }
    });
    $('#student_registration').validate({
        rules: {
            f_name: {
                maxlength: 100,
                required: true
            },
            l_name: {
                maxlength: 100,
                required: true
            },
            email: {
                email: true,
                required: true
            },
            pass: {
                minlength: 6,
                required: true
            },
            conf_pass: {
                required: true,
                equalTo: '#pass'
            },
            mobile: {
                number: true,
                maxlength: 10,
                minlength: 10,
                required: true
            },
            institute: {
                maxlength: 30,
                minlength: 6,
                required: true
            },
            high_qualify: {
                maxlength: 20,
                minlength: 3,
                required: true
            },
            specialisation: {
                maxlength: 20,
                minlength: 3,
                required: true
            },
            stream: {
                maxlength: 20,
                minlength: 2,
                required: true
            },
            passing_year: {
                maxlength: 10,
                minlength: 1,
                number: true,
                required: true
            }
        },
        errorPlacement: function(label, element) {
            if (element.attr("name") == "mobile" || element.attr("name") == "country_code") {
                label.insertAfter("#registration-phone-primary");
            }
            else
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
            var url = '/registration/student_submit';
            var data = $('#student_registration').serialize();
            $.ajax({
                data: data,
                url: url,
                success: onSuccess_student,
                error: onError_student,
                type: "POST"
            });
            $('.loading_image').show();
            return false;
        }
    });
});
var onSuccess_others = function(data) {
    data = JSON.parse(data);
    if (!data.success) {
        alert(data.errorThrown);
    }
    else {
        alert(data.successMsg);
        location = data.successPage;
    }
};
var onError_others = function(data) {
    alert("Something went wrong");
};
var onSuccess_student = function(data) {
    data = JSON.parse(data);
    if (!data.success) {
        alert(data.errorThrown);
    }
    else {
        alert(data.successMsg);
        location = data.successPage;
    }
};
var onError_student = function(data) {

    alert("something went wrong");
};
var onSuccess_subject_extract = function(data) {
   var json = $.parseJSON(data);
$(json).each(function(i,val){    
            $("#subject_for").append("<option value='"+val.id+"'>"+val.name+"</option>");     
});
};


var onError_subject_extract = function(data) {

    alert("something went wrong");
};
