<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>用户列表</title>
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
                        <h5>用户参加的活动</h5>
                    </div>
                    <div class="ibox-content">
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
        $(document).ready(function () {
        	//初始化表格,动态从服务器加载数据  
			$("#table_list").bootstrapTable({
			    //使用get请求到服务器获取数据
			    method: "GET",
			    //必须设置，不然request.getParameter获取不到请求参数
			    contentType: "application/x-www-form-urlencoded",
			    //获取数据的Servlet地址  
			    url: "${ctx!}/admin/user/${userId}/creation",
			    //表格显示条纹  
			    striped: true,
			    //启动分页  
			    pagination: true,
			    //每页显示的记录数  
			    pageSize: 10,
			    //当前第几页  
			    pageNumber: 1,
			    //记录数可选列表
			    pageList: [5, 10, 15, 20, 25],
                //是否启用排序
                sortable: true,
                //排序方式
                sortOrder: "asc",
				//排序主键
				sortName: "actId",
                //是否启用查询
			    search: true,
			    //是否启用详细信息视图
			    detailView:true,
			    detailFormatter:detailFormatter,
			    //表示服务端请求  
			    sidePagination: "server",
			    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
			    //设置为limit可以获取limit, offset, search, sort, order  
			    queryParamsType: "undefined",
			    //json数据解析
			    responseHandler: function(res) {
			        console.log(res)
			        return {
			            "rows": res.extend.page.content,
			            "total": res.extend.page.totalElements
			        };
			    },
			    //数据列
			    columns: [{
			        title: "ID",
			        field: "actId",
			        sortable: true
			    },{
			        title: "活动名称",
			        field: "actTitle"
			    },{
			        title: "活动类型",
			        field: "category",
			    },{
			        title: "活动状态",
			        field: "actStatus"
			    },{
			        title: "活动详情",

			    },{
			        title: "创建时间",
			        field: "createTime",
			        sortable: true
			    },{
			        title: "更新时间",
			        field: "updateTime",
			        sortable: true
			    }]
			});
        });
        
        function detailFormatter(index, row) {
	        var html = [];
	        var content = (row.userRole === 1)?'管理员':'普通用户';
	        html.push('<p><b>描述:</b> ' +  content+ '</p>');
	        return html.join('');
	    }
    </script>

    
    

</body>

</html>
