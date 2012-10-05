;(function( $ ) {
  $.fn.aeImageResize = function( params ) {
    var aspectRatio = 0
      ,	isIE6 = $.browser.msie && (6 == ~~ $.browser.version)
      ;
    if ( !params.height && !params.width ) {
      return this;
    }
    if ( params.height && params.width ) {
      aspectRatio = params.width / params.height;
    }
    return this.one( "load", function() {
      this.removeAttribute( "height" );
      this.removeAttribute( "width" );
      this.style.height = this.style.width = "";
      var imgHeight = this.height
        , imgWidth = this.width
        , imgAspectRatio = imgWidth / imgHeight
        , bxHeight = params.height
        , bxWidth = params.width
        , bxAspectRatio = aspectRatio;
      if ( !bxAspectRatio ) {
        if ( bxHeight ) {
          bxAspectRatio = imgAspectRatio + 1;
        } else {
          bxAspectRatio = imgAspectRatio - 1;
        }
      }
      if ( (bxHeight && imgHeight > bxHeight) || (bxWidth && imgWidth > bxWidth) ) {

        if ( imgAspectRatio > bxAspectRatio ) {
          bxHeight = ~~ ( imgHeight / imgWidth * bxWidth );
        } else {
          bxWidth = ~~ ( imgWidth / imgHeight * bxHeight );
        }

        this.height = bxHeight;
        this.width = bxWidth;
      }
    })
    .each(function() {
      if ( this.complete || isIE6 ) {
        $( this ).trigger( "load" );
      }
      this.src = this.src;
    });
  };
})( jQuery );
