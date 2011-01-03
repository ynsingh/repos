<div align="center"><center>

<table border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%"><form method="POST" name="result"><table border="1" width="100%" cellpadding="0" >
        <tr>
          <td>No of questions you got right:</td>
          <td><p><input type="text" name="p" ></td>
        </tr>
       
      </table>
    </form>
    </td>
  </tr>
</table>
</center></div>

<form method="POST"><div
  align="center"><center><p>

<script>
var wrong=0
for (e=0;e<=2;e++)
document.result[e].value=""

var results=document.cookie.split(";")
for (n=0;n<=results.length-1;n++){
if (results[n].charAt(1)=='q')
parse=n

}

var incorrect=results[parse].split("=")
incorrect=incorrect[1].split("/")
if (incorrect[incorrect.length-1]=='b')
incorrect=""
document.result[0].value=totalquestions-incorrect.length+" out of "+totalquestions
document.result[2].value=(totalquestions-incorrect.length)/totalquestions*100+"%"
for (temp=0;temp<incorrect.length;temp++)
document.result[1].value+=incorrect[temp]+", "


</script>

</p>
  </center></div>
</form>