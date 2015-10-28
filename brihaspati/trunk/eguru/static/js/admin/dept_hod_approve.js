$(document).ready(function() {
    $(".done").hide();
    $('#add_subject').validate({
        rules:
                {
                    add_subject: {required: true
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
            var url = '/admin/subject_add';
            var data = $('#add_subject').serialize();
            $.ajax({
                data: data,
                url: url,
                success: onSuccess_add,
                error: onError_add,
                type: "POST"
            });
            $('.loading_image').show();
            return false;
        }
    });
    $(".change").click(function() {
        $(this).hide();
        $(this).next().show();
        $(this).prev().removeAttr('readonly');
    });
    $(".done").click(function() {
        var subject_name = $(this).prev().prev().val();
        var subject_id = $(this).val();
        data = {subject: subject_name,
            id: subject_id};
        var url = '/admin/subject_edit';
        $.ajax(url, {
            data: data,
            success: onSuccess_edit,
            error: onError_edit,
            type: "POST"
        });
        $('.loading_image').show();
        return false;
    });

    $(".approve").click(function() {
        var id = $(this).attr('id');
        var status = $(this).val();
        var subject=$(this).attr('aid');
        data = {id: id,
            status: status,
            subject:subject
        };
        var url = "/admin/approve_sub_hod/";
        $.ajax(url, {
            data: data,
            type: "POST",
            success: onSuccess_dash_approve,
            error: onError_dash_approve
        });
        return(false);
    });
    $(".delete").click(function() {
        var subject_id = $(this).prev().val();
        data = {
            id: subject_id
        };
        var url = "/admin/subject_delete";
        $.ajax(url, {
            data: data,
            type: "POST",
            success: onSuccess_delete_subject,
            error: onError_delete_subject
        });
        return(false);
    });
});
var onSuccess_add = function(data) {
    data = JSON.parse(data);
    if (data.success) {
        alert(data.successMsg);
        window.location.reload();
    }
};
var onError_add = function(data) {
alert("something went wrong");
    
};
var onSuccess_edit = function(data) {
    data = JSON.parse(data);
    if (data.success) {
        alert(data.successMsg);
        window.location.reload();
    }
};
var onError_edit = function(data) {
alert("something went wrong");
    
};
var onSuccess_delete_subject = function(data){
    data=JSON.parse(data);
    if(data.success){
        alert(data.successMsg);
        window.location.reload();
    }
};
var onError_delete_subject = function(data){
  a 
};

var onSuccess_dash_approve = function(data) {
    obj = JSON.parse(data);
    if (obj.response === 'done')
        window.location.reload();
    else{
        alert("Subject Incharge is already assgined to this subject");
        window.location.reload();
    }
};
var onError_dash_approve = function(data) {

alert("something went wrong");
    
};
