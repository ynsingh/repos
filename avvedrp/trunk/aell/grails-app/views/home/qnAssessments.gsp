<br/>
<div id="mChoiceHeadStd" style="display:none;" class="qnType ui-corner-tl ui-corner-tr ui-corner-bl ui-corner-br">
  <h2 align="center">Multiple choice questions.</h2><br>
</div>
<div id="mContent" align="left" style="display: none;">
	<g:render template="/common/mChoiceAssessments"/> 
</div>

<div id="fibQuest" align="center" style="display: none;">
    <g:render template="/common/fibDisplayQn" model="['mode':'assess','val':fbqn,'subSec':ssHead,'Qtype':'FIB']" />
</div>

<br/>
<div id="dndTQuest" align="center" style="display:none;">	
	      <g:render template="/common/dndTextDisplay" model="['mode':'assess','val':dndqn,'Qtype':'DNDT','subSec':ssHead]"/>			
</div>
<br />
<div id="dispMatchFollowing">
      <g:render template="/common/dragDropDisplay" model="['mode':'student','val':qaList,'subSec':ssHead,'Qtype':'DND']" />
</div>