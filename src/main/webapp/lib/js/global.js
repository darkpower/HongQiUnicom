/*!
 * FastJson Plugin v1.0
 * https://github.com/sdragoncai/fastjson.js
 * Copyright 2015 dragoncai（蔡小龙）
 * Email dragoncai@banbang.cn
 */
var FastJson = {
    isArray: function (a) {
        return "object" == typeof a && "[object array]" == Object.prototype.toString.call(a).toLowerCase()
    }, isObject: function (a) {
        return "object" == typeof a && "[object object]" == Object.prototype.toString.call(a).toLowerCase()
    }, format: function (a) {
        if (null == a)return null;
        "string" == typeof a && (a = eval("(" + a + ")"));
        return this._format(a, a, null, null, null)
    }, _randomId: function () {
        return "randomId_" + parseInt(1E9 * Math.random())
    }, _getJsonValue: function (a, c) {
        var d = this._randomId(), b;
        b = "" + ("function " + d + "(root){") + ("return root." + c + ";");
        b += "}";
        b += "";
        var e = document.createElement("script");
        e.id = d;
        e.text = b;
        document.body.appendChild(e);
        d = window[d](a);
        e.parentNode.removeChild(e);
        return d
    }, _format: function (a, c, d, b, e) {
        d || (d = "");
        if (this.isObject(c)) {
            if (c.$ref) {
                var g = c.$ref;
                0 == g.indexOf("$.") && (b[e] = this._getJsonValue(a, g.substring(2)));
                return
            }
            for (var f in c)b = d, "" != b && (b += "."), g = c[f], b += f, this._format(a, g, b, c, f)
        } else if (this.isArray(c))for (f in c)b = d, g = c[f], b = b + "[" + f + "]", this._format(a, g, b, c, f);
        return a
    }
};

function Broadband() {
    var o = new Object();
    o.broadbandId = 0;
    o.broadbandAccount = "";
    o.broadbandExpireDate = "";
    o.broadbandRenewalDate = "";
    o.broadbandPrice = 0;
    o.broadbandSystemType = "";
    o.broadbandState = "";
    o.broadbandXuFeiState = "";
    o.broadbandRetentionDate = "";
    o.broadbandRetentionContent = "";
    o.customer = new Customer();
    Broadband.createEntity = function (data) {
        o.broadbandId = data.broadbandId;
        o.broadbandAccount = data.broadbandAccount;
        o.broadbandExpireDate = data.broadbandExpireDate;
        o.broadbandRenewalDate = data.broadbandRenewalDate;
        o.broadbandPrice = data.broadbandPrice;
        o.broadbandSystemType = data.broadbandSystemType;
        o.broadbandState = data.broadbandState;
        o.broadbandXuFeiState = data.broadbandXuFeiState;
        o.broadbandRetentionDate = data.broadbandRetentionDate == null ? "" : data.broadbandRetentionDate;
        o.broadbandRetentionContent = data.broadbandRetentionContent == null ? "" : data.broadbandRetentionContent;
        o.customer = Customer.createEntity(data.customer);
        return o;
    }

    Broadband.ajaxGetEntityById = function (broadbandId, vm) {
        $.ajax({
            url: "/Ajax/Broadband/Show",
            type: "post",
            dataType: "json",
            data: {'broadbandId': broadbandId},
            success: function (data) {
                vm.broadband = Broadband.createEntity(data);
            },
            error: function (data) {
                alert(error);
            }
        });
    };
    Broadband.ajaxGetListByOption = function (vm) {
        $.ajax({
            url: "/Ajax/Broadband/List",
            type: "post",
            dataType: "json",
            data: {
                'page': vm.page.nowPage,
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
    };
    Broadband.ajaxGetPageByOption = function (vm) {
        $.ajax({
            url: "/Ajax/Broadband/Page",
            type: "post",
            dataType: "json",
            data: {
                'list': vm.option.list,
                'xuFeiType': vm.option.xuFeiType,
                'systemType': vm.option.systemType
            },
            success: function (data) {
                vm.page.totalCounts = data;
                vm.page.totalPages = data / 10;
                if (vm.page.totalCounts % 10 != 0) {
                    vm.page.totalPages++;
                } else if (vm.totalCounts == 0) {
                    vm.page.totalPages++;
                }
                var options = {
                    bootstrapMajorVersion: 3,
                    currentPage: vm.page.nowPage,//当前页面
                    totalPages: vm.page.totalPages, //总页数
                    numberOfPages: 10//一页显示几个按钮（在ul里面生成5个li）
                }
                $('#page').bootstrapPaginator("setOptions", options);
            },
            error: function () {
                alert("error");
            }
        });
    };
    Broadband.ajaxModifyEntity = function (vm) {
        $.ajax({
            url: "/Ajax/Broadband/Update",
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
    };
    return o;


}

function Customer() {
    var o = new Object();
    o.customerId = 0;
    o.customerCardId = "";
    o.customerName = "";
    o.customerTelphone = "";
    o.customerQualityVoice = 0;
    o.customerQualityData = 0;
    Customer.createEntity = function (data) {
        o.customerId = data.customerId;
        o.customerCardId = data.customerCardId;
        o.customerName = data.customerName;
        o.customerTelphone = data.customerTelphone;
        o.customerQualityVoice = data.customerQualityVoice;
        o.customerQualityData = data.customerQualityData;
        return o;
    }
    return o;
}

function Business() {
    var o = new Object();
    o.businessId = 0;
    o.businessDate = "";
    o.businessType = new BusinessType();
    o.businessAccount = "";
    o.businessCost = 0;
    o.businessState = 0;
    Business.createEntity = function (data) {
        o.businessId = data.businessId;
        o.businessDate = data.businessDate;
        o.businessType = BusinessType.createEntity(data);
        o.businessAccount = data.businessAccount;
        o.businessCost = data.businessCost;
        o.businessState = data.businessState;
        return o;
    }
    Business.ajaxGetEntityById = function (businessId, vm) {
        $.ajax({
            url: "/Ajax/Business/Show",
            type: "post",
            dataType: "json",
            data: {'businessId': businessId},
            success: function (data) {
                vm.business = Business.createEntity(data);
            },
            error: function (data) {
                alert(error);
            }
        });
    };
    Business.ajaxGetListByOption = function (vm) {
        $.ajax({
            url: "/Ajax/Business/List",
            type: "post",
            dataType: "json",
            data: {
                'page': vm.page.nowPage,
                'list': vm.option.list,
                'startDay': vm.option.startDay,
                'endDay': vm.option.endDay
            },
            success: function (data) {
                vm.businesses = [];
                vm.businesses = data;
            },
            error: function () {
                alert("error");
            }
        });
    };
    Business.ajaxGetPageByOption = function (vm) {
        $.ajax({
            url: "/Ajax/Business/Page",
            type: "post",
            dataType: "json",
            data: {
                'list': vm.option.list,
                'startDay': vm.option.startDay,
                'endDay': vm.option.endDay
            },
            success: function (data) {
                vm.page.totalCounts = data;
                vm.page.totalPages = data / 10;
                if (vm.page.totalCounts % 10 != 0) {
                    vm.page.totalPages++;
                } else if (vm.totalCounts == 0) {
                    vm.page.totalPages++;
                }
                var options = {
                    bootstrapMajorVersion: 3,
                    currentPage: vm.page.nowPage,//当前页面
                    totalPages: vm.page.totalPages, //总页数
                    numberOfPages: 10//一页显示几个按钮（在ul里面生成5个li）
                }
                $('#page').bootstrapPaginator("setOptions", options);
            },
            error: function () {
                alert("error");
            }
        });
    };
    return o;
}

function BusinessType() {
    var o = new Object();
    o.businessTypeId = 0;
    o.businessTypeName = "";
    BusinessType.createEntity = function (data) {
        o.businessTypeId = data.businessTypeId;
        o.businessTypeName = data.businessTypeName;
        return o;
    }
    return o;
}

function UnicomOrder() {
    var o = new Object();
    o.unicomOrderId = 0;
    o.unicomOrderDate = "";
    o.unicomOrderState = 0;
    o.unicomOrderRenewalTag = 0;
    o.unicomOrderType = new UnicomOrderType();
    o.staff = new Staff();
    o.businesses = [];
    UnicomOrder.createEntity = function (data) {
        o.unicomOrderId = data.unicomOrderId;
        o.unicomOrderDate = data.unicomOrderDate;
        o.unicomOrderState = data.unicomOrderState;
        o.unicomOrderRenewalTag = data.unicomOrderRenewalTag;
        o.businesses = data.businesses;
        o.unicomOrderType = UnicomOrderType.createEntity(data.unicomOrderType);
        o.staff = Staff.createEntity(data.staff);
        return o;
    }

    UnicomOrder.ajaxGetEntityById = function (unicomOrderId, vm) {
        $.ajax({
            url: "/Ajax/UnicomOrder/Show",
            type: "post",
            dataType: "json",
            data: {'unicomOrderId': unicomOrderId},
            success: function (data) {
                vm.unicomOrder = new UnicomOrder();
                vm.unicomOrder = UnicomOrder.createEntity(data);
                vm.unicomOrder.businesses.sort(function (a, b) {
                    var compA = a.businessDate;
                    var compB = b.businessDate;
                    return (compA < compB) ? -1 : (compA > compB) ? 1 : 0;
                });
            },
            error: function (data) {
                alert("error");
            }
        });
    };
    UnicomOrder.ajaxGetListByOption = function (vm) {
        $.ajax({
            url: "/Ajax/UnicomOrder/List",
            type: "post",
            dataType: "json",
            data: {
                'page': vm.page.nowPage,
                'state': vm.option.state
            },
            success: function (data) {
                vm.unicomOrders = [];
                vm.unicomOrders = data;
            },
            error: function () {
                alert("error");
            }
        });
    }
    UnicomOrder.ajaxGetPageByOption = function (vm) {
        $.ajax({
            url: "/Ajax/UnicomOrder/Page",
            type: "post",
            dataType: "json",
            data: {
                'state': vm.option.state
            },
            success: function (data) {
                vm.page.totalCounts = data;
                vm.page.totalPages = data / 10;
                if (vm.page.totalCounts % 10 != 0) {
                    vm.page.totalPages++;
                } else if (vm.totalCounts == 0) {
                    vm.page.totalPages++;
                }
                var options = {
                    bootstrapMajorVersion: 3,
                    currentPage: vm.page.nowPage,//当前页面
                    totalPages: vm.page.totalPages, //总页数
                    numberOfPages: 10//一页显示几个按钮（在ul里面生成5个li）
                }
                $('#page').bootstrapPaginator("setOptions", options);
            },
            error: function () {
                alert("error");
            }
        });
    }
    UnicomOrder.ajaxModifyEntity = function (vm) {
        $.ajax({
            url: "/Ajax/UnicomOrder/Update",
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
    };

    return o;
}

function UnicomOrderType() {
    var o = new Object();
    o.unicomOrderTypeId = 0;
    o.unicomOrderTypeName = "";
    o.unicomOrderTypeState = 0;
    UnicomOrderType.createEntity = function (data) {
        if (data == null) return new UnicomOrderType();
        else {
            o.unicomOrderTypeId = data.unicomOrderTypeId;
            o.unicomOrderTypeName = data.unicomOrderTypeName;
            o.unicomOrderTypeState = data.unicomOrderTypeState;
        }
        return o;
    }
    UnicomOrderType.ajaxGetSelectList = function (vm) {
        $.ajax({
            url: "/Ajax/UnicomOrderType/SelectList",
            type: "post",
            dataType: "json",
            data: {},
            success: function (data) {
                vm.unicomOrderTypes = [];
                vm.unicomOrderTypes = data;
            },
            error: function () {
                alert("error");
            }
        });
    }
    return o;
}

function Staff() {
    var o = new Object();
    o.staffId = 0;
    o.staffName = "";
    o.staffState = 0;
    Staff.createEntity = function (data) {
        if (data == null) return new Staff();
        else {
            o.staffId = data.staffId;
            o.staffName = data.staffName;
            o.staffState = data.staffState;
        }
        return o;
    }
    Staff.ajaxGetSelectList = function (vm) {
        $.ajax({
            url: "/Ajax/Staff/SelectList",
            type: "post",
            dataType: "json",
            data: {},
            success: function (data) {
                vm.staffs = [];
                vm.staffs = data;
            },
            error: function () {
                alert("error");
            }
        });
    }
    return o;
}


function defineBroadbandProduct(vm) {

    this.makeBroadbandProductWithData = function (data) {

        return this;
    }
}

function createBroadbandProduct(data) {
    var o = new Object();
    if (data == null) {
        o.broadbandProductId = 0;
        o.broadbandProductType = "";
        o.broadbandProductName = "";
        o.broadbandProductState = "";
        o.broadbandProductLength = 0;
        o.broadbandProductDeposit = 0;
        o.broadbandProductMonthly = 0;
        o.broadbandProductDownloadSpeed = "";
    } else {
        o.broadbandProductId = data.broadbandProductId;
        o.broadbandProductType = data.broadbandProductType;
        o.broadbandProductName = data.broadbandProductName;
        o.broadbandProductState = data.broadbandProductState;
        o.broadbandProductLength = data.broadbandProductLength;
        o.broadbandProductDeposit = data.broadbandProductDeposit;
        o.broadbandProductMonthly = data.broadbandProductMonthly;
        o.broadbandProductDownloadSpeed = data.broadbandProductDownloadSpeed;
    }
    return o;
}

