<g:if test="${val?.size()}"> 
<g:form action="updateSequenceMF" name="sequenceFormMF" method="post" >
  <input type="hidden" name="sequenceOrderMF" id="sequenceOrderMF"/>
</g:form>
<div id="showDnD" style="display: none;">
	<tr>
		<td width="5%"></td>
		<td width="92%"><g:if test="${val}"><div id="dndName" class="qnType ui-corner-tl ui-corner-tr ui-corner-bl ui-corner-br">
				<h2 align="center">Match the following.</h2>
				</div>
			</g:if>
		</td>
		<td width="8%"></td>
	</tr>
	<tr>
		<td width="5%"></td>
			<td width="92%">
	 <table  border="0" id="tableDragMF"  width="100%" >   
   <tbody>
       <tr>       
        <td width="5%" style="height:30px;"><g:if test="${mode=='admin'}"><img id="secInsImg${Qtype}" style="display:none;cursor: pointer;" width="16px" height="16px" src="/aell/images/add.gif" onclick="secAdd('${Qtype}');" alt="Add Section Instructions"></g:if></td>
        <td width="92%">
          <g:if test="${subSec.keySet().contains('MFH')}">
             <g:render template="/common/secInstructions" model="['Qtype':'DND','sId':subSec.MFH.qId,'sText':subSec.MFH.text,'saved':'true']"/>
          </g:if>
          <g:else>
            <g:render template="/common/secInstructions" model="['Qtype':'DND','saved':'false']"/>
          </g:else>
        </td>
     </tr>  
   </tbody>
     <tbody>
	<g:each in="${val}" var="i" status="jk">
	<tr  id="${i.key}" style="cursor: move; ">
       <td width="5%" class="dragDropQuiz" title="Click and drag to change the display order." >
                   &nbsp;&nbsp;&nbsp;&nbsp;
            </td>
           <td width="92%" class="text" align="left">
	         <input type="hidden" id="parQId${jk}" value="${i.key}" />
	         <input type="hidden" id="ques${jk}" value="${i.value.Q}" />
	         <input type="hidden" id="hint${jk}" value="${i.value.H}" />
			<input type="hidden" id="hntUsed${jk}" name="hntUsed" value="N" />
				<div id="content${jk}" style="cursor:default;" class="content" <g:if test="${mode=='student'}">onmouseover="document.getElementById('dndBHint${jk}').style.display = 'block';" onmouseout="document.getElementById('dndBHint${jk}').style.display = 'none';" </g:if>>

					<table width="100%">
						<tr>
							<td width="5%"><b style="font-family: Verdana,Geneva,sans-serif;font-size: 12px;font-weight: bold;">${jk+1})</b></td>
              				<td width='85%'><b style="font-family: Verdana,Geneva,sans-serif;font-size: 12px;font-weight: bold;">${i.value.Q}</b></td>
              				<g:if test="${mode=='student'}">
							<td align="left" width="10%" style="height:30px;">
              <img id="dndBHint${jk}" align="right" height="18"  onclick="showDndHint('dndHint${jk}','#hntUsed${jk}');" src="${hostname}/images/bulb.gif" style="cursor:pointer;display:none;float: right;padding-right: 45px;" title="Show Hint"/>
              </td>
              </g:if>
              <td align="right" width="10%"></td>
					</tr>
					</table>
					<table width="500px">
						<tr>
							<td width="100%">
								<table width="100%" border="0">
									<tr>
										<g:each in="${i.value}" var="j">
											<g:if test="${j.key == 'option'}">
												<td width="50%">
													<div id="Questions${jk}" class="Questions">
														<table id="quesTable" align="left" border="0">
															<tbody>
																<g:each in="${j.value}" var="k" status="ik">
																	<tr>
																	  <td width="20%" style="width:20px;height: 20px;"><p id="dndOutput${k.id}" class="dndOutput correct"></p></td>
																		<td width="70%" class="dndText"><p class="QuestText" id="sidebar">
																				${k.text}
																			</p></td>
																		<td width="10%"><div style="cursor:pointer;" class="QuestDrag" id="${k.id}">
																				${ik+1}
																			</div></td>
																	</tr>
																</g:each>
															</tbody>
														</table>
													</div>
												</td>
											</g:if>
											<g:if test="${j?.key == 'answer'}">
												<td width="50%">
													<div id="answerSlots${jk}" class="answerSlots">
														<table id="ansTable" align="left" border="0">
															<tbody>
																<g:each in="${j.value}" var="k" status="ik">
																	<tr>
																		<td width="20%">
																			<div class="answerDrop" id="${k.id}">
																				A
																			</div>
																		</td>
																		<td width="80%" class="dndText">
																			<p class="answerText" id="aText${k.id}">
																				${k.text}
																			</p>
																		</td>
																	</tr>

																</g:each>
															</tbody>
														</table>
													</div>
												</td>
											</g:if>
										</g:each>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table align="left" width="100%">
					<tr>
              <td width="20%"><div id="dndHint${jk}" <g:if test="${mode=='student'}">style='display:none;'</g:if> ><b>Hint</b>: ${i.value.H}</div></td>
              <g:if test="${mode=='student'}">
              <td width="70%"></td>
              <td width="10%">
                <div id="ddRes${jk}" class="ddRes" style="cursor: pointer;">
                  <img src="${hostname}/images/refresh.png" title="Restore Question" onclick='restoreDD("${jk}");'/>
                </div>
              </td>
              </g:if>
            </tr>
					</table>
					<br>
					<br>
				</div>
			</td>
			<td width="8%" style="padding-left: 15px">
				<div id="rightContent${jk}">
					<table>
					<g:if test="${mode=='admin'}">
						<tr>
							<td>
								<div id="ddEdit" onclick='editDD("${jk}");'
									style="cursor: pointer;">
									<u>Edit</u>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div id="ddDel" onclick='deleteDD("${jk}");'
									style="cursor: pointer;">
									<u>Delete</u>
								</div>
							</td>
						</tr>
						<tr>
              <td>
                <div id="ddReset" onclick='resetDD("${jk}");'
                  style="cursor: pointer;">
                  <u>Reset</u>
                </div>
              </td>
            </tr>
						<tr>
							<td>
								<div id="ddRes${jk}" class="ddRes" onclick='restoreDD("${jk}");'
									style="cursor: pointer;">
									<u>Restore</u>
								</div>
							</td>
						</tr>
						</g:if>
					</table>
				</div>
			
		  </td>
		  </tr>
	</g:each>
	<tr>
      <td width="5%"></td>
      <td width="92%" style="height:30px;">
           <input type="submit" name="evaluate" id="evaluateDND" value="Submit" onclick='evalDndAll();' style="cursor:pointer;width:80px;height:25px;">
      </td>
      <td width="8%">
      </td>
    </tr>
	</table>
		</td>
		</tr>
		
</div>
</g:if>
