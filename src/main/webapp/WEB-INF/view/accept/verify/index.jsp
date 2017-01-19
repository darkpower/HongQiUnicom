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
    <div class="content" ms-controller="broadband_list">


        <div class="row row-offcanvas row-offcanvas-right">


            <!-- 左侧表格内容 Start -->
            <div id="tableData" class="col-xs-12 col-sm-9">


                <div class="col-sm-3">
                    <input type="button" id="flushButton" class="btn btn-default" value="刷新"/>
                </div>
                <div class="col-sm-offset-5 col-sm-4 btn-group text-right">
                    <div class="input-group">
                        <input type="text" id="searchUnicomOrderInput" class="form-control"/>
                        <span class="input-group-btn">
                            <input type="button" id="searchUnicomOrderButton" class="btn btn-default" type="button"
                                   value="搜索"/>
                        </span>
                    </div><!-- /input-group -->
                </div>


                <div class="col-sm-12 pull-left table-responsive">
                    <table id="vmTable" class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width: 15%">受理日期</th>
                            <th>受理业务</th>
                            <th>联系人</th>
                            <th>受理人</th>
                            <th>相关操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ms-for="($index, $unicomOrder) in @unicomOrders">
                            <td>{{$unicomOrder.unicomOrderDate | date('yyyy-MM-dd')}}</td>
                            <td>{{$unicomOrder.unicomOrderType == null ? "" :
                                $unicomOrder.unicomOrderType.unicomOrderTypeName }}
                            </td>
                            <td>{{$unicomOrder.customer == null ? "" : $unicomOrder.customer.customerName }}</td>
                            <td>{{$unicomOrder.staff == null ? "" : $unicomOrder.staff.staffName }}</td>
                            <td>
                                <button option="manual" class="btn-block btn-default" data-toggle="modal"
                                        data-target="#myModal" ms-click="@openModal($unicomOrder.unicomOrderId)">详细
                                </button>
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
                    <a class="list-group-item" verify="全部">全部</a>
                    <a id="optionVerifyDefault" class="list-group-item active" verify="尚未验收">尚未验收</a>
                    <a class="list-group-item" verify="验收合格">验收合格</a>
                    <a class="list-group-item" verify="业务差错">业务差错</a>
                </div>

                <div class="list-group">
                    <a href="#" class="list-group-item active">业务受理清单</a>
                    <a href="/Accept/Businesses/" class="list-group-item">流水工单</a>
                    <a href="/Accept/Customer/" class="list-group-item">客户信息</a>
                    <a href="/Accept/UnicomOrder/" class="list-group-item">受理明细</a>
                    <a href="/Accept/Verify/" class="list-group-item">受理验收</a>
                    <a href="/Accept/Save/" class="list-group-item">数据存档</a>
                </div>
            </div>
            <!-- 右侧导航内容 End -->
        </div>


        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">详细信息</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="unicomOrderId" class="col-sm-2 control-label">受理编号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="unicomOrderId"
                                           ms-duplex="@unicomOrder.unicomOrderId" disabled>
                                </div>
                                <label for="unicomOrderDate" class="col-sm-2 control-label">受理日期</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="unicomOrderDate"
                                           ms-duplex="@unicomOrder.unicomOrderDate | date('yyyy-MM-dd')" disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="unicomOrderType" class="col-sm-2 control-label">受理业务</label>
                                <div class="col-sm-4">
                                    <select type="text" class="form-control" id="unicomOrderType"
                                            ms-duplex="@unicomOrder.unicomOrderType.unicomOrderTypeId">
                                        <option ms-for="($index, $unicomOrderType) in @unicomOrderTypes"
                                                ms-attr="{value: $unicomOrderType.unicomOrderTypeId }">
                                            {{$unicomOrderType.unicomOrderTypeName
                                            }}
                                        </option>
                                    </select>
                                </div>
                                <label for="unicomOrderTag" class="col-sm-2 control-label">业务标签</label>
                                <div class="col-sm-4">
                                    <select type="text" class="form-control" id="unicomOrderTag"
                                            ms-duplex="@unicomOrder.unicomOrderTag.unicomOrderTagId">
                                        <option ms-for="($index, $unicomOrderTag) in @unicomOrderTags"
                                                ms-attr="{value: $unicomOrderTag.unicomOrderTagId }">
                                            {{$unicomOrderTag.unicomOrderTagName
                                            }}
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="staff" class="col-sm-2 control-label">受理人</label>
                                <div class="col-sm-4">
                                    <select type="text" class="form-control" id="staff"
                                            ms-duplex="@unicomOrder.staff.staffId">
                                        <option ms-for="($index, $staff) in @staffs" ms-attr="{value: $staff.staffId }">
                                            {{$staff.staffName }}
                                        </option>
                                    </select>
                                </div>
                                <label for="unicomOrderState" class="col-sm-2 control-label">验收状态</label>
                                <div class="col-sm-4">
                                    <select type="text" class="form-control" id="unicomOrderState"
                                            ms-duplex="@unicomOrder.unicomOrderVerify">
                                        <option value="1">尚未验收</option>
                                        <option value="2">验收合格</option>
                                        <option value="3">业务差错</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="unicomOrderMistakeDescription" class="col-sm-2 control-label">差错明细</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="unicomOrderMistakeDescription"
                                           ms-duplex="@unicomOrder.unicomOrderMistakeDescription">
                                </div>
                            </div>

                            <div>
                                <div class="table-responsive">
                                    <table id="selectBusinessTable" class="table table-striped"
                                           style="word-break:break-all">
                                        <thead>
                                        <tr>
                                            <th width="14%">工单时间</th>
                                            <th width="15%">工单流水</th>
                                            <th width="14%">工单类型</th>
                                            <th width="14%">对应账号</th>
                                            <th width="14%">对应姓名</th>
                                            <th>工单备注</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr ms-for="($index, $business) in @unicomOrder.businesses">
                                            <td>{{$business.businessDate | date('yy-MM-dd hh:mm:ss') }}</td>
                                            <td>{{$business.businessSerialNumber | trim() }}</td>
                                            <td>{{$business.businessType.businessTypeName }}</td>
                                            <td>{{$business.businessAccount }}</td>
                                            <td>{{$business.businessUserName }}</td>
                                            <td>{{$business.businessDescription | truncate(21, '…') }}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </form>


                    </div>
                    <div class="modal-footer">
                        <button type="button" id="updateUnicomOrderButton" class="btn btn-primary">保存</button>
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

    $(function () {


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
                UnicomOrder.ajaxGetListByOption(vm);
            }
        });


        /**
         *  初始化avalon
         */
        var vm = avalon.define({
            $id: "broadband_list",
            option: {
                state: "已完工",
                verify: "尚未验收",
                savedata: "全部",
                search: "全部"
            },
            page: {
                nowPage: 1,
                totalPages: 1,
                totalCounts: 1
            },
            unicomOrder: new UnicomOrder(),
            unicomOrders: [],
            staffs: [],
            unicomOrderTypes: [],
            unicomOrderTags: [],
            businesses: [],
            openModal: function (unicomOrderId) {
                UnicomOrder.ajaxGetEntityById(unicomOrderId, vm);
            },
            flush: function () {
                UnicomOrder.ajaxGetListByOption(vm);
                UnicomOrder.ajaxGetPageByOption(vm);
                $("#page").bootstrapPaginator("show", 1);
                avalon.scan(document.body);
            }

        });
        Staff.ajaxGetSelectList(vm);
        UnicomOrderType.ajaxGetSelectList(vm);
        UnicomOrderTag.ajaxGetSelectList(vm);
        vm.flush();



        $('.optionSwitch a').click(function () {
            $(this).nextAll().removeClass("active");
            $(this).prevAll().removeClass("active");
            $(this).addClass("active");
            vm.option.state = $(this).attr("state") != null ? $(this).attr("state") : vm.option.state;
            vm.option.verify = $(this).attr("verify") != null ? $(this).attr("verify") : vm.option.verify;
            vm.page.nowPage = 1;
            vm.flush();
        });

        $('#updateUnicomOrderButton').click(function () {
            UnicomOrder.ajaxModifyEntity(vm);
        });

        $('#exportExcelButton').click(function () {
            $("<form>").attr({
                "action": "Export",
                "method": "POST"
            }).append("<input type='text' name='list' value='" + vm.option.list + "' />").append("<input type='text' name='xuFeiType' value='" + vm.option.xuFeiType + "' />").append("<input type='text' name='systemType' value='" + vm.option.systemType + "' />").submit();
        });

        $('#searchUnicomOrderButton').click(function () {
            if ($('#searchUnicomOrderInput').val() != null && $('#searchUnicomOrderInput').val() != "")
                vm.option.search = $('#searchUnicomOrderInput').val();
            else
                vm.option.search = "全部";
            vm.flush();

        });

        $('#flushButton').click(function () {
            vm.option.state = "已完工";
            vm.option.verify = "尚未验收";
            vm.option.savedata = "全部";
            vm.option.search = "全部";
            $("#optionVerifyDefault").nextAll().removeClass("active");
            $("#optionVerifyDefault").prevAll().removeClass("active");
            $("#optionVerifyDefault").addClass("active");
            vm.flush();
        });


    });


</script>

</body>
</html>
