CKEDITOR.editorConfig = function( config )
{
       // Declare the additional plugin
	
   // EXAMPLE TOOLBAR USING THE NEW PLUGIN
   config.toolbar_custom = [
       ['NewPage','Preview'],
               ['Source','-','Cut','Copy','Paste','PasteText','PasteFromWord','-','Scayt'],
               ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
               ['Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
               '/',
               ['Styles','Format','Font','FontSize'],
               ['TextColor','BGColor'],
               ['Bold','Italic','Underline','Strike'],
               ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
               ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
               ['Link','Unlink','Anchor'],['Maximize']
           ]
};