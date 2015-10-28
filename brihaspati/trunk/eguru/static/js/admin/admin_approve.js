$(document).ready(function() {
    $(".done").hide();
  $(".approve").click(function() {
 var id = $(this).attr('id');
        var status = $(this).val();
var department=$(this).attr('aid');
        data = {id: id,
            status: status,
            department:department
        };
var url = "/admin/approve_dept_hod/";
        $.ajax(url, {            
            data: data,
            type: "POST",
            success: onSuccess_dash_approve,
            error: onError_dash_approve
        });
        return(false);
    });
    $(".change").click(function(){
   $(this).hide();
   $(this).next().show();
   $(this).prev().removeAttr('readonly');
   });
   $(".done").click(function(){ 
       var department_name=$(this).prev().prev().val();
       var department_id=$(this).val();
   data ={ department:department_name,
            id:department_id};
            var url = '/admin/department_edit';           
            $.ajax(url,{
                data: data,                
                success: onSuccess_edit,
                error: onError_edit,
                type: "POST"
            });
            $('.loading_image').show();
            return false;        
    });
    $('#add_department').validate({
        rules:
                {
                    add_department: {required: true
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
            var url = '/admin/department_add';
            var data = $('#add_department').serialize();
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
    $(".delete").click(function() {
        var department_id = $(this).prev().val();
        data = {
            id: department_id
        };
        var url = "/admin/department_delete";
        $.ajax(url, {
            data: data,
            type: "POST",
            success: onSuccess_delete_department,
            error: onError_delete_department
        });
        return(false);
    });
});
var onSuccess_dash_approve = function(data) {
    obj = JSON.parse(data);
    if(obj.response==='done')
   window.location.reload();
   else{
        alert("Department Incharge is already assgined to this department");
        window.location.reload();
    }    
};
var onError_dash_approve = function(data) {
alert("something went wrong");

};
var onSuccess_add = function(data) {
    data = JSON.parse(data);
    if (data.success){
        alert(data.successMsg);
        window.location.reload();
    }
};
var onError_add = function(data) {
alert("something went wrong");
    
};
var onSuccess_edit  = function(data) {
    data = JSON.parse(data);
    if (data.success){
        alert(data.successMsg);
        window.location.reload();
    }
};
var onError_edit = function(data) {
    
};
var onSuccess_delete_department = function(data){
    data=JSON.parse(data);
    if(data.success){
        alert(data.successMsg);
        window.location.reload();
    }
};
var onError_delete_department = function(data){
alert("something went wrong"); 
};



