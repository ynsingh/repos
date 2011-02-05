
<div class="paginateButtons" id="pageNav" style="text-align:right">
		     		      
		        	    <a href="#">Page ${page} of 7</a>
		        	    <g:if test="${page!=1}">
		      		    <g:link action="proposalAppPart1PersonalDetails" onload="alert("hiiiiiiiiiii");" onclick="return pageNavigation(this,${page},${lastPage},${1});" params="['proposalApplication.id':proposalApplicationInstance?.id]">1</g:link>
		        		</g:if>
		        		<g:else>
		        		1
		        		</g:else>
		        		<g:if test="${page!=2}">
		      		    <g:link action="proposalAppPartInformationOfDepartment" onclick="return pageNavigation(this,${page},${lastPage},${2});" params="['proposalApplication.id':proposalApplicationInstance?.id]">2</g:link>
		        		</g:if>
		        		<g:else>
		        		2
		        		</g:else>
		        		<g:if test="${page!=3}">
		                <g:link action="proposalAppPartThreeInformationRelatingDepartment" onclick="return pageNavigation(this,${page},${lastPage},${3});" params="['proposalApplication.id':proposalApplicationInstance?.id]">3</g:link>
		       			</g:if>
		        		<g:else>
		        		3
		        		</g:else>
		        		<g:if test="${page!=4}">
		                <g:link action="proposalAppPartFourAboutResearchProject" onclick="return pageNavigation(this,${page},${lastPage},${4});" params="['proposalApplication.id':proposalApplicationInstance?.id]">4</g:link>
		          		</g:if>
		        		<g:else>
		        		4
		        		</g:else>
		        		<g:if test="${page!=5}">
		                <g:link action="proposalAppPartFiveDetailProjectReport" onclick="return pageNavigation(this,${page},${lastPage},${5});" params="['proposalApplication.id':proposalApplicationInstance?.id]">5</g:link>
		        		</g:if>
		        		<g:else>
		        		5
		        		</g:else>
		        		<g:if test="${page!=6}">
		                <g:link action="proposalAppPartSixUploadDocuments" onclick="return pageNavigation(this,${page},${lastPage},${6});" params="['proposalApplication.id':proposalApplicationInstance?.id]">6</g:link>
		         		</g:if>
		        		<g:else>
		        		6
		        		</g:else>
		        		<g:if test="${page!=7}">
		                <g:link action="proposalAppSummary" onclick="return pageNavigation(this,${page},${lastPage},${7});" params="['proposalApplication.id':proposalApplicationInstance?.id]">7</g:link>
		   				</g:if>
		        		<g:else>
		        		7
		        		</g:else>
</div>
