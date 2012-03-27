YAHOO.namespace("example.calendar"); 
                YAHOO.example.calendar.init = function()
                { 
                    YAHOO.example.calendar.cal1 = new YAHOO.widget.Calendar("cal1","cal1Container");
                    // function to handle cell click event 
                    YAHOO.example.calendar.cal1.doSelectCell = function(e , cal1)
					{
						var cell, d, date, index;
						var target = YAHOO.util.Event.getTarget(e),
						tagName = target.tagName.toLowerCase(),
						defSelector = false;
						while (tagName != "td" && !YAHOO.util.Dom.hasClass(target, cal1.Style.CSS_CELL_SELECTABLE)) {
							if (!defSelector && tagName == "a" && YAHOO.util.Dom.hasClass(target, cal1.Style.CSS_CELL_SELECTOR)) {
								defSelector = true;
							}
							target = target.parentNode;
							tagName = target.tagName.toLowerCase();
							if (target == this.oDomContainer || tagName == "html") {
								return;
							}
						}
					
						if (defSelector) {
							// Stop link href navigation for default renderer
							YAHOO.util.Event.preventDefault(e);
						}
						cell = target;
						if (YAHOO.util.Dom.hasClass(cell, cal1.Style.CSS_CELL_SELECTABLE)) {
							index = cal1.getIndexFromId(cell.id);
							if (index > -1) {
								d = cal1.cellDates[index];
								if (d) {
									date = YAHOO.widget.DateMath.getDate(d[0],d[1]-1,d[2]);
									var day = date.getDate();
									var month=date.getMonth();
									var year=date.getFullYear();
									for(i=0;i<evntArray.length;i++)
									{
										if((evntArray[i].day==day)&&(evntArray[i].month==month)&&(evntArray[i].year==year))
										{
											mnth=month+1;
											window.location.href = "listevents.jsp?date="+year+"-"+mnth+"-"+day;
										}
									}
									var link;
									if (cal1.Options.MULTI_SELECT) {
										link = cell.getElementsByTagName("a")[0];
										if (link) {
											link.blur();
										}
										var cellDate = cal1.cellDates[index];
										var cellDateIndex = cal1._indexOfSelectedFieldArray(cellDate);
										if (cellDateIndex > -1) { 
											cal1.deselectCell(index);
										} else {
											cal1.selectCell(index);
											} 
					
										} else {
											link = cell.getElementsByTagName("a")[0];
											if (link) {
												link.blur();
											}
											cal1.selectCell(index);
										}
									}
								}
							}
					}
					//end of function to handle cell click event
					
					//function to handle cell mouse over event
					YAHOO.example.calendar.cal1.doCellMouseOver = function ( e , cal1 )
                     {
						var target;
                        if (e) 
                        {
                            target = YAHOO.util.Event.getTarget(e);
                        }
                        else 
                        {
                            target = this;
                        }
                        while (target.tagName && target.tagName.toLowerCase() != "td") 
                        {
                            target = target.parentNode;
                            if (!target.tagName || target.tagName.toLowerCase() == "html") 
                            {
                                return;
                            }
                        }
						         
                        if (YAHOO.util.Dom.hasClass(target, YAHOO.example.calendar.cal1.Style.CSS_CELL_SELECTABLE)) 
                        {
                            YAHOO.util.Dom.addClass(target, YAHOO.example.calendar.cal1.Style.CSS_CELL_HOVER);
                        }
                        var cell;
                        cell = target;
                        var index;
                        index = cal1.getIndexFromId(cell.id);
						
                        var d;
                        d = cal1.cellDates[index];
						var selectedDate = new Date();
                        var selectedDate = YAHOO.widget.DateMath.getDate(d[0],d[1]-1,d[2]);
                        var date = selectedDate.getDate();
                        var month=selectedDate.getMonth();
                        var year=selectedDate.getFullYear();
										
						for(i=0;i<evntArray.length;i++)
						{
							if((evntArray[i].day==date)&&(evntArray[i].month==month)&&(evntArray[i].year==year))
							{
								YAHOO.util.Dom.setXY('container', YAHOO.util.Event.getXY(e));
								
								YAHOO.example.calendar.panel2 = new YAHOO.widget.Panel("panel2", { width:"320px", visible:false, draggable:true,} );	
								YAHOO.example.calendar.panel2.setHeader(evntArray[i].title);
								YAHOO.example.calendar.panel2.setBody(evntArray[i].ttip);
								var temp = evntArray[i].month;
								temp++;
								YAHOO.example.calendar.panel2.setFooter("Date:"+evntArray[i].day+"/"+temp+"/"+evntArray[i].year);
								YAHOO.example.calendar.panel2.render("container");
								YAHOO.example.calendar.panel2.show();
							}
						}
                        
                    } 
					//end of function to handle mouse over event
					
                    /*YAHOO.example.calendar.cal1.doCellMouseOut = function(e,call1)
                    {
                        YAHOO.example.calendar.panel2.hide();
                    }*/
                    YAHOO.example.calendar.cal1.render();
                    
					//code to highlight cell with events associated with it
					dateString="";
                    flag=0;
                    for(i=0;i<evntArray.length;i++)
                    {
						var mnth=evntArray[i].month+1;
						if(flag==0)
							dateString = dateString + mnth +"/"+evntArray[i].day+"/"+evntArray[i].year;
                        else if(flag ==1)
                        	dateString = dateString + "," +mnth +"/"+evntArray[i].day+"/"+evntArray[i].year;
                        flag =1;
                    }
					
                    if(flag==1);
                    {
                        YAHOO.example.calendar.cal1.addRenderer(dateString, function(workingDate, cell) {
                        YAHOO.util.Dom.addClass(cell, YAHOO.example.calendar.cal1.Style.CSS_CELL_HIGHLIGHT1); });
                        YAHOO.example.calendar.cal1.resetRenderers();	
                        YAHOO.example.calendar.cal1.render();
                    }
                } 
                YAHOO.util.Event.onDOMReady(YAHOO.example.calendar.init); 
				