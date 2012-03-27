<h2>ashwini's new joke</h2>

<div id="innerData">
	<div id="innerContent">
		<g:if test="${contentDetails}">
			<g:if test="${contentDetails.contentMode == 'T'}"> 
				<div class="divContent">
					${contentText.decodeHTML()}
				</div>
			</g:if>
			<g:elseif test="${contentDetails.contentMode == 'L'}">
				<iframe inLoad="iFrameHeight();"	src="${contentDetails.contentDescription}" scrolling="no" scroll="auto" id="iframename" marginwidth="0" marginheight="0" frameborder="0" style="width: 800px;" height=600></iframe>
			</g:elseif>
			<g:elseif test="${contentDetails.contentMode == 'Q'}"> 
				<div class="divQuiz">
					<div id="displayQuiz">
						<br>
						<g:set var="charCnt" value="${20}" />
						<g:set var="QuestionCount" value="${0}" />
						<form action="quizAction" name="formQuiz" id="formQuiz"	method="post">
							<input type="hidden" name="timeDuration" id="timeDuration" />
							<g:if test="${questionContentList}">
								<g:set var="count" value="${0}" />
								<g:each in="${questionContentList}" var="i" status="ik">
									<g:if test="${i.type!=3}">
										<g:set var="QuestionCount" value="${ik+1}" />
									</g:if>
									<g:if test="${i!=[]}">
										<input type="hidden" value="N" name="hintUsed${i.qnId}"
											id="hintUsed${i.qnId}" />
										<!-- new design  -->
										<g:if test="${flash.checked}">
											<g:set var="pos" value="${flash.checked.indexOf('Q'+i.qnId)}" />
											<g:set var="posEqual"
												value="${flash.checked.indexOf('=', pos) }" />
											<g:set var="strLength"
												value="${ flash.checked.substring(pos+1,posEqual).length()}" />
											<g:set var="pos_" value="${flash.checked.indexOf('_', pos) }" />
											<g:set var="checkedValue"
												value=" ${ flash.checked.substring(pos+strLength+2,pos_)}" />
										</g:if>
										<g:if test="${i.type == 1||i.type ==3}">
											<table border="0" style="width: 100%"
												onmouseover="document.getElementById('hintDiv${i.qnId}').style.display = 'block';"
												onmouseout="document.getElementById('hintDiv${i.qnId}').style.display = 'none';">
												<tr>
													<td style="width: 2%; vertical-align: top;"><g:if
															test="${flash.submitMessage}">
															<g:set var="x"
																value="${ flash.answer.indexOf(''+i.qnId)}" />
															<g:if test="${ flash.answer.substring(x-5,x)=='succe'}">
																<g:if test="${i.type !=3}">
																	<img src="${hostname}/images/tick.gif" title="Success"
																		height="20" width="20">
																</g:if>
															</g:if>
															<g:else>
																<g:if test="${i.type !=3}">
																	<img src="${hostname}/images/wrong.gif" title="Failure"
																		height="20" width="20">
																</g:if>
															</g:else>
														</g:if></td>
													<g:if test="${i.type !=3}">
														<g:set var="count" value="${count+1}" />
														<td width="5%" style="width: 1%; vertical-align: top;">
															<span id="questionNum"> ${count})
														</span></b>
														</td>
													</g:if>
													<td width="70%" style="vertical-align: top;" colspan="2">
														<span id="question"> ${i.qnText}
													</span>
													</td>
													<g:if test="${i.qnHint}">
														<td valign="top" style="padding-right: 60px;" colspan="3"
															width="12%" height="30px;"><g:if
																test="${!flash.submitMessage}">
																<div style="display: none" id="hintDiv${i.qnId}">
																	<img align="right" height="18"
																		onclick="javascript:showHint('toggleText${i.qnId}','hintUsed${i.qnId}')"
																		src="${hostname}/images/bulb.gif"
																		style="cursor: pointer" />
																</div>
															</g:if></td>
													</g:if>
													<g:else>
														<td colspan="3" width="3%">&nbsp;</td>
													</g:else>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<g:if test="${i.image}">
														<td align="left" style="width: 3%"><img width="100"
															height="100"
															src="${hostname}/uploads/quiz/crop/${i.image}" /></td>
													</g:if>
													<g:if test="${i.image}">
														<td width="80%" colspan="4">
													</g:if>
													<g:else>
														<td width="80%" colspan="5">
													</g:else>
													<table border="0" width="100%" align="left">
														<g:set var="flaglength" value="${0}" />
														<g:each status="k" in="${i.answers}" var="j">
															<g:if test="${j.text.length()>charCnt}">
																<g:set var="flaglength" value="${1}" />
															</g:if>
														</g:each>
														<g:each status="k" in="${i.answers}" var="j">
															<g:if test="${flash.submitMessage&&checkedValue}">
																<g:if test="${j.key==checkedValue.toInteger()}">
																	<g:set var="check" value="checked" />
																</g:if>
																<g:else>
																	<g:set var="check" value="" />
																</g:else>
															</g:if>
															<g:if
																test="${flaglength==1||i.answers.size()>4||flaglength!=1&&i.answers.size()==3 }">
																<tr>
																	<td align="right" width="32px" id="textAnsChoice"
																		style="vertical-align: middle"><g:radio id="${j.text}" checked="${check}"
																			name="myAns${i.qnId}" value="${j.key }" /></td>
																	<td id="textAnsChoice" style="padding-left: 3px;">
																		<label for="ans${j.key }"> ${j.text }
																	</label>
																	</td>
																</tr>
															</g:if>
															<g:elseif test="${i.image!='null'||flaglength!=1&&i.answers.size()<=4}">
																<g:set var="temp" value="${k+1}" />
																<g:if test="${temp%2==0}">
																	<g:set var="align" value="align='left'" />
																	<g:set var="width" value=" " />
																</g:if>
																<g:else>
																	<g:set var="align" value="align='left'" />
																	<g:set var="width" value="width='30%'" />
																	<tr>
																</g:else>
																<td id="textAnsChoice" style="padding-left: 20px;"
																	${align} ${width}><g:radio id="${j.text}"
																		checked="${check}" name="myAns${i.qnId}"
																		value="${j.key }" /> <label for="ans${j.key }">
																		${j.text }
																</label></td>
																<g:if test="${temp%2==0}">
																	</tr>
																</g:if>
															</g:elseif>
														</g:each>
													</table>
													</td>
												</tr>
											</table>
										</g:if>
										<g:elseif test="${i.type  == 2}">
											<!-- if image question -->
											<table border="0" style="width: 100%" onmouseover="document.getElementById('hintDiv${i.qnId}').style.display = 'block';" onmouseout="document.getElementById('hintDiv${i.qnId}').style.display = 'none';">
												<tr>
													<td width="4%" style="width: 2%; vertical-align: top;">
														<g:if test="${flash.submitMessage}">
															<g:set var="x" value="${ flash.answer.indexOf(''+i.qnId)}" />
															<g:if test="${ flash.answer.substring(x-5,x)=='succe'}">
																<img src="${hostname}/images/tick.gif" title="Success" height="20" width="20">
															</g:if>
															<g:else>
																<img src="${hostname}/images/wrong.gif" title="Failure" height="20" width="20">
															</g:else>
														</g:if>
													</td>
													<g:if test="${i.type !=3}">
														<g:set var="count" value="${count+1}" />
														<td width="9%" style="width: 1%; vertical-align: top;">
															<span id="questionNum"> ${count})
														</span></b>
														</td>
													</g:if>
													<td width="70%" style="vertical-align: top;"><span id="question"> ${i.qnText}
													</span></td>
													<g:if test="${i.qnHint}">
														<td valign="top" style="padding-right: 60px;" colspan="3" width="12%" height="30px;">
														<g:if test="${!flash.submitMessage}">
																<div style="display: none" id="hintDiv${i.qnId}">
																	<img align="right" height="18"
																		onclick="javascript:showHint('toggleText${i.qnId}','hintUsed${i.qnId}')"
																		src="${hostname}/images/bulb.gif"
																		style="cursor: pointer" />
																</div>
														</g:if>
														</td>
													</g:if>
												</tr>
												<g:if test="${i.image}">
													<tr>
														<td height="119">&nbsp;</td>
														<td>&nbsp;</td>
														<td align="left" colspan="4"><img width="100"
															height="100"
															src="${hostname}/uploads/quiz/crop/${i.image}" /></td>
													</tr>
												</g:if>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td align="center" width="50%">
														<table border="0" style="width: 70%" align="center">
															<tr>
																<!-- here choices are images -->
																<g:each status="k" in="${i.answers}" var="j">
																	<g:if test="${flash.submitMessage && checkedValue}">
																		<g:if test="${j.key == checkedValue.toInteger()}">
																			<g:set var="check" value="checked" />
																		</g:if>
																		<g:else>
																			<g:set var="check" value="" />
																		</g:else>
																	</g:if>
																	<g:if test="${j.text }">
																		<td align="center" width="5%"><label
																			for="ans${j.key }"> <img width="100"
																				height="100"
																				src="${hostname}/uploads/quiz/crop/${j.text }" />
																		</label> <br /> <g:radio id="${j.text}" checked="${check}"
																				name="myAns${i.qnId}" value="${j.key }" /></td>
																	</g:if>
																</g:each>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</g:elseif>
										<br />
										<g:if test="${flash.submitMessage}">
											<g:if test="${ flash.answer.substring(x-5,x)!='succe'}">
												<g:if test="${i.qnHint}">
													<div class="divQuizHint">
														<p id="hint">
															<i>hint: ${i.qnHint}</i>
														</p>
													</div>
												</g:if>
											</g:if>
										</g:if>
										<div class="divQuizHint" id="toggleText${i.qnId}"
											style="display: none;">
											<p id="hint">
												<i>hint:${i.qnHint}</i>
											</p>
										</div>
										<br />
										<hr id="horizontalLine" />
										<br />
									</g:if>
								</g:each>
                <!-- the submit button -->
								<g:if test="${QuestionCount>0}">
									<p>&nbsp;</p>
									<div style="padding-left: 40px; padding-bottom: 15px;">
										<input type="submit" name="submit" id="submit" value="Submit"
											onclick="javascript:quizAnswer()"
											style="cursor: pointer; width: 80px; height: 25px;">
									</div>
								</g:if>
							</g:if>
						</form>
					</div>
				</div>

			</g:elseif>
		</g:if>
	</div>
</div>
