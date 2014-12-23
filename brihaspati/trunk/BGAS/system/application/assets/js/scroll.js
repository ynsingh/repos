$(function() {
				var $elem = $('#content');
				
			$(window).scroll(function(){
			if($(window).scrollTop()>600){
			$("#scroll_up").fadeIn();
			}else{
			$("#scroll_up").fadeOut();}
			});	
			 $(window).scroll(function(){
			if($(window).scrollTop()<$elem.height()-1200){
			$("#scroll_down").fadeIn();
			}else{
			$("#scroll_down").fadeOut();}
				});
				$(window).bind('scrollstart', function(){
					$('#scroll_up,#scroll_down').stop().animate({'opacity':'0.5'});
				});
				$(window).bind('scrollstop', function(){
					$('#scroll_up,#scroll_down').stop().animate({'opacity':'1'});
				});
				
				$('#scroll_down').click(
					function (e) {
						$('html, body').animate({scrollTop: $elem.height()}, 800);
					}
				);
				$('#scroll_up').click(
					function (e) {
						$('html, body').animate({scrollTop: '0px'}, 800);
					}
				);
            });
