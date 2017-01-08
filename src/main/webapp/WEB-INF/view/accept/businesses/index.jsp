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
                    <li><a href="/Task/">沃店考核</a></li>
                    <li><a href="/ERP/">进销存</a></li>
                    <li class="active"><a href="/Accept/">业务受理明细</a></li>
                    <li><a href="/Retention/">维系挽留</a></li>
                    <li><a href="/System/">系统设置</a></li>
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
                <div class="col-sm-6 pull-right btn-group text-right" style=" padding-left: 0px; padding-right: 0px;">
                    <input id="createUnicomOrderButton" type="button" class="btn btn-default" value="生成营业"/>
                    <input id="joinUnicomOrderButton" type="button" class="btn btn-default" value="并入营业"/>
                    <input id="updateBusinessToHaltButton" type="button" class="btn btn-default" value="停机工单"/>
                    <input id="updateBusinessToCardButton" type="button" class="btn btn-default" value="开卡工单"/>
                    <input id="updateBusinessToOtherButton" type="button" class="btn btn-default" value="其他工单"/>
                </div>
                <div class="col-sm-12 pull-left table-responsive">
                    <table id="vmTable" class="table table-striped">
                        <thead>
                        <tr>
                            <th width="5%"></th>
                            <th width="12%">工单时间</th>
                            <th width="10%">对应账号</th>
                            <th width="13%">对应姓名</th>
                            <th>备注</th>
                            <th width="7%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ms-for="($index, $business) in @businesses">
                            <td><input type="checkbox" name="businessIds" ms-attr="{'value': $business.businessId}"></td>
                            <td>{{$business.businessDate | date('yyyy-MM-dd') }}</td>
                            <td>{{$business.businessAccount }}</td>
                            <td>{{$business.businessUserName | truncate(5, '…') }}</td>
                            <td>{{$business.businessDescription | truncate(25, '…') }}</td>
                            <td>
                                <div class="btn-group">
                                    <a class="btn btn-default" data-toggle="modal" data-target="#oldUnicomOrder"
                                       ms-click="@openModal($business.businessId)">详细</a>
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
                    <a class="list-group-item" state="全部">全部</a>
                    <a class="list-group-item active" state="初检工单">初检工单</a>
                    <a class="list-group-item" state="营业工单">营业工单</a>
                    <a class="list-group-item" state="停机工单">停机工单</a>
                    <a class="list-group-item" state="开卡工单">开卡工单</a>
                    <a class="list-group-item" state="其他工单">其他工单</a>
                </div>

                <div class="list-group">
                    <a href="#" class="list-group-item">业务受理清单</a>
                    <a href="/Accept/UnicomOrder/" class="list-group-item">受理明细</a>
                    <a href="/Accept/Businesses/" class="list-group-item active">流水工单</a>
                </div>
            </div>
            <!-- 右侧导航内容 End -->
        </div>


        <!-- createUnicomOrder模态框（Modal） -->
        <div class="modal fade" id="createUnicomOrderModal" tabindex="-1" role="dialog" aria-labelledby="createUnicomOrderModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="createUnicomOrderModalLabel">生成业务受理</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="unicomOrderId" class="col-sm-2 control-label">受理编号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="unicomOrderId" ms-duplex="@unicomOrder.unicomOrderId" disabled>
                                </div>
                                <label for="unicomOrderDate" class="col-sm-2 control-label">受理日期</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="unicomOrderDate" ms-duplex="@unicomOrder.unicomOrderDate | date('yyyy-MM-dd')" disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="unicomOrderType" class="col-sm-2 control-label">受理业务</label>
                                <div class="col-sm-4">
                                    <select type="text" class="form-control" id="unicomOrderType" ms-duplex="@unicomOrder.unicomOrderType.unicomOrderTypeId">
                                        <option ms-for="($index, $unicomOrderType) in @unicomOrderTypes" ms-attr="{value: $unicomOrderType.unicomOrderTypeId }">{{$unicomOrderType.unicomOrderTypeName
                                            }}
                                        </option>
                                    </select>
                                </div>
                                <label for="staff" class="col-sm-2 control-label">受理人</label>
                                <div class="col-sm-4">
                                    <select type="text" class="form-control" id="staff" ms-duplex="@unicomOrder.staff.staffId">
                                        <option ms-for="($index, $staff) in @staffs" ms-attr="{value: $staff.staffId }">{{$staff.staffName }}</option>
                                    </select>
                                </div>
                            </div>


                            <div>
                                <div class="table-responsive">
                                    <table id="selectBusinessTable" class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th width="15%">工单时间</th>
                                            <th width="10%">对应账号</th>
                                            <th width="15%">对应姓名</th>
                                            <th>备注</th>
                                            <th width="7%">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr ms-for="($index, $business) in @selectBusinesses">
                                            <td>{{$business.businessDate | date('yyyy-MM-dd') }}</td>
                                            <td>{{$business.businessAccount }}</td>
                                            <td>{{$business.businessUserName | truncate(5, '…') }}</td>
                                            <td>{{$business.businessDescription | truncate(25, '…') }}</td>
                                            <td>
                                                <div class="btn-group">
                                                    <a class="btn btn-default" data-toggle="modal" data-target="#oldUnicomOrder"
                                                       ms-click="@openModal($business.businessId)">删除</a>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </form>


                    </div>
                    <div class="modal-footer">
                        <button type="button" id="createUnicomOrderSubmitButton" class="btn btn-primary">生成</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

        <!-- joinUnicomOrder模态框（Modal） -->
        <div class="modal fade" id="joinUnicomOrderModal" tabindex="-1" role="dialog" aria-labelledby="joinUnicomOrderLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="joinUnicomOrderLabel">导入现存业务受理</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="unicomOrderId" class="col-sm-2 control-label">受理编号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="joinUnicomOrderId"/>
                                </div>
                            </div>
                        </form>


                    </div>
                    <div class="modal-footer">
                        <button type="button" id="joinUnicomOrderSubmitButton" class="btn btn-primary">导入</button>
                        <button type="button" id="joinUnicomOrderCloseButton" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>


        <!-- joinUnicomOrder模态框（Modal） -->
        <div class="modal fade" id="showBusinessModal" tabindex="-1" role="dialog" aria-labelledby="showBusinessModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="showBusinessModalLabel">工单详细信息</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="businessDate" class="col-sm-2 control-label">工单时间</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="businessDate" ms-duplex="@business.businessDate" disabled/>
                                </div>
                                <label for="businessSerialNumber" class="col-sm-2 control-label">工单流水</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="businessSerialNumber" ms-duplex="@business.businessSerialNumber"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="businessAccount" class="col-sm-2 control-label">对应账号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="businessAccount" ms-duplex="@business.businessAccount"/>
                                </div>
                                <label for="businessUserName" class="col-sm-2 control-label">用户姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="businessUserName" ms-duplex="@business.businessUserName" disabled/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="businessTypeName" class="col-sm-2 control-label">工单类型</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="businessTypeName" ms-duplex="@business.businessType.businessTypeName" disabled/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="businessDescription" class="col-sm-2 control-label">工单备注</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" id="businessDescription" ms-duplex="@business.businessDescription"></textarea>
                                </div>
                            </div>
                        </form>


                    </div>
                    <div class="modal-footer">
                        <button type="button" id="showBusinessCloseButton" class="btn btn-default" data-dismiss="modal">关闭</button>
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
                state: "未分拣工单"
            },
            page: {
                nowPage: 1,
                totalPages: 1,
                totalCounts: 1
            },
            unicomOrder: new UnicomOrder(),
            business: new Business(),
            businesses: [],
            selectBusinesses: [],
            staffs: [],
            unicomOrderTypes: [],
            openModal: function (businessId) {
                Business.ajaxGetEntityById(businessId, vm);
                $('#showBusinessModal').modal("show");
            }
        });
        Business.ajaxGetListByOption(vm);
        Business.ajaxGetPageByOption(vm);
        Staff.ajaxGetSelectList(vm);
        UnicomOrderType.ajaxGetSelectList(vm);
        avalon.scan(document.body);

        $('#updateUnicomOrderModal').on('hidden.bs.modal', function () {
            $('updateUnicomOrderId').val("");
        });

        $('.optionSwitch a').click(function () {
            $(this).nextAll().removeClass("active");
            $(this).prevAll().removeClass("active");
            $(this).addClass("active");
            vm.option.state = $(this).attr("state") != null ? $(this).attr("state") : vm.option.state;

            vm.page.nowPage = 1;
            Business.ajaxGetListByOption(vm);
            Business.ajaxGetPageByOption(vm);
        });

        $('#createUnicomOrderButton').click(function () {
            vm.unicomOrder = new UnicomOrder();
            vm.selectBusinesses = [];
            $("input[name='businessIds']:checked").each(function () {
                var selectId = $(this).val();
                $.each(vm.businesses, function (index, item) {
                    if (item.businessId == selectId) {
                        vm.selectBusinesses.push(item);
                    }
                });
            });

            $("#createUnicomOrderModal").modal("show");

        });

        $('#createUnicomOrderSubmitButton').click(function () {
            $.each(vm.selectBusinesses, function (index, item) {
                vm.unicomOrder.businesses.push(Business.createEntity(item));
            });

            $.ajax({
                url: "/Ajax/UnicomOrder/Create",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(vm.unicomOrder),
                success: function (data) {
                    alert("success,请记录好业务编码");
                    vm.unicomOrder = data;
                    Business.ajaxGetListByOption(vm);
                    Business.ajaxGetPageByOption(vm);
                },
                error: function () {
                    alert("error");
                }
            });
        });

        $('#joinUnicomOrderButton').click(function () {
            vm.unicomOrder = new UnicomOrder();
            vm.selectBusinesses = [];
            $("input[name='businessIds']:checked").each(function () {
                var selectId = $(this).val();
                $.each(vm.businesses, function (index, item) {
                    if (item.businessId == selectId) {
                        vm.selectBusinesses.push(item);
                    }
                });
            });

            $("#joinUnicomOrderModal").modal("show");

        });

        $('#joinUnicomOrderSubmitButton').click(function () {
            vm.unicomOrder = new UnicomOrder();
            vm.unicomOrder.unicomOrderId = $("#joinUnicomOrderId").val();
            $.each(vm.selectBusinesses, function (index, item) {
                vm.unicomOrder.businesses.push(Business.createEntity(item));
            });

            $.ajax({
                url: "/Ajax/UnicomOrder/JoinBusinesses",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(vm.unicomOrder),
                success: function (data) {
                    alert("success");
                    Business.ajaxGetListByOption(vm);
                    Business.ajaxGetPageByOption(vm);
                },
                error: function () {
                    alert("error");
                }
            });
        });

        $('#joinUnicomOrderCloseButton').click(function () {
            $('#updateUnicomOrderId').val("");
        });

        $('#updateBusinessToHaltButton').click(function () {
            var unicomOrder = new UnicomOrder();
            $("input[name='businessIds']:checked").each(function () {
                var business = new Business();
                business.businessId = $(this).val();
                unicomOrder.businesses.push(business);
            });

            if (confirm("确认批量注册为停机工单？")) {

                $.ajax({
                    url: "/Ajax/Business/Halt",
                    type: "post",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(unicomOrder),
                    success: function (data) {
                        alert("success");
                        Business.ajaxGetListByOption(vm);
                        Business.ajaxGetPageByOption(vm);
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
        });

        $('#updateBusinessToOtherButton').click(function () {
            var unicomOrder = new UnicomOrder();
            $("input[name='businessIds']:checked").each(function () {
                var business = new Business();
                business.businessId = $(this).val();
                unicomOrder.businesses.push(business);
            });

            if (confirm("确认批量注册为其他工单？")) {

                $.ajax({
                    url: "/Ajax/Business/Other",
                    type: "post",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(unicomOrder),
                    success: function (data) {
                        alert("success");
                        Business.ajaxGetListByOption(vm);
                        Business.ajaxGetPageByOption(vm);
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
        });

        $('#updateBusinessToCardButton').click(function () {
            var unicomOrder = new UnicomOrder();
            $("input[name='businessIds']:checked").each(function () {
                var business = new Business();
                business.businessId = $(this).val();
                unicomOrder.businesses.push(business);
            });

            if (confirm("确认批量注册为其他工单？")) {

                $.ajax({
                    url: "/Ajax/Business/Card",
                    type: "post",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(unicomOrder),
                    success: function (data) {
                        alert("success");
                        Business.ajaxGetListByOption(vm);
                        Business.ajaxGetPageByOption(vm);
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
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
