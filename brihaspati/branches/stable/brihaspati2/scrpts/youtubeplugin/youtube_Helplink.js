
(function ($) {
    var Helpvideo_YouTube = null;
    var methods = {
        init: function (Values) {
            Values = $.extend({}, $.fn.YouTubePopup.defaults, Values);
            if (Helpvideo_YouTube == null) {
                Helpvideo_YouTube = $('<div></div>').css({ display: 'none', padding: 0 });
                $('body').append(Helpvideo_YouTube);
                Helpvideo_YouTube.dialog({ autoOpen: false, resizable: false, draggable: Values.draggable, modal: Values.modal,
                    close: function () {
						Helpvideo_YouTube.html(''); 
						$(".ui-dialog-titlebar").show();
					}
                });
            }

            return this.each(function () {
                var obj = $(this);
                var data = obj.data('YouTube');
                if (!data) { 
                    obj.data('YouTube', { target: obj, 'active': true });
                    $(obj).bind('click.YouTubePopup', function () {
                        var youtubeId = Values.youtubeId;
                        if ($.trim(youtubeId) == '') youtubeId = obj.attr(Values.idAttribute);
                        var videoTitle = $.trim(Values.title);
						if (videoTitle == '') {
							if (Values.useYouTubeTitle) setYouTubeTitle(youtubeId);
							else videoTitle = obj.attr('title');
						}

                        
                        var YouTubeURL = "http://www.youtube.com/embed/" + youtubeId + "?rel=0&showsearch=0&autohide=" + Values.autohide;
                        YouTubeURL += "&autoplay=" + Values.autoplay + "&color1=" + Values.color1 + "&color2=" + Values.color2;
                        YouTubeURL += "&controls=" + Values.controls + "&fs=" + Values.fullscreen + "&loop=" + Values.loop;
                        YouTubeURL += "&hd=" + Values.hd + "&showinfo=" + Values.showinfo + "&color=" + Values.color + "&theme=" + Values.theme;

                        
                        Helpvideo_YouTube.html(getYouTubePlayer(YouTubeURL, Values.width, Values.height));
                        Helpvideo_YouTube.dialog({ 'width': 'auto', 'height': 'auto' }); 
                        Helpvideo_YouTube.dialog({ 'minWidth': Values.width, 'minHeight': Values.height, title: videoTitle });
                        Helpvideo_YouTube.dialog('open');
						$(".ui-widget-overlay").fadeTo('fast', Values.overlayOpacity); 
						if(Values.hideTitleBar && Values.modal){ 
							$(".ui-dialog-titlebar").hide(); 
							$(".ui-widget-overlay").click(function () { Helpvideo_YouTube.dialog("close"); }); 
						}
						if(Values.clickOutsideClose && Values.modal){ 
							$(".ui-widget-overlay").click(function () { Helpvideo_YouTube.dialog("close"); });
						}
                        return false;
                    });
                }
            });
        },
        destroy: function () {
            return this.each(function () {
                $(this).unbind(".YouTubePopup");
                $(this).removeData('YouTube');
            });
        }
    };

    function getYouTubePlayer(URL, width, height) {
        var YouTubePlayer = '<iframe title="YouTube video player" style="margin:0; padding:0;" width="' + width + '" ';
        YouTubePlayer += 'height="' + height + '" src="' + URL + '" frameborder="0" allowfullscreen></iframe>';
        return YouTubePlayer;
    }
	
	function setYouTubeTitle(id) {
		var url = "https://gdata.youtube.com/feeds/api/videos/" + id + "?v=2&alt=json";
		$.ajax({ url: url, dataType: 'jsonp', cache: true, success: function(data){ Helpvideo_YouTube.dialog({ title: data.entry.title.$t }); } });
	}

    $.fn.YouTubePopup = function (method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.YouTubePopup');
        }
    };

    
    $.fn.YouTubePopup.defaults = {
		'youtubeId': '',
		'title': '',
		'useYouTubeTitle': true,
		'idAttribute': 'rel',
		'draggable': false,
		'modal': true,
		'width': 640,
		'height': 480,
		'hideTitleBar': false,
		'clickOutsideClose': false,
		'overlayOpacity': 0.5,
		'autohide': 2,
		'autoplay': 1,
		'color': 'red',
		'color1': 'FFFFFF',
		'color2': 'FFFFFF',
		'controls': 1,
		'fullscreen': 1,
		'loop': 0,
		'hd': 1,
		'showinfo': 0,
		'theme': 'light'
    };
})(jQuery);
