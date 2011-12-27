<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="/js/gears-init.js"></script>
<script type="text/javascript" lang="javascript">
// Note that using Google Gears requires loading the Javascript
// at http://code.google.com/apis/gears/gears_init.js

var initialLocation;
var browserSupportFlag =  new Boolean();
/*
 * GeoCoder is to get lat long of a known address
 * Map is to control the map
 * marker is the single marker that we use throughout
 **/
var geocoder,map,marker;
function initialize() {
  geocoder = new google.maps.Geocoder();
  var myOptions = {
    zoom: 10,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  map = new google.maps.Map(document.getElementById("map"), myOptions);
  
  // Try W3C Geolocation (Preferred)
  if(navigator.geolocation) {
    browserSupportFlag = true;
    navigator.geolocation.getCurrentPosition(function(position) {
      initialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
      map.setCenter(initialLocation);
    }, function() {
      handleNoGeolocation(browserSupportFlag);
    });
  // Try Google Gears Geolocation
  } else if (google.gears) {
    browserSupportFlag = true;
    var geo = google.gears.factory.create('beta.geolocation');
    geo.getCurrentPosition(function(position) {
      initialLocation = new google.maps.LatLng(position.latitude,position.longitude);
      map.setCenter(initialLocation);
    }, function() {
      handleNoGeoLocation(browserSupportFlag);
    });
  // Browser doesn't support Geolocation
  } else {
    browserSupportFlag = false;
    handleNoGeolocation(browserSupportFlag);
  }
  
  function handleNoGeolocation(errorFlag) {
    if (errorFlag == true) {
      alert("Geolocation service failed.");
      initialLocation = newyork;
    } else {
      alert("Your browser doesn't support geolocation. We've placed you in Delhi.");
      initialLocation = siberia;
    }
    map.setCenter(initialLocation);
  }
  //we now change the lat long values
  //This function runs for the first time only
  //when the location is changed using the geolocation feature
  map.center_changed = function(){
	  marker = new google.maps.Marker({
			position: map.getCenter(), 
			map: map,
			draggable:true,
			animation: google.maps.Animation.DROP,
			title:document.getElementById('form_left').elements["name"].value
		});
		map.center_changed=changeMarkerPosition;	//this function runs from the next time.
		marker.position_changed = function(){
			//we update the positions in the text boxes.
			document.getElementById('form_left').elements["latitude"].value = marker.getPosition().lat();
			document.getElementById('form_left').elements["longitude"].value = marker.getPosition().lng();
			geocoder.geocode({'latLng' : marker.getPosition()},function(results,status){
				if (status == google.maps.GeocoderStatus.OK) {
					var state = results[0].address_components[results[0].address_components.length-2].long_name;
					var address = results[0].address_components[0].long_name+"\n"+results[0].address_components[1].long_name;
					$('#address').val(address);
					$('#state').val(state);
				}
			});
		}
	}
}
function changeMarkerPosition(){
	//change the place of the marker to the map center
}
$(initialize);
function pointOnMap(){
	geocoder.geocode( { 'address': document.forms[0].name.value}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        map.setCenter(results[0].geometry.location);
		marker.setPosition(map.getCenter());
      }
    });
}
$(document).ready(function() {
  $(document.forms[0].name).change(function(){marker.setTitle(document.forms[0].name.value);});
});

</script>

<div id="map"></div>
<form method="post" id="form_left">
	<table>
		<form method="post">
		<tr><td>Institute Name</td><td><input required placeholder="Enter Institute Name here" type="text" name="name" value="" /><button onclick="pointOnMap();return false;">Find</button></td></tr>
		<tr><td>Latitude</td><td><input id="lat" type="text" required name="latitude" value="" /></td></tr>
		<tr><td>Longitude</td><td><input id="lng" type="text" required name="longitude" value="" /></td></tr>
		<tr><td>Address</td><td><textarea id="address" required placeholder="Enter institute's address" rows="4" cols="30" name="address" ></textarea></td></tr>
		<tr><td>State</td>
		<td><input placeholder = "Enter state" required name="state" id="state"></td>
		<!--<td><select name="associations[institute][]">
			<option value="state:0-institute">Andaman and Nicobar Island</option>
			<option value="state:1-institute">Arunachal Pradesh</option>
			<option value="state:2-institute">Delhi</option>
			<option value="state:3-institute">Goa</option>
			<option value="state:4-institute">Gujarat</option>
			<option value="state:5-institute">Haryana</option>
			<option value="state:6-institute">Jammu and Kashmir</option>
			<option value="state:7-institute">Karnataka</option>
			<option value="state:8-institute">Kerala</option>
			<option value="state:9-institute">Lakshadweep</option>
			<option value="state:10-institute">Maharashtra</option>
			<option value="state:11-institute">Puducherry</option>
			<option value="state:12-institute">Punjab</option>
			<option value="state:13-institute">Rajasthan</option>
			<option value="state:14-institute">Tamil Nadu</option>
			<option value="state:15-institute">Tripura</option>
			<option value="state:16-institute">Uttarakhand</option>
		</select></td>-->
		</tr>
		<tr><td>Phone</td><td><input required maxlength="30" type="text" name="phone" value="" /></td></tr>
		<tr><td>Year Of Establishment</td><td><input required type="number" min="1800" max="2012" name="year" value="" /></td></tr>
		<tr><td>Home Page</td><td><input novalidate type="url" placeholder="Institute's website" name="url" value="" /></td></tr>
		<tr><td><input type="reset" value="Clear"></td><td><input type="submit" value="Register"></td></tr>
		</form>
	</table>
</form>
