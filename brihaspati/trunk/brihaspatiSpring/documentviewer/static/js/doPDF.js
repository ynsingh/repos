/**
 * jQuery.ScrollTo
 * @id jQuery.scrollTo
 * @id jQuery.fn.scrollTo
 * @param {String, Number, DOMElement, jQuery, Object} target Where to scroll the matched elements.
 *        The different options for target are:
 *            - A number position (will be applied to all axes).
 *              - A string position ('44', '100px', '+=90', etc ) will be applied to all axes
 *              - A jQuery/DOM element ( logically, child of the element to scroll )
 *              - A string selector, that will be relative to the element to scroll ( 'li:eq(2)', etc )
 *              - A hash { top:x, left:y }, x and y can be any kind of number/string like above.
 *               - A percentage of the container's dimension/s, for example: 50% to go to the middle.
 *              - The string 'max' for go-to-end.
 * @param {Number} duration The OVERALL length of the animation, this argument can be the settings object instead.
 * @param {Object,Function} settings Optional set of settings or the onAfter callback.
 *       @option {String} axis Which axis must be scrolled, use 'x', 'y', 'xy' or 'yx'.
 *       @option {Number} duration The OVERALL length of the animation.
 *       @option {String} easing The easing method for the animation.
 *       @option {Boolean} margin If true, the margin of the target element will be deducted from the final position.
 *       @option {Object, Number} offset Add/deduct from the end position. One number for both axes or { top:x, left:y }.
 *       @option {Object, Number} over Add/deduct the height/width multiplied by 'over', can be { top:x, left:y } when using both axes.
 *       @option {Boolean} queue If true, and both axis are given, the 2nd axis will only be animated after the first one ends.
 *       @option {Function} onAfter Function to be called after the scrolling ends.
 *       @option {Function} onAfterFirst If queuing is activated, this function will be called after the first scrolling ends.
 * @return {jQuery} Returns the same jQuery object, for chaining.
 *
 * @desc Scroll to a fixed position
 * @example $('div').scrollTo( 340 );
 *
 * @desc Scroll relatively to the actual position
 * @example $('div').scrollTo( '+=340px', { axis:'y' } );
 * 
 *  @dec Scroll using a selector (relative to the scrolled element)
 *  @example $('div').scrollTo( 'p.paragraph:eq(2)', 500, { easing:'swing', queue:true, axis:'xy' } );
 * 
 *  @ Scroll to a DOM element (same for jQuery object)
 *  @example var second_child = document.getElementById('container').firstChild.nextSibling;
 *  $('#container').scrollTo( second_child, { duration:500, axis:'x', onAfter:function(){
 *  alert('scrolled!!');
 * }});
 * 
 *  @desc Scroll on both axes, to different values
 *  @example $('div').scrollTo( { top: 300, left:'+=200' }, { axis:'xy', offset:-20 } );
 **/
;(function( $ ){
        var $scrollTo = $.scrollTo = function( target, duration, settings ){
                $(window).scrollTo( target, duration, settings );
        };

        $scrollTo.defaults = {
                axis:'xy',
                duration: parseFloat($.fn.jquery) >= 1.3 ? 0 : 1
        };

	// Returns the element that needs to be animated to scroll the window.
	//         // Kept for backwards compatibility (specially for localScroll & serialScroll)
	$scrollTo.window = function( scope ){
                return $(window)._scrollable();
        };
	// Hack, hack, hack :)
	//         // Returns the real elements to scroll (supports window/iframes, documents and regular nodes)
	$.fn._scrollable = function(){
                return this.map(function(){
                        var elem = this,
                                isWin = !elem.nodeName || $.inArray( elem.nodeName.toLowerCase(), ['iframe','#document','html','body'] ) != -1;

                                if( !isWin )
                                        return elem;

                        var doc = (elem.contentWindow || elem).document || elem.ownerDocument || elem;

                        return $.browser.safari || doc.compatMode == 'BackCompat' ?
                                doc.body :
                                doc.documentElement;
                });
        };

        $.fn.scrollTo = function( target, duration, settings ){
                if( typeof duration == 'object' ){
                        settings = duration;
                        duration = 0;
                }
                if( typeof settings == 'function' )
                        settings = { onAfter:settings };

                if( target == 'max' )
                        target = 9e9;

                settings = $.extend( {}, $scrollTo.defaults, settings );
		 // Speed is still recognized for backwards compatibility
		 duration = duration || settings.speed || settings.duration;
		// Make sure the settings are given right
		settings.queue = settings.queue && settings.axis.length > 1;
		if( settings.queue )
			 // Let's keep the overall duration
			 duration /= 2;
                settings.offset = both( settings.offset );
                settings.over = both( settings.over );

                return this._scrollable().each(function(){
                        var elem = this,
                                $elem = $(elem),
                                targ = target, toff, attr = {},
                                win = $elem.is('html,body');

                        switch( typeof targ ){
				// A number will pass the regex
				case 'number':
                                case 'string':
                                        if( /^([+-]=)?\d+(\.\d+)?(px|%)?$/.test(targ) ){
                                                targ = both( targ );
						// We are done
						break;
                                        }
					// Relative selector, no break!
					targ = $(targ,this);
                                case 'object':
					// DOMElement / jQuery
					if( targ.is || targ.style )
					// Get the real position of the target
					toff = (targ = $(targ)).offset();
                        }
                        $.each( settings.axis.split(''), function( i, axis ){
                                var Pos = axis == 'x' ? 'Left' : 'Top',
                                        pos = Pos.toLowerCase(),
                                        key = 'scroll' + Pos,
                                        old = elem[key],
                                        max = $scrollTo.max(elem, axis);

                                if( toff ){// jQuery / DOMElement
					attr[key] = toff[pos] + ( win ? 0 : old - $elem.offset()[pos] );
					
					// If it's a dom element, reduce the margin
					if( settings.margin ){
                                                attr[key] -= parseInt(targ.css('margin'+Pos)) || 0;
                                                attr[key] -= parseInt(targ.css('border'+Pos+'Width')) || 0;
                                        }

                                        attr[key] += settings.offset[pos] || 0;

                                        if( settings.over[pos] )
						// Scroll to a fraction of its width/height
						attr[key] += targ[axis=='x'?'width':'height']() * settings.over[pos];
                                }else{
                                        var val = targ[pos];
					 // Handle percentage values
					 attr[key] = val.slice && val.slice(-1) == '%' ?
                                                parseFloat(val) / 100 * max
                                                : val;
                                }
				

				// Number or 'number'
				if( /^\d+$/.test(attr[key]) )
                                        // Check the limits
	                                attr[key] = attr[key] <= 0 ? 0 : Math.min( attr[key], max );
        			// Queueing axes
        			if( !i && settings.queue ){
					// Don't waste time animating, if there's no need.
					if( old != attr[key] )
						// Intermediate animation
						animate( settings.onAfterFirst );
					// Don't animate this axis again in the next iteration.
					delete attr[key];
                                }
                        });

                        animate( settings.onAfter );

                        function animate( callback ){
                                $elem.animate( attr, duration, settings.easing, callback && function(){
                                        callback.call(this, target, settings);
                                });
                        };

                }).end();
        };
	// Max scrolling position, works on quirks mode
	//         // It only fails (not too badly) on IE, quirks mode.
	$scrollTo.max = function( elem, axis ){
                var Dim = axis == 'x' ? 'Width' : 'Height',
                        scroll = 'scroll'+Dim;

                if( !$(elem).is('html,body') )
                        return elem[scroll] - $(elem)[Dim.toLowerCase()]();

                var size = 'client' + Dim,
                        html = elem.ownerDocument.documentElement,
                        body = elem.ownerDocument.body;

                return Math.max( html[scroll], body[scroll] )
                         - Math.min( html[size]  , body[size]   );

        };

        function both( val ){
                return typeof val == 'object' ? val : { top:val, left:val };
        };

})( jQuery );


/**
 * jQuery based pdf viewer
 * Copyright (c) 2007-2010 Deep Shah - deep(at)gitshah(dot)com | http://www.gitshah.com/
 * Licensed under Apache License 2.0.
 * Date: 10/21/2010
 *
 * @projectDescription This project enables users to embed a PDF viewer and an editor directly in the browser.
 * It will integrate seamlessly with the web application where the viewer is embedded..
 * Works with jQuery +1.2.6. Tested on FF 2/3, IE 6/7/8, Opera 9.5/6, Safari 3, Chrome 1 on Windowns 7.
 *
 * @author Deep Shah
 * @version 0.1
 **/
function PDFViewer(config) {
    this.config = config;
    this.document = this.config.document;
    this.imageUrlCreator = new UrlCreator(this.config.urlParts, this.document);
    this.viewerSelector = ".viewer";

    this.show = function() {
        var _this = this;
        setTimeout(function() {
            _this.init();
            _this.clearViewer();
            _this.addMenu();
            _this.addPDFPages();
        }, 10);
    };

    this.clearViewer = function() {
        $(this.viewerSelector).empty();
    };

    this.addMenu = function() {
        this.menu = new Menu(this, this.document);
        this.menu.create($(this.viewerSelector));
    };

    this.addPDFPages = function() {
        this.pages = new Pages(this.document, this.imageUrlCreator, this);
        this.pages.create($(this.viewerSelector));
    };

    this.zoomIn = function() {
        var newSize = new Zoom(this.document.size).nextValue();
        if(newSize == this.document.size) return;
        this.document.size = newSize;
        this.show();
    };

    this.zoomOut = function() {
        var newSize = new Zoom(this.document.size).previousValue();
        if(newSize == this.document.size) return;
        this.document.size = newSize;
        this.show();
    };

    this.previous = function() {
        this.pages.previous();
    };

    this.next = function() {
        this.pages.next();
    };

    this.scrollToPage = function(pageNumber) {
        this.pages.scrollTo(pageNumber);
    };

    this.currentPageUpdated = function(currentPageNumber) {
        this.menu.updateCurrentPage(currentPageNumber);
    };

    this.download = function() {
        window.open(this.imageUrlCreator.createDownloadUrl(), "document");
    }

    this.init = function() {
        if(!this.document.strategy) {
            this.document.strategy = "lru";
        }
        if(!this.document.size) {
            //this.document.size = "medium";
            this.document.size = "large";
        }
    };
};

function Pages(document, imageUrlCreator, pdfViewer) {
    this.container = "pagesContainer";
    this.containerSelector = "#" + this.container;
    this.pages = {};
    this.document = document;
    this.imageUrlCreator = imageUrlCreator;
    this.pdfViewer = pdfViewer;
    this.currentPageIndex = -1;

    this.postCreate = function() {
        var _this = this;
        $(this.containerSelector).scroll(function() {
            _this.handelScroll();
        });
        this.handelScroll();
    };

    this.createContainer = function(viewer) {
        viewer.append("<div id='" + this.container +"' class='pages'></div>");
    };

    this.create = function(viewer) {
        this.createContainer(viewer)
        for(var i=0; i<this.document.numberOfPages; i++) {
            this.addPage(i);
        }
        this.postCreate();
    };

    this.addPage = function(pageNumber) {
        this.pages[pageNumber] = new Page(pageNumber)
        this.pages[pageNumber].create($(this.containerSelector), this.imageUrlCreator, this.document.size);
    };

    this.scrollTo = function(pageNumber) {
        $(this.containerSelector).scrollTo($(this.pages[pageNumber - 1].pageContainerSelector), 0)
    };

    this.handelScroll = function() {
        this.updateCurrentPageIndex();
        this.notifyCurrentPageUpdated();
        this.loadPagesAroundCurrentPage();
    };

    this.loadPagesAroundCurrentPage = function() {
        for(var j=this.currentPageIndex-1; j <= this.currentPageIndex+1; j++) {
            if(j >= 0 && j < this.document.numberOfPages) {
                this.pages[j].loadIfRequired();
            }
        }
    };

    this.notifyCurrentPageUpdated = function() {
	$(".currentPageNumber").val(this.currentPageIndex + 1);
        //this.pdfViewer.currentPageUpdated(this.currentPageIndex + 1);
    };

    this.updateCurrentPageIndex = function() {
        var newPageIndex = this.currentPageIndex;
        for(var i=0; i<this.document.numberOfPages; i++) {
            if(this.pages[i].isCurrentlyVisible($(this.containerSelector).scrollTop(),
                    $(this.containerSelector).height() * 0.65)) {
                newPageIndex = i;
                break;
            }
        }
        this.currentPageIndex = newPageIndex;
    };

    this.previous = function() {
        var newScrollTop = $(this.containerSelector).scrollTop() - this.pages[0].height - 14;
        if(newScrollTop < 0) newScrollTop = 0;
        $(this.containerSelector).scrollTo(newScrollTop, 500);
    };

    this.next = function() {
        $(this.containerSelector).scrollTo($(this.containerSelector).scrollTop() + this.pages[0].height + 14, 500);
    };
};

function Page(pageNumber) {
    this.pageNumber = pageNumber;
    this.dummyPage = "dummyPage_" + this.pageNumber;
    this.dummyPageSelector = "#" + this.dummyPage;
    this.page = "page_" + this.pageNumber;
    this.pageSelector = "#" + this.page;
    this.pageContainer = "pageContainer_" + this.pageNumber;
    this.pageContainerSelector = "#" + this.pageContainer;
    this.isLoaded = false;

    this.show = function() {
        $(this.dummyPageSelector).addClass("hidden");
        $(this.pageSelector).removeClass("hidden");
    };

    this.create = function(container, imageUrlCreator, size) {
        container.append("<div class='page' id='" + this.pageContainer + "'>" +
                "<div id='" + this.dummyPage + "' class='" + size + " border load_on_demand'>" +
                "Getting the page " + (this.pageNumber + 1) + "..." +
                "</div><img alt='" + imageUrlCreator.createFor(this.pageNumber)
                    + "' onload='new Page(" + this.pageNumber + ").show();' id='"
                +  this.page + "' class='" + size + " hidden border grab'/>  " +
                "</div>");
        this.offset = $(this.pageContainerSelector).offset();
        this.height = $(this.pageContainerSelector).height();
        this.setupEventScroll();
    };

    this.isToBeLoaded = function() {
        if(this.isLoaded) return false;

        var windowTop = $(this.dummyPageSelector).height() * -1;
        var windowBottom = $(this.dummyPageSelector).height() * 2;
        var pageTop = $(this.dummyPageSelector).offset().top;
        var pageBottom = $(this.dummyPageSelector).height() + pageTop;
        return (windowTop <= pageTop && windowBottom >= pageTop)
                || (windowTop <= pageBottom && windowBottom >= pageBottom);
    };

    this.isCurrentlyVisible = function(windowTop, windowHeight) {
        var pageTop = this.offset.top;
        var pageBottom = this.offset.top + this.height;
        var windowBottom = windowTop + windowHeight;
        return (windowTop <= pageTop && windowBottom >= pageTop)
                || (pageTop <= windowTop && pageBottom >= windowBottom);
    };

    this.loadIfRequired = function() {
        if(this.isLoaded) return;
        this.load();
    };

    this.load = function() {
        this.isLoaded = true;
        $(this.dummyPageSelector).removeClass("load_on_demand");
        var pageImage = $(this.dummyPageSelector).next();
        pageImage.attr("src", pageImage.attr("alt"));
        pageImage.attr("alt", "");
    };

    this.setupEventScroll = function() {
        var _this = this;
        new GrabDragScroller(this.pageSelector, $(this.pageContainerSelector).parent()).scroll();
    };
};

function UrlCreator(urlParts, document) {
    this.urlParts = urlParts;
    this.document = document;
    this.urlPrefix = this.urlParts.contextRoot + this.urlParts.filterMapping;

    this.createFor = function(pageNumber) {
                + "&strategy=" + this.document.strategy
                + "&page=" + pageNumber
                + "&size=" + this.document.size);
        return this.urlPrefix + "/page/show?id=" + this.document.id
                + "&strategy=" + this.document.strategy
                + "&page=" + pageNumber
                + "&size=" + this.document.size;
    };

    this.createDownloadUrl = function() {
                + "&strategy=" + this.document.strategy
                + "&download=true");
        return this.urlPrefix + "/download/document?id=" + this.document.id
                + "&strategy=" + this.document.strategy
                + "&download=true";
	
    };
};

function Menu(pdfViewer, document) {
    this.pdfViewer = pdfViewer;
    this.document = document;

    this.create = function(viewer) {
        var _this = this;
        viewer.append("<div id='menuContainer' class='menu'>"
                + "<span class='menuItem download'>Download</span>"
                + "<span class='menuItem seperator'></span>"
                + "<span class='menuItem previous'></span>"
                + "<span class='menuItem next'></span>"
                + "<input type='text' maxlength='3' size='2' id='currentPageNumber' class='menuItem currentPageNumber' value='1'/>"
                + "<span class='menuItem totalPages'>/ " + document.numberOfPages + "</span>"
                + "<span class='menuItem seperator'></span>"
                + "<span class='menuItem zoomIn'></span>"
                + "<span class='menuItem zoomOut'></span>"
                + "</div>");

        $(".download").click(function() {
            _this.pdfViewer.download();
        });
        $(".previous").click(function() {
            _this.pdfViewer.previous();
            return false;
        })
        $(".next").click(function() {
            _this.pdfViewer.next();
            return false;
        })
        $(".zoomIn").click(function() {
            _this.pdfViewer.zoomIn();
            return false;
        });
        $(".zoomOut").click(function() {
            _this.pdfViewer.zoomOut();
            return false;
        });
        $(".currentPageNumber").blur(function() {
            _this.scrollToPage(this);
        }).keypress(function(event) {
            if(event.keyCode != "13") {
                return;
            }
            _this.scrollToPage(this)
        })
    };

    this.updateToValidPageNumberIfRequired = function(currentPageNumberElement) {
        if($(currentPageNumberElement).val() > this.document.numberOfPages) {
            $(currentPageNumberElement).val(this.document.numberOfPages);
        }
        if($(currentPageNumberElement).val() < 1 || isNaN($(currentPageNumberElement).val())) {
            $(currentPageNumberElement).val(1);
        }
    };

    this.updateCurrentPage = function(currentPageNumber) {
        $(".currentPageNumber").val(currentPageNumber);
    };

    this.scrollToPage = function(currentPageNumberElement) {
        this.updateToValidPageNumberIfRequired(currentPageNumberElement);
        this.pdfViewer.scrollToPage($(currentPageNumberElement).val());
    };
};

function Zoom(currentValue) {
    this.currentValue = currentValue;

    this.nextValue = function() {
        if(this.currentValue == "small") return "medium";
        if(this.currentValue == "medium") return "large";
        return this.currentValue;
    };

    this.previousValue = function() {
        if(this.currentValue == "medium") return "small";
        if(this.currentValue == "large") return "medium";
        return this.currentValue;
    };

}

function GrabDragScroller(elementId, containerId) {
    this.elementId = elementId;
    this.containerId = containerId;

    this.scroll = function() {
        var _this = this;
        $(this.elementId).mousedown(function(event){
                    _this.startGrabbing();
                    _this.scrollStartY = event.pageY;
                    return false;
                }).mousemove(function(event){
                    if (!_this.isgrabbing) return true;
                    _this.scrollTo(_this.scrollStartY - event.pageY);
                    _this.scrollStartY = event.pageY;
                    return false;
                })
                .mouseout(function(){ _this.stopGrabbing() })
                .mouseup(function(){ _this.stopGrabbing() })

    };

    this.startGrabbing = function(){
        this.isgrabbing = true;
        $(this.elementId).removeClass("grab");
        $(this.elementId).addClass("grabbing");
    };

    this.stopGrabbing = function(){
        this.isgrabbing = false;
        $(this.elementId).removeClass("grabbing");
        $(this.elementId).addClass("grab");
    };

    this.scrollTo = function(distanceFromTop){
            var totalDistanceFromTop = $(this.containerId).scrollTop() + distanceFromTop;
            $(this.containerId).scrollTop(totalDistanceFromTop);
    };
}

