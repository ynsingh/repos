function call_autocomplete(id, array, multiselect, log_id, result_id) {
    var cache = [];
    var temp = [];
    var temp1 = [];
    var selected = [];

    $("#" + id)
            .bind("keydown", function(event) {
        if (event.keyCode === $.ui.keyCode.TAB &&
                $(this).data("ui-autocomplete").menu.active) {
            event.preventDefault();
        }
    })
            .autocomplete({
        source: function(request, response) {
            var term = extractLast(request.term);
//            $.ajax({
//                url: url+term,
//                dataType: "text",
//                async:false,
//                cache:true,
//                success: function (data) {
//                    cache=data;
//                    temp=cache.split(",");
//                    temp1=diff(temp,selected);
//                }
//
//            });
//            response(temp1.slice(0,10));
            response($.ui.autocomplete.filter(array, term).slice(0, 10));
        },
        focus: function() {
            return false;
        },
        select: function(event, ui) {
            if (multiselect) {
                var terms = split(this.value);
                terms.pop();
                terms.push(ui.item.value);
                terms.push("");
                this.value = terms.join(",");
                return false;
            }
        }
    });

    function extractLast(term) {
        return split(term).pop();
    }

    function split(val) {
        return val.split(/,\s*/);
    }

    function log(message) {
        $("<div>").text(message).prependTo("#" + log_id);
        $("#" + log_id).scrollTop(0);
    }

    function diff(A, B) {
        return A.filter(function(a) {
            return B.indexOf(a) == -1;
        });
    }

    return selected;
}


