<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
    <div class="content">


        <div class="row row-offcanvas row-offcanvas-right" ms-controller="woTask">
            <div class="col-xs-12 col-sm-9">
                <p class="pull-right visible-xs">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                </p>
                <div class="jumbotron">
                    <h1>宽带续费率 83%!!!</h1>
                    <p>做为每月沃店考核第一攻坚内容，续费率，做为首要任务！</p>
                </div>
                <div class="row">
                    <div class="col-xs-6 col-lg-4">
                        <h2>新装率100%！</h2>
                        <p>
                            总任务数：% <br/>
                            任务需求：% <br/>
                            目前完成：% <br/>
                            还差数量：% <br/>
                        </p>
                        <p><a class="btn btn-default" href="#" role="button">查看详情</a></p>
                    </div><!--/.col-xs-6.col-lg-4-->
                    <div class="col-xs-6 col-lg-4">
                        <h2>续费率83%！</h2>
                        <p>
                            总任务数：{{@task.xuFei.allCounts }} <br/>
                            任务需求：{{Math.ceil(@task.xuFei.allCounts * 0.83)}}<br/>
                            目前完成：{{@task.xuFei.yiXuFeiCounts }}<br/>
                            还差数量：{{Math.ceil(@task.xuFei.allCounts * 0.83) - @task.xuFei.yiXuFeiCounts}}<br/>
                        </p>
                        <p><a class="btn btn-default" href="/Task/XuFei/" role="button">查看详情</a></p>
                    </div><!--/.col-xs-6.col-lg-4-->
                    <div class="col-xs-6 col-lg-4">
                        <h2>续约率50%！</h2>
                        <p>
                            总任务数：% <br/>
                            任务需求：% <br/>
                            目前完成：% <br/>
                            还差数量：% <br/>
                        </p>
                        <p><a class="btn btn-default" href="#" role="button">查看详情</a></p>
                    </div><!--/.col-xs-6.col-lg-4-->
                    <div class="col-xs-6 col-lg-4">
                        <h2>登网率30%！</h2>
                        <p>
                            总任务数：% <br/>
                            任务需求：% <br/>
                            目前完成：% <br/>
                            还差数量：% <br/>
                        </p>
                        <p><a class="btn btn-default" href="#" role="button">查看详情</a></p>
                    </div><!--/.col-xs-6.col-lg-4-->
                    <div class="col-xs-6 col-lg-4">
                        <h2>欠费回收98.5%！</h2>
                        <p>
                            总任务数：% <br/>
                            任务需求：% <br/>
                            目前完成：% <br/>
                            还差数量：% <br/>
                        </p>
                        <p><a class="btn btn-default" href="#" role="button">查看详情</a></p>
                    </div><!--/.col-xs-6.col-lg-4-->
                    <div class="col-xs-6 col-lg-4">
                        <h2>移动业务100%！</h2>
                        <p>
                            总任务数：% <br/>
                            任务需求：% <br/>
                            目前完成：% <br/>
                            还差数量：% <br/>
                        </p>
                        <p><a class="btn btn-default" href="#" role="button">查看详情</a></p>
                    </div>
                </div>
            </div>


            <!--  沃店考核指标导航 Start  -->
            <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
                <div class="list-group">
                    <a href="#" class="list-group-item active">维系挽留导航</a>
                    <a href="/Retention/Syncretize/" class="list-group-item">融合用户</a>
                </div>
            </div>
            <!--  沃店考核指标导航 End  -->


        </div><!--/row-->

    </div>
    <!-- 内容 End -->


</div>


<!-- javascript -->

<script src="/lib/jquery/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="/lib/avalon2/avalon.js"></script>
<script>

    function getCountsAjax(vm) {
        $.ajax({
            url: "/Task/Counts",
            type: "post",
            dataType: "json",
            success: function (data) {
                vm.task.xuFei.allCounts = data[0];
                vm.task.xuFei.yiXuFeiCounts = data[1];
            }
        });
    }


    $(function () {

        //        初始化avalon
        var vm = avalon.define({
            $id: "woTask",
            test: "sss",
            task: {
                xuFei: {
                    allCounts: 0,
                    yiXuFeiCounts: 0
                }
            }
        });

        getCountsAjax(vm);

        avalon.scan(document.body);
    });
</script>

</body>
</html>
