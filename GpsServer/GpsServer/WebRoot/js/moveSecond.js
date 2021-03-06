/*JavaScript source file, sq */

/*-----------------google map api service------------------*/

var map;
var marker;
var infowindow;
var myhtml = "";
/**
 * 地图初始化并定位
 * 
 * @param lng
 *            经度
 * @param lat
 *            纬度
 * @param zoom
 *            地图比例
 * @param id
 *            承载地图ID
 * @param status
 *            终端状态
 * @param  style
 *             1-显示标记 2-不显示标记
 * @return
 */
function google_location(lng, lat, zoom, id, status, style) {
	var myLatlng = new google.maps.LatLng(lat, lng);
	var myOptions = {
		zoom : zoom,
		center : myLatlng,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}
	//初始化google地图
	 map = new google.maps.Map(document.getElementById(id), myOptions);
	
	if(style == 1){ //加注标记点
		if(status=="0"){  //初始化离线图标
		 marker = new google.maps.Marker( {
			position : myLatlng,
			map : map,
			icon: 'images/blue-dot.png'
		});
		}else{
		 marker = new google.maps.Marker( {
				position : myLatlng,
				map : map,
				title: 'hello'
			});
		}
		infowindow = new google.maps.InfoWindow();
		google.maps.event.addListener(marker,'click',function(){
				infowindow.open(map);
			});
	}
}


/**********************************ajax 获取地址解释信息 start*********************************************/
var bXmlHttpSupport = (typeof XMLHttpRequest != "undefined" || window.ActiveXObject);
if (typeof XMLHttpRequest == "undefined" && window.ActiveXObject) {
	function XMLHttpRequest() {
		var arrSignatures = [ "MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0",
				"MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP" ];
		for ( var i = 0; i < arrSignatures.length; i++) {
			try {
				var oRrequest = new ActiveXObject(arrSignatures[i]);
				return oRrequest;
			} catch (oError) {
			}
		}
		throw new Error("MSXML is not installed on your system");
	}
}
 /**
  * 地址解析
  */
 function getAddress(lat,lng,point){
   		if(bXmlHttpSupport){
   			var sUrl = "getAddress.action?lat="+lat+"&lng="+lng+"&zoom="+map.getZoom();
   			var oRequest = new XMLHttpRequest();
   			oRequest.onreadystatechange= function(){
   				if(oRequest.readyState==4){
   					var position = eval('('+oRequest.responseText+')');
   					infowindow.setContent("<br>"
						+ position.address);
   				}
   			};
   			oRequest.open("POST",sUrl,true);
   			oRequest.send(null);
   		}
   }
/**********************************ajax 获取地址解释信息 end*********************************************/


/*************************************锁定跟踪 定时获取定位信息 start************************************/
/**
 * 锁定跟踪
 */
function retrievePosition(){
   		if(bXmlHttpSupport){
   			var sUrl = "position.action?sim="+document.getElementById("simid").value;
   			var oRequest = new XMLHttpRequest();
   			oRequest.onreadystatechange= function(){
   				if(oRequest.readyState==4){
   					var position = eval('('+oRequest.responseText+')');
   					document.getElementById("p_sim").innerHTML = position.sim;
   					if(position.locationMode == 1)
   						document.getElementById("p_station").innerHTML = "基站定位";
   					else
   						document.getElementById("p_station").innerHTML = "GPS定位";
   					document.getElementById("p_p_time").innerHTML = position.p_time;
   					document.getElementById("p_lati").innerHTML = position.lati_direction+position.latitude;
   					document.getElementById("p_long").innerHTML = position.long_direction+position.longitude;
   					document.getElementById("p_speed").innerHTML = position.speed;
   					document.getElementById("p_direction").innerHTML = position.direction;
   					if(position.netstatus == 1)
   						document.getElementById("p_net").innerHTML = "在线";
   					else
   						document.getElementById("p_net").innerHTML = "信号弱或丢失电源";
   					if(position.pstatus == 1)
   						document.getElementById("p_status").innerHTML = "工作";
   					else
   						document.getElementById("p_status").innerHTML = "空闲";
   					var myLatlng = new google.maps.LatLng(position.latitude, position.longitude);
					//if(infowindow!=null)
					//	infowindow.close();
					
					myhtml = "<b>经度：" + position.longitude + "  纬度："+position.latitude+"</b><br/><b>终端号码:"+position.sim+"</b><br/><b>速度:"+position.speed+"</b><br/><b>方向:"+position.direction+"</b><br/><b>时间:"+position.p_time+"</b>";
					infowindow.setContent(myhtml);
					infowindow.setPosition(myLatlng);
					marker.setPosition(myLatlng);
					if(position.pstatus=='离线'){
						marker.setIcon('images/blue-dot.png');
					}
				    map.setCenter(myLatlng);
   				}
   			};
   			oRequest.open("POST",sUrl,"true");
   			oRequest.send(null);
   		}
}
/*************************************锁定跟踪 定时获取定位信息 end************************************/
