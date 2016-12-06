function Broadband(data){
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

function Customer(data){
    this.customerId = data.customerId;
    this.customerCardId = data.customerCardId;
    this.customerName = data.customerName;
    this.customerTelphone = data.customerTelphone;
    this.customerQualityVoice = data.customerQualityVoice == null ? 0 : data.customerQualityVoice;
    this.customerQualityData = data.customerQualityData == null ? 0 : data.customerQualityData;
}