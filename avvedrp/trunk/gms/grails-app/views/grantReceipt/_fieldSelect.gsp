<g:if test="${(params.modeOfPayment=='DD') || (params.modeOfPayment=='Cheque')}">

<input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:grantReceiptInstance,field:'ddNo')}" style="text-align: right" />
</g:if>
<g:else>
<input type="text" id="ddNo" name="ddNo" value="" disabled="true" />
</g:else>
