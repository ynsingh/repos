<?php
/**
 * This is the default view
 * for showing map of all the institutes
 * Whatever data the controller passes us
 * we just display it
 */
?>
<script src="http://www.google.com/jsapi"></script> 
<script type="text/javascript" src="<?=HTTP_ROOT_DIR?>cache/<?=$json?>"></script>
<script type="text/javascript" src="<?=HTTP_ROOT_DIR?>js/markercluster_packed.js"></script>
<script type="text/javascript"> 
/*
 On the use of markercluster below , see
 http://google-maps-utility-library-v3.googlecode.com/svn/tags/markerclusterer/1.0/examples/simple_example.html
*/
  google.load('maps', '3', {
	other_params: 'sensor=false'
  });
  google.setOnLoadCallback(initialize);
   function markerClick(e){
	console.log(e);
	//
  }
  function initialize() {
	var center = new google.maps.LatLng(29.9882586715553,77.89220771796);
	var map = new google.maps.Map(document.getElementById('map'), {
	  zoom: 4,center: center,
	  mapTypeId: google.maps.MapTypeId.ROADMAP
	});
	var markers = [];
	for (var i = 0, institute; institute = institutes[i]; i++) {
	  var latLng = new google.maps.LatLng(institute.latitude, institute.longitude);
	  var marker = new google.maps.Marker({position: latLng,title:institute.name});
	  markers.push(marker);
	  google.maps.event.addListener(marker, 'click', markerClick);
	}
	var markerCluster = new MarkerClusterer(map, markers);
  }
 function markerClick(){
	 console.log("I was clicked");
 }
</script> 
<div id="map"></div> 
