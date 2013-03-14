function checkList(frm,field)
        {       if(frm.detail.value!="")
                {
                        var textval=frm.detail.value;
                        var maxValue=frm.maxValue.value;
                        var data=new Array();
                        var flag = true;
                        var n=textval.split(";");
                        for (var i=0;i<(parseInt(n.length)-1);i++){
                                var x=n[i].split("#");
                                var y=x[0];
                                var z=x[1];
                                if((parseInt(y))<=(parseInt(maxValue))){
                                        data.push(y);
                                }
			}
                        if((parseInt(n.length)-1)==data.length)
                        {
                                frm.actionName.value=field.name;
                                frm.submit();
                        }
                        else{
                                alert("Enter proper date");
                        }

                        }
                else if(field.name=="eventSubmit_doUpdate")
                {
                        frm.actionName.value=field.name;
                                        frm.submit();
                }
                else
                {
                        alert("Please write the events");
                }
        }

