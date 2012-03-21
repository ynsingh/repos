<g:if test="${(params.modeOfPayment=='DD') || (params.modeOfPayment=='Cheque')}">
<input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:grantExpenseInstance,field:'ddNo')}" style="text-align: right" />
</g:if>
<g:else>
<input type="text" id="ddNo" name="ddNo" value="" style="background-color: #E6E8EB;" disabled="true" />
</g:else>
