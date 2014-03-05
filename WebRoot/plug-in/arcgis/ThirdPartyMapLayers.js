//dojo.require("esri");
//dojo.require("esri.layers");
dojo.ready(function (){
dojo.declare(
          "sogoumapLayer", esri.layers.TiledMapServiceLayer, {
              tileUrls: new Array("http://p0.go2map.com/seamless1/0/174/", "http://p1.go2map.com/seamless1/0/174/", "http://p2.go2map.com/seamless1/0/174/", "http://p3.go2map.com/seamless1/0/174/"),
              constructor: function() {
                  this.spatialReference = new esri.SpatialReference({ wkid: 102113 });
                  this.initialExtent = (this.fullExtent = new esri.geometry.Extent(7736923.324417573, 1557979.4506958486, 7810264.653057101, 1595204.283470679, this.spatialReference));
                  this.tileInfo = new esri.layers.TileInfo({
                      "rows": 256,
                      "cols": 256,
                      "compressionQuality": 0,
                      "origin": {
                          "x": -20037508.342787,
                          "y": 20037508.342787
                      },    
                      "spatialReference": {
                          "wkid": 102113
                      },
                      "lods": [

                        { "level": 4, "resolution": 9783.93962049996, "scale": 36978595.474472 },
                        { "level": 5, "resolution": 4891.96981024998, "scale": 18489297.737236 },
                        { "level": 6, "resolution": 2445.98490512499, "scale": 9244648.868618 },
                        { "level": 7, "resolution": 1222.99245256249, "scale": 4622324.434309 },
                        { "level": 8, "resolution": 611.49622628138, "scale": 2311162.217155 },
                        { "level": 9, "resolution": 305.748113140558, "scale": 1155581.108577 },
                        { "level": 10, "resolution": 152.874056570411, "scale": 577790.554289 },
                        { "level": 11, "resolution": 76.4370282850732, "scale": 288895.277144 },
                        { "level": 12, "resolution": 38.2185141425366, "scale": 144447.638572 },
                        { "level": 13, "resolution": 19.1092570712683, "scale": 72223.819286 },
                        { "level": 14, "resolution": 9.55462853563415, "scale": 36111.909643 },
                        { "level": 15, "resolution": 4.77731426794937, "scale": 18055.954822 },
                        { "level": 16, "resolution": 2.38865713397468, "scale": 9027.977411 },
                        { "level": 17, "resolution": 1.19432856685505, "scale": 4513.988705 },
                        { "level": 18, "resolution": 0.597164283559817, "scale": 2256.994353 }
                        ]
                  });

                  this.loaded = true;
                  this.onLoad(this);
              },

              getTileUrl: function(level, row, col) {
              level = level - 1;
              var offsetX = Math.pow(2,level);
              var offsetY = offsetX - 1;
              
              var numX = col - offsetX;
              var numY = (-row) + offsetY;
              
              level = level + 1
              
              var zooml = 729 - level;
               if (zooml == 710) zooml = 792;
               
               var blo = Math.floor(numX/200);
               var bla = Math.floor(numY/200);
               
                var blos,blas;
                if (blo < 0) 
                 blos = "M" + ( - blo);
                else 
                 blos = "" + blo;
                if (bla < 0) 
                 blas = "M" + ( - bla);
                else 
                 blas = "" + bla;
                 
                var x = numX.toString().replace("-","M");
                var y = numY.toString().replace("-","M");
              
              var num = (row+col) % this.tileUrls.length;
                  return this.tileUrls[num] + zooml + "/" + blos + "/" + blas + "/" + x +"_"+ y + ".GIF";

              }
          }

);

dojo.declare("abcmapLayer", esri.layers.TiledMapServiceLayer, {
              _subDomains: new Array("emap0", "emap1", "emap2", "emap3"),
              constructor: function() {
                  this.spatialReference = new esri.SpatialReference({ wkid: 102113 });
//                  this.initialExtent = (this.fullExtent = new esri.geometry.Extent(12668267.217712665, 2566487.8802165673, 12741608.546352193, 2603559.838934828, this.spatialReference));
                this.initialExtent = (this.fullExtent = new esri.geometry.Extent(-20037508.342787, -20037508.342787, 20037508.342787, 20037508.342787, this.spatialReference));
                  this.tileInfo = new esri.layers.TileInfo({
                      "rows": 256,
                      "cols": 256,
                      "compressionQuality": 0,
                      "origin": {
                          "x": -20037508.342787,
                          "y": 20037508.342787
                      },
                      "spatialReference": {
                          "wkid": 102113
                      },
                      "lods": [

                    { "level": 4, "resolution": 9783.93962049996, "scale": 36978595.474472 },
                    { "level": 5, "resolution": 4891.96981024998, "scale": 18489297.737236 },
                    { "level": 6, "resolution": 2445.98490512499, "scale": 9244648.868618 },
                    { "level": 7, "resolution": 1222.99245256249, "scale": 4622324.434309 },
                    { "level": 8, "resolution": 611.49622628138, "scale": 2311162.217155 },
                    { "level": 9, "resolution": 305.748113140558, "scale": 1155581.108577 },
                    { "level": 10, "resolution": 152.874056570411, "scale": 577790.554289 },
                    { "level": 11, "resolution": 76.4370282850732, "scale": 288895.277144 },
                    { "level": 12, "resolution": 38.2185141425366, "scale": 144447.638572 },
                    { "level": 13, "resolution": 19.1092570712683, "scale": 72223.819286 },
                    { "level": 14, "resolution": 9.55462853563415, "scale": 36111.909643 },
                    { "level": 15, "resolution": 4.77731426794937, "scale": 18055.954822 },
                    { "level": 16, "resolution": 2.38865713397468, "scale": 9027.977411 },
                    { "level": 17, "resolution": 1.19432856685505, "scale": 4513.988705 },
                    { "level": 18, "resolution": 0.597164283559817, "scale": 2256.994353 }
                    ]
//                      new LOD(19, 0.298582141647617, 1128.497176)
                  });

                  this.loaded = true;
                  this.onLoad(this);
              },

              getTileUrl: function(level, row, col) {
                  var subdomain = this._subDomains[(level + col + row) % this._subDomains.length];
                  return "http://" + subdomain + ".is.autonavi.com/appmaptile?&z=" + level + "&x=" + (col) + "&y=" + (row);

              }
          }

);


dojo.declare("qqmapLayer", esri.layers.TiledMapServiceLayer, 
{
            _subDomains: new Array("p0", "p1", "p2", "p3"),
            scope: new Array(0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 7, 0, 7, 0, 15, 0, 15, 0, 31, 0, 31, 0, 63, 4, 59, 0, 127, 12, 115, 0, 225, 28, 227, 356, 455, 150, 259, 720, 899, 320, 469, 1440, 1799, 650, 929, 2880, 3589, 1200, 2069, 5760, 7179, 2550, 3709, 11520, 14349, 5100, 7999, 23060, 28689, 10710, 15429, 46120, 57369, 20290, 29849, 89990, 124729, 41430, 60689, 184228, 229827, 84169, 128886),
            constructor: function() {
                this.spatialReference = new esri.SpatialReference({ wkid: 102113 });
                this.initialExtent = this.fullExtent;
                this.fullExtent = new esri.geometry.Extent(-20037508.342787, -20037508.342787, 20037508.342787, 20037508.342787, this.spatialReference);
                this.tileInfo = new esri.layers.TileInfo({
                    "rows": 256,
                    "cols": 256,
                    "compressionQuality": 0,
                    "origin": {
                        "x": -20037508.342787,
                        "y": 20037508.342787
                    },
                    "spatialReference": {
                        "wkid": 102113
                    },
                    "lods": [
            
              { "level": 4, "resolution": 9783.93962049996, "scale": 36978595.474472 },
              { "level": 5, "resolution": 4891.96981024998, "scale": 18489297.737236 },
              { "level": 6, "resolution": 2445.98490512499, "scale": 9244648.868618 },
              { "level": 7, "resolution": 1222.99245256249, "scale": 4622324.434309 },
              { "level": 8, "resolution": 611.49622628138, "scale": 2311162.217155 },
              { "level": 9, "resolution": 305.748113140558, "scale": 1155581.108577 },
              { "level": 10, "resolution": 152.874056570411, "scale": 577790.554289 },
              { "level": 11, "resolution": 76.4370282850732, "scale": 288895.277144 },
              { "level": 12, "resolution": 38.2185141425366, "scale": 144447.638572 },
              { "level": 13, "resolution": 19.1092570712683, "scale": 72223.819286 },
              { "level": 14, "resolution": 9.55462853563415, "scale": 36111.909643 },
              { "level": 15, "resolution": 4.77731426794937, "scale": 18055.954822 },
              { "level": 16, "resolution": 2.38865713397468, "scale": 9027.977411 },
              { "level": 17, "resolution": 1.19432856685505, "scale": 4513.988705 },
              { "level": 18, "resolution": 0.597164283559817, "scale": 2256.994353 }
            ]
                });

                this.loaded = true;
                this.onLoad(this);
            },

            getTileUrl: function(level, row, col) {

                var subdomain = this._subDomains[(level + col + row) % this._subDomains.length];
                var f = level * 4;
                var i = this.scope[f++];
                var j = this.scope[f++];
                var l = this.scope[f++];
                var scope = this.scope[f];
                var tileNo = "";
                if (col >= i && col <= j && row >= l && row <= scope) {
                    row = Math.pow(2, level) - 1 - row;
                    tileNo = level + "/" + Math.floor(col / 16) + "/" + Math.floor(row / 16) + "/" + col + "_" + row + ".png";
                }
                return "http://" + subdomain + ".map.qq.com/maptiles/" + tileNo;

            }
        });
});