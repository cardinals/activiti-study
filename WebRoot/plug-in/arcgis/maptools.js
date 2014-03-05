dojo.require("esri.map");
    dojo.require("esri.layers.GraphicsLayer");
    dojo.require("esri.dijit.Scalebar");
    dojo.require("esri.toolbars.draw");
    dojo.require("esri.toolbars.edit");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.Menu");
    dojo.require("esri.dijit.OverviewMap");
    dojo.require("dijit.layout.BorderContainer");
    dojo.require("dijit.layout.ContentPane");
  

    var map, defaultSymbol, x, y;
    var defaultLineSymbol,polylineSymbol1,polylineSymbol2,polylineSymbol3;
    
    
    function initMap(mapDivId) {
    	
    	console.log(mapDivId)
    	
//    	initSymbols();
    	
		var initExtent = new esri.geometry.Extent({
            "xmin": 12634205.508134518,
            "ymin": 2558425.495406349,
            "xmax": 12762696.152681727,
            "ymax": 2621409.6067132493,
            "spatialReference": {
            "wkid": 102113
            }
        });
		console.log(initExtent)
		
		map = new esri.Map(mapDivId, {
            extent: initExtent,
            logo: false
        });
        
		console.log('chei')
		
        dojo.connect(map, "onLoad", function(theMap) {
        });
        
        console.log(map)
        map.addLayer(new abcmapLayer());
        
        defaultSymbol = new esri.symbol.SimpleMarkerSymbol(esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE, 10, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([33,125,33]), 1), new dojo.Color([15,22,125,0.25]));
        polylineSymbol1 = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([104,23,14]), 3);
        polylineSymbol2 = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([104,0,14]), 3);
        polylineSymbol3 = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([22,0,125]), 3);
        
        dojo.connect(map, "onClick", function(evt){
        });
        
//        dojo.connect(map.graphics, "onMouseOver", cursorChangeToPointer);
//        dojo.connect(map.graphics, "onMouseOut", cursorChangeToArrow);
        
        
//        dojo.connect(dijit.byId('map'), 'resize', 
//            function() {
//            	map.resize();
//            	map.reposition();
//            }
//        );

  }
    
//dojo.ready(initMap);
  
function onMapDivResize(width,height){
	if(map){
		map.resize();
		map.reposition();	
	}
}


function addGraphicLayer(layerId){
	var graphicsLayer = new esri.layers.GraphicsLayer({id:layerId});
	map.addLayer(graphicsLayer);
//	dojo.connect(graphicsLayer, "onMouseOver", cursorChangeToPointer);
//    dojo.connect(graphicsLayer, "onMouseOut", cursorChangeToArrow);
}

function bindingGraphicLayerEvent(lyaerId){
	console.log(map.getLayer(lyaerId));
	dojo.connect(map.getLayer(lyaerId), "onMouseOver", cursorChangeToPointer);
    dojo.connect(map.getLayer(lyaerId), "onMouseOut", cursorChangeToArrow);
}

function addGraphics(layerId, roadId, roadDirection){
	var attr = facilityTypeTree.tree('find',layerId).attributes;
	var url = attr.url;
	var type = attr.type;
	var symbol = attr.symbol;
	var result;
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType:"json",
		url : url,// 请求的action路径
		data:{
			roadId:roadId
		},
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			console.log(data);
//			var d = $.parseJSON(data);
			if (data.success) {
//				var msg = d.msg;
				tip("加载成功");
				result = data.items;
			}
		}
	});
	
	var rr = {};
	appendGraphics(result, type, symbol, layerId);
}

function appendGraphics(result, type, symbol, layerId){
	var graphicSymbol;
	switch(symbol){
		case "markSymbol":
			graphicSymbol = markSymbol;
			break;
		case "guardrailSymbol":
			graphicSymbol = guardrailSymbol;
			break;
				
	}
	
	switch(type){
		case "point":
			appendPointGraphics(result, graphicSymbol, layerId);
		case "line":
			appendLineGraphics(result, graphicSymbol, layerId);
			
	}
	
}

//渲染点图像
function appendPointGraphics(data, symbol, layerId){
	var popupTemplate = new esri.dijit.PopupTemplate({
      title: "{title}",
      description:"{description}"
//      description:"posi:路右侧<br/>gps:114.3124/22.3412<br/><img src='plug-in/arcgis/images/red_arrow.png'>"
    });
	
	var ddd = [
//       {"title":"mark","description":"test1", "gps":[114.631,23.51]},
//       {"title":"mark","description":"test2", "gps":[114.632,23.51]},
       {"title":"mark","description":"posi:路右侧<br/>gps:114.3124/22.3412<br/><img src='plug-in/arcgis/images/red_arrow.png'>", "gps":[114.633,23.51]}
   ];
//	data = ddd;
	
	console.log(data);
	for(var j = 0; j < data.length; j++){
		var item = data[j];
		var point = new esri.geometry.Point(item.gps);
		var pg = new esri.Graphic(point, symbol, item, popupTemplate);
		map.getLayer(layerId).add(pg);
	}
	
}

//渲染线图像
function appendLineGraphics(data, symbol, layerId){
	var popupTemplate = new esri.dijit.PopupTemplate({
      title: "{title}",
      description:"{description}"
    });
	
	var ddd = [
       {"title":"guardrail","description":"h", "gps":{"paths":[[[114.631,23.521],[114.641,23.51]]]}},
       {"title":"guardrail","description":"g", "gps":{"paths":[[[114.632,23.521],[114.642,23.51]]]}},
       {"title":"guardrail","description":"t", "gps":{"paths":[[[114.0461,22.5516],[114.0461,22.5514]]]}}
   ];
//	data = ddd;
	
	for(var j = 0; j < data.length; j++){
		var item = data[j];
		var line = new esri.geometry.Polyline(item.gps);
		console.log(line);
		var pg = new esri.Graphic(line, symbol, item, popupTemplate);
		map.getLayer(layerId).add(pg);
	}
	
}

//change cursor
function cursorChangeToPointer(mouseOverEvt){
	map.setMapCursor("pointer");

}

function cursorChangeToArrow(mouseOutEvt){
	map.setMapCursor("default");
}