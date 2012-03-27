<g:if test="${val?.size()}"> 
<g:form action="updateSequenceDNDT" name="sequenceFormDNDT" method="post" >
  <input type="hidden" name="sequenceOrderDNDT" id="sequenceOrderDNDT"/>
</g:form>
<div id="displayDNDTQn" style="text-align:left;">
  <div id="dndTName" class="qnType ui-corner-tl ui-corner-tr ui-corner-bl ui-corner-br">
  <h2 align="center"></h2><br>
  </div>
  <table  border="0" id="tableDragDNDT"  width="100%" >
  <tbody>   
       <tr>       
        <td width="5%" <g:if test="${mode=='admin'}">style="height:30px;"</g:if>><g:if test="${mode=='admin'}"><img id="secInsImg${Qtype}" style="display:none;cursor: pointer;" width="16px" height="16px" src="/aell/images/add.gif"  onclick="secAdd('${Qtype}');" alt="Add Section Instructions"></g:if>
        <td width="92%">
          <g:if test="${subSec.keySet().contains('DDH')}">
          	<g:render template="/common/secInstructions" model="['Qtype':'DNDT','sId':subSec.DDH.qId,'sText':subSec.DDH.text,'saved':'true']"/>
          </g:if>
          <g:else>
            <g:render template="/common/secInstructions" model="['Qtype':'DNDT','saved':'false']"/>
          </g:else>
        </td>
     </tr>
     </tbody>
     <tbody>  
      <g:each in="${val}" var="i" status="ik">
      <input type="hidden" id="dndtQn${i.key}" value="${i.value.Q}"/>
      <input type="hidden" id="hntUsed${i.key}" name="hntUsed" value="N"/>
      <input type="hidden" id="dndTKey${ik}" value="${i.key}"/>
      <tr  id="${i.key}" class="dndtGroup" style="cursor: move; ">
      <td style="width:1px;" class="dragDropQuiz" title="Click and drag to change the display order." >
                   &nbsp;&nbsp;&nbsp;&nbsp;
       </td>
       <td width="92%" class="text" align="left">
      	<div class="dndTQuestion" id="dndTQuestion${i.key}" <g:if test="${i.value.H !='Hint'}"> <g:if test="${mode=='assess'}">onmouseover="document.getElementById('dndTBHint${ik}').style.display = 'block';" onmouseout="document.getElementById('dndTBHint${ik}').style.display = 'none';" </g:if> </g:if>>
	      <br />
    	   <%
            List choices=[]  
		    str = i?.value?.Q
			String ansChoice="["
            str.count("[").times{
				choices.add(i.value."A$it")
				blankVal = (mode == "admin") ? i.value."A$it" : "";
				str=str?.replaceFirst("\\[]", "<input type=\"text\" readonly=\"readonly\" class=\"dndch\" id=\"ch$it\" value=\"" + blankVal +"\" />")
        	}
			(mode == "assess") ? choices.sort() : "";
			choices.size().times(){
				ansChoice += '&nbsp;<span class="dndTOptions">' + choices.get(it)  + '</span>,&nbsp;&nbsp;'
			}
			ansChoice = ansChoice.substring(0, ansChoice.lastIndexOf(",")) + " ]"
            %> 
           <table width="100%" align="left">
           <tr>
           <td width="5%"><b>${ik + 1})</b></td>
           <td width="85%">
           <div class="divClass">
           ${ansChoice}
           </div>
           </td>
           <td width="10%" style="width: 55px; height: 28px;padding: 0;">
           <g:if test="${mode=='assess'}">
		   	<img id="dndTBHint${ik}" height="18px" onclick="showDndTHint('dndTHint${ik}','${i.key}');" src="${hostname}/images/bulb.gif" style="cursor:pointer;display:none;" title="Show Hint"/>
           </g:if>
           </td> 
           </tr>
           	<tr>
            	<td width="5%"></td>
              	<td width='85%'><p class="dndText">${str}</p></td>
              	<td width="10%">
        		<g:if test="${mode=='admin'}">   
            		<p id="controls" style="width: 10px;">
       			        <a onclick='deleteDNDTQn("${i.key}");' style="margin-bottom: 5px;text-decoration: none !important;">
             				<img src="${hostname}/aell/images/delete.png" width="20px" height="20px" title="delete" id="delete" name="delete"   style="cursor:pointer"/>
                		</a> 
          				<a onclick='editDNDTQn("${i.key}","${i.value.H}");' style="margin-bottom: 5px;text-decoration: none !important;">
            				<img src="${hostname}/aell/images/Edit.gif" name="edit" title="edit"id="edit"  style="cursor:pointer"  /><br /><br />
                		</a>
                	</p>
          		</g:if>
	           	</td>
            </tr>
            <tr>
            	<td colspan="3" style="margin-bottom:5em;">
            		<div style='height:20px;'>
    	    			<g:if test="${i.value.H !='Hint'}">
      						<p id="dndTHint${ik}" <g:if test="${mode=='assess'}">style='display:none;'</g:if>><b>Hint</b>: ${i.value.H }</p>     
	      				</g:if>
      				</div>
            	</td>
            </tr>
           </table>
    </div>
   </td>
   </tr>
    </g:each>
    </tbody>
    <tr>
      <td style="width:1px;">
      </td>
      <td width="92%" class="text" align="left">
        <div style="padding-left:10px;padding-bottom:15px;">
           <input type="submit" name="evaluate" id="evaluateDNDT" value="Submit" onclick='evalDNDTAll();' style="cursor:pointer;width:80px;height:25px;">
        </div> 
      </td>
      <td width="5%">
      </td>
    </tr>
    </table>
</div>
</g:if>