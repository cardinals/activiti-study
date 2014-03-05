
$(document).ready(function(){
	
	
});

function cal(){
	
	var points=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15];
	
	$.each(points,function(i,value){
		
		var pointS = points[i];
		var pointE = points[i+1];
		
		var distParams=[pointE,pointS];
		
		aysnc(distParams, function(distance) {
		    debugger;
		    console.log("success cal with j="+j+",distance="+distance+",i="+i);
		    generateResult(j, distance);
		    j++;
		}); 
		
	});
	

}

function aysnc(distParams,fun){
	console.log(distParams);
}