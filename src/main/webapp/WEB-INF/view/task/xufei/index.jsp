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
    <div class="content" ms-controller="broadband_list">


        <div class="row row-offcanvas row-offcanvas-right">
            <!-- 左侧表格内容 Start -->
            <div id="tableData" class="col-xs-12 col-sm-9">
                <div class="col-sm-6 pull-left">
                    <input id="excelFileUploadInput" name="excelFile" type="file" class="file-loading"
                           data-show-preview="false">
                </div>
                <div class="col-sm-6 pull-left">
                    {{@option.list}}　中　{{@option.xuFeiType}} 共　{{@page.totalCounts}}　户
                    <input id="exportExcelButton" type="submit" class="btn btn-primary" value="导出Excel"/>
                </div>
                <div class="col-sm-12 pull-left table-responsive">
                    <table id="vmTable" class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width: 15%">宽带账号</th>
                            <th>当前续费状态</th>
                            <th>当前状态</th>
                            <th>系统标识</th>
                            <th style="width: 15%">联系人</th>
                            <th>电话</th>
                            <th>相关操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ms-for="($index, $broadband) in @broadbands">
                            <td>{{$broadband.broadbandAccount}}</td>
                            <td>{{$broadband.broadbandXuFeiState}}</td>
                            <td>{{$broadband.broadbandState}}</td>
                            <td>{{$broadband.broadbandSystemType}}</td>
                            <td>{{$broadband.customer == null ? "" : $broadband.customer.customerName}}</td>
                            <td>{{$broadband.customer == null ? "" : $broadband.customer.customerTelphone}}</td>
                            <td>
                                <button option="manual" class="btn-block btn-default" data-toggle="modal" data-target="#myModal" ms-click="@openModal($broadband.broadbandId)">手工调整</button>
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
                    <a class="list-group-item" list="全部宽带续费清单">全部宽带续费清单</a>
                    <a class="list-group-item" list="当月宽带续费清单">当月宽带续费清单</a>
                    <a class="list-group-item active" list="次月宽带续费清单">次月宽带续费清单</a>
                </div>
                <div class="optionSwitch list-group" style="margin-top: 0px; margin-bottom: 20px;">
                    <a class="list-group-item" xuFeiType="全部">全部</a>
                    <a class="list-group-item active" xuFeiType="未续费">未续费</a>
                    <a class="list-group-item" xuFeiType="已续费">已续费</a>
                    <a class="list-group-item" xuFeiType="已销号">已销号</a>
                    <a class="list-group-item" xuFeiType="有问题">有问题</a>
                </div>
                <div class="optionSwitch list-group" style="margin-top: 0px; margin-bottom: 20px;">
                    <a class="list-group-item active" systemType="全部">全部</a>
                    <a class="list-group-item" systemType="BSS">BSS</a>
                    <a class="list-group-item" systemType="CBSS">CBSS</a>
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

    $(function () {

        /**
         *  初始化上传控件
         */
        $("#excelFileUploadInput").fileinput({
            language: "zh",
            uploadUrl: "/Task/XuFei/Upload",
            allowedFileExtensions: ['xlsx', 'xls'],
            maxFileSize: 0,
            enctype: 'multipart/form-data'
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
                Broadband.ajaxGetListByOption(vm);
            }
        });


        /**
         *  初始化avalon
         */
        var vm = avalon.define({
            $id: "broadband_list",
            option: {
                list: "次月宽带续费清单",
                xuFeiType: "未续费",
                systemType: "全部"
            },
            page : {
                nowPage: 1,
                totalPages: 1,
                totalCounts: 1
            },
            broadband: new Broadband(),
            broadbands: [],
            openModal: function (broadbandId) {
                Broadband.ajaxGetEntityById(broadbandId, vm);
            }
        });
        Broadband.ajaxGetListByOption(vm);
        Broadband.ajaxGetPageByOption(vm);
        avalon.scan(document.body);


        $('.optionSwitch a').click(function () {
            $(this).nextAll().removeClass("active");
            $(this).prevAll().removeClass("active");
            $(this).addClass("active");
            vm.option.list = $(this).attr("list") != null ? $(this).attr("list") : vm.option.list;
            vm.option.xuFeiType = $(this).attr("xuFeiType") != null ? $(this).attr("xuFeiType") : vm.option.xuFeiType;
            vm.option.systemType = $(this).attr("systemType") != null ? $(this).attr("systemType") : vm.option.systemType;
            vm.page.nowPage = 1;
            Broadband.ajaxGetListByOption(vm);
            Broadband.ajaxGetPageByOption(vm);
        });

        $('#saveBroadbandButton').click(function () {
            Broadband.ajaxModifyEntity(vm);
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
