<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>活动位置信息管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx!}/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>活动位置信息管理</h5>
                    </div>
                    <div class="ibox-content">

                        <div class="panel-body" style="padding-bottom:0px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">查询条件</div>
                                <div class="panel-body">
                                        <div class="form-group" style="margin-top:15px">
                                            <div class="col-sm-2">
                                            <select class="form-control">
                                                <option value="0">条件</option>
                                                <option value="1">地址</option>
                                                <option value="2">名称</option>
                                            </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" id="txt_search_departmentname">
                                            </div>
                                            <div class="col-sm-4" style="text-align:left;">
                                                <button  onclick="doQuery();" style="margin-left:50px" class="btn btn-primary">查询</button>&nbsp;&nbsp;
                                            </div>
                                        </div>
                                </div>
                            </div>

                            <div class="row row-lg">
		                    <div class="col-sm-12">
		                        <!-- Example Card View -->
		                        <div class="example-wrap">
		                            <div class="example">
		                            	<table id="table_list"></table>
		                            </div>
		                        </div>
		                        <!-- End Example Card View -->
		                    </div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

	<!-- Bootstrap table -->
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>


    <!-- Peity -->
    <script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>
    
    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- Page-Level Scripts -->
    <script>

        $(function () {
            //初始化Table
            var oTable = new TableInit();
            oTable.Init();
        });

     /*   function queryParams() {
            var param = {};
            param['searchText'] = $("input").val();
            param['type'] = $("select").val();
            param['pageSize'] = this.pageSize;   //页面大小
            param['pageNumber'] = this.pageNumber;   //页码
            return param;
        }*/


        var TableInit = function () {
            var oTableInit = new Object();
            //初始化Table
            oTableInit.Init = function () {
                //初始化表格,动态从服务器加载数据
                $("#table_list").bootstrapTable({
                    //使用get请求到服务器获取数据
                    method: "GET",
                    //必须设置，不然request.getParameter获取不到请求参数
                    contentType: "application/x-www-form-urlencoded",
                    //获取数据的Servlet地址
                    url: "${ctx!}/admin/location/list",
                    //表格显示条纹
                    striped: true,
                    //启动分页
                    pagination: true,
                    //每页显示的记录数
                    pageSize: 10,
                    //使用缓存
                    cache: false,
                    //当前第几页
                    pageNumber: 1,
                    //刷新事件必须设置
                    silent: true,
                    //是否启用排序
                    sortable: true,
                    //排序方式
                    sortOrder: "desc",
                    //排序主键
                    sortName: "actHeat",
                    //记录数可选列表
                    pageList: [5, 10, 15, 20, 25],
                    //是否启用详细信息视图
                    detailView:true,
                    detailFormatter:detailFormatter,
                    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                    //设置为limit可以获取limit, offset, search, sort, order
                    queryParams: oTableInit.queryParams,//传递参数（*）
                    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                    clickToSelect: true,                //是否启用点击选中行
                    queryParamsType: 'undefined',
                    //json数据解析
                    responseHandler: oTableInit.responseHandler,
                    //数据列
                    columns: [{
                        title: "排名",
                        field: "actId",
                        sortable: true
                    },{
                        title: "活动名称",
                        field: "actTitle"
                    },{
                        title: "活动类型",
                        field: "category"
                    },{
                        title: "地址",
                        field: "actAddress"
                    },{
                        title: "经度",
                        field: "longitude"
                    },{
                        title: "纬度",
                        field: "latitude"
                    },{
                        title: "参与人数",
                        field: "participantsNumber",
                        sortable: true
                    },{
                        title: "热度",
                        sortable: true,//启用排序
                        field: "actHeat",
                    }]
                });

            };
            oTableInit.responseHandler = function(res) { //数据筛选
                if (res) {
                    return {
                        "rows": res.extend.page.content,
                        "total": res.extend.page.totalElements
                    };
                } else {
                    return {
                        "rows" : [],
                        "total" : 0
                    };
                }
            }

            oTableInit.queryParams = function (params) {
                var param = {};
                param['searchText'] = $("input").val();
                param['type'] = $("select").val();
                param['pageSize'] = params.pageSize;   //页面大小
                param['pageNumber'] = params.pageNumber;   //页码
                param['sortOrder']= "desc";
                param['sortName']= "actHeat";
                return param;
            };
            return oTableInit;
        };

        function doQuery(){
            $('#table_list').bootstrapTable('refresh');    //刷新表格
        }

        
        function detailFormatter(index, row) {
	        var html = [];
	        html.push('<p><b>描述:</b>暂无</p>');
	        return html.join('');
	    }
    </script>


</body>

</html>
