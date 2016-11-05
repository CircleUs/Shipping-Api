package us.circle.model;


import java.math.BigDecimal;

public class Rate{

    private String transitTime;

    private BigDecimal baseRate;

    private BigDecimal customerRate;

    private BigDecimal baseCharge;

    private BigDecimal customerCharge;

    private BigDecimal baseFuelSurcharge;

    private Float fuelPercentage;

    private BigDecimal customerFuelSurcharge;

    private BigDecimal baseSurchargeTransport;

    private String baseSurchargeTransportDetail;

    private BigDecimal customerSurchargeTransport;

    private String customerSurchargeTransportDetail;

    private BigDecimal baseOtherCharge;

    private String baseOtherchargeDetail;

    private BigDecimal customerOtherCharge;

    private String customerOtherChargeDetail;

    private BigDecimal baseInsuranceCharge;

    private BigDecimal customerInsuranceCharge;

    private BigDecimal baseIns3rdCharge;

    private BigDecimal customerIns3rdCharge;

    private String adjusted;

    public String getTransitTime() {
        return transitTime;
    }

    public void setTransitTime(String transitTime) {
        this.transitTime = transitTime == null ? null : transitTime.trim();
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    public BigDecimal getCustomerRate() {
        return customerRate;
    }

    public void setCustomerRate(BigDecimal customerRate) {
        this.customerRate = customerRate;
    }

    public BigDecimal getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(BigDecimal baseCharge) {
        this.baseCharge = baseCharge;
    }

    public BigDecimal getCustomerCharge() {
        return customerCharge;
    }

    public void setCustomerCharge(BigDecimal customerCharge) {
        this.customerCharge = customerCharge;
    }

    public BigDecimal getBaseFuelSurcharge() {
        return baseFuelSurcharge;
    }

    public void setBaseFuelSurcharge(BigDecimal baseFuelSurcharge) {
        this.baseFuelSurcharge = baseFuelSurcharge;
    }

    public Float getFuelPercentage() {
        return fuelPercentage;
    }

    public void setFuelPercentage(Float fuelPercentage) {
        this.fuelPercentage = fuelPercentage;
    }

    public BigDecimal getCustomerFuelSurcharge() {
        return customerFuelSurcharge;
    }

    public void setCustomerFuelSurcharge(BigDecimal customerFuelSurcharge) {
        this.customerFuelSurcharge = customerFuelSurcharge;
    }

    public BigDecimal getBaseSurchargeTransport() {
        return baseSurchargeTransport;
    }

    public void setBaseSurchargeTransport(BigDecimal baseSurchargeTransport) {
        this.baseSurchargeTransport = baseSurchargeTransport;
    }

    public String getBaseSurchargeTransportDetail() {
        return baseSurchargeTransportDetail;
    }

    public void setBaseSurchargeTransportDetail(String baseSurchargeTransportDetail) {
        this.baseSurchargeTransportDetail = baseSurchargeTransportDetail == null ? null : baseSurchargeTransportDetail.trim();
    }

    public BigDecimal getCustomerSurchargeTransport() {
        return customerSurchargeTransport;
    }

    public void setCustomerSurchargeTransport(BigDecimal customerSurchargeTransport) {
        this.customerSurchargeTransport = customerSurchargeTransport;
    }

    public String getCustomerSurchargeTransportDetail() {
        return customerSurchargeTransportDetail;
    }

    public void setCustomerSurchargeTransportDetail(String customerSurchargeTransportDetail) {
        this.customerSurchargeTransportDetail = customerSurchargeTransportDetail == null ? null : customerSurchargeTransportDetail.trim();
    }

    public BigDecimal getBaseOtherCharge() {
        return baseOtherCharge;
    }

    public void setBaseOtherCharge(BigDecimal baseOtherCharge) {
        this.baseOtherCharge = baseOtherCharge;
    }

    public String getBaseOtherchargeDetail() {
        return baseOtherchargeDetail;
    }

    public void setBaseOtherchargeDetail(String baseOtherchargeDetail) {
        this.baseOtherchargeDetail = baseOtherchargeDetail == null ? null : baseOtherchargeDetail.trim();
    }

    public BigDecimal getCustomerOtherCharge() {
        return customerOtherCharge;
    }

    public void setCustomerOtherCharge(BigDecimal customerOtherCharge) {
        this.customerOtherCharge = customerOtherCharge;
    }

    public String getCustomerOtherChargeDetail() {
        return customerOtherChargeDetail;
    }

    public void setCustomerOtherChargeDetail(String customerOtherChargeDetail) {
        this.customerOtherChargeDetail = customerOtherChargeDetail == null ? null : customerOtherChargeDetail.trim();
    }

    public BigDecimal getBaseInsuranceCharge() {
        return baseInsuranceCharge;
    }

    public void setBaseInsuranceCharge(BigDecimal baseInsuranceCharge) {
        this.baseInsuranceCharge = baseInsuranceCharge;
    }

    public BigDecimal getCustomerInsuranceCharge() {
        return customerInsuranceCharge;
    }

    public void setCustomerInsuranceCharge(BigDecimal customerInsuranceCharge) {
        this.customerInsuranceCharge = customerInsuranceCharge;
    }

    public BigDecimal getBaseIns3rdCharge() {
        return baseIns3rdCharge;
    }

    public void setBaseIns3rdCharge(BigDecimal baseIns3rdCharge) {
        this.baseIns3rdCharge = baseIns3rdCharge;
    }

    public BigDecimal getCustomerIns3rdCharge() {
        return customerIns3rdCharge;
    }

    public void setCustomerIns3rdCharge(BigDecimal customerIns3rdCharge) {
        this.customerIns3rdCharge = customerIns3rdCharge;
    }

    public String getAdjusted() {
        return adjusted;
    }

    public void setAdjusted(String adjusted) {
        this.adjusted = adjusted == null ? null : adjusted.trim();
    }
}