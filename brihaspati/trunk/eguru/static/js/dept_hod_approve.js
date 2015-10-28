$(document).ready(function() {
  $(".approve").click(function() {
 var id = $(this).attr('id');
        var status = $(this).val();
data = {id: id,
            status: status
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
});
var onSuccess_dash_approve = function(data) {
    obj = JSON.parse(data);
    if(obj.response==='done')
   window.location.reload();    
};
var onError_dash_approve = function(data) {

alert(data);
}; 
