<head>
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.min.js')}"></script>

<script type="text/javascript" src="${createLinkTo(dir:'js/fileupload',file:'ajaxupload.js')}"></script>
<link rel="stylesheet" href="${resource(dir:'css',file:'ajaxfileupload.css')}" />

<style media="print">
	
	#adminHeader,
	#ZURBar, 
	div#sidebar,
	ul.tabs li, 
	#site-info div#zurbword, 
	#site-info div#zurbnews,
	#site-info div#footer { display: none !important; }
	
	#site-info div#zurbcontact { border-top: 2px solid #e0e0e0; padding-top: 10px; width: 400px; }
	
	ul.tabs li.current { display: block !important; margin: -7px -30px 0 0; }
	li.current a span { color: #000; background-color: green; }
	
	
	/* Stripping the Blog --------- */
	
	div#content ul.options,
	div.post-header ul.admin, 
	div#pageHeader,
	div#content-sec div,
	div#content-main div.footer,
	div#content-main div#comments { display: none !important; };

	div.column-row div#content-main { width: 20%; }
	
	body#showPage div#content-main div.post { width: 120%; }
	div#content-sec div#aboutZURBlog { display: block !important; width: 70%; margin-left: 80px; }
	
	div.secondary.page-header { display: none !important; }
	
	div#content-main { font-size: 15px; line-height: 17px;}
	
	div.post-header h2 { font-size: 26px; }
	div.body { font-size: 16px; line-height: 19px; }

	body#showPage div#content-main blockquote,
	body#showPage div#content-main blockquote p { color: #555; }
	
	div.vcard ul. div.vcard address, div.vcard p { color: 000; }
	
</style>
 
<script type="text/javascript">
imgBaseURL = 'http://' + window.document.location.host + '/aell/images/';
$(document).ready(function(){
	var thumb = $('img#thumb');	
	new AjaxUpload('imageUpload', {
		action: "/aell/util/uploadImage",
		name: 'image',
		onSubmit: function(file, extension) {
			$('div.preview').addClass('loading');
		},
		onComplete: function(file, response) {
			thumb.load(function(){
				$('div.preview').removeClass('loading');
				thumb.unbind();
			});
			var imgURL = imgBaseURL + response;
			thumb.attr('src', imgURL);
			$("#imgURL").attr("value", response);
			$("label").html("Image Preview");
			//$('form#uploadFile').attr('action','saveImage')
		}
	});
});
//$("#thumb").onclick = function(){$("#hidbut").click();};
	
</script>
</head>

<body>
    <div class="seven columns">
		<div class="preview">
			<img id="thumb" width="100px" height="100px" src="/aell/images/upload_question.jpg" onclick=""/>
		</div>
		<div class="imageForm">
			<g:form id="uploadFile" url="[controller:'util',action:'saveImage']" method="post">
				<label>upload pic</label>
				<input type="file" id="imageUpload" size="1" />
				<input type="hidden" name="imgURI" id="imgURI" value=""/>
				<button id="hidbut" type="submit" class="button">Save</button>
			</g:form>
		</div>
	</div>
</body>
