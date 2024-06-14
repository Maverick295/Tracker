package com.tracker.tracker.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String uuid;
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

    public Company getCompany() {
        return company;
    }

    public Payment setCompany(Company company) {
        this.company = company;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Payment setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Payment setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getInn() {
        return inn;
    }

    public Payment setInn(String inn) {
        this.inn = inn;
        return this;
    }

    public String getKpp() {
        return kpp;
    }

    public Payment setKpp(String kpp) {
        this.kpp = kpp;
        return this;
    }

    public String getOgrn() {
        return ogrn;
    }

    public Payment setOgrn(String ogrn) {
        this.ogrn = ogrn;
        return this;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public Payment setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public Payment setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getBankBik() {
        return bankBik;
    }

    public Payment setBankBik(String bankBik) {
        this.bankBik = bankBik;
        return this;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public Payment setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
        return this;
    }

    public String getBankInn() {
        return bankInn;
    }

    public Payment setBankInn(String bankInn) {
        this.bankInn = bankInn;
        return this;
    }

    public String getBankKpp() {
        return bankKpp;
    }

    public Payment setBankKpp(String bankKpp) {
        this.bankKpp = bankKpp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;

        return Objects.equals(company, payment.company) &&
            Objects.equals(uuid, payment.uuid) &&
            Objects.equals(companyName, payment.companyName) &&
            Objects.equals(inn, payment.inn) &&
            Objects.equals(kpp, payment.kpp) &&
            Objects.equals(ogrn, payment.ogrn) &&
            Objects.equals(paymentAccount, payment.paymentAccount) &&
            Objects.equals(bankName, payment.bankName) &&
            Objects.equals(bankBik, payment.bankBik) &&
            Objects.equals(correspondentAccount, payment.correspondentAccount) &&
            Objects.equals(bankInn, payment.bankInn) &&
            Objects.equals(bankKpp, payment.bankKpp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            company,
            uuid,
            companyName,
            inn,
            kpp,
            ogrn,
            paymentAccount,
            bankName,
            bankBik,
            correspondentAccount,
            bankInn,
            bankKpp
        );
    }
}
