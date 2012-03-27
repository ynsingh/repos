
<div id="secIns${Qtype}" align="center" style="display:none;">
  <input type="hidden" id="secQId${Qtype}" value="${sId}"/>
  <input type="hidden" id="secIsEdit${Qtype}" value="${saved}"/>
  <textarea id="secInsText${Qtype}" name="secInsText" style=" width: 570px; height: 30px; font-weight: bold;">${sText}</textarea>
  <img id="secInsSave${Qtype}" src="/aell/images/save.png" onclick="secInsSave('${Qtype}');" alt="Save">
</div>
<div id="secInsData${Qtype}" align="Left" class="secInsDisp">
  <h3 style="line-height: 150%;">${sText}</h3>
</div>