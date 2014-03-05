dojo.ready(function(){
    esri.bundle.toolbars.draw = 
    {
        addMultipoint:"单击添加多个点",
        addPoint:"单击添加点",
        addShape:"单击添加图形，或者按住鼠标左键开始绘制，释放以完成操作",
        complete:"双击完成绘制",
        convertAntiClockwisePolygon:"逆时针绘制的多边形将转换成顺时针方向",
        finish:"双击完成绘制",
        freehand:"按住鼠标左键开始绘制，释放以完成操作",
        invalidType:"不支持该几何类型",
        resume:"单击继续绘制",
        start:"单击开始绘制"
    }
});

var DRAW_ARROW = '绘制箭头';
var DRAW_FREEHAND_POLYLINE = '绘制自由折线';
var DRAW_MULTI_POINT = '绘制多点';
var DRAW_POINT = '绘制单点';
var DRAW_POLYGON = '绘制多边形';
var DRAW_POLYLINE = '绘制折线';
var DRAW_RECTANGLE = '绘制长方形';

var GEOMETRY = {
    TYPE:{
        ARROW:'arrow',
        CIRCLE:'circle',
        ELLIPSE:'ellipse',
        EXTENT:'extent',
        LINE:'line',
        MULTI_POINT:'multipoint',
        POINT:'point',
        POLYGON:'polygon',
        POLYLINE:'polyline',
        RECTANGLE:'rectangle',
        TRIANGLE:'triangle'
    }
}

var LOCALE_EN = 0;
var LOCALE_ZH = 1;
var LOCALE_ZH_CN = 2;

var LOCALE = LOCALE_ZH_CN;