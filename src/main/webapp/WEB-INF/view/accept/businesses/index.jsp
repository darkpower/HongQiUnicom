<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>中国联通红旗镇森清专营店 - CRM</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <link rel="stylesheet" href="/lib/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/lib/bootstrap/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="/lib/bootstrap-fileinput/css/fileinput.css"/>
    <link rel="stylesheet" href="/lib/bootstrap-datepicker/dist/css/bootstrap-datepicker3.min.css"/>
    <link rel="stylesheet" href="/lib/css/global.css"/>
</head>
<body>

<div class="container">


    <!-- 导航栏 Start -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">红旗镇森清专营店</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">首页</a></li>
                    <li class="active"><a href="/Task/">沃店考核</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="">Default</a></li>
                    <li><a href="../navbar-static-top/">Static top</a></li>
                    <li><a href="../navbar-fixed-top/">Fixed top</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- 导航栏 End -->


    <!-- 内容 Start -->
    <div class="content" ms-controller="business_list">


        <div class="row row-offcanvas row-offcanvas-right">
            <!-- 左侧表格内容 Start -->
            <div id="tableData" class="col-xs-12 col-sm-9">

                <div class="col-sm-6 pull-left">
                    <input id="excelFileUploadInput" name="excelFile" type="file" class="file-loading"
                           data-show-preview="false">
                </div>
                <div class="col-sm-6 pull-left">

                </div>
                <div class="col-sm-12 pull-left table-responsive">
                    <table id="vmTable" class="table table-striped">
                        <thead>
                        <tr>
                            <th width="15%">工单时间</th>
                            <th width="10%">对应账号</th>
                            <th width="10%">对应姓名</th>
                            <th>备注</th>
                            <th width="18%">相关操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ms-for="($index, $business) in @businesses">
                            <td>{{$business.businessDate | date('yyyy-MM-dd') }}</td>
                            <td>{{$business.businessAccount }}</td>
                            <td>{{$business.businessUserName }}</td>
                            <td>{{$business.businessDescription | truncate(25, '...') }}</td>
                            <td>
                                <div class="btn-group">
                                    <a class="btn btn-default" data-toggle="modal" data-target="#newUnicomOrder"
                                       ms-click="@updateBroadband($broadband.broadbandId)">生成</a>
                                    <a class="btn btn-default" data-toggle="modal" data-target="#oldUnicomOrder"
                                       ms-click="@retentionBroadband($broadband.broadbandId)">并入</a>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tfoot>
                        <!-- 分页 Start -->
                        <tr>
                            <td colspan="7" class="text-center">
                                <ul id="page" style="margin-left: 10px; margin-right: 10px;"></ul>
                            </td>
                        </tr>
                        <!-- 分页 End -->
                        </tfoot>
                    </table>
                </div>
            </div>
            <!-- 左侧表格内容 End -->


            <!-- 右侧导航内容 Start -->
            <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">

                <div class="optionSwitch list-group" style="margin-top: 0px; margin-bottom: 20px;">
                    <a class="list-group-item" list="全部">全部</a>
                    <a class="list-group-item active" list="未分拣">未分拣</a>
                </div>

                <div class="list-group">
                    <a href="#" class="list-group-item">业务受理清单</a>
                    <a href="#" class="list-group-item">受理明细</a>
                    <a href="/Accept/Businesses/" class="list-group-item active">流水工单</a>
                </div>
            </div>
            <!-- 右侧导航内容 End -->
        </div>


        <!-- 模态框（Modal） -->
        <div class="modal fade" id="newUnicomOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">生成业务受理</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="unicomOrderId" class="col-sm-2 control-label">受理编号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="unicomOrderId" ms-duplex="@unicomOrder.unicomOrderId" disabled>
                                </div>
                                <label for="unicomOrderDate" class="col-sm-2 control-label">受理时间</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="unicomOrderDate" ms-duplex="@unicomOrder.unicomOrderDate" disabled>
                                </div>
                            </div>

                        </form>


                    </div>
                    <div class="modal-footer">
                        <button type="button" id="saveUnicomOrder" class="btn btn-primary">生成</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>


    </div>
    <!-- 内容 End -->


</div>


<!-- javascript -->

<script src="/lib/jquery/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="/lib/avalon2/avalon.js"></script>
<script src="/lib/bootstrap-fileinput/js/fileinput.js"></script>
<script src="/lib/bootstrap-fileinput/js/locales/zh.js"></script>
<script src="/lib/bootstrap-paginator/build/bootstrap-paginator.min.js"></script>
<script src="/lib/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
<script src="/lib/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="/lib/js/global.js"></script>
<script>

    $(function () {

        $("#excelFileUploadInput").fileinput({
            language: "zh",
            uploadUrl: "/Accept/Businesses/Upload",
            allowedFileExtensions: ['xlsx', 'xls'],
            maxFileSize: 0,
            enctype: 'multipart/form-data'
        });

        $('#excelFileUploadInput').on('fileerror', function (event, data, msg) {
        });

        $("#excelFileUploadInput").on("fileuploaded", function (event, data, previewId, index) {
            Business.ajaxGetListByOption(vm);
            Business.ajaxGetPageByOption(vm);
        });

        $(".date-picker").datepicker({
            language: "zh-CN",
            autoclose: true,
            clearBtn: true,
            todayBtn: 'linked'
        });


        //初始化分页
        $("#page").bootstrapPaginator({
            bootstrapMajorVersion: 3,
            currentPage: 1,
            totalPages: 1,
            numberOfPages: 10,
            itemTexts: function (type, page, current) {
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return page;
                }
            },
            onPageChanged: function (event, oldPage, newPage) {
                vm.page.nowPage = newPage;
                Business.ajaxGetListByOption(vm);
            }
        });


        var vm = avalon.define({
            $id: "business_list",
            option: {
                list: "未分拣",
                startDay: "",
                endDay: ""
            },
            page: {
                nowPage: 1,
                totalPages: 1,
                totalCounts: 1
            },
            unicomOrder: new UnicomOrder(),
            business: new Business(),
            businesses: [],
            openModal: function (businessId) {
                Business.ajaxGetEntityById(businessId, vm);
            }
        });
        Business.ajaxGetListByOption(vm);
        Business.ajaxGetPageByOption(vm);
        avalon.scan(document.body);


        $('.optionSwitch a').click(function () {
            $(this).nextAll().removeClass("active");
            $(this).prevAll().removeClass("active");
            $(this).addClass("active");
            vm.option.list = $(this).attr("list") != null ? $(this).attr("list") : vm.option.list;
            vm.option.startDay = $("#startDay").val() != null ? $("startDay").val() : vm.option.startDay;
            vm.option.endDay = $("#endDay").val() != null ? $("endDay").val() : vm.option.endDay;
            vm.page.nowPage = 1;
            Business.ajaxGetListByOption(vm);
            Business.ajaxGetPageByOption(vm);
        });

        $('#saveUnicomOrder').click(function () {
            vm.unicomOrder.add(vm.business);
            $.ajax({
                url: "/Ajax/UnicomOrder/Create",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(vm.unicomOrder),
                success: function (data) {
                    alert("success");
                },
                error: function () {
                    alert("error");
                }
            });
        });

        $('#exportExcelButton').click(function () {
            $("<form>").attr({
                "action": "Export",
                "method": "POST"
            }).append("<input type='text' name='list' value='" + vm.option.list + "' />").append("<input type='text' name='xuFeiType' value='" + vm.option.xuFeiType + "' />").append("<input type='text' name='systemType' value='" + vm.option.systemType + "' />").submit();
        });


    });


</script>

</body>
</html>
