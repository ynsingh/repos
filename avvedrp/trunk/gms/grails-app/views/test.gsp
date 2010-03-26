<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>Grails jQuery UI test</title>
   <gui:resources components="['richEditor','dialog']"/>
  </head>
  <body>
    <form>
      
      
   <script>
    var yesHandler = function(o) {
        alert('You clicked "Yes"');
        this.cancel();
    }
</script>
<form>
<gui:dialog
    title="Modal Confirm Dialog"
    draggable="false"
    modal="true"
    buttons="[
        [text:'Yes', handler: 'yesHandler', isDefault: true],
        [text:'No', handler: 'function() {this.cancel();}', isDefault: false]
    ]"
    triggers="[show:[type:'link', text:'Confirm', on:'click']]"
>
Are you sure?
</gui:dialog>
    </form>
  </body>
</html>
