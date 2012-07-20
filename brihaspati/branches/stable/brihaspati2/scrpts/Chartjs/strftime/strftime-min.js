Date.ext={};
//alert("----------1");
Date.ext.util={};
//alert("------------2");
Date.ext.util.xPad=function(x,pad,r)
{
//alert("-----------3");
	if(typeof (r)=="undefined")
	{r=10}
	for(;parseInt(x,10)<r&&r>1;r/=10)
	{
	//alert("4---------------");
	x=pad.toString()+x
	}return x.toString()};
	//alert("5------------------");
	Date.prototype.locale="en-GB";
	//alert("6---------------"+Date.prototype.locale);
	if(document.getElementsByTagName("html")&&document.getElementsByTagName("html")[0].lang)
	{
		Date.prototype.locale=document.getElementsByTagName("html")[0].lang}Date.ext.locales={};
		//alert("sm-------------"+Date.ext.locales);
		Date.ext.locales.en={a:["Sun","Mon","Tue","Wed","Thu","Fri","Sat"],
					A:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],
					b:["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
					B:["January","February","March","April","May","June","July","August","September","October","November","December"],
					c:"%a %d %b %Y %T %Z",p:["AM","PM"],P:["am","pm"],x:"%d/%m/%y",X:"%T"};
		//alert("------------7"+Date.ext.locales.en);
		Date.ext.locales["en-US"]=Date.ext.locales.en;Date.ext.locales["en-US"].c="%a %d %b %Y %r %Z";
		Date.ext.locales["en-US"].x="%D";Date.ext.locales["en-US"].X="%r";
		Date.ext.locales["en-GB"]=Date.ext.locales.en;
		Date.ext.locales["en-AU"]=Date.ext.locales["en-GB"];
		Date.ext.formats={a:function(d){return Date.ext.locales[d.locale].a[d.getDay()]},
				 A:function(d){return Date.ext.locales[d.locale].A[d.getDay()]},
				b:function(d){return Date.ext.locales[d.locale].b[d.getMonth()]},
				B:function(d){return Date.ext.locales[d.locale].B[d.getMonth()]},
				c:"toLocaleString",C:function(d){return Date.ext.util.xPad(parseInt(d.getFullYear()/100,10),0)},
				d:["getDate","0"],e:["getDate"," "],g:function(d){return Date.ext.util.xPad(parseInt(Date.ext.util.G(d)/100,10),0)},
				G:function(d){
						var y=d.getFullYear();
						var V=parseInt(Date.ext.formats.V(d),10);
						var W=parseInt(Date.ext.formats.W(d),10);
				
						if(W>V)
						{y++}
						else{
							if(W===0&&V>=52)
							{y--}
							}
					return y},
				H:["getHours","0"],I:function(d){var I=d.getHours()%12;return Date.ext.util.xPad(I===0?12:I,0)},
				j:function(d){var ms=d-new Date(""+d.getFullYear()+"/1/1 GMT");
				//alert("------------24--------"+ms);
	ms+=d.getTimezoneOffset()*60000;
	//alert("--------25-------"+ms+);
		//alert("Date.ext.formats--------"+Date.ext.formats);
	var doy=parseInt(ms/60000/60/24,10)+1;
	//alert("--------------19---------"+doy);
	return Date.ext.util.xPad(doy,0,100)},
	
		m:function(d){return Date.ext.util.xPad(d.getMonth()+1,0)},
		M:["getMinutes","0"],p:function(d){return Date.ext.locales[d.locale].p[d.getHours()>=12?1:0]},
		P:function(d){return Date.ext.locales[d.locale].P[d.getHours()>=12?1:0]},S:["getSeconds","0"],
		u:function(d){var dow=d.getDay();
		return dow===0?7:dow},
		U:function(d){var doy=parseInt(Date.ext.formats.j(d),10);
				
		var rdow=6-d.getDay();
		//alert("-----------rdow------"+rdow);
		var woy=parseInt((doy+rdow)/7,10);
		return Date.ext.util.xPad(woy,0)},
		V:function(d){var woy=parseInt(Date.ext.formats.W(d),10);
	var dow1_1=(new Date(""+d.getFullYear()+"/1/1")).getDay();
	//alert("----------23-------"+dow1_1);
	
	var idow=woy+(dow1_1>4||dow1_1<=1?0:1);
	//alert("------22--------"+(idow==53&&(new Date(""+d.getFullYear()+"/12/31")).getDay()<4));
	if(idow==53&&(new Date(""+d.getFullYear()+"/12/31")).getDay()<4)
	{idow=1}
	else{
		if(idow===0)
			{
				idow=Date.ext.formats.V(new Date(""+(d.getFullYear()-1)+"/12/31"))
				//alert("11-----------"+idow);
			}
		}return Date.ext.util.xPad(idow,0)},
		w:"getDay",W:function(d){var doy=parseInt(Date.ext.formats.j(d),10);var rdow=7-Date.ext.formats.u(d);	
										//alert("------------21-------"+rdo);
		var woy=parseInt((doy+rdow)/7,10);
		//alert("------------12"+woy);
		return Date.ext.util.xPad(woy,0,10)},y:function(d){return Date.ext.util.xPad(d.getFullYear()%100,0)},Y:"getFullYear",z:function(d){var o=d.getTimezoneOffset();
		//alert("o-----------"+o);
		var H=Date.ext.util.xPad(parseInt(Math.abs(o/60),10),0);
		//alert("----------H--"+H);
		var M=Date.ext.util.xPad(o%60,0);
		//alert("---------M---------"+M);
		return(o>0?"-":"+")+H+M},Z:function(d){return d.toString().replace(/^.*\(([^)]+)\)$/,"$1")},"%":function(d){return"%"}};
		//Alert("-----------d------");
		//alert("H-----------"+H);
		Date.ext.aggregates={c:"locale",D:"%m/%d/%y",h:"%b",n:"\n",r:"%I:%M:%S %p",R:"%H:%M",t:"\t",T:"%H:%M:%S",x:"locale",X:"locale"};
		//alert("--------------13");
		Date.ext.aggregates.z=Date.ext.formats.z(new Date());Date.ext.aggregates.Z=Date.ext.formats.Z(new Date());Date.ext.unsupported={};
		//alert("---------14--------"+Date.ext.aggregates.z);
		Date.prototype.strftime=function(fmt){
						//alert("0-----------"+Date.ext.locales);
						if(!(this.locale in Date.ext.locales)){
							if(this.locale.replace(/-[a-zA-Z]+$/,"") in Date.ext.locales){
								this.locale=this.locale.replace(/-[a-zA-Z]+$/,"")
								//alert("---------------a------"+this.locale);							
							}else{
								this.locale="en-GB"
								
								}
							}
					var d=this;
		//alert("-------18----"+d);
					while(fmt.match(/%[cDhnrRtTxXzZ]/))
					{
						fmt=fmt.replace(/%([cDhnrRtTxXzZ])/g,
						function(m0,m1){
							var f=Date.ext.aggregates[m1];
							//alert("-----------17--------"+f);
							return(f=="locale"?Date.ext.locales[d.locale][m1]:f)
						})
					}
					var str=fmt.replace(/%([aAbBCdegGHIjmMpPSuUVwWyY%])/g,
					//alert("----------15-------"+str);
					function(m0,m1){
					var f=Date.ext.formats[m1];
					//alert("----------16-------"+f);
					if(typeof (f)=="string")
						{return d[f]()}
					else{if(typeof (f)=="function"){return f.call(d,d)}else{if(typeof (f)=="object"&&typeof (f[0])=="string"){return Date.ext.util.xPad(d[f[0]](),f[1])}else{return m1}}}});d=null;return str};
//alert("----------Date.prototype.strftime-------"+Date.prototype.strftime);
