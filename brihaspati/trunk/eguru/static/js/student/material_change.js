$(document).ready(function() {
    $('#student_material_change').validate({        
        submitHandler: function() {
            var url = '/student/mode_change';
            var data = $('#student_material_change').serialize();
            $.ajax({
                data: data,
                url: url,
                success: onSuccess_mode_change,
                error: onError_mode_change,
                type: "POST"
            });
            $('.loading_image').show();
            return false;
        }
    });
    var onSuccess_mode_change = function(data) {
        data = JSON.parse(data);
	if (!(data.response)){
		alert("there are no materials");
		location='/student/profile_student';
	}
	else{
        var url = data.path;
        var topic_name = data.topic;
        var data = {topic: topic_name};
        $.ajax({
            url: url,
            data: data,
            type:"POST",
            success:onSuccess_mode_updated,
            error:onError_mode_updated
        });
    	}
   }; 
    
    var onError_mode_change = function(data) {
        alert ("something went wrong");
    };
    var onSuccess_mode_updated = function(data){       
        document.open();
        document.write(data);
        document.close();
    };
    var onError_mode_updated = function(data){
        alert ("chaap");
    };
});

