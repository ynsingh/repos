<g:if test="${party.partyDepartment}">
<g:select 																	       
       from="${party.partyDepartment}"
       name="department.id" 
       optionKey="id" optionValue="departmentCode" value="party.partyDepartment.id">
</g:select>
</g:if>
<g:else>
    <g:select 																	       
       name="department" 
       value="" 
	   disabled="true">
	</g:select>
</g:else>


