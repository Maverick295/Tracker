package com.tracker.tracker.dto.payment;

public class PaymentDTO {
    private String companyName;
    private String inn;
    private String kpp;
    private String ogrn;
    private String paymentAccount;
    private String bankName;
    private String bankBik;
    private String correspondentAccount;
    private String bankInn;
    private String bankKpp;

    public String getCompanyName() {
        return companyName;
    }

    public PaymentDTO setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getInn() {
        return inn;
    }

    public PaymentDTO setInn(String inn) {
        this.inn = inn;
        return this;
    }

    public String getKpp() {
        return kpp;
    }

    public PaymentDTO setKpp(String kpp) {
        this.kpp = kpp;
        return this;
    }

    public String getOgrn() {
        return ogrn;
    }

    public PaymentDTO setOgrn(String ogrn) {
        this.ogrn = ogrn;
        return this;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public PaymentDTO setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public PaymentDTO setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getBankBik() {
        return bankBik;
    }

    public PaymentDTO setBankBik(String bankBik) {
        this.bankBik = bankBik;
        return this;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public PaymentDTO setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
        return this;
    }

    public String getBankInn() {
        return bankInn;
    }

    public PaymentDTO setBankInn(String bankInn) {
        this.bankInn = bankInn;
        return this;
    }

    public String getBankKpp() {
        return bankKpp;
    }

    public PaymentDTO setBankKpp(String bankKpp) {
        this.bankKpp = bankKpp;
        return this;
    }
}
