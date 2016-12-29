function Broadband(data) {
    this.broadbandId = data.broadbandId;
    this.broadbandAccount = data.broadbandAccount;
    this.broadbandExpireDate = data.broadbandExpireDate;
    this.broadbandRenewalDate = data.broadbandRenewalDate;
    this.broadbandPrice = data.broadbandPrice;
    this.broadbandSystemType = data.broadbandSystemType;
    this.broadbandState = data.broadbandState;
    this.broadbandXuFeiState = data.broadbandXuFeiState;
    this.customer = new Customer(data.customer);
    this.broadbandRetentionDate = data.broadbandRetentionDate == null ? "-" : data.broadbandRetentionDate;
    this.broadbandRetentionContent = data.broadbandRetentionContent == null ? "" : data.broadbandRetentionContent;
}

function Customer(data) {
    this.customerId = data.customerId;
    this.customerCardId = data.customerCardId;
    this.customerName = data.customerName;
    this.customerTelphone = data.customerTelphone;
    this.customerQualityVoice = data.customerQualityVoice == null ? 0 : data.customerQualityVoice;
    this.customerQualityData = data.customerQualityData == null ? 0 : data.customerQualityData;
}


/**
 * Json -> BroadbandProduct
 * @param data
 * @constructor
 */
function defineBroadbandProduct(vm) {

    this.makeBroadbandProductWithData = function (data) {

        return this;
    }
}

function createBroadbandProduct(data){
    var o = new Object();
    if(data == null){
        o.broadbandProductId = 0;
        o.broadbandProductType = "";
        o.broadbandProductName = "";
        o.broadbandProductState = "";
        o.broadbandProductLength = 0;
        o.broadbandProductDeposit = 0;
        o.broadbandProductMonthly = 0;
        o.broadbandProductDownloadSpeed = "";
    }else{
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

