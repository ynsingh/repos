<g:if test="${fbqn?.size()}"> 
<g:form action="updateSequenceFIB" name="sequenceFormFIB" method="post" >
  <input type="hidden" name="sequenceOrderFIB" id="sequenceOrderFIB"/>
</g:form>
<div id="displayQn" style="text-align:left;">

  <div id="fibName" class="qnType ui-corner-tl ui-corner-tr ui-corner-bl ui-corner-br">
  <h2 align="center">Fill in the blanks.</h2><br>
  </div>
  <table  border="0" id="tableDragFIB"  width="100%" >   
   <tbody>
       <tr>       
        <td width="5%" <g:if test="${mode=='admin'}">style="height:30px;"</g:if>><g:if test="${mode=='admin'}"><img id="secInsImg${Qtype}" style="display:none;cursor: pointer;" width="16px" height="16px" src="/aell/images/add.gif"  onclick="secAdd('${Qtype}');" alt="Add Section Instructions"></g:if>
        <td width="92%">
          <g:if test="${subSec.keySet().contains('FBH')}">
             <g:render template="/common/secInstructions" model="['Qtype':'FIB','sId':subSec.FBH.qId,'sText':subSec.FBH.text,'saved':'true']"/>
          </g:if>
          <g:else>
            <g:render template="/common/secInstructions" model="['Qtype':'FIB','saved':'false']"/>
          </g:else>
        </td>
     </tr>  
     </tbody>
     <tbody>  
      <g:each in="${fbqn}" var="i" status="ik">
      <input type="hidden" id="fibQn${i.key}" value="${i.value.Q}"/>
      <input type="hidden" id="hntUsed${i.key}" name="hntUsed" value="N"/>
      <input type="hidden" id="fbKey${ik}" value="${i.key}"/>
      <tr  id="${i.key}" style="cursor: move; ">
      <td style="width:1px;" class="dragDropQuiz" title="Click and drag to change the display order." >
                   &nbsp;&nbsp;&nbsp;&nbsp;
       </td>
       <td width="92%" class="text" align="left">
      	<div class="fbQuestion" id="fbQuestion${i.key}" <g:if test="${mode=='assess'}">onmouseover="document.getElementById('FibBHint${ik}').style.display = 'block';" onmouseout="document.getElementById('FibBHint${ik}').style.display = 'none';" </g:if>>
	      <br />
    	    <%  str = i?.value?.Q
            str.count("[").times{
            blankVal = (mode == "admin") ? i.value."A$it" : "";
            str=str?.replaceFirst("\\[]", "<input type=\"text\" class=\"fibch\" id=\"ch$it\" value=\"" + blankVal +"\" />")
        	  }
             %>
           <table width="100%" align="left">
           	<tr>
            	<td width="5%"><b style="line-height:200%;">${ik + 1})</b></td>
              	<td width='85%'><p class="fibText">${str}</p></td>
              	<td width="10%">
              	<g:if test="${mode=='assess'}">
              	<img id="FibBHint${ik}" height="18px" onclick="showFibHint('FibHint${ik}','${i.key}');" src="${hostname}/images/bulb.gif" style="cursor:pointer;display:none;float:right; padding-right:45px; clear: both;" title="Show Hint"/>
            	</g:if>
        		<g:if test="${mode=='admin'}">   
            		<p id="controls" style="width: 10px;">
       			        <a onclick='deleteFBQn("${i.key}");' style="margin-bottom: 5px;text-decoration: none !important;">
             				<img src="${hostname}/aell/images/delete.png" width="20px" height="20px" title="delete" id="delete" name="delete"   style="cursor:pointer"/>
                		</a> 
          				<a onclick='editFBQn("${i.key}","${i.value.H}");' style="margin-bottom: 5px;text-decoration: none !important;">
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
      						<p id="FibHint${ik}" <g:if test="${mode=='assess'}">style='display:none;'</g:if>><b>Hint</b>: ${i.value.H }</p>     
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
           <input type="submit" name="evaluate" id="evaluateFIB" value="Submit" onclick='evalFibAll();' style="cursor:pointer;width:80px;height:25px;">
        </div> 
      </td>
      <td width="5%">
      </td>
    </tr>
    </table>
</div>
</g:if>