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
function defineBroadbandProduct(){
    vm.broadbandProduct = {};
    vm.broadbandProduct.broadbandProductId = 0;
    vm.broadbandProduct.broadbandProductType = "";
    vm.broadbandProduct.broadbandProductName = "";
    vm.broadbandProduct.broadbandProductState = "";
    vm.broadbandProduct.broadbandProductLength = 0;
    vm.broadbandProduct.broadbandProductDeposit = 0;
    vm.broadbandProduct.broadbandProductMonthly = 0;
    vm.broadbandProduct.broadbandProductDownloadSpeed = "";
}
function BroadbandProduct(data) {
    this.broadbandProductId = data.broadbandProductId;
    this.broadbandProductType = data.broadbandProductType;
    this.broadbandProductName = data.broadbandProductName;
    this.broadbandProductState = data.broadbandProductState;
    this.broadbandProductLength = data.broadbandProductLength;
    this.broadbandProductDeposit = data.broadbandProductDeposit;
    this.broadbandProductMonthly = data.broadbandProductMonthly;
    this.broadbandProductDownloadSpeed = data.broadbandProductDownloadSpeed;
}

