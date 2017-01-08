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
    <div class="content" ms-controller="customer_list">


        <div class="row row-offcanvas row-offcanvas-right">
            <!-- 左侧表格内容 Start -->
            <div id="tableData" class="col-xs-12 col-sm-9">

                <div class="col-sm-offset-8 col-sm-4 btn-group text-right">
                    <div class="input-group">
                        <input type="text" id="searchCustomerInput" class="form-control"/>
                        <span class="input-group-btn">
                            <input type="button" id="searchCustomerButton" class="btn btn-default" type="button" value="搜索"/>
                        </span>
                    </div><!-- /input-group -->
                </div>

                <div class="col-sm-12 pull-left table-responsive">
                    <table id="vmTable" class="table table-striped">
                        <thead>
                        <tr>
                            <th width="5%"></th>
                            <th width="12%">客户编号</th>
                            <th width="10%">客户姓名</th>
                            <th width="13%">身份证号</th>
                            <th>联系电话</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ms-for="($index, $customer) in @customers">
                            <td><input type="checkbox" name="customerIds" ms-attr="{'value': $customer.customerId}"></td>
                            <td>{{$customer.customerId }}</td>
                            <td>{{$customer.customerName }}</td>
                            <td>{{$customer.customerCardId }}</td>
                            <td>{{$customer.customerTelphone }}</td>
                            <td class="text-right">
                                <div class="btn-group">
                                    <button option="manual" class="btn btn-default" data-toggle="modal" data-target="#myModal" ms-click="@openModal($customer.customerId)">详细</button>
                                    <button option="manual" class="btn btn-default" data-toggle="modal" data-target="#joinUnicomOrderModal" ms-click="@joinUnicomOrderModal($customer.customerId)">受理
                                    </button>
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
                    <a class="list-group-item active" state="未完工">未完工</a>
                    <a class="list-group-item" state="已完工">已完工</a>
                    <a class="list-group-item" state="留单">留单</a>
                </div>

                <div class="list-group">
                    <a href="#" class="list-group-item active">业务受理清单</a>
                    <a href="/Accept/Businesses/" class="list-group-item">流水工单</a>
                    <a href="/Accept/Customer/" class="list-group-item">客户信息</a>
                    <a href="/Accept/UnicomOrder/" class="list-group-item">受理明细</a>
                    <a href="/Accept/Verify/" class="list-group-item">受理验收</a>
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
                                <label for="customerId" class="col-sm-2 control-label">客户编号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerId" ms-duplex="@customer.customerId" disabled>
                                </div>
                                <label for="customerCardId" class="col-sm-2 control-label">身份证号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerCardId" ms-duplex="@customer.customerCardId">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="customerName" class="col-sm-2 control-label">客户姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerName" ms-duplex="@customer.customerName">
                                </div>
                                <label for="customerTelphone" class="col-sm-2 control-label">联系电话</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="customerTelphone" ms-duplex="@customer.customerTelphone">
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

        <!-- joinUnicomOrder模态框（Modal） -->
        <div class="modal fade" id="joinUnicomOrderModal" tabindex="-1" role="dialog" aria-labelledby="joinUnicomOrderLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="joinUnicomOrderLabel">加入受理信息</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="joinUnicomOrderId" class="col-sm-2 control-label">受理编号</label>
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
                Customer.ajaxGetListByOption(vm);
            }
        });


        /**
         *  初始化avalon
         */
        var vm = avalon.define({
            $id: "customer_list",
            option: {
                state: "未完工",
                verify: "全部",
                search: "全部"
            },
            page: {
                nowPage: 1,
                totalPages: 1,
                totalCounts: 1
            },
            customer: new Customer(),
            customers: [],
            unicomOrder: new UnicomOrder(),
            openModal: function (customerId) {
                Customer.ajaxGetEntityById(customerId, vm);
            },
            joinUnicomOrderModal: function (customerId) {
                Customer.ajaxGetEntityById(customerId, vm);
            }
        });
        Customer.ajaxGetListByOption(vm);
        Customer.ajaxGetPageByOption(vm);
        avalon.scan(document.body);


        $('.optionSwitch a').click(function () {
            $(this).nextAll().removeClass("active");
            $(this).prevAll().removeClass("active");
            $(this).addClass("active");
            vm.option.state = $(this).attr("state") != null ? $(this).attr("state") : vm.option.state;
            vm.page.nowPage = 1;
            UnicomOrder.ajaxGetListByOption(vm);
            UnicomOrder.ajaxGetPageByOption(vm);
        });

        $('#updateUnicomOrderButton').click(function () {
            UnicomOrder.ajaxModifyEntity(vm);
            UnicomOrder.ajaxGetListByOption(vm);
            UnicomOrder.ajaxGetListByOption(vm);
        });

        $('#exportExcelButton').click(function () {
            $("<form>").attr({
                "action": "Export",
                "method": "POST"
            }).append("<input type='text' name='list' value='" + vm.option.list + "' />").append("<input type='text' name='xuFeiType' value='" + vm.option.xuFeiType + "' />").append("<input type='text' name='systemType' value='" + vm.option.systemType + "' />").submit();
        });

        $('#searchCustomerButton').click(function () {
            if ($('#searchCustomerInput').val() != null)
                vm.option.search = $('#searchCustomerInput').val();
            Customer.ajaxGetListByOption(vm);
            Customer.ajaxGetPageByOption(vm);
        })

        $('#joinUnicomOrderSubmitButton').click(function () {
            vm.unicomOrder = new UnicomOrder();
            vm.unicomOrder.customer = new Customer();
            vm.unicomOrder.unicomOrderId = $("#joinUnicomOrderId").val();
            vm.unicomOrder.customer.customerId = vm.customer.customerId;
            $.ajax({
                url: "/Ajax/UnicomOrder/JoinCustomer",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(vm.unicomOrder),
                success: function (data) {
                    alert("success");
                    Customer.ajaxGetListByOption(vm);
                    Customer.ajaxGetPageByOption(vm);
                },
                error: function () {
                    alert("error");
                }
            });
        });


    });


</script>

</body>
</html>
