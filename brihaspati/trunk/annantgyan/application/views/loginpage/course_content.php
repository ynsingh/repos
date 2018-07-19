        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <!--<link href="<?php //echo base_url('assets/css');?>/bootstrap.min1.css" rel="stylesheet">-->
        <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        
<div class="container" style="margin-top: 10px;border:1px solid black;">
    <div class="row">
        <div class="col col-sm-4">
            <ul class="nav nav-tabs nav-stacked text-center" role="tablist" style="color:#79522f;">
                <li role="presentation" class="active"><a href="#intro" aria-controls="home" role="tab" data-toggle="tab" style="color:#79522f;">Introduction</a></li>
                <li role="presentation"><a href="#wcc" aria-controls="profile" role="tab" data-toggle="tab" style="color:#79522f;">Weekly Course Content </a></li>
                <li role="presentation"><a href="#feedback" aria-controls="messages" role="tab" data-toggle="tab" style="color:#79522f;"> Feedback</a></li>
                <li role="presentation"><a href="#ocg" aria-controls="messages" role="tab" data-toggle="tab" style="color:#79522f;">Online Certification Generation </a></li>
            </ul>
        </div>
        <div class="col col-sm-8" style="font-size: 18px;">
            <div class="row tab-content">
                <div role="tabpanel" class="tab-pane fade active in" id="intro">
                    Course introduction
                </div>
                <div role="tabpanel" class="tab-pane fade" id="wcc">
                   Weekly Course Content 
                </div>
                <div role="tabpanel" class="tab-pane fade" id="feedback">
                   feedback
                </div>
                 <div role="tabpanel" class="tab-pane fade" id="ocg">
                   Online Certification Generation
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="border:1px solid black;">
        <ul class="list-inline pull-right">
            <li><button type="button" class="btn btn-default prev-step"><span class="glyphicon glyphicon-chevron-left"></span> Previous</button></li>
            <li><button type="button" class="btn btn-default next-step">Next <span class="glyphicon glyphicon-chevron-right"></span></button></li>
           <!-- <li><button type="button" class="btn btn-primary ">Save</button></li>-->
        </ul>                
    </div>
</div>
<script type="text/javascript">
    
    $(document).ready(function () {

    $(".next-step").click(function (e) {

        var $active = $('.nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.nav-tabs li.active');
        prevTab($active);

    });
});
function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}
</script>