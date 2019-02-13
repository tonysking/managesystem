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

<button style="display: none" id="actIdBtn"></button>
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">查看活动详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <label for="inputPassword3" class="col-sm-2 control-label">活动名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" disabled="disabled" id="actTitle" placeholder="Password">
                    </div>
                    <label for="inputPassword3" class="col-sm-2 control-label">活动简介</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" disabled="disabled" id="actDetailInfo"></textarea>
                    </div>
                    <label for="inputPassword3" class="col-sm-2 control-label">活动地址</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" disabled="disabled" id="actAddress" placeholder="Password">
                    </div>
                    <label for="inputPassword3" class="col-sm-2 control-label">活动人数</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" disabled="disabled" id="participantsNumber" placeholder="Password">
                    </div>
                    <label for="inputPassword3" class="col-sm-2 control-label">活动类型</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" disabled="disabled" id="category" placeholder="Password">
                    </div>
                    <label for="inputPassword3" class="col-sm-2 control-label">活动状态</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" disabled="disabled" id="actStatus" placeholder="Password">
                    </div>
                    <label for="inputPassword3" class="col-sm-2 control-label">报名截止时间</label>
                    <div class="col-sm-4">
                        <input type="datetime" class="form-control" disabled="disabled" id="actSignupDeadline" placeholder="Password">
                    </div>
                    <label for="inputPassword3" class="col-sm-2 control-label">活动开始时间</label>
                    <div class="col-sm-4">
                        <input type="datetime" class="form-control" disabled="disabled" id="actStartTime" placeholder="Password">
                    </div>
                    <label for="inputPassword3" class="col-sm-2 control-label">活动运行状态</label>
                    <div class="col-sm-10">
                        <label class="radio-inline">
                            <input type="radio" name="actRunStatus" id="inlineRadio1" value="0"> 允许
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="actRunStatus" id="inlineRadio2" value="1"> 禁止
                        </label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="setBtn" class="btn btn-primary">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>用户${userNickName}发起的活动</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row row-lg">
		                    <div class="col-sm-12">
		                        <!-- Example Card View -->
		                        <div class="example-wrap">
		                            <div class="example">
		                            	<table id="table_list"></table>
		                            </div>
                                    <input type="button" name="Submit" onclick="javascript:history.back(-1);" value="返回上一页">
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
                    formatter: function (value, row, index) {
                        var operateHtml = '<button id="btn_lookover" onclick="lookover('+row.actId+')" type="button" class="btn btn-default">\n' +
                                '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>查看\n' +
                                '</button>';
                        return operateHtml;
                    }
			    },{
			        title: "创建时间",
			        field: "createTime",
			        sortable: true
			    },{
			        title: "更新时间",
			        field: "updateTime",
			        sortable: true
			    },{
                    title: "活动状态",
                    field: "actRunStatus",
                    formatter: function (value, row, index) {
                        if (value === 0)
                            return '正常';
                        return '失效';
                    }
                }

			    ]
			});
            $("#setBtn").on("click",function(){
                var actRunStatus = $("input[name='actRunStatus']:checked").val();
                var actId = $("#actIdBtn").val();
                $.ajax({
                    type: "POST",
                    url: "/admin/activity/"+actId+"/actRunStatus",
                    dataType: "json",
                    data:{actRunStatus:actRunStatus},
                    success: function(data){
                        location.reload();
                    }
                })
            });
        });


        function lookover(actId){
            $.ajax({
                type: "GET",
                url: "/admin/activity/"+actId,
                dataType: "json",
                success: function(data){
                    console.log(data);
                    var act = data.extend.act;
                    $("#actTitle").val(act.actTitle);
                    $("#category").val(act.category);
                    $("#actSignupDeadline").val(act.actSignupDeadline);
                    $("#actStartTime").val(act.actStartTime);
                    $("#actStatus").val(act.actStatus);
                    $("#participantsNumber").val(act.participantsNumber);
                    $("#actAddress").val(act.actAddress);
                    $("#actDetailInfo").val(act.actDetailInfo);
                    $("#actIdBtn").val(act.actId);
                    if(act.actRunStatus===0)
                  {

                      $("input[type=radio][name=actRunStatus][value=0]").attr("checked",'checked');
                  }else{
                        $("input[type=radio][name=actRunStatus][value=1]").attr("checked",'checked');
                    }

                    $("#detailModal").modal({backdrop:false});
                }
            })

        }
        
        function detailFormatter(index, row) {
	        var html = [];
	        var content = (row.userRole === 1)?'管理员':'普通用户';
	        html.push('<p><b>描述:</b> ' +  content+ '</p>');
	        return html.join('');
	    }
    </script>

    
    

</body>

</html>
