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
                    <li><a href="/Task/">沃店考核</a></li>
                    <li class="active"><a href="/ERP/">进销存</a></li>
                    <li><a href="/Business/">业务受理明细</a></li>
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
    <div class="content" ms-controller="card_list">


        <div class="row row-offcanvas row-offcanvas-right">
            <!-- 左侧表格内容 Start -->
            <div id="tableData" class="col-xs-12 col-sm-9">
                <div class="col-sm-6 pull-left">
                    <input id="exportExcelButton" type="submit" class="btn btn-primary" value="上传模版"/>
                </div>
                <div class="col-sm-6 pull-left">
                    <input id="excelFileUploadInput" name="excelFile" type="file" class="file-loading"
                           data-show-preview="false">
                </div>
                <div class="col-sm-12 pull-left table-responsive">
                    <table id="vmTable" class="table table-striped">
                        <thead>
                        <tr>
                            <th>号卡ICCID</th>
                            <th>号卡类型</th>
                            <th>号卡卡号</th>
                            <th>代售卡点</th>
                            <th>相关操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ms-for="($index, $card) in @cards">
                            <td>{{$card.cardIccid }}</td>
                            <td>{{$card.cardType }}</td>
                            <td>{{$card.cardTelphoneNumber }}</td>
                            <td>{{$card.retailer.retailerName }}</td>
                            <td>
                                <button option="manual" class="btn-block btn-default" data-toggle="modal" data-target="#myModal" ms-click="@openModal($card.cardId)">手工调整</button>
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


            <!--  进销存导航 Start  -->
            <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">

                <div class="optionSwitch list-group" style="margin-top: 0px; margin-bottom: 20px;">
                    <a class="list-group-item" list="未配号">未配号</a>
                    <a class="list-group-item" list="已配号">已配号</a>
                </div>
                <div class="list-group">
                    <a href="#" class="list-group-item">进销存管理系统导航</a>
                    <a href="/ERP/Card/" class="list-group-item active">号卡</a>
                    <a href="#" class="list-group-item">手机</a>
                    <a href="#" class="list-group-item">路由器</a>
                    <a href="#" class="list-group-item"></a>
                    <a href="#" class="list-group-item"></a>
                    <a href="#" class="list-group-item"></a>
                    <a href="#" class="list-group-item"></a>
                    <a href="/ERP/Settings/" class="list-group-item">系统设置</a>
                </div>
            </div>
            <!--  进销存导航 End  -->
        </div>


        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">手工调整</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="broadbandAccount" class="col-sm-2 control-label">宽带账号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="broadbandAccount" ms-duplex="@broadband.broadbandAccount" disabled>
                                </div>
                                <label for="broadbandState" class="col-sm-2 control-label">宽带状态</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="broadbandState" ms-duplex="@broadband.broadbandState" disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="broadbandXuFeiState" class="col-sm-2 control-label">续费状态</label>
                                <div class="col-sm-4">
                                    <select type="text" class="form-control" id="broadbandXuFeiState" ms-duplex="@broadband.broadbandXuFeiState">
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
                                    <input type="text" class="form-control" id="customerName" ms-duplex="@broadband.customer.customerName">
                                </div>
                                <label for="customerCardId" class="col-sm-2 control-label">机主证件</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerCardId" ms-duplex="@broadband.customer.customerCardId">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customerTelphone" class="col-sm-2 control-label">机主电话</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerTelphone" ms-duplex="@broadband.customer.customerTelphone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customerQualityVoice" class="col-sm-2 control-label">次月分钟</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerQualityVoice" ms-duplex="@broadband.customer.customerQualityVoice">
                                </div>
                                <label for="customerQualityData" class="col-sm-2 control-label">次月流量</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerQualityData" ms-duplex="@broadband.customer.customerQualityData">
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
<script src="/lib/js/global.js"></script>
<script>

    function ajaxBroadbandsTotalPages(vm) {
        $.ajax({
            url: "/Task/XuFei/Page",
            type: "post",
            dataType: "json",
            data: {
                'list': vm.option.list,
                'xuFeiType': vm.option.xuFeiType,
                'systemType': vm.option.systemType
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
            url: "/Task/XuFei/List",
            type: "post",
            dataType: "json",
            data: {
                'page': vm.nowPage,
                'list': vm.option.list,
                'xuFeiType': vm.option.xuFeiType,
                'systemType': vm.option.systemType
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
            uploadUrl: "/ERP/Card/Upload",
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
                list: "次月宽带续费清单",
                xuFeiType: "未续费",
                systemType: "全部"
            },
            nowPage: 1,
            totalPages: 1,
            totalCounts: 1,
            broadband: {customer: {}},
            broadbands: [],
            openModal: function (broadbandId) {
                ajaxBroadband(broadbandId, this);
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

        $('#exportExcelButton').click(function () {
            $("<form>").attr({
                "action": "Export",
                "method": "POST"
            }).submit();
        });


    });


</script>

</body>
</html>
