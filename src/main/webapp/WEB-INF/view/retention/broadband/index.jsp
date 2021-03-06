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
    <link rel="stylesheet" href="/lib/css/global.css"/>










</head>
<body>

<div class="container">


    <!-- 导航栏 Start -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
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
    <div class="content" ms-controller="broadband_list">


        <div class="row row-offcanvas row-offcanvas-right">
            <!-- 左侧表格内容 Start -->
            <div id="tableData" class="col-xs-12 col-sm-9">
                <div class="col-sm-6 pull-left">
                    <input id="excelFileUploadInput" name="excelFile" type="file" class="file-loading"
                           data-show-preview="false">
                </div>
                <div class="col-sm-6 pull-left">
                    {{@option.date == "" ? "全部" : @option.date}} 已续约融合用户 共计 {{@totalCounts}} 户
                    <input id="exportExcelButton" type="submit" class="btn btn-primary" value="导出Excel"/>
                </div>
                <div class="col-sm-12 pull-left table-responsive">
                    <table id="vmTable" class="table table-striped">
                        <thead>
                        <tr>
                            <th>宽带账号</th>
                            <th>当前续费状态</th>
                            <th>当前状态</th>
                            <th>产品分类</th>
                            <th>宽带到期时间</th>
                            <th>联系人</th>
                            <th>电话</th>
                            <th>相关操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ms-for="($index, $broadband) in @broadbands">
                            <td>{{$broadband.broadbandAccount }}</td>
                            <td>{{$broadband.broadbandXuFeiState }}</td>
                            <td>{{$broadband.broadbandState }}</td>
                            <td>{{$broadband.broadbandSystemType }}</td>
                            <td>{{$broadband.broadbandExpireDate  | date('yyyy-MM-dd') }}</td>
                            <td>{{$broadband.customer == null ? "" : $broadband.customer.customerName }}</td>
                            <td>{{$broadband.customer == null ? "" : $broadband.customer.customerTelphone }}</td>
                            <td>
                                <div class="btn-group">
                                    <a class="btn btn-default" data-toggle="modal" data-target="#updateModal"
                                       ms-click="@updateBroadband($broadband.broadbandId)">调整</a>
                                    <a class="btn btn-default" data-toggle="modal" data-target="#retentionModal"
                                       ms-click="@retentionBroadband($broadband.broadbandId)">维系</a>
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


            <!--  沃店考核指标导航 Start  -->
            <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
                <div class="optionSwitch list-group" style="margin-top: 0; margin-bottom: 20px;">
                    <a href="#" class="list-group-item active">续约日期</a>
                    <input class="list-group-item date-picker" data-date-format="yyyy-mm" style="width: 100%"
                           type="text" />
                    <a id="dateSubmit" class="list-group-item">确定</a>
                </div>
                <div class="list-group">
                    <a href="#" class="list-group-item active">维系挽留导航</a>
                    <a href="/Retention/Syncretize/" class="list-group-item">融合用户</a>
                </div>
            </div>
        </div>


        <!-- BroadbandUpdate模态框（Modal） -->
        <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="updateModalLabel">调整</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="broadbandAccount" class="col-sm-2 control-label">宽带账号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="broadbandAccount"
                                           ms-duplex="@broadband.broadbandAccount" disabled>
                                </div>
                                <label for="broadbandState" class="col-sm-2 control-label">宽带状态</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="broadbandState"
                                           ms-duplex="@broadband.broadbandState" disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="broadbandXuFeiState" class="col-sm-2 control-label">续费状态</label>
                                <div class="col-sm-4">
                                    <select type="text" class="form-control" id="broadbandXuFeiState"
                                            ms-duplex="@broadband.broadbandXuFeiState">
                                        <option value="已续费">已续费</option>
                                        <option value="未续费">未续费</option>
                                        <option value="有问题">有问题</option>
                                        <option value="已销号">已销号</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customerName" class="col-sm-2 control-label">机主姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerName"
                                           ms-duplex="@broadband.customer.customerName">
                                </div>
                                <label for="customerCardId" class="col-sm-2 control-label">机主证件</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerCardId"
                                           ms-duplex="@broadband.customer.customerCardId">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customerTelphone" class="col-sm-2 control-label">机主电话</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerTelphone"
                                           ms-duplex="@broadband.customer.customerTelphone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customerQualityVoice" class="col-sm-2 control-label">次月分钟</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerQualityVoice"
                                           ms-duplex="@broadband.customer.customerQualityVoice">
                                </div>
                                <label for="customerQualityData" class="col-sm-2 control-label">次月流量</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerQualityData"
                                           ms-duplex="@broadband.customer.customerQualityData">
                                </div>
                            </div>
                        </form>


                    </div>
                    <div class="modal-footer">
                        <button type="button" id="saveBroadbandButton" class="btn btn-primary">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

        <!-- BroadbandRetention模态框（Modal） -->
        <div class="modal fade" id="retentionModal" tabindex="-1" role="dialog" aria-labelledby="retentionModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="retentionModalLabel">维系</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="retentionBroadbandAccount" class="col-sm-2 control-label">宽带账号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="retentionBroadbandAccount"
                                           ms-duplex="@broadband.broadbandAccount" disabled>
                                </div>
                                <label for="retentionBroadbandXuFeiState" class="col-sm-2 control-label">续费状态</label>
                                <div class="col-sm-4">
                                    <select type="text" class="form-control" id="retentionBroadbandXuFeiState"
                                            ms-duplex="@broadband.broadbandXuFeiState">
                                        <option value="已续费">已续费</option>
                                        <option value="未续费">未续费</option>
                                        <option value="有问题">有问题</option>
                                        <option value="已销号">已销号</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="retentionCustomerName" class="col-sm-2 control-label">机主姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="retentionCustomerName"
                                           ms-duplex="@broadband.customer.customerName">
                                </div>
                                <label for="retentionCustomerTelphone" class="col-sm-2 control-label">机主电话</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="retentionCustomerTelphone"
                                           ms-duplex="@broadband.customer.customerTelphone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="retentionDate" class="col-sm-2 control-label">回访时间</label>
                                <div class="col-sm-4">
                                    <input class="list-group-item date-picker form-control" data-date-format="yyyy-mm-hh" type="text" id="retentionDate"
                                           ms-duplex="@broadband.broadbandRetentionDate">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="retentionContent" class="col-sm-2 control-label">回访内容</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" id="retentionContent"
                                              ms-duplex="@broadband.broadbandRetentionContent"></textarea>
                                </div>
                            </div>
                        </form>


                    </div>
                    <div class="modal-footer">
                        <button type="button" id="retentionSaveBroadbandButton" class="btn btn-primary">保存</button>
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

    function ajaxBroadbandsTotalPages(vm) {
        $.ajax({
            url: "/Retention/Broadband/Page",
            type: "post",
            dataType: "json",
            data: {
                'date': vm.option.date
            },
            success: function (data) {
                vm.totalCounts = data;
                vm.totalPages = data / 10;
                if (vm.totalCounts % 10 != 0) {
                    vm.totalPages++;
                } else if (vm.totalCounts == 0) {
                    vm.totalPages++;
                }
                var options = {
                    bootstrapMajorVersion: 3,
                    currentPage: vm.nowPage,//当前页面
                    totalPages: vm.totalPages, //总页数
                    numberOfPages: 10//一页显示几个按钮（在ul里面生成5个li）
                }
                $('#page').bootstrapPaginator("setOptions", options);
            },
            error: function () {
                alert("error");
            }
        });
    }

    function ajaxBroadband(broadbandId, vm) {
        $.ajax({
            url: "/Task/XuFei/Show",
            type: "post",
            dataType: "json",
            data: {'broadbandId': broadbandId},
            success: function (data) {
                vm.broadband = {customer: {}};
                vm.broadband = new Broadband(data);

            },
            error: function (data) {
                alert(error);
            }
        });
    }

    function ajaxBroadbands(vm) {

        $.ajax({
            url: "/Retention/Broadband/List",
            type: "post",
            dataType: "json",
            data: {
                'date': vm.option.date,
                'page': vm.nowPage
            },
            success: function (data) {
                vm.broadbands = [];
                vm.broadbands = data;
            },
            error: function () {
                alert("error");
            }
        });
    }

    $(function () {

        $("#excelFileUploadInput").fileinput({
            language: "zh",
            uploadUrl: "/Task/XuFei/Upload",
            allowedFileExtensions: ['xlsx', 'xls'],
            maxFileSize: 0,
            enctype: 'multipart/form-data'
        });

        $('#excelFileUploadInput').on('fileerror', function (event, data, msg) {
        });

        $("#excelFileUploadInput").on("fileuploaded", function (event, data, previewId, index) {
        });

        $('')


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
                vm.nowPage = newPage;
                ajaxBroadbands(vm);
            }
        });


//        初始化avalon
        var vm = avalon.define({
            $id: "broadband_list",
            option: {
                date: ""
            },
            nowPage: 1,
            totalPages: 1,
            totalCounts: 1,
            broadband: {customer: {}},
            broadbands: [],
            updateBroadband: function (broadbandId) {
                ajaxBroadband(broadbandId, this);
            },
            retentionBroadband: function (broadbandId) {
                ajaxBroadband(broadbandId, this)
            }
        });
        ajaxBroadbands(vm);
        ajaxBroadbandsTotalPages(vm);


        avalon.scan(document.body);

        $('.optionSwitch a').click(function () {
            $(this).nextAll().removeClass("active");
            $(this).prevAll().removeClass("active");
            $(this).addClass("active");
            vm.option.list = $(this).attr("list") != null ? $(this).attr("list") : vm.option.list;
            vm.option.xuFeiType = $(this).attr("xuFeiType") != null ? $(this).attr("xuFeiType") : vm.option.xuFeiType;
            vm.option.systemType = $(this).attr("systemType") != null ? $(this).attr("systemType") : vm.option.systemType;
            vm.nowPage = 1;
            ajaxBroadbandsTotalPages(vm);
            ajaxBroadbands(vm);
        });

        $('#saveBroadbandButton').click(function () {


            $.ajax({
                url: "/Task/XuFei/Update",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(vm.broadband),
                success: function (data) {
                    alert("success");
                },
                error: function () {
                    alert("error");
                }
            });
        });

        $('#retentionSaveBroadbandButton').click(function () {


            $.ajax({
                url: "/Task/XuFei/Retention",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(vm.broadband),
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

        $(".date-picker").datepicker({
            language: "zh-CN",
            autoclose: true,
            clearBtn: true,
            todayBtn: 'linked'
        });

        $("#dateSubmit").click(function () {
            vm.option.date = $(".date-picker").val();
            ajaxBroadbandsTotalPages(vm);
            ajaxBroadbands(vm);
        });


    });


</script>

</body>
</html>
