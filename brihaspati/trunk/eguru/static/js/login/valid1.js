$(document).ready(function() {
    $("#register-form").validate({
        rules: {
            firstname: {
                required: true
            },
            lastname: {
                required: true
            },
            mothers_name: {
                required: true
            },
            username: {
                required: true
            },
            email: {
                requried: true,
                email: true
            },
            password: {
                required: true
            },
            confirm: {
                required: true,
            }
        },
        message: {
            firstname: {
                required: "You Can't Leave This Field Empty"
            },
            lastname: {
                required: "You Can't Leave This Field Empty"
            },
            mothers_name: {
                required: "You Can't Leave This Field Empty"
            },
             username: {
                required: "You Can't Leave This Field Empty"
            },
            email: {
                requried: "You Can't Leave This Field Empty",
                  email: "Your E-mail Is Invalid"
            },
            password: {
                required: "You Can't Leave This Field Empty"
            },
            confirm: {
                required: "You Can't Leave This Field Empty"
            }
        }
    });
 $("#user_signin").validate({
     rules: {
            username: {
                required: true
            },
            password: {
                required: true
    }
        },
        message: {
            username: {
                required: "You Can't Leave This Field Empty"
            },
     	      password: {
                required: "You Can't Leave This Field Empty"
        }
}
    });
});
