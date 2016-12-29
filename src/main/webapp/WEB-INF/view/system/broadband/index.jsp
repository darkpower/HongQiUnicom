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
    <div class="content" ms-controller="broadband_product_list">


        <div class="row row-offcanvas row-offcanvas-right">
            <!-- 左侧表格内容 Start -->
            <div id="tableData" class="col-xs-12 col-sm-9">

                <div class="col-sm-12 pull-left">
                    <input id="addBroadbandProductButton" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal" value="添加宽带产品"/>
                </div>
                <div class="col-sm-12 pull-left table-responsive">
                    <table id="vmTable" class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width: 15%">宽带产品名称</th>
                            <th>合约期</th>
                            <th>首付</th>
                            <th>月供</th>
                            <th>相关操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ms-for="($index, $broadbandProduct) in @broadbandProducts">
                            <td>{{$broadbandProduct.broadbandProductName == null ? "" : $broadbandProduct.broadbandProductName }}</td>
                            <td>{{$broadbandProduct.broadbandProductLength == null ? "" : $broadbandProduct.broadbandProductLength }}</td>
                            <td>{{$broadbandProduct.broadbandProductDeposit == null ? "" : $broadbandProduct.broadbandProductDeposit }}</td>
                            <td>{{$broadbandProduct.broadbandProductMonthly == null ? "" : $broadbandProduct.broadbandProductMonthly }}</td>
                            <td>
                                <button option="manual" class="btn-block btn-default pull-left" data-toggle="modal" data-target="#myModal" ms-click="@openModal($broadbandProduct.broadbandProductId)">修改</button>
                                <button option="manual" class="btn-block btn-default pull-left" ms-click="@deleteBroadbandProduct($broadbandProduct.broadbandProductId)">删除</button>
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
                    <a class="list-group-item active" list="全部">全部</a>
                    <a class="list-group-item" list="有效">有效</a>
                    <a class="list-group-item" list="失效">失效</a>
                </div>

                <div class="list-group">
                    <a href="#" class="list-group-item">当月沃店考核指标导航</a>
                    <a href="#" class="list-group-item">当月宽带新装率</a>
                    <a href="/Task/XuFei/" class="list-group-item active">次月宽带续费率</a>
                    <a href="#" class="list-group-item">当月宽带融合率</a>
                    <a href="#" class="list-group-item">当月终端发展率</a>
                    <a href="#" class="list-group-item">次月合约续约率</a>
                    <a href="#" class="list-group-item">当月移动业务发展率</a>
                    <a href="#" class="list-group-item">当月移动业务登网率</a>
                    <a href="#" class="list-group-item">次月固网欠费回收率</a>
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
                        <h4 class="modal-title" id="myModalLabel">手工调整</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="broadbandProductType" class="col-sm-2 control-label">产品分类</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="broadbandProductType" ms-duplex="@broadbandProduct.broadbandProductType">
                                        <option value="宽带单产品">宽带单产品</option>
                                        <option value="智慧沃家共享版">智慧沃家共享版</option>
                                        <option value="合约惠机终端版">合约惠机终端版</option>
                                    </select>
                                </div>
                                <label for="broadbandProductName" class="col-sm-2 control-label">产品名称</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="broadbandProductName" ms-duplex="@broadbandProduct.broadbandProductName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="broadbandProductState" class="col-sm-2 control-label">产品状态</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="broadbandProductState" ms-duplex="@broadbandProduct.broadbandProductState">
                                        <option value="有效">有效</option>
                                        <option value="失效">失效</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="broadbandProductLength" class="col-sm-2 control-label">合约期</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="broadbandProductLength" ms-duplex="@broadbandProduct.broadbandProductLength">
                                </div>
                                <label for="broadbandProductDeposit" class="col-sm-2 control-label">首付</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="broadbandProductDeposit" ms-duplex="@broadbandProduct.broadbandProductDeposit">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="broadbandProductMonthly" class="col-sm-2 control-label">月供</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="broadbandProductMonthly" ms-duplex="@broadbandProduct.broadbandProductMonthly">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="broadbandProductDownloadSpeed" class="col-sm-2 control-label">下载速率</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="broadbandProductDownloadSpeed" ms-duplex="@broadbandProduct.broadbandProductDownloadSpeed">
                                        <option value="10M">10M</option>
                                        <option value="20M">20M</option>
                                        <option value="50M">50M</option>
                                        <option value="100M">100M</option>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="broadbandProductSubmit" class="btn btn-primary">保存</button>
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


    /**
     * ajax获取全部宽带产品数据
     *
     * @param vm
     */

    function ajaxBroadbandProductList(vm) {
        $.ajax({
            url: "/System/Broadband/List",
            type: "post",
            dataType: "json",
            data: {
                'nowPage': vm.page.nowPage
            },
            success: function (data) {
                vm.broadbandProducts = [];
                vm.broadbandProducts = data;
            },
            error: function () {
                alert("error");
            }
        });
    }

    /**
     * ajax获取全部宽带信息分页数据
     *
     * @param vm
     */
    function ajaxBroadbandProductPage(vm) {
        $.ajax({
            url: "/System/Broadband/Page",
            type: "post",
            dataType: "json",
            data: {},
            success: function (data) {
                vm.page.totalCounts = data;
                vm.page.totalPages = data / 10;
                if (vm.page.totalCounts % 10 != 0) {
                    vm.page.totalPages++;
                } else if (vm.page.totalCounts == 0) {
                    vm.page.totalPages++;
                }
                var options = {
                    bootstrapMajorVersion: 3,
                    currentPage: vm.page.nowPage,//当前页面
                    totalPages: vm.page.totalPages, //总页数
                    numberOfPages: 10//一页显示几个按钮（在ul里面生成5个li）
                };
                $('#page').bootstrapPaginator("setOptions", options);
            },
            error: function () {
                alert("error");
            }
        });
    }


    /**
     *  根据Id获取BroadbandProduct实体
     */
    function ajaxBroadbandProductEntity(broadbandProductId, vm) {
        $.ajax({
            url: "/System/Broadband/Show",
            type: "post",
            dataType: "json",
            data: {'broadbandProductId': broadbandProductId},
            success: function (data) {
                vm.broadbandProduct = {};
                vm.broadbandProduct = new BroadbandProduct(data);

            },
            error: function () {
                alert("error");
            }
        });
    }


    $(function () {


        /**
         * 初始化Bootstrap分页控件
         */
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
                ajaxBroadbandProductList(vm);
            }
        });


        /**
         * 初始化AVALON
         */
        var vm = avalon.define({
            $id: "broadband_product_list",
            option: {
                list: "次月宽带续费清单",
                xuFeiType: "未续费",
                systemType: "全部"
            },
            page: {
                nowPage: 1,
                totalPages: 1,
                totalCounts: 1
            },
            broadbandProducts: [],
            broadbandProduct: {},
            openModal: function (broadbandProductId) {
                ajaxBroadbandProductEntity(broadbandProductId, this);
            }
        });
        ajaxBroadbandProductList(vm);
        ajaxBroadbandProductPage(vm);
        avalon.scan(document.body);


        $('#addBroadbandProductButton').on('show.bs.modal', function(){
            vm.broadbandProduct.broadbandProductId = 0;
            vm.broadbandProduct.broadbandProductType = "";
            vm.broadbandProduct.broadbandProductName = "";
            vm.broadbandProduct.broadbandProductState = "";
            vm.broadbandProduct.broadbandProductLength = 0;
            vm.broadbandProduct.broadbandProductDeposit = 0;
            vm.broadbandProduct.broadbandProductMonthly = 0;
            vm.broadbandProduct.broadbandProductDownloadSpeed = "";
        });

        $('#broadbandProductSubmit').click(function () {
            $.ajax({
                url: "/System/Broadband/Update",
                type: "post",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(vm.broadbandProduct),
                success: function () {
                    alert("success");
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
