(function(){var version="1.4.1";var author="Vova Feldman";var server="94.249.188.201"
var global=this;var is_migrating=false;if(typeof(RW_Advanced_Options)==="undefined"){RW_Advanced_Options={};}
if(typeof(String.prototype.isEmpty)!=="function"){String.prototype.isEmpty=function(){return(this.length===0);};}
if(typeof(String.prototype.ltrim)!=="function"){String.prototype.ltrim=function(str)
{if(typeof(str)=="undefined"){str="\\s";}
return this.replace(new RegExp("^("+str+")+"),"");};}
if(typeof(String.prototype.rtrim)!=="function"){String.prototype.rtrim=function(str)
{if(typeof(str)=="undefined"){str="\\s";}
return this.replace(new RegExp("("+str+")+$"),"");};}
if(typeof(String.prototype.trim)!=="function"){String.prototype.trim=function(str){return this.ltrim(str).rtrim(str);};}
if(typeof(String.prototype.left)!=="function"){String.prototype.left=function(index){return this.substring(0,index);};}
if(typeof(String.prototype.right)!=="function"){String.prototype.right=function(index){return this.substring(index);};}
var doc=document,body=document.body,win=window,nav=navigator;var DUMMY_STR="DUMMY",UNDEF="undefined",EMPTY_PCID="00000000-0000-0000-0000-000000000000";var is_IE=false,is_FF=false,is_CH=false,is_SF=false;if(nav.userAgent){is_IE=(-1!=nav.userAgent.indexOf("MSIE"));is_FF=(-1!=nav.userAgent.indexOf("Firefox"));is_CH=(-1!=nav.userAgent.indexOf("Chrome"));is_SF=(-1!=nav.userAgent.indexOf("Safari"));}
global.RW=function()
{var defaults=null,ratings={},elements={},options={},coptions={},langs={en:true},themes={},render_options={};var ratings_data=[];var load_call_num=0;var parseRatingProperty=function(pRating,pProperty,pErrorRetVal)
{var className=pRating.className;var prefix="rw-"+pProperty+"-";var start=className.indexOf(prefix);if(start<0){return pErrorRetVal;}
else if(start>0)
{start=className.indexOf(" "+prefix);if(start<0){return pErrorRetVal;}
start++;}
var i=start+prefix.length,len=className.length;var ret="";while(i<len&&className.charAt(i)!=" ")
{ret+=className.charAt(i);i++;}
return ret;};var getRatingId=function(pRating)
{var urid=parseRatingProperty(pRating,"urid",false);if(!RW._isNumeric(urid)){return;}
return urid;};var getAccamulatorRatingId=function(pRating)
{var uarid=parseRatingProperty(pRating,"uarid",false);if(!RW._isNumeric(uarid)||uarid<1){return"0";}
return uarid;};var getRatingClass=function(pRating){return parseRatingProperty(pRating,"class","");};var getRatingTheme=function(pRating){return parseRatingProperty(pRating,"theme","");};var getRatingLanguage=function(pRating){return parseRatingProperty(pRating,"lng","");};var extract_property=function(pRating,pUrid,pClass,pProperty,pInitialValue,pDefaultValue)
{var ret=pInitialValue;if(!RW._is(ret)||ret.isEmpty())
{if(RW._is(options[pUrid])&&RW._isString(options[pUrid][pProperty])&&!options[pUrid][pProperty].isEmpty())
{ret=options[pUrid][pProperty];}
else if(!pClass.isEmpty()&&RW._is(coptions[pClass])&&RW._isString(coptions[pClass][pProperty])&&!coptions[pClass][pProperty].isEmpty())
{ret=coptions[pClass][pProperty];}
else if(RW._isString(defaults[pProperty])&&!defaults[pProperty].isEmpty())
{ret=defaults[pProperty];}
else
{ret=pDefaultValue;}}
return ret;};var enrich_options_by_size=function(pOptions)
{if(!RW._is(pOptions)||!RW._is(pOptions.size)){return;}
pOptions.advanced=RW._getDefaultValue(pOptions.advanced,{});pOptions.advanced.font=RW._getDefaultValue(pOptions.advanced.font,{});pOptions.advanced.font.size=RW._getDefaultValue(pOptions.advanced.font.size,RW._DEF_FONT_SIZE[pOptions.size.toUpperCase()]);pOptions.advanced.layout=RW._getDefaultValue(pOptions.advanced.layout,{});pOptions.advanced.layout.lineHeight=RW._getDefaultValue(pOptions.advanced.layout.lineHeight,RW._DEF_LINE_HEIGHT[pOptions.size.toUpperCase()]);};var init_tooltip=function()
{RW._tooltip=doc.createElement("span");RW._tooltip.style.display="none";RW._tooltip.className="rw-ui-tooltip";body.appendChild(RW._tooltip);};var is_flash_installed=function()
{var SHOCKWAVE_FLASH="Shockwave Flash",SHOCKWAVE_FLASH_AX="ShockwaveFlash.ShockwaveFlash",FLASH_MIME_TYPE="application/x-shockwave-flash";var version=[0,0,0],d=null;if(RW._is(nav.plugins)&&typeof(nav.plugins[SHOCKWAVE_FLASH])=="object")
{d=nav.plugins[SHOCKWAVE_FLASH].description;if(d&&!(RW._is(nav.mimeTypes)&&nav.mimeTypes[FLASH_MIME_TYPE]&&!nav.mimeTypes[FLASH_MIME_TYPE].enabledPlugin))
{d=d.replace(/^.*\s+(\S+\s+\S+$)/,"$1");version[0]=parseInt(d.replace(/^(.*)\..*$/,"$1"),10);version[1]=parseInt(d.replace(/^.*\.(.*)\s.*$/,"$1"),10);version[2]=/[a-zA-Z]/.test(d)?parseInt(d.replace(/^.*[a-zA-Z]+(.*)$/,"$1"),10):0;return version;}}
else if(RW._is(win.ActiveXObject))
{try{var a=new ActiveXObject(SHOCKWAVE_FLASH_AX);if(a)
{d=a.GetVariable("$version");if(d)
{d=d.split(" ")[1].split(",");return[parseInt(d[0],10),parseInt(d[1],10),parseInt(d[2],10)];}}}
catch(e){}}
return false;};return{MAX:5,MIN:1,MIN_STARS:1,MAX_STARS:20,CALL_RATINGS_MAX_LEN:1000,PREFIX:"rw",CUSTOM:"custom",_TYPE:{STRING:"string",BOOL:"boolean",NUM:"numeric",SIZE:"size",DIR:"dir",ALIGN_HOR:"hor",ALIGN_VER:"ver",RTYPE:"rating_type"},_DEF_STYLES:{STAR:"yellow",NERO:"thumbs"},_DEF_FONT_SIZE:{SMALL:"12px",MEDIUM:"16px",LARGE:"20px"},_DEF_LINE_HEIGHT:{SMALL:"16px",MEDIUM:"20px",LARGE:"30px"},TYPE:{STAR:"star",NERO:"nero"},SIZE:{SMALL:"small",MEDIUM:"medium",LARGE:"large"},COLOR:{YELLOW:"yellow",RED:"red",GREEN:"green",BLUE:"blue",CUSTOM:"custom"},STYLE:{YELLOW:"yellow",RED:"red",GREEN:"green",BLUE:"blue",CUSTOM:"custom"},DIR:{LTR:"ltr",RTL:"rtl"},ALIGN:{HOR:{LEFT:"left",CENTER:"center",RIGHT:"right"},VER:{TOP:"top",MIDDLE:"middle",BOTTOM:"bottom"}},Uid:null,PCId:EMPTY_PCID,VId:null,Token:null,_tooltip:null,_defaultOptions:{lng:"en",url:"",title:"",type:"star",rclass:"",size:"small",theme:"",color:"yellow",style:"",imgUrl:{ltr:"",rtl:""},readOnly:false,reVote:true,showInfo:true,showTooltip:true,boost:{votes:0,rate:5},uarid:"0",advanced:{star:{stars:5},nero:{showDislike:true,showLike:true},font:{bold:false,italic:false,color:"#000",size:"12px",type:'arial',hover:{color:"#000"}},layout:{dir:DUMMY_STR,align:{hor:DUMMY_STR,ver:"middle"},lineHeight:"16px"},text:{rateAwful:DUMMY_STR,ratePoor:DUMMY_STR,rateAverage:DUMMY_STR,rateGood:DUMMY_STR,rateExcellent:DUMMY_STR,rateThis:DUMMY_STR,like:DUMMY_STR,dislike:DUMMY_STR,vote:DUMMY_STR,votes:DUMMY_STR,thanks:DUMMY_STR},css:{container:""}},beforeRate:null,afterRate:null},init:function(pUid,pOptions,pVid,pToken)
{var uid=pUid,options=pOptions,vid=pVid,token=pToken;if(typeof(pUid)=="object")
{uid=this._getDefaultValue(pUid.uid,null);options=this._getDefaultValue(pUid.options,null);vid=this._getDefaultValue(pUid.vid,null);token=this._getDefaultValue(pUid.token,null);}
if(this._is(options)&&this._isString(options.lng))
{this._defaultOptions.lng=options.lng;}
enrich_options_by_size(options);defaults=this._getEnrichedOptions({},this._defaultOptions);this._identify(uid,vid,token);this._setDefaults(options);},_renderFlashCookie:function()
{if((this._is(RW_Advanced_Options.blockFlash)&&true===RW_Advanced_Options.blockFlash)||false===is_flash_installed())
{this._cookieReady(EMPTY_PCID);return;}
var html='';var moviePath="http://js.rating-widget.com/RatingWidget.swf";if(is_IE){var protocol=location.href.match(/^https/i)?'https://':'http://';html+='<object id="rw_lso_flash_inner" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="'+protocol+'download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="100" height="100" align="middle"><param name="allowScriptAccess" value="always" /><param name="allowFullScreen" value="false" /><param name="movie" value="'+moviePath+'" /><param name="loop" value="false" /><param name="menu" value="false" /><param name="quality" value="best" /><param name="bgcolor" value="#ffffff" /><param name="wmode" value="transparent"/></object>';}
else
{html+='<embed id="rw_lso_flash_inner" src="'+moviePath+'" loop="false" menu="false" quality="best" bgcolor="#ffffff" width="100" height="100" align="middle" allowScriptAccess="always" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" />';}
var div=doc.createElement("div");div.id="rw_lso_flash";div.style.position="fixed";div.style.top=div.style.left="0";div.innerHTML=html;var c=RW._getByClassName("rw-js-container","div");if(c.length>0){c[0].appendChild(div);}else{body.prependChild(div);}},_cookieReady:function(pPCId)
{RW.PCId=pPCId;RW._invokeHook("RW_HOOK_INIT");if(typeof(RW_Async_Init)!==UNDEF&&RW._isCallable(RW_Async_Init)){RW_Async_Init();}},initRating:function(pURid,pOptions)
{if(this._is(options[pURid])){delete options[pURid];}
options[pURid]=pOptions;},initClass:function(pClass,pOptions)
{if(this._is(coptions[pClass])){delete coptions[pClass];}
enrich_options_by_size(pOptions);coptions[pClass]=pOptions;},render:function(callback,optimized,container)
{if(is_migrating)
return;optimized=this._getDefaultValue(optimized,true);render_options.optimized=optimized;render_options.callback=callback;render_options.container=container;var data=[];var lngs_data=[];var all_ratings=RW._getByClassName("rw-ui-container","div",container);if(optimized&&all_ratings.length==0)
{if(this._isCallable(callback)){callback([]);}
return;}
if(all_ratings.length>0){init_tooltip();}
var call_num=-1;var call_len=RW.CALL_RATINGS_MAX_LEN+1;RW._foreach(all_ratings,function(rating){if(RW._Class.has(rating,"rw-no-render")){return;}
if(rating.style.display===""){rating.style.display="inline-table";}
rating.innerHTML='<img src="http://img.rating-widget.com/rw.loader.gif" alt="" />';var urid=getRatingId(rating);if(!urid){RW._error('Your rating widget container must include a rating id. E.g. <div class="rw-ui-container rw-urid-17"></div>');return;}
var first_instance=false;if(!RW._is(elements[urid]))
{elements[urid]=[];first_instance=true;}
elements[urid].push(rating);if(first_instance)
{if(call_len>RW.CALL_RATINGS_MAX_LEN)
{ratings_data.push([]);call_len=0;call_num++;}
var rating_data={urid:urid};call_len+=rating_data.urid.length+26;if(!RW._is(options[urid])){options[urid]={};}
options[urid].uarid=extract_property(rating,urid,rclass,"uarid",getAccamulatorRatingId(rating),"0");var rclass=extract_property(rating,urid,"","rclass",getRatingClass(rating),"");options[urid].rclass=rclass;options[urid].theme=extract_property(rating,urid,rclass,"theme",getRatingTheme(rating),"");options[urid].lng=extract_property(rating,urid,rclass,"lng",getRatingLanguage(rating),"en");options[urid].title=extract_property(rating,urid,rclass,"title",options[urid].title,"");if(options[urid].title.length>512){options[urid].title=options[urid].title.substring(0,512);}
options[urid].url=extract_property(rating,urid,rclass,"url",options[urid].url,"");if(options[urid].url.length>512){options[urid].url=options[urid].url.substring(0,512);}
if(RW._Class.has(rating,"rw-ui-nero")){options[urid].type=RW.TYPE.NERO;}else if(RW._Class.has(rating,"rw-ui-star")){options[urid].type=RW.TYPE.STAR;}
options[urid].type=extract_property(rating,urid,rclass,"type",options[urid].type,"star");if(!options[urid].type.isEmpty()){rating_data.type=options[urid].type;call_len+=rating_data.type.length+21;}
if(!options[urid].uarid.isEmpty()&&parseInt(options[urid].uarid,10)>0){rating_data.uarid=options[urid].uarid;call_len+=rating_data.uarid.length+22;}
if(!options[urid].rclass.isEmpty()){rating_data.rclass=options[urid].rclass;call_len+=rating_data.rclass.length+23;}
if(!options[urid].title.isEmpty()){rating_data.title=options[urid].title;call_len+=rating_data.title.length+22;}
if(!options[urid].url.isEmpty()){rating_data.url=options[urid].url;call_len+=rating_data.url.length+20;}
ratings_data[call_num].push(rating_data);if(!options[urid].lng.isEmpty()){langs[options[urid].lng]=true;}
if(!options[urid].theme.isEmpty()){themes[options[urid].theme]=true;}}});if(!optimized)
{data.push({name:"all",value:""});}
else
{data.push({name:"lngs",value:encodeURIComponent(RW.JSON.stringify(RW._keys(langs)))});data.push({name:"themes",value:encodeURIComponent(RW.JSON.stringify(RW._keys(themes)))});}
if(ratings_data.length>0){data.push({name:"ratings",value:encodeURIComponent(RW.JSON.stringify(ratings_data[0]))});}
this._jsCall("http://js.rating-widget.com/action/load.php",data);},_isEqualImg:function(pImg1,pImg2)
{return(this._is(pImg1)&&this._is(pImg2)&&pImg1.ltr==pImg2.ltr&&pImg1.rtl==pImg2.rtl);},_keys:function(pObject)
{var keys=[];for(var p in pObject){keys.push(p);}
return keys;},_clearJS:function()
{RW._foreach(RW._getByClassName("rw-js-container","div"),function(js){if(js.hasChildNodes())
{while(js.childNodes.length>0){js.removeChild(js.firstChild);}}});},_loadCallback:function(res)
{if(!res.success)
{for(var urid in elements)
{RW._foreach(elements[urid],function(e){e.parentNode.removeChild(e);})}
RW._error(res.msg);return;}
if(load_call_num==0&&res.data.basic){this.Token=null;}
if(ratings_data.length>0)
{ratings_data[load_call_num]=res.data.ratings;load_call_num++;if(ratings_data.length>load_call_num)
{var data=[{name:"next",value:""}];data.push({name:"ratings",value:encodeURIComponent(RW.JSON.stringify(ratings_data[load_call_num]))});this._jsCall("http://js.rating-widget.com/action/load.php",data);return;}
res.data.ratings=[];for(var c=0,len_c=ratings_data.length;c<len_c;c++){res.data.ratings=res.data.ratings.concat(ratings_data[c]);}}
if(!this._is(res.data)){return;}
var optimized=render_options.optimized,callback=render_options.callback;this._defaultOptions.lng=defaults.lng=(this._is(RWL[defaults.lng]))?defaults.lng:"en";this._defaultOptions.advanced.layout.dir=defaults.advanced.layout.dir=this._getDefaultValue(defaults.advanced.layout.dir,RWL[this._defaultOptions.lng].dir,RW._TYPE.STRING,DUMMY_STR);this._defaultOptions.advanced.layout.align.hor=defaults.advanced.layout.align.hor=this._getDefaultValue(defaults.advanced.layout.align.hor,RWL[this._defaultOptions.lng].align.hor,RW._TYPE.STRING,DUMMY_STR);this._defaultOptions.advanced.layout.align.ver=defaults.advanced.layout.align.ver=this._getDefaultValue(defaults.advanced.layout.align.ver,RWL[this._defaultOptions.lng].align.ver,RW._TYPE.STRING,DUMMY_STR);for(var p in this._defaultOptions.advanced.text){this._defaultOptions.advanced.text[p]=RWL[this._defaultOptions.lng].text[p];defaults.advanced.text[p]=this._getDefaultValue(defaults.advanced.text[p],RWL[this._defaultOptions.lng].text[p],RW._TYPE.STRING,DUMMY_STR);}
var css_load_data={};var inline_css_data={classes:{},themes:{}};if(this._is(res.data.ratings)&&this._isArray(res.data.ratings))
{for(var i=0,len=res.data.ratings.length;i<len;i++)
{if(!RW._is(res.data.ratings[i])){continue;}
var urid=res.data.ratings[i].urid;if(this._is(options[urid].rclass)&&!options[urid].rclass.isEmpty()){}else if(this._is(defaults.rclass)&&!defaults.rclass.isEmpty()){options[urid].rclass=defaults.rclass;}else{options[urid].rclass="";}
if(this._is(options[urid].theme)&&!options[urid].theme.isEmpty()){}else if(!options[urid].rclass.isEmpty()&&this._is(coptions[options[urid].rclass])&&this._is(coptions[options[urid].rclass].theme)&&!coptions[options[urid].rclass].theme.isEmpty())
{options[urid].theme=coptions[options[urid].rclass].theme;}else if(this._is(defaults.theme)&&!defaults.theme.isEmpty()){options[urid].theme=defaults.theme;}else{options[urid].theme="";}
var t_dir=defaults.advanced.layout.dir,t_hor_align=defaults.advanced.layout.align.hor,t_text=defaults.advanced.text;if(options[urid].lng!==defaults.lng&&this._is(RWL[options[urid].lng]))
{defaults.advanced.layout.dir=RWL[options[urid].lng].dir;defaults.advanced.layout.align.hor=RWL[options[urid].lng].align.hor;defaults.advanced.text=RWL[options[urid].lng].text;}
var ostyle=options[urid].style;var oimgUrl=options[urid].imgUrl;var opts=defaults;var theme_options=null;if(!options[urid].theme.isEmpty()&&this._is(RWT[options[urid].theme]))
{theme_options=RWT[options[urid].theme].options;enrich_options_by_size(theme_options);var tmp_imgUrls=theme_options.imgUrl;if(theme_options.style==RW.CUSTOM)
{if(!this._is(options[urid].size))
{if(!options[urid].rclass.isEmpty()&&this._is(coptions[options[urid].rclass].size)){options[urid].size=coptions[options[urid].rclass].size;}else if(!options[urid].theme.isEmpty()&&this._is(RWT[options[urid].theme].size)){options[urid].size=RWT[options[urid].theme].size;}else{options[urid].size=defaults.size;}}
theme_options.imgUrl=theme_options.imgUrl[options[urid].size];}
opts=RW._getEnrichedOptions(theme_options,opts);theme_options.imgUrl=tmp_imgUrls;}
if(!options[urid].rclass.isEmpty()){opts=RW._getEnrichedOptions(coptions[options[urid].rclass],opts);}
options[urid]=RW._getEnrichedOptions(options[urid],opts);defaults.advanced.layout.dir=t_dir;defaults.advanced.layout.align.hor=t_hor_align;defaults.advanced.text=t_text;if(options[urid].style==RW.CUSTOM&&(ostyle!==RW.CUSTOM||oimgUrl.ltr.isEmpty()||oimgUrl.rtl.isEmpty()))
{if(!options[urid].rclass.isEmpty()&&this._is(coptions[options[urid].rclass])&&this._is(coptions[options[urid].rclass].style)&&this._is(coptions[options[urid].rclass].imgUrl)&&coptions[options[urid].rclass].style==RW.CUSTOM&&this._isEqualImg(coptions[options[urid].rclass].imgUrl,options[urid].imgUrl))
{if(!this._is(inline_css_data.classes[options[urid].rclass])){inline_css_data.classes[options[urid].rclass]={imgUrl:coptions[options[urid].rclass].imgUrl,types:{}};}
inline_css_data.classes[options[urid].rclass].types[options[urid].type]=true;}
else if(!options[urid].theme.isEmpty()&&this._is(RWT[options[urid].theme])&&this._is(theme_options.style)&&this._is(theme_options.imgUrl)&&theme_options.style==RW.CUSTOM&&this._isEqualImg(theme_options.imgUrl,options[urid].imgUrl))
{if(!this._is(inline_css_data.themes[options[urid].theme])){inline_css_data.themes[options[urid].theme]={imgUrl:theme_options.imgUrl[options[urid].size],types:{}};}
inline_css_data.themes[options[urid].theme].types[options[urid].type]=true;}}
ratings[urid]=new RW.Rating(res.data.ratings[i],elements[urid],options[urid]);var type=options[urid].type,style=options[urid].style,size=options[urid].size;if(!this._is(css_load_data[type])){css_load_data[type]={};}
if(!this._is(css_load_data[type][size])){css_load_data[type][size]={};}
css_load_data[type][size][style]=true;}}
var href="http://css.rating-widget.com/external.css?"+
((!optimized)?"all":"data="+encodeURIComponent(RW.JSON.stringify(css_load_data)));var head=this._getByTagName("head",doc)[0];var stylesheet=doc.createElement("link");stylesheet.type="text/css";stylesheet.rel="stylesheet";stylesheet.href=href;stylesheet.media="screen";head.appendChild(stylesheet);for(var c in inline_css_data.classes){this._addCustomImgStyle(inline_css_data.classes[c].imgUrl,this._keys(inline_css_data.classes[c].types),"class",c);}
for(var t in inline_css_data.themes){this._addCustomImgStyle(inline_css_data.themes[t].imgUrl,this._keys(inline_css_data.themes[t].types),"theme",t);}
var js_con=this._getByClassName("rw-js-container","div",doc)[0];var con=doc.createElement("div");con.id="rw_action_preloader";body.appendChild(con);con.innerHTML='<i class="rw-action-loader" style="position: fixed; top: -1000px;"></i>';var on_css_ready=function()
{var d=doc.createElement("div");d.id="rw-css-loaded-flag";body.appendChild(d);var computed_style=RW._getStyle(d,"font-size");d.parentNode.removeChild(d);if(computed_style!="1px")
{setTimeout(on_css_ready,100);}
else
{for(var urid in ratings){ratings[urid].render();}
if(RW._isCallable(callback)){callback(ratings);}
RW._clearJS();delete ratings_data;ratings_data=[];RW._invokeHook("RW_HOOK_READY");}};setTimeout(on_css_ready,100);},_rateCallback:function(res,urid)
{if(!this._isNumber(urid)||!this._is(ratings[urid]))
{RW._clearJS();return;}
var rating=ratings[urid];if(res.success)
{rating.rate=parseFloat(res.data[0].rate);rating.votes=Math.max(0,parseInt(res.data[0].votes,10));rating.rate=Math.max(0,Math.min(RW.MAX*rating.votes,rating.rate));rating.voteRate=res.data[0].vote_rate;rating.rated=true;if(false===rating.options.reVote){rating.setReadOnly(true);}}
else
{RW._error(res.msg);}
rating.callState="finished";rating.refreshUI=true;if(rating.afterRate){rating.afterRate(res.success,i,rating,res.data[0].first,res.msg);}
RW._clearJS();},_likeCallback:function(res,urid)
{if(!this._isNumber(urid)||!this._is(ratings[urid]))
{RW._clearJS();return;}
var rating=ratings[urid];if(res.success)
{rating.rate=parseInt(res.data[0].rate,10);rating.votes=Math.max(0,parseInt(res.data[0].votes,10));rating.rate=Math.max(0,Math.min(RW.MAX*rating.votes,rating.rate));rating.likes=Math.floor(rating.rate/RW.MAX);rating.dislikes=Math.max(0,rating.votes-rating.likes);rating.voteRate=res.data[0].vote_rate;rating.rated=true;if(false===rating.options.reVote){rating.setReadOnly(true);}}
else
{RW._error(res.msg);}
rating.callState="finished";rating.refreshUI=true;if(rating.afterRate){rating.afterRate(res.success,like,rating,res.data[0].first,res.msg);}
RW._clearJS();},_identify:function(pUid,pVid,pToken)
{if(!this._isString(pUid)||pUid.length!==32)
{this._error("Invalid user-key.");}
else
{this.Uid=pUid.toLowerCase();}
if(this._is(pVid))
{if(!this._isNumeric(pVid)||parseInt(pVid,10)<0)
{this._error("Invalid voter id.");}
else
{this.Vid=parseInt(pVid,10);}}
if(this._is(pToken))
{if(this._is(pToken.token)&&this._isString(pToken.token)&&pToken.token.length==32&&this._is(pToken.timestamp)&&this._isNumeric(pToken.timestamp))
{this.Token={timestamp:pToken.timestamp,token:pToken.token};}}},_getEnrichedOptions:function(pOptions,pDefaults)
{pOptions=this._getDefaultValue(pOptions,{});pOptions.imgUrl=this._getDefaultValue(pOptions.imgUrl,{});pOptions.boost=this._getDefaultValue(pOptions.boost,{});pOptions.advanced=this._getDefaultValue(pOptions.advanced,{});pOptions.advanced.star=this._getDefaultValue(pOptions.advanced.star,{});pOptions.advanced.nero=this._getDefaultValue(pOptions.advanced.nero,{});pOptions.advanced.font=this._getDefaultValue(pOptions.advanced.font,{});pOptions.advanced.font.hover=this._getDefaultValue(pOptions.advanced.font.hover,{});pOptions.advanced.layout=this._getDefaultValue(pOptions.advanced.layout,{});pOptions.advanced.layout.align=this._getDefaultValue(pOptions.advanced.layout.align,{});pOptions.advanced.text=this._getDefaultValue(pOptions.advanced.text,{});pOptions.advanced.css=this._getDefaultValue(pOptions.advanced.css,{});var settings={};settings.uarid=this._getDefaultValue(pOptions.uarid,pDefaults.uarid,this._TYPE.NUM);settings.lng=this._getDefaultValue(pOptions.lng,pDefaults.lng,this._TYPE.STRING);settings.url=this._getDefaultValue(pOptions.url,pDefaults.url,this._TYPE.STRING);settings.title=this._getDefaultValue(pOptions.title,pDefaults.title,this._TYPE.STRING);settings.type=this._getDefaultValue(pOptions.type,pDefaults.type,this._TYPE.RTYPE);settings.rclass=this._getDefaultValue(pOptions.rclass,pDefaults.rclass,this._TYPE.STRING);settings.size=this._getDefaultValue(pOptions.size,pDefaults.size,this._TYPE.SIZE);settings.theme=this._getDefaultValue(pOptions.theme,pDefaults.theme,this._TYPE.STRING);if(this._is(settings.color)&&this._isString(settings.color)&&settings.color!=="yellow"&&settings.style===""){settings.style=settings.color;}
settings.style=this._getDefaultValue(pOptions.style,pDefaults.style,this._TYPE.STRING);settings.readOnly=this._getDefaultValue(pOptions.readOnly,pDefaults.readOnly,this._TYPE.BOOL);settings.reVote=this._getDefaultValue(pOptions.reVote,pDefaults.reVote,this._TYPE.BOOL);settings.showInfo=this._getDefaultValue(pOptions.showInfo,pDefaults.showInfo,this._TYPE.BOOL);settings.showTooltip=this._getDefaultValue(pOptions.showTooltip,pDefaults.showTooltip,this._TYPE.BOOL);if(this._isString(pOptions.imgUrl))
{pOptions.imgUrl={ltr:pOptions.imgUrl,rtl:pOptions.imgUrl};}
settings.imgUrl={};settings.imgUrl.ltr=this._getDefaultValue(pOptions.imgUrl.ltr,pDefaults.imgUrl.ltr,this._TYPE.STRING);settings.imgUrl.rtl=this._getDefaultValue(pOptions.imgUrl.rtl,pDefaults.imgUrl.rtl,this._TYPE.STRING);settings.boost={};settings.boost.votes=this._getDefaultValue(pOptions.boost.votes,pDefaults.boost.votes,this._TYPE.NUM);settings.boost.rate=this._getDefaultValue(pOptions.boost.rate,pDefaults.boost.rate,this._TYPE.NUM);if(settings.boost.votes<0){settings.boost.votes=0;}
settings.advanced={};settings.advanced.star={};settings.advanced.star.stars=this._getDefaultValue(pOptions.advanced.star.stars,pDefaults.advanced.star.stars,this._TYPE.NUM);settings.advanced.nero={};settings.advanced.nero.showDislike=this._getDefaultValue(pOptions.advanced.nero.showDislike,pDefaults.advanced.nero.showDislike,this._TYPE.BOOL);settings.advanced.nero.showLike=this._getDefaultValue(pOptions.advanced.nero.showLike,pDefaults.advanced.nero.showLike,this._TYPE.BOOL);settings.advanced.font={};settings.advanced.font.bold=this._getDefaultValue(pOptions.advanced.font.bold,pDefaults.advanced.font.bold,this._TYPE.BOOL);settings.advanced.font.italic=this._getDefaultValue(pOptions.advanced.font.italic,pDefaults.advanced.font.italic,this._TYPE.BOOL);settings.advanced.font.color=this._getDefaultValue(pOptions.advanced.font.color,pDefaults.advanced.font.color,this._TYPE.STRING);settings.advanced.font.type=this._getDefaultValue(pOptions.advanced.font.type,pDefaults.advanced.font.type,this._TYPE.STRING);settings.advanced.font.size=this._getDefaultValue(pOptions.advanced.font.size,pDefaults.advanced.font.size,this._TYPE.STRING);settings.advanced.font.hover={};settings.advanced.font.hover.color=this._getDefaultValue(pOptions.advanced.font.hover.color,pDefaults.advanced.font.hover.color,this._TYPE.STRING);settings.advanced.layout={};settings.advanced.layout.dir=this._getDefaultValue(pOptions.advanced.layout.dir,pDefaults.advanced.layout.dir,this._TYPE.DIR);settings.advanced.layout.align={};settings.advanced.layout.align.hor=this._getDefaultValue(pOptions.advanced.layout.align.hor,pDefaults.advanced.layout.align.hor,this._TYPE.ALIGN_HOR);settings.advanced.layout.align.ver=this._getDefaultValue(pOptions.advanced.layout.align.ver,pDefaults.advanced.layout.align.ver,this._TYPE.ALIGN_VER);settings.advanced.layout.lineHeight=this._getDefaultValue(pOptions.advanced.layout.lineHeight,pDefaults.advanced.layout.lineHeight,this._TYPE.STRING);settings.advanced.text={};settings.advanced.text.rateAwful=this._getDefaultValue(pOptions.advanced.text.rateAwful,pDefaults.advanced.text.rateAwful,this._TYPE.STRING);settings.advanced.text.ratePoor=this._getDefaultValue(pOptions.advanced.text.ratePoor,pDefaults.advanced.text.ratePoor,this._TYPE.STRING);settings.advanced.text.rateAverage=this._getDefaultValue(pOptions.advanced.text.rateAverage,pDefaults.advanced.text.rateAverage,this._TYPE.STRING);settings.advanced.text.rateGood=this._getDefaultValue(pOptions.advanced.text.rateGood,pDefaults.advanced.text.rateGood,this._TYPE.STRING);settings.advanced.text.rateExcellent=this._getDefaultValue(pOptions.advanced.text.rateExcellent,pDefaults.advanced.text.rateExcellent,this._TYPE.STRING);settings.advanced.text.rateThis=this._getDefaultValue(pOptions.advanced.text.rateThis,pDefaults.advanced.text.rateThis,this._TYPE.STRING);settings.advanced.text.like=this._getDefaultValue(pOptions.advanced.text.like,pDefaults.advanced.text.like,this._TYPE.STRING);settings.advanced.text.dislike=this._getDefaultValue(pOptions.advanced.text.dislike,pDefaults.advanced.text.dislike,this._TYPE.STRING);settings.advanced.text.vote=this._getDefaultValue(pOptions.advanced.text.vote,pDefaults.advanced.text.vote,this._TYPE.STRING);settings.advanced.text.votes=this._getDefaultValue(pOptions.advanced.text.votes,pDefaults.advanced.text.votes,this._TYPE.STRING);settings.advanced.text.thanks=this._getDefaultValue(pOptions.advanced.text.thanks,pDefaults.advanced.text.thanks,this._TYPE.STRING);settings.advanced.css={};settings.advanced.css.container=this._getDefaultValue(pOptions.advanced.css.container,pDefaults.advanced.css.container,this._TYPE.STRING);return settings;},_setDefaults:function(pOptions)
{defaults=this._getEnrichedOptions(pOptions,defaults);},getRating:function(pURid)
{return ratings[pURid];},_getClassOptions:function(pClass)
{if(this._is(coptions[pClass])){return coptions[pClass];}else{return false;}},_getThemeOptions:function(pTheme)
{if(this._is(themes[pTheme])&&this._is(RWT[pTheme])){return RWT[pTheme].options;}else{return false;}},_addCustomImgStyle:function(pImgUrl,pTypes,pProperty,pPropertyValue)
{var style_identifier="rw_"+pProperty+"_"+pPropertyValue+"_custom_style",identifier_class=".rw-"+pProperty+"-"+pPropertyValue;var stylesheet=RW._getById(style_identifier);if(RW._is(stylesheet)){stylesheet.parentNode.removeChild(stylesheet);}
stylesheet=doc.createElement("style");stylesheet.type="text/css";stylesheet.rel="stylesheet";stylesheet.id=style_identifier;doc.getElementsByTagName("head")[0].appendChild(stylesheet);var style_text="";for(var i=0,len=pTypes.length;i<len;i++)
{style_text+=(pTypes[i]==RW.TYPE.STAR)?(identifier_class+".rw-ui-star.rw-ui-container.rw-style-custom .rw-ui-stars li { background-image: url('"+pImgUrl.ltr+"'); }\n"+
identifier_class+".rw-rtl.rw-ui-star.rw-ui-container.rw-style-custom .rw-ui-stars li { background-image: url('"+pImgUrl.rtl+"'); }\n"):(identifier_class+".rw-ui-nero.rw-ui-container.rw-style-custom .rw-ui-like-icon,\n"+
identifier_class+".rw-ui-nero.rw-ui-container.rw-style-custom .rw-ui-dislike-icon\n"+"{ background-image: url('"+pImgUrl.ltr+"'); }"+
identifier_class+".rw-rtl.rw-ui-nero.rw-ui-container.rw-style-custom .rw-ui-like-icon,\n"+
identifier_class+".rw-rtl.rw-ui-nero.rw-ui-container.rw-style-custom .rw-ui-dislike-icon\n"+"{ background-image: url('"+pImgUrl.rtl+"'); }");}
if(stylesheet.styleSheet){stylesheet.styleSheet.cssText=style_text;}else{var cssTextNode=doc.createTextNode(style_text);stylesheet.appendChild(cssTextNode);}},_getDefaultValue:function(pValue,pDefault,pType,pNull)
{if(!this._is(pValue)||(this._is(pNull)&&pValue===pNull)||(this._is(pType)&&((pType==this._TYPE.BOOL&&!this._isBoolean(pValue))||(pType==this._TYPE.NUM&&!this._isNumeric(pValue))||(pType==this._TYPE.STRING&&!this._isString(pValue))||(pType==this._TYPE.SIZE&&!this._isSize(pValue))||(pType==this._TYPE.DIR&&!this._isDir(pValue))||(pType==this._TYPE.ALIGN_HOR&&!this._isHorAlign(pValue))||(pType==this._TYPE.ALIGN_VER&&!this._isVerAlign(pValue))||(pType==this._TYPE.RTYPE&&!this._isRatingType(pValue)))))
{return pDefault;}
return pValue;},_getById:function(pElement)
{return doc.getElementById(pElement);},_getByTagName:function(pTag,pContainer)
{return pContainer.getElementsByTagName(pTag);},_getByClassName:function(pClassName,pTag,pContainer)
{pClassName=pClassName.trim();var elm=pContainer||doc;var tag=pTag||"*";var elements=(tag=="*"&&elm.all)?elm.all:elm.getElementsByTagName(tag);if(pClassName==""){return elements;}
return this._filterElements(elements,function(e){return RW._Class.has(e,pClassName);});},_typeOf:function(value)
{var s=typeof(value);if(s=='object')
{if(value)
{if(value instanceof Array||(!(value instanceof Object)&&(Object.prototype.toString.call((value))=='[object Array]')||typeof(value.length)=='number'&&typeof(value.splice)!=UNDEF&&typeof(value.propertyIsEnumerable)!=UNDEF&&!value.propertyIsEnumerable('splice')))
{return'array';}
if(!(value instanceof Object)&&(Object.prototype.toString.call((value))=='[object Function]'||typeof(value.call)!=UNDEF&&typeof(value.propertyIsEnumerable)!=UNDEF&&!value.propertyIsEnumerable('call')))
{return'function';}}
else
{return'null';}}else if(s=='function'&&typeof(value.call)==UNDEF){return'object';}
return s;},_isDefined:function(pObject)
{return(typeof(pObject)!=UNDEF);},_isNull:function(pObject)
{return(pObject===null);},_is:function(pObject)
{return(pObject!=null);},_isUndefinedOrNull:function(pObject)
{return(pObject==null);},_isString:function(pObject)
{return(typeof(pObject)=="string");},_isBoolean:function(pObject)
{return(typeof(pObject)=="boolean");},_isNumber:function(pObject)
{return(typeof(pObject)=="number");},_isNumeric:function(pObject)
{return!isNaN(parseFloat(pObject));},_isRatingType:function(pObject)
{return(this._isString(pObject)&&(pObject==this.TYPE.STAR||pObject==this.TYPE.NERO));},_isSize:function(pObject)
{return(this._isString(pObject)&&(pObject==this.SIZE.SMALL||pObject==this.SIZE.MEDIUM||pObject==this.SIZE.LARGE));},_isDir:function(pObject)
{return(this._isString(pObject)&&(pObject==this.DIR.LTR||pObject==this.DIR.RTL));},_isHorAlign:function(pObject)
{return(this._isString(pObject)&&(pObject==this.ALIGN.HOR.LEFT||pObject==this.ALIGN.HOR.CENTER||pObject==this.ALIGN.HOR.RIGHT));},_isVerAlign:function(pObject)
{return(this._isString(pObject)&&(pObject==this.ALIGN.VER.TOP||pObject==this.ALIGN.VER.MIDDLE||pObject==this.ALIGN.VER.BOTTOM));},_isArray:function(pObject)
{return(this._is(pObject)&&this._typeOf(pObject)=="array");},_isCallable:function(fun)
{return(this._typeOf(fun)=="function");},_error:function(pMessage)
{alert(pMessage);throw(pMessage);},_guid:function()
{var date=new Date();return date.getTime();},_getStyle:function(pElement,pProperty)
{if(RW._is(pElement.currentStyle))
{pProperty=pProperty.replace(/\-(\w)/g,function(strMatch,p1){return p1.toUpperCase();});return pElement.currentStyle[pProperty];}
else if(window.getComputedStyle)
{return doc.defaultView.getComputedStyle(pElement,null).getPropertyValue(pProperty);}},_filterElements:function(pItems,pPredicate,pCallback)
{var has_callback=this._isCallable(pCallback);var filtered=[];for(var i=0,length=pItems.length;i<length;i++)
{if(pPredicate(pItems[i]))
{if(has_callback){filtered.push(pCallback(pItems[i]));}else{filtered.push(pItems[i]);}}}
return filtered;},_foreach:function(pEnumerable,pCallback)
{for(var i=0,length=pEnumerable.length;i<length;i++){pCallback(pEnumerable[i],i);}},_jsCall:function(url,data)
{data.push({name:"uid",value:this.Uid});if(RW.PCId!==EMPTY_PCID){data.push({name:"pcid",value:RW.PCId});}
if(RW._is(RW.Vid))
{var vid_exist=false;for(i=0,len=data.length;i<len;i++){if(data[i].name=="vid"){vid_exist=true;break;}}
if(!vid_exist){data.push({name:"vid",value:RW.Vid});}}
if(this._is(this.Token))
{data.push({name:"token",value:this.Token.token});data.push({name:"timestamp",value:this.Token.timestamp});}
if(data.length>0){url+=((url.indexOf("?")+1)?"&":"?");}
for(var i=0,len=data.length;i<len;i++){url+=((i>0)?"&":"")+data[i].name+"="+data[i].value;}
var s=doc.createElement("script");s.charset="utf-8";s.src=url+((url.indexOf("?")+1)?"&":"?")+"cguid="+RW._guid();var c=RW._getByClassName("rw-js-container","div");if(c.length>0){c[0].appendChild(s);}else{var h=doc.getElementsByTagName("head").item(0);h.appendChild(s);}},_invokeHook:function(pHook)
{if(eval('typeof('+pHook+') !== UNDEF')){eval('pHook = '+pHook);if(RW._isArray(pHook)){for(var h=0,h_len=pHook.length;h<h_len;h++)
{if(typeof(pHook[h])!==UNDEF&&RW._isCallable(pHook[h])){pHook[h]();}}}}}};}();RW.JSON=function(){var cx=/[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,escapable=/[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,gap,indent,meta={'\b':'\\b','\t':'\\t','\n':'\\n','\f':'\\f','\r':'\\r','"':'\\"','\\':'\\\\'},rep;var quote=function(string)
{escapable.lastIndex=0;return escapable.test(string)?'"'+string.replace(escapable,function(a){var c=meta[a];return typeof c==='string'?c:'\\u'+('0000'+a.charCodeAt(0).toString(16)).slice(-4);})+'"':'"'+string+'"';};var str=function(key,holder)
{var i,k,v,length,mind=gap,partial,value=holder[key];if(value&&typeof value==='object'&&typeof value.toJSON==='function')
{value=value.toJSON(key);}
switch(typeof value){case'string':return quote(value);case'number':return isFinite(value)?String(value):'null';case'boolean':case'null':return String(value);case'object':if(!value){return'null';}
gap+=indent;partial=[];if(Object.prototype.toString.apply(value)==='[object Array]')
{length=value.length;for(i=0;i<length;i+=1){partial[i]=str(i,value)||'null';}
v=partial.length===0?'[]':gap?'[\n'+gap+
partial.join(',\n'+gap)+'\n'+
mind+']':'['+partial.join(',')+']';gap=mind;return v;}
for(k in value){if(Object.hasOwnProperty.call(value,k)){v=str(k,value);if(v){partial.push(quote(k)+(gap?': ':':')+v);}}}
v=partial.length===0?'{}':gap?'{\n'+gap+partial.join(',\n'+gap)+'\n'+
mind+'}':'{'+partial.join(',')+'}';gap=mind;return v;}};return{stringify:function(value)
{return str('',{'':value});}};}();RW._Class=function(){var CLASS_NAMES_REGEX_CACHE=[];var get_class_regex=function(pClassName)
{pClassName=pClassName.trim();if(RW._isUndefinedOrNull(CLASS_NAMES_REGEX_CACHE[pClassName])){CLASS_NAMES_REGEX_CACHE[pClassName]=new RegExp("(\\s|^)"+pClassName+"(\\s|$)");}
return CLASS_NAMES_REGEX_CACHE[pClassName];};var iterate=function(callback,object,params)
{if(RW._isArray(object))
{var res;for(var i=0,len=object.length;i<len;i++){res=callback.apply(RW._Class,[object[i]].concat(params));}
return res;}
else
{return callback.apply(RW._Class,[object].concat(params));}};return{_set:function(pObject,pClassName)
{pObject.className=pClassName;},set:function(pObject,pClassName)
{iterate(this._set,pObject,[pClassName]);},_clear:function(pObject)
{this.set(pObject,"");},clear:function(pObject,pClassName)
{iterate(this._clear,pObject,[pClassName]);},_has:function(pObject,pClassName)
{return(RW._is(pClassName)&&get_class_regex(pClassName).test(pObject.className));},has:function(pObject,pClassName)
{return iterate(this._has,pObject,[pClassName]);},_add:function(pObject,pClassName)
{pClassName=pClassName.trim();if(pClassName.isEmpty()){return;}
if(!this.has(pObject,pClassName)){pObject.className+=(pObject.className.isEmpty()?"":" ")+pClassName;pObject.className.trim();}},add:function(pObject,pClassName)
{iterate(this._add,pObject,[pClassName]);},_remove:function(pObject,pClassName)
{if(this.has(pObject,pClassName)){pObject.className=pObject.className.replace(get_class_regex(pClassName)," ").trim();}},remove:function(pObject,pClassName)
{iterate(this._remove,pObject,[pClassName]);},_toggle:function(pObject,pClassName)
{if(this.has(pObject,pClassName)){this.remove(pObject,pClassName);}else{this.add(pObject,pClassName);}},toggle:function(pObject,pClassName)
{iterate(this._toggle,pObject,[pClassName]);},_replace:function(pObject,pFromClassName,pToClassName)
{if(this.has(pObject,pFromClassName)){this.remove(pObject,pFromClassName);this.add(pObject,pToClassName);}},replace:function(pObject,pFromClassName,pToClassName)
{iterate(this._replace,pObject,[pFromClassName,pToClassName]);}};}();RW.Rating=function(pData,pDOMElements,pOptions)
{if(pOptions.style.isEmpty()){pOptions.style=RW._DEF_STYLES[pOptions.type.toUpperCase()];}
pOptions.advanced.star.stars=Math.max(RW.MIN_STARS,Math.min(RW.MAX_STARS,pOptions.advanced.star.stars));if(pOptions.type==RW.TYPE.STAR){pOptions.boost.rate=Math.max(1,Math.min(pOptions.advanced.star.stars,pOptions.boost.rate));}
var font_color=(pOptions.advanced.font.color===RW._defaultOptions.advanced.font.color)?"":"color: "+pOptions.advanced.font.color+";";var font_family=(pOptions.advanced.font.type===RW._defaultOptions.advanced.font.type)?"":"font-family: "+pOptions.advanced.font.type+";";var font_size=(pOptions.advanced.font.size===RW._DEF_FONT_SIZE[pOptions.size.toUpperCase()])?"":"font-size: "+pOptions.advanced.font.size+";";var line_height=(pOptions.advanced.layout.lineHeight===RW._DEF_LINE_HEIGHT[pOptions.size.toUpperCase()])?"":"line-height: "+pOptions.advanced.layout.lineHeight+";";var rating=this;var prefix=RW.PREFIX+"-";var elements=pDOMElements;var labels;var stars_lists;var stars;var options=pOptions;var advanced=options.advanced;var font=advanced.font;var hover=font.hover;var text=advanced.text;var layout=advanced.layout;var align=layout.align;var icon_width=(options.size==RW.SIZE.SMALL)?16:((options.size==RW.SIZE.MEDIUM)?20:30);var mouseOutTimeout=null;var voteTimeout=null;var get_width=function(e)
{var width=0;if(e.clientWidth){width=Math.max(width,e.clientWidth);}
if(e.scrollWidth){width=Math.max(width,e.scrollWidth);}
if(e.offsetWidth){width=Math.max(width,e.offsetWidth);}
return width;};var get_height=function(e)
{var height=0;if(e.clientHeight){height=Math.max(height,e.clientHeight);}
if(e.scrollHeight){height=Math.max(height,e.scrollHeight);}
if(e.offsetHeight){height=Math.max(height,e.offsetHeight);}
return height;};this.options=pOptions;this.advanced=this.options.advanced;this.beforeRate=(RW._isCallable(this.options.beforeRate)?beforeRate:false);this.afterRate=(RW._isCallable(this.options.afterRate)?afterRate:false);this.rid=pData.rid;this.urid=pData.urid;this.huid=pData.huid;this.uid=pData.uid;this.rate=parseFloat(pData.rate);this.votes=Math.max(0,parseInt(pData.votes,10));this.rate=Math.max(0,Math.min(RW.MAX*this.votes,this.rate));this.type=options.type;this.rated=pData.rated;this.voteRate=(this.rated)?pData.vote_rate:0;if(this.rated&&!(this.options.reVote)){this.options.readOnly=true;}
if(this.type==RW.TYPE.NERO){this.likes=Math.floor(this.rate/RW.MAX);this.dislikes=Math.max(0,this.votes-this.likes);}
this.callState="ready";this._setOptions=function(pOptions)
{font_color=(pOptions.advanced.font.color===RW._defaultOptions.advanced.font.color)?"":"color: "+pOptions.advanced.font.color+";";font_family=(pOptions.advanced.font.type===RW._defaultOptions.advanced.font.type)?"":"font-family: "+pOptions.advanced.font.type+";";font_size=(pOptions.advanced.font.size===RW._DEF_FONT_SIZE[pOptions.size.toUpperCase()])?"":"font-size: "+pOptions.advanced.font.size+";";line_height=(pOptions.advanced.layout.lineHeight===RW._DEF_LINE_HEIGHT[pOptions.size.toUpperCase()])?"":"line-height: "+pOptions.advanced.layout.lineHeight+";";rating.options=options=pOptions;rating.advanced=advanced=options.advanced;font=advanced.font;hover=font.hover;text=advanced.text;layout=advanced.layout;align=layout.align;icon_width=(options.size==RW.SIZE.SMALL)?16:((options.size==RW.SIZE.MEDIUM)?20:30);rating.render();};this.setSize=function(pSize)
{if(pSize==options.size){return;}
RW._Class.remove(elements,prefix+"size-"+options.size);RW._Class.add(elements,prefix+"size-"+pSize);icon_width=(pSize==RW.SIZE.SMALL)?16:((pSize==RW.SIZE.MEDIUM)?20:30);options.size=pSize;};this.setStyle=function(pStyle,pUrl)
{pUrl=RW._getDefaultValue(pUrl,{ltr:"",rtl:""});pStyle=pStyle.toLowerCase();if(pStyle==options.style&&(pStyle!=RW.CUSTOM||RW._isEqualImg(pUrl,options.imgUrl))){return;}
if(options.style==RW.CUSTOM&&pStyle!==RW.CUSTOM)
{RW._Class.remove(elements,prefix+"theme-"+options.theme);RW._Class.remove(elements,prefix+"class-"+options.rclass);var stylesheet=RW._getById("rw_urid_"+rating.urid+"_custom_style");if(RW._is(stylesheet)){stylesheet.parentNode.removeChild(stylesheet);}}
RW._Class.remove(elements,prefix+"style-"+options.style);RW._Class.add(elements,prefix+"style-"+pStyle);options.style=pStyle;options.imgUrl=pUrl;if(options.style.toLowerCase()==RW.CUSTOM){RW._addCustomImgStyle(options.imgUrl,[rating.type],"urid",rating.urid);}};this.toggleBold=function()
{RW._Class.toggle(elements,prefix+"bold");font.bold=!font.bold;};this.toggleLike=function()
{if(rating.type!==RW.TYPE.NERO){return;}
advanced.nero.showLike=!advanced.nero.showLike;rating.render();};this.toggleDislike=function()
{if(rating.type!==RW.TYPE.NERO){return;}
advanced.nero.showDislike=!advanced.nero.showDislike;rating.render();};this.setReadOnly=function(pReadOnly)
{if(pReadOnly==options.readOnly){return;}
RW._Class.toggle(elements,prefix+"active");options.readOnly=pReadOnly;};this.toggleItalic=function()
{RW._Class.toggle(elements,prefix+"italic");font.italic=!font.italic;};this.setFontType=function(pFamily)
{if(pFamily==font.type){return;}
RW._foreach(elements,function(e){RW._foreach(RW._getByTagName("span",e),function(s){s.style.fontFamily=pFamily;});});font_family=(pFamily.toLowerCase()===RW._defaultOptions.advanced.font.type)?"":"font-family: "+pFamily+";";font.type=pFamily;};this.setFontColor=function(pColor)
{if(pColor==font.color){return;}
this.setLabelColor(pColor);font_color=(pColor===RW._defaultOptions.advanced.font.color)?"":"color: "+pColor+";";font.color=pColor;};this.setFontHOverColor=function(pColor)
{if(pColor==hover.color){return;}
hover.color=pColor;};this.setFontSize=function(pSize)
{if(pSize==font.size){return;}
RW._foreach(labels,function(l){l.style.fontSize=pSize;});if(rating.type==RW.TYPE.NERO)
{RW._foreach(elements,function(e){if(advanced.nero.showLike){get_like_label(e).style.fontSize=pSize;}
if(advanced.nero.showDislike){get_dislike_label(e).style.fontSize=pSize;}});}
font_size=(pSize===RW._DEF_FONT_SIZE[options.size.toUpperCase()])?"":"font-size: "+pSize+";";font.size=pSize;};this.setDirection=function(pDir)
{if(pDir==layout.dir){return;}
RW._Class.add(elements,prefix+"dir-"+pDir);RW._Class.remove(elements,prefix+"dir-"+layout.dir);layout.dir=pDir;};this.setAlignment=function(pVer,pHor)
{if(pVer==align.ver&&pHor==align.hor){return;}
if(pVer!=align.ver){RW._Class.replace(elements,prefix+"valign-"+align.ver,prefix+"valign-"+pVer);}
if(pHor!=align.hor){RW._Class.replace(elements,prefix+"halign-"+align.hor,prefix+"halign-"+pHor);}
if(pVer!=align.ver)
{if(pVer==RW.ALIGN.VER.TOP)
{RW._foreach(elements,function(e,i){e.appendChild(labels[i]);e.appendChild(get_active(e));});}
else
{RW._foreach(elements,function(e,i){e.appendChild(get_active(e));e.appendChild(labels[i]);});}}
align.ver=pVer;align.hor=pHor;};this.setLineHeight=function(pHeight)
{if(pHeight==layout.lineHeight){return;}
RW._foreach(labels,function(l){l.style.lineHeight=pHeight;});if(rating.type==RW.TYPE.NERO)
{RW._foreach(elements,function(e){if(advanced.nero.showLike){get_like_label(e).style.lineHeight=pHeight;}
if(advanced.nero.showDislike){get_dislike_label(e).style.lineHeight=pHeight;}});}
line_height=(pHeight===RW._DEF_LINE_HEIGHT[options.size.toUpperCase()])?"":"line-height: "+pHeight+";";layout.lineHeight=pHeight;};this.setLanguage=function(pLng)
{if(pLng==options.lng||!RW._is(RWL[pLng])){return;}
for(var p in advanced.text){advanced.text[p]=RWL[pLng].text[p];RW._defaultOptions.advanced.text[p]=RWL[pLng].text[p];}
rating.setDirection(RWL[pLng].dir);rating.setAlignment(RWL[pLng].align.ver,RWL[pLng].align.hor);RW._defaultOptions.advanced.layout.dir=RWL[pLng].dir;RW._defaultOptions.advanced.layout.align.ver=RWL[pLng].align.ver;RW._defaultOptions.advanced.layout.align.hor=RWL[pLng].align.hor;options.lng=pLng;rating.refresh();};this.setStarsNum=function(pNum)
{pNum=Math.max(RW.MIN_STARS,Math.min(RW.MAX_STARS,parseInt(pNum,10)));if(rating.type!==RW.TYPE.STAR||advanced.star.stars===pNum){return;}
advanced.star.stars=pNum;rating.render();};this.setCSS=function(pItem,pStyle)
{if(!RW._is(advanced.css[pItem])||advanced.css[pItem]===pStyle){return;}
advanced.css[pItem]=pStyle;var display;RW._foreach(elements,function(e){display=e.style.display;e.setAttribute("style",pStyle);e.style.display=display;});};var get_active=function(e){return(RW._getByTagName("div",e))[0];};var render_star=function()
{stars_lists=[];stars=[];labels=[];var inner_html="";var star_items="";for(var i=0,len=advanced.star.stars-1;i<len;i++){star_items+='<li></li>';}
if(advanced.layout.align.ver==RW.ALIGN.VER.TOP)
{inner_html='<span class="rw-ui-info" style="'+font_color+font_family+font_size+line_height+'"></span>'+'<div class="rw-action-area rw-clearfix"><ul class="rw-ui-stars"><li></li>'+star_items+'</ul>';if(!RW._is(RW.Token)){inner_html+='<a class="rw-report-link" target="_blank" title="Rating-Widget Report" href="http://rating-widget.com/my-rating-report/'+rating.type+'/'+rating.options.style.replace('_','-')+'/rating-'+rating.rid+'-'+advanced.star.stars+'/"></a>';}
inner_html+='</div>';}
else
{inner_html='<div class="rw-action-area rw-clearfix"><ul class="rw-ui-stars"><li></li>'+star_items+'</ul>';if(!RW._is(RW.Token)){inner_html+='<a class="rw-report-link" target="_blank" title="Rating-Widget Report" href="http://rating-widget.com/my-rating-report/'+rating.type+'/'+rating.options.style.replace('_','-')+'/rating-'+rating.rid+'-'+advanced.star.stars+'/"></a>';}
inner_html+='</div>'+'<span class="rw-ui-info" style="'+font_color+font_family+font_size+line_height+'"></span>';}
RW._foreach(elements,function(e){e.innerHTML=inner_html;labels.push((RW._getByClassName("rw-ui-info","span",e))[0]);stars_lists.push(RW._getByTagName("ul",e)[0]);stars.push(RW._getByTagName("li",e));});};var get_like=function(e){return(RW._getByTagName("i",(RW._getByClassName("rw-ui-like","span",e))[0]))[0];};var get_like_label=function(e){return(RW._getByClassName("rw-ui-like-label","span",e))[0];};var get_dislike=function(e){return(RW._getByTagName("i",(RW._getByClassName("rw-ui-dislike","span",e))[0]))[0];};var get_dislike_label=function(e){return(RW._getByClassName("rw-ui-dislike-label","span",e))[0];};var get_document_offset=function()
{var docElem=document.documentElement;return{top:Math.max(docElem.scrollTop,body.scrollTop),left:Math.max(docElem.scrollLeft,body.scrollLeft)};};var get_offset=function(e)
{var computedStyle,offsetParent=e.offsetParent,prevOffsetParent=e,doc=e.ownerDocument,docElem=doc.documentElement,htmls=doc.getElementsByTagName("html"),body=doc.body,defaultView=doc.defaultView,prevComputedStyle=defaultView?defaultView.getComputedStyle(e,null):e.currentStyle,top=e.offsetTop,left=e.offsetLeft;while((e=e.parentNode)&&e!==body&&e!==docElem)
{if(prevComputedStyle.position==="fixed"){break;}
computedStyle=defaultView?defaultView.getComputedStyle(e,null):e.currentStyle;top-=e.scrollTop;left-=e.scrollLeft;if(e===offsetParent)
{top+=e.offsetTop;left+=e.offsetLeft;top+=parseFloat(computedStyle.borderTopWidth)||0;left+=parseFloat(computedStyle.borderLeftWidth)||0;prevOffsetParent=offsetParent;offsetParent=e.offsetParent;}
if(computedStyle.overflow!=="visible")
{top+=parseFloat(computedStyle.borderTopWidth)||0;left+=parseFloat(computedStyle.borderLeftWidth)||0;}
prevComputedStyle=computedStyle;}
if(prevComputedStyle.position==="relative"||prevComputedStyle.position==="static")
{top+=body.offsetTop;left+=body.offsetLeft;}
if(prevComputedStyle.position==="fixed")
{top+=Math.max(docElem.scrollTop,body.scrollTop);left+=Math.max(docElem.scrollLeft,body.scrollLeft);}
if(htmls.length>0)
{top+=htmls[0].offsetTop;left+=htmls[0].offsetLeft;}
return{top:top,left:left};};var place_tooltip=function(pTarget)
{if(rating.callState!="ready"){return;}
if(!rating.options.showTooltip){return;}
RW._tooltip.style.left="";RW._tooltip.style.top="";pTarget.appendChild(RW._tooltip);RW._tooltip.style.display="block";RW._tooltip.style.left="-"+Math.ceil((get_width(RW._tooltip)-icon_width)/2)+"px";var offset=get_offset(RW._tooltip),doc_offset=get_document_offset();doc.body.appendChild(RW._tooltip);RW._tooltip.style.left=offset.left+"px";var height=get_height(RW._tooltip);if((offset.top-height-6)>doc_offset.top){RW._Class.set(RW._tooltip,"rw-ui-tooltip rw-valign-top");RW._tooltip.style.top=(offset.top-height-6)+"px";}else{RW._Class.set(RW._tooltip,"rw-ui-tooltip rw-valign-bottom");RW._tooltip.style.top=(offset.top+height-4)+"px";}};var render_nero=function()
{labels=[];var inner_html="";if(advanced.layout.align.ver==RW.ALIGN.VER.TOP)
{inner_html='<span class="rw-ui-info" style="'+font_color+font_family+font_size+line_height+'"></span>'+'<div class="rw-action-area rw-clearfix">';if(advanced.nero.showLike){inner_html+='<span class="rw-ui-like"><i class="rw-ui-like-icon"></i><span class="rw-ui-like-label" style="'+font_family+font_size+line_height+'">13</span></span>';}
if(advanced.nero.showDislike){inner_html+='<span class="rw-ui-dislike"><i class="rw-ui-dislike-icon"></i><span class="rw-ui-dislike-label" style="'+font_family+font_size+line_height+'"></span></span>';}
if(!RW._is(RW.Token)){inner_html+='<a class="rw-report-link" target="_blank" title="Rating-Widget Report" href="http://rating-widget.com/my-rating-report/'+rating.type+'/'+rating.options.style.replace('_','-')+'/rating-'+rating.rid+'/"></a>';}
inner_html+='</div>';}
else
{inner_html='<div class="rw-action-area rw-clearfix">';if(advanced.nero.showLike){inner_html+='<span class="rw-ui-like"><i class="rw-ui-like-icon"></i><span class="rw-ui-like-label" style="'+font_family+font_size+line_height+'">13</span></span>';}
if(advanced.nero.showDislike){inner_html+='<span class="rw-ui-dislike"><i class="rw-ui-dislike-icon"></i><span class="rw-ui-dislike-label" style="'+font_family+font_size+line_height+'"></span></span>';}
if(!RW._is(RW.Token)){inner_html+='<a class="rw-report-link" target="_blank" title="Rating-Widget Report" href="http://rating-widget.com/my-rating-report/'+rating.type+'/'+rating.options.style.replace('_','-')+'/rating-'+rating.rid+'/"></a>';}
inner_html+='</div>'+'<span class="rw-ui-info" style="'+font_color+font_family+font_size+line_height+'"></span>';}
RW._foreach(elements,function(e){e.innerHTML=inner_html;labels.push((RW._getByClassName("rw-ui-info","span",e))[0]);});};this.render=function()
{RW._foreach(elements,function(e){e.innerHTML="";e.setAttribute("style",advanced.css.container);});RW._Class.set(elements,"rw-ui-container rw-urid-"+rating.urid+" rw-ui-"+rating.type);if(!options.theme.isEmpty()){RW._Class.add(elements,prefix+"theme-"+options.theme);}
if(!options.readOnly){RW._Class.add(elements,prefix+"active");}
RW._Class.add(elements,prefix+"size-"+options.size);if(advanced.font.bold){RW._Class.add(elements,prefix+"bold");}
if(advanced.font.italic){RW._Class.add(elements,prefix+"italic");}
RW._Class.add(elements,prefix+"dir-"+((advanced.layout.dir==RW.DIR.RTL)?RW.DIR.RTL:RW.DIR.LTR));RW._Class.add(elements,prefix+"halign-"+advanced.layout.align.hor);RW._Class.add(elements,prefix+"valign-"+advanced.layout.align.ver);RW._Class.add(elements,prefix+"style-"+options.style);if(!(options.rclass.trim().isEmpty())){RW._Class.add(elements,prefix+"class-"+options.rclass);}
if(options.style.toLowerCase()==RW.CUSTOM)
{var class_options=(!options.rclass.trim().isEmpty())?RW._getClassOptions(options.rclass):false;var theme_options=(!options.theme.trim().isEmpty())?RW._getThemeOptions(options.theme):false;if(false===class_options||class_options.style!==RW.CUSTOM||!RW._isEqualImg(options.imgUrl,class_options.imgUrl))
{if(false===theme_options||theme_options.style!==RW.CUSTOM||!RW._isEqualImg(options.imgUrl,theme_options.imgUrl))
{RW._addCustomImgStyle(options.imgUrl,[rating.type],"urid",rating.urid);}}}
if(rating.type==RW.TYPE.STAR){render_star();}else if(rating.type==RW.TYPE.NERO){render_nero();}
rating.refresh();attach_events();};var refresh_star=function()
{var votes=rating.votes+options.boost.votes,rate=rating.rate*(advanced.star.stars/RW.MAX)+(options.boost.rate*options.boost.votes);var tmp_num=(votes>0)?Math.round((rate/votes)*2):0;RW._foreach(stars,function(s){var len=(tmp_num-(tmp_num%2))/2;for(var i=0;i<len;i++){RW._Class.set(s[i],"rw-ui-star-selected");}
if((tmp_num%2)==1)
{RW._Class.set(s[len],"rw-ui-star-half-selected");len++;}
for(var j=len;j<advanced.star.stars;j++){RW._Class.clear(s[j]);}});if(votes==0&&!options.readOnly){rating.setLabel(advanced.text.rateThis);}else if(votes==1){rating.setLabel(votes+" "+advanced.text.vote);}else{rating.setLabel(votes+" "+advanced.text.votes);}};var refresh_nero=function()
{RW._foreach(elements,function(e){if(advanced.nero.showLike){get_like_label(e).innerHTML=rating.likes;}
if(advanced.nero.showDislike){get_dislike_label(e).innerHTML=rating.dislikes;}
if(rating.rated)
{if(rating.voteRate>0)
{if(advanced.nero.showLike){RW._Class.add(get_like(e).parentNode,"rw-selected");}
if(advanced.nero.showDislike){RW._Class.remove(get_dislike(e).parentNode,"rw-selected");}}
else
{if(advanced.nero.showDislike){RW._Class.add(get_dislike(e).parentNode,"rw-selected");}
if(advanced.nero.showLike){RW._Class.remove(get_like(e).parentNode,"rw-selected");}}}});if(!(rating.rated)&&!options.readOnly)
{rating.setLabel(advanced.text.rateThis);}
else
{var votes=0;if(advanced.nero.showLike){votes+=rating.likes;}
if(advanced.nero.showDislike){votes+=rating.dislikes;}
if(votes==0&&!options.readOnly){rating.setLabel(advanced.text.rateThis);}else if(!advanced.nero.showLike||!advanced.nero.showDislike){rating.setLabel("");}else if(votes==1){rating.setLabel(votes+" "+advanced.text.vote);}else{rating.setLabel(votes+" "+advanced.text.votes);}}};this.refresh=function()
{if(rating.type==RW.TYPE.STAR){refresh_star();}else if(rating.type==RW.TYPE.NERO){refresh_nero();}
rating.hideTooltip();};var wakeup_fragment=100;var timer_refresh=function()
{if(rating.callState!="calling"&&rating.refreshUI&&rating.refreshCount>4)
{rating.callState="ready";rating.refresh();}
else
{voteTimeout=setTimeout(timer_refresh,wakeup_fragment);rating.refreshCount++;}};this.doRate=function(i,star)
{if(rating.callState!="ready"){return;}
i=Math.min(advanced.star.stars,Math.max(RW.MIN,i));var vid=(rating.beforeRate?rating.beforeRate(rating,i):true);if(false===vid){return;}
if(true===vid&&RW._is(RW.Vid)){vid=RW.Vid;}
rating.setLabel(advanced.text.thanks);rating.setTooltip('<i class="rw-action-loader"></i>');place_tooltip(star);rating.callState="calling";var data=[{name:"urid",value:rating.urid},{name:"rate",value:i*(RW.MAX/advanced.star.stars)}];if(vid!==true){data.push({name:"vid",value:vid});}
if(rating.options.uarid>0){data.push({name:"uarid",value:rating.options.uarid});}
rating.refreshCount=0;rating.refreshUI=false;clearTimeout(voteTimeout);voteTimeout=setTimeout(timer_refresh,wakeup_fragment);RW._jsCall("http://js.rating-widget.com/action/rate.php",data);};this.like=function(elm,like)
{if(rating.callState!="ready"){return;}
like=RW._getDefaultValue(like,true);var vid=(rating.beforeRate?rating.beforeRate(rating,like):true);if(false===vid){return;}
if(true===vid&&RW._is(RW.Vid)){vid=RW.Vid;}
rating.setLabel(advanced.text.thanks);rating.setTooltip('<i class="rw-action-loader"></i>');place_tooltip(elm);rating.callState="calling";var data=[{name:"urid",value:rating.urid},{name:"like",value:like}];if(vid!==true){data.push({name:"vid",value:vid});}
if(rating.options.uarid>0){data.push({name:"uarid",value:rating.options.uarid});}
rating.refreshCount=0;rating.refreshUI=false;clearTimeout(voteTimeout);voteTimeout=setTimeout(timer_refresh,wakeup_fragment);RW._jsCall("http://js.rating-widget.com/action/rate.php",data);};this.dislike=function(elm)
{return rating.like(elm,false);};this.setLabel=function(label)
{if(rating.callState!="ready"){return;}
if(!rating.options.showInfo){return;}
RW._foreach(labels,function(l){l.innerHTML=label;});};this.setLabelColor=function(color)
{if(rating.callState!="ready"){return;}
RW._foreach(labels,function(l){l.style.color=color;});};this.setTooltip=function(tip)
{if(rating.callState!="ready"){return;}
if(!rating.options.showTooltip){return;}
RW._tooltip.innerHTML='<b></b><span class="rw-ui-tooltip-label"><nobr>'+tip+'</nobr></span>';};this.hideTooltip=function()
{if(RW._is(RW._tooltip)){RW._tooltip.style.display="none";}};var attach_events=function()
{RW._foreach(elements,function(r,i){r.onmouseover=function(e){rating.setLabelColor(hover.color);};r.onmouseout=function(e){rating.setLabelColor(font.color);};});if(rating.type==RW.TYPE.STAR)
{RW._foreach(stars,function(s,k){RW._foreach(s,function(star,i){star.onmouseover=function(e)
{if(options.readOnly){return;}
if(rating.callState!="ready"){return;}
clearTimeout(mouseOutTimeout);mouseOutTimeout=null;for(var j=0,len=s.length;j<len;j++){if(j<=i){RW._Class.set(stars[k][j],"rw-ui-star-over");}else{RW._Class.set(stars[k][j],"rw-ui-star-preview");}}
if(advanced.star.stars!==RW.MAX)
{rating.setTooltip((i+1)+" / "+advanced.star.stars);}
else
{switch(i)
{case 0:rating.setTooltip(advanced.text.rateAwful);break;case 1:rating.setTooltip(advanced.text.ratePoor);break;case 2:rating.setTooltip(advanced.text.rateAverage);break;case 3:rating.setTooltip(advanced.text.rateGood);break;case 4:rating.setTooltip(advanced.text.rateExcellent);break;}}
place_tooltip(star);};star.onmouseout=function(e)
{if(options.readOnly){return;}
if(RW._isUndefinedOrNull(mouseOutTimeout))
{mouseOutTimeout=setTimeout(function(){rating.refresh();},200);}};star.onclick=function(e)
{if(options.readOnly){return;}
if(rating.callState!="ready"){return;}
rating.doRate(i+1,star);};});});}
else if(rating.type==RW.TYPE.NERO)
{RW._foreach(elements,function(elm){var like=null,like_label=null,dislike=null,dislike_label=null;if(advanced.nero.showLike)
{like=get_like(elm);like_label=get_like_label(elm);}
if(advanced.nero.showDislike)
{dislike=get_dislike(elm);dislike_label=get_dislike_label(elm);}
if(advanced.nero.showLike){like.onmouseover=function(e)
{if(options.readOnly){return;}
if(rating.callState!="ready"){return;}
clearTimeout(mouseOutTimeout);mouseOutTimeout=null;rating.setTooltip(advanced.text.like);place_tooltip(like);};}
if(advanced.nero.showDislike){dislike.onmouseover=function(e)
{if(options.readOnly){return;}
if(rating.callState!="ready"){return;}
clearTimeout(mouseOutTimeout);mouseOutTimeout=null;rating.setTooltip(advanced.text.dislike);place_tooltip(dislike);};}
var mouseout=function(e)
{if(options.readOnly){return;}
if(RW._isUndefinedOrNull(mouseOutTimeout))
{mouseOutTimeout=setTimeout(function(){rating.refresh();},200);}};if(advanced.nero.showLike){like.onmouseout=mouseout;}
if(advanced.nero.showDislike){dislike.onmouseout=mouseout;}
if(advanced.nero.showLike){like.onclick=function(e)
{if(options.readOnly){return;}
if(rating.callState!="ready"){return;}
RW._foreach(elements,function(e){if(advanced.nero.showLike){RW._Class.add(get_like(e).parentNode,"rw-selected");}
if(advanced.nero.showDislike){RW._Class.remove(get_dislike(e).parentNode,"rw-selected");}});rating.like(like);};}
if(advanced.nero.showDislike){dislike.onclick=function(e)
{if(options.readOnly){return;}
if(rating.callState!="ready"){return;}
RW._foreach(elements,function(e){if(advanced.nero.showDislike){RW._Class.add(get_dislike(e).parentNode,"rw-selected");}
if(advanced.nero.showLike){RW._Class.remove(get_like(e).parentNode,"rw-selected");}});rating.dislike(dislike);};}});}};};})();RW._renderFlashCookie();