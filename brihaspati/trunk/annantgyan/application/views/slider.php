<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<style type="text/css">
	/*
inspired from http://codepen.io/Rowno/pen/Afykb
& https://jsfiddle.net/q0rgL8ws/
*/
.carousel-fade .carousel-inner .item {
	height:550px;
  opacity: 0;
  transition-property: opacity;
    overflow:hidden;
}
.item.active img {
    transition: transform 4000ms linear 0s;
    /* This should be based on your carousel setting. For bs, it should be 5second*/
    transform: scale(1.50, 1.50);
}
.carousel-fade .carousel-inner .active {
  opacity: 1;
}

.carousel-fade .carousel-inner .active.left,
.carousel-fade .carousel-inner .active.right {
  left: 0;
  opacity: 0;
  z-index: 1;
}

.carousel-fade .carousel-inner .next.left,
.carousel-fade .carousel-inner .prev.right {
  opacity: 1;
}

.carousel-fade .carousel-control {
  z-index: 2;
}

/*
WHAT IS NEW IN 3.3: "Added transforms to improve carousel performance in modern browsers."
now override the 3.3 new styles for modern browsers & apply opacity
*/
@media all and (transform-3d), (-webkit-transform-3d) {
    .carousel-fade .carousel-inner > .item.next,
    .carousel-fade .carousel-inner > .item.active.right {
      opacity: 0;
      -webkit-transition-property: opacity;
  -moz-transition-property: opacity;
  -o-transition-property: opacity;
  transition-property: opacity;
    }
    .carousel-fade .carousel-inner > .item.prev,
    .carousel-fade .carousel-inner > .item.active.left {
      opacity: 0;
       -webkit-transition-property: opacity;
  -moz-transition-property: opacity;
  -o-transition-property: opacity;
  transition-property: opacity;
    }
    .carousel-fade .carousel-inner > .item.next.left,
    .carousel-fade .carousel-inner > .item.prev.right,
    .carousel-fade .carousel-inner > .item.active {
      opacity: 1;
   -webkit-transition-property: opacity;
  -moz-transition-property: opacity;
  -o-transition-property: opacity;
  transition-property: opacity;
}
.carousel-caption h4 {
	font-size: 3em;
	line-height: 1.5em;
	text-align: center;
	color: #fff;
	font-weight: 700;
	letter-spacing: 4px;
	
}
.carousel-caption h4 span {
	color: #93c83f;
	
	display: block;
}
</style>
<div class="container" >
<div id="carousel" class="carousel slide carousel-fade" data-ride="carousel" >
    <ol class="carousel-indicators">
        <li data-target="#carousel" data-slide-to="0" class="active"></li>
        <li data-target="#carousel" data-slide-to="1"></li>
        <li data-target="#carousel" data-slide-to="2"></li>
         <li data-target="#carousel" data-slide-to="3"></li>
          <li data-target="#carousel" data-slide-to="4"></li>
          <li data-target="#carousel" data-slide-to="5"></li>
    </ol>
    <!-- Carousel items -->
    <div class="carousel-inner carousel-zoom" >
        <div class="active item"><img class="img-responsive" src="images/bann1.jpg" style="height:550px;width:100%;">
          <div class="carousel-caption">
           <h4>Play, Explore & <span>Learn</span></h4>
           
          </div>
        </div>
  <div class="item"><img class="img-responsive" src="images/bann10.jpg" style="height:550px;width:100%;">
          <div class="carousel-caption">
           <h4></h4>
          </div>
        </div>
        <div class="item"><img class="img-responsive" src="images/bann2.jpg" style="height:550px;width:100%;">
          <div class="carousel-caption">
           <h4>Play, Explore & <span>Learn</span></h4>
            
          </div>
        </div>
        <div class="item"><img class="img-responsive" src="images/banner.jpg" style="height:550px;width:100%;">
          <div class="carousel-caption">
           <h4>Play, Explore & <span>Learn</span></h4>
          </div>
        </div>
         <div class="item"><img class="img-responsive" src="images/bann7.jpg" style="height:550px;width:100%;">
          <div class="carousel-caption">
           <h4>Play, Explore & <span>Learn</span></h4>
          </div>
        </div>
        <div class="item"><img class="img-responsive" src="images/bann8.png" style="height:550px;width:100%;">
          <div class="carousel-caption">
           <h4>Play, Explore & <span>Learn</span></h4>
          </div>
        </div>
        
       </div>
    <!-- Carousel nav    <a class="carousel-control left" href="#carousel" data-slide="prev">‹</a>
    <a class="carousel-control right" href="#carousel" data-slide="next">›</a>-->
    <a class="carousel-control left" href="#carousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
    <a class="carousel-control right" href="#carousel" data-slide="next"> <span class="glyphicon glyphicon-chevron-right"></span></a>
  
</div>
</div>