var myChart1 = echarts.init(document.getElementById('echarts1'));
var myChart2 = echarts.init(document.getElementById('echarts2'));
var myChart3 = echarts.init(document.getElementById('echarts3'));
var myChart4 = echarts.init(document.getElementById('echarts4'));
var myChart6 = echarts.init(document.getElementById('echarts6'));
var myChart7 = echarts.init(document.getElementById('echarts7'));
$(window).load(function () {
    setInterval(function () {
        fnDate();
    }, 1000);
})
$(function () {
    $("html").css({fontSize: $(window).width() / 20});
    myChart1.resize();
    myChart2.resize();
    myChart3.resize();
    myChart4.resize();
    myChart7.resize();
    myChart6.resize();
    initBaiduMap();
});

function initBaiduMap() {
    createMap();//创建地图
    setMapEvent();//设置地图事件
    //addMapControl();//向地图添加控件
    addMarker();//向地图中添加marker
}

//创建地图函数：
function createMap() {
    var map = new BMap.Map("wcPBaiduMap");//在百度地图容器中创建一个地图
    var point = new BMap.Point(112.992107,28.194273);//定义一个中心点坐标
    map.centerAndZoom(point, 18);//设定地图的中心点和坐标并将地图显示在地图容器中
    window.map = map;//将map变量存储在全局
}

//地图事件设置函数：
function setMapEvent() {
    map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
    map.enableScrollWheelZoom();//启用地图滚轮放大缩小
    map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
    map.enableKeyboard();//启用键盘上下左右键移动地图
   // map.setMapType(BMAP_PERSPECITVE_MAP);//透视图
   // map.setMapType(BMAP_PERSPECITVE_MAP);//透视图
 //   map.setMapType(BMAP_PERSPECITVE_MAP);//透视图
  /* */ map.setMapStyleV2({
        styleId: '9260a25a95016dc8c9e64c5d62af26c7'
    });
}

//地图控件添加函数：
function addMapControl() {
    //向地图中添加缩放控件
    var ctrl_nav = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_LARGE});
    map.addControl(ctrl_nav);
    //向地图中添加缩略图控件
    var ctrl_ove = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, isOpen: 1});
    map.addControl(ctrl_ove);
    //向地图中添加比例尺控件
    var ctrl_sca = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
    map.addControl(ctrl_sca);
}

//标注点数组
var markerArr = [{
    title: "泽福科技",
    content: "电话：0731-82685268\n手机：18802058397",
    point: "112.992107|28.194273",
    isOpen: 1,
    icon: {w: 23, h: 25, l: 46, t: 21, x: 9, lb: 12}
}];

//创建marker
function addMarker() {
    for (var i = 0; i < markerArr.length; i++) {
        var json = markerArr[i];
        var p0 = json.point.split("|")[0];
        var p1 = json.point.split("|")[1];
        var point = new BMap.Point(p0, p1);
        var marker = new BMap.Marker(point);
        var iw = createInfoWindow(i);
        var label = new BMap.Label(json.title, {"offset": new BMap.Size(json.icon.lb - json.icon.x + 10, -20)});
        marker.setLabel(label);
        map.addOverlay(marker);
        label.setStyle({
            borderColor: "#808080",
            color: "#333",
            cursor: "pointer"
        });

        (function () {
            var index = i;
            var _iw = createInfoWindow(i);
            var _marker = marker;
            _marker.addEventListener("click", function () {
                this.openInfoWindow(_iw);
            });
            _iw.addEventListener("open", function () {
                _marker.getLabel().hide();
            })
            _iw.addEventListener("close", function () {
                _marker.getLabel().show();
            })
            label.addEventListener("click", function () {
                _marker.openInfoWindow(_iw);
            })
            if (!!json.isOpen) {
                label.hide();
                _marker.openInfoWindow(_iw);
            }
        })()
    }
}

//创建InfoWindow
function createInfoWindow(i) {
    var json = markerArr[i];
    var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>" + json.content + "</div>");
    return iw;
}


function initPage(event) {
    if (event.data) {
        let largeScreen = JSON.parse(event.data);
        if (largeScreen.isSensorDataUpdate) {
            if (largeScreen.sensorDataVos != null) {
                sensorDataUlInit(largeScreen.sensorDataVos);
            }
            UsagePieInit(largeScreen.waterUsageV, largeScreen.energyUsageV);
        }
        if (largeScreen.isSetDataUpdate) {
            for (var key in largeScreen) {
                if (key == "dayGrowth") {
                    $('#' + key).text(largeScreen[key].toFixed(2));
                } else {
                    $('#' + key).text(largeScreen[key]);
                }
            }
            myChartPieInit(largeScreen.avgTime, largeScreen.manFlow, largeScreen.womanFlow);
            if (largeScreen.wcStatisticsInfos != null) {
                myChartLineInit(largeScreen.wcStatisticsInfos);
            }
            if (largeScreen.setDataVos != null) {
                setDataUlInit(largeScreen.setDataVos);
            }
        }
        $(".loading").fadeOut();
    }
}

function fnDate() {
    var date = new Date();
    var year = date.getFullYear();//当前年份
    var month = date.getMonth();//当前月份
    var data = date.getDate();//天
    var hours = date.getHours();//小时
    var minute = date.getMinutes();//分
    var second = date.getSeconds();//秒
    var time = year + "-" + fnW((month + 1)) + "-" + fnW(data) + " " + fnW(hours) + ":" + fnW(minute) + ":" + fnW(second);
    $("#time").html(time);
}

function fnW(str) {
    return str >= 10 ? str : "0" + str;
}

function myChartPieInit(avgTime, manFlow, womanFlow) {
    var v1 = manFlow;
    var v2 = womanFlow;
    var v3 = v1 + v2;
    option1 = {
        series: [{
            type: 'pie',
            radius: ['70%', '80%'],
            color: '#0088cc',
            label: {
                normal: {
                    position: 'center'
                }
            },
            data: [{
                value: avgTime,
                name: '平均时长',
                label: {
                    normal: {
                        formatter: avgTime + '',
                        textStyle: {
                            fontSize: 20,
                            color: '#fff',
                        }
                    }
                }
            },
            ]
        }]
    };
    option2 = {
        series: [{
            type: 'pie',
            radius: ['70%', '80%'],
            color: '#fccb00',
            label: {
                normal: {
                    position: 'center'
                }
            },
            data: [{
                value: v1,
                name: '男厕流量',
                label: {
                    normal: {
                        formatter: v1 + '\n',
                        textStyle: {
                            fontSize: 20,
                            color: '#fff',
                        }
                    }
                }
            }, {
                value: v2,
                name: '男厕占比',
                label: {
                    normal: {
                        formatter: function (params) {
                            return '\n占比' + Math.round(v1 / v3 * 100) + '%'
                        },
                        textStyle: {
                            color: '#aaa',
                            fontSize: 12
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: 'rgba(255,255,255,.2)'
                    },
                    emphasis: {
                        color: '#fff'
                    }
                },
            }]
        }]
    };
    option3 = {
        series: [{
            type: 'pie',
            radius: ['70%', '80%'],
            color: '#62b62f',
            label: {
                normal: {
                    position: 'center'
                }
            },
            data: [{
                value: v2,
                name: '女厕流量',
                label: {
                    normal: {
                        formatter: v2 + '\n',
                        textStyle: {
                            fontSize: 20,
                            color: '#fff',
                        }
                    }
                }
            }, {
                value: v1,
                name: '女厕站比',
                label: {
                    normal: {
                        formatter: function (params) {
                            return '\n占比' + Math.round(v2 / v3 * 100) + '%'
                        },
                        textStyle: {
                            color: '#aaa',
                            fontSize: 12
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: 'rgba(255,255,255,.2)'
                    },
                    emphasis: {
                        color: '#fff'
                    }
                },
            }]
        }]
    };
    myChart1.setOption(option1);
    myChart2.setOption(option2);
    myChart3.setOption(option3);
}

function myChartLineInit(wcStatisticsInfos) {
    let xAxisData = new Array();
    let dataMaps = new Array();
    $.each(wcStatisticsInfos, function (dataIndex, data) {
        xAxisData[dataIndex] = data.date;
        dataMaps[dataIndex] = data.number;
    });
    option4 = {
        title: {
            textStyle: {
                color: "rgb(85, 85, 85)",
                fontSize: 18,
                fontStyle: "normal",
                fontWeight: "normal"
            }
        },
        grid: {
            left: '1%',
            right: '4%',
            bottom: '1%',
            containLabel: true
        },
        tooltip: {
            trigger: "axis"
        },
        legend: {
            selectedMode: false,
        },
        xAxis: {
            type: "category"
            , axisLabel: {
                textStyle: {
                    color: '#fff'
                }
            }
            , data: xAxisData
        },
        yAxis: {
            axisLabel: {
                textStyle: {
                    color: '#fff'
                }
            },
            type: "value"
        }
        , series: {
            type: "line",
            smooth: true,
            itemStyle: {
                normal: {
                    color: "rgb(" + Math.random() * 255 + ", " + Math.random() * 255 + ", " + Math.random() * 255 + ")"
                }
            },
            data: dataMaps
        }
    };
    myChart4.setOption(option4);
}

function setDataUlInit(setDataVos) {
    var $setDataUl = $('#setDataUlId');
    $setDataUl.find("li").remove();
    $.each(setDataVos, function (index, data) {
        let setDataStr = "<li><p>" + (index + 1) + ":" + data.wcTypeName + data.wcSetId + ":" + "开始于" + data.startTime + "起持续" + Math.round(data.time / 1000) + "秒；" + "</p></li>";
        $setDataUl.append(setDataStr)
    });
}

function sensorDataUlInit(sensorDataVos) {
    var $sensorDataUl = $('#sensorDataUlId');
    $sensorDataUl.find("li").remove();
    $.each(sensorDataVos, function (index, data) {
        let text = data.value1Text == undefined ? '' : data.value1Text;
        let sensorDataStr = "<li class='clearfix'><span class='pulll_left'>" + (index + 1) + ":" + data.sensorTypeName + "</span> <span class='pulll_right'> " + text + "</span></li>";
        $sensorDataUl.append(sensorDataStr)
    });
}

function UsagePieInit(waterUsageV, energyUsageV) {
    option6 = {
        series: [{
            type: 'pie',
            radius: ['70%', '80%'],
            color: '#fccb00',
            label: {
                normal: {
                    position: 'center'
                }
            },
            data: [{
                value: energyUsageV,
                name: '用电量',
                label: {
                    normal: {
                        formatter: energyUsageV + '\n',
                        textStyle: {
                            fontSize: 20,
                            color: '#fff'
                        }
                    }
                }
            }, {
                name: '用电量',
                label: {
                    normal: {
                        formatter: function (params) {
                            return '\n用电量';
                        },
                        textStyle: {
                            color: '#aaa',
                            fontSize: 12
                        }
                    }
                }
            }
            ]
        }]
    };
    option7 = {
        series: [{
            type: 'pie',
            radius: ['70%', '80%'],
            color: '#0088cc',
            label: {
                normal: {
                    position: 'center'
                }
            },
            data: [{
                value: waterUsageV,
                name: '用水量',
                label: {
                    normal: {
                        formatter: waterUsageV + '\n',
                        textStyle: {
                            fontSize: 20,
                            color: '#fff',
                        }
                    }
                }
            }, {
                name: '用水量',
                label: {
                    normal: {
                        formatter: function (params) {
                            return '\n用水量';
                        },
                        textStyle: {
                            color: '#aaa',
                            fontSize: 12
                        }
                    }
                }
            }
            ]
        }]
    };
    myChart6.setOption(option6);
    myChart7.setOption(option7);
}

$(window).resize(function () {
    myChart1.resize();
    myChart2.resize();
    myChart3.resize();
    myChart4.resize();
    myChart6.resize();
    myChart7.resize();
});











