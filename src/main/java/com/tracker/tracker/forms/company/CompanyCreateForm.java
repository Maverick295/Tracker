package com.tracker.tracker.forms.company;

public class CompanyCreateForm {
    private String companyName; // Название компании
    private String legalEntity; // Юридическое лицо
    private String inn; // ИНН компании
    private String bankAccount; // Расчетный счет
    private String bankBik; // БИК банка
    private String legalAddress; // Юридический адрес
    private String actualAddress; // Фактический адрес
    private String email; // Электронная почта
    private String phoneNumber; // Номер телефона
    private String directorFullName; // ФИО директора
    private String ogrn; // ОГРН компании
    private String okpo; // ОКПО компании
    private String ogrnip;
    private String bankName;
    private String correspondentAccount;
    private String directorName;
    private String directorSurname;
    private String directorPatronymic;
    private String kpp;
    private String bankInn;
    private String bankKpp;

    // Геттеры и сеттеры для полей
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(String legalEntity) {
        this.legalEntity = legalEntity;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankBik() {
        return bankBik;
    }

    public void setBankBik(String bankBik) {
        this.bankBik = bankBik;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDirectorFullName() {
        return directorFullName;
    }

    public void setDirectorFullName(String directorFullName) {
        this.directorFullName = directorFullName;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getOgrnip() {
        return ogrnip;
    }

    public CompanyCreateForm setOgrnip(String ogrnip) {
        this.ogrnip = ogrnip;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public CompanyCreateForm setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public CompanyCreateForm setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
        return this;
    }

    public String getDirectorName() {
        return directorName;
    }

    public CompanyCreateForm setDirectorName(String directorName) {
        this.directorName = directorName;
        return this;
    }

    public String getDirectorSurname() {
        return directorSurname;
    }

    public CompanyCreateForm setDirectorSurname(String directorSurname) {
        this.directorSurname = directorSurname;
        return this;
    }

    public String getDirectorPatronymic() {
        return directorPatronymic;
    }

    public CompanyCreateForm setDirectorPatronymic(String directorPatronymic) {
        this.directorPatronymic = directorPatronymic;
        return this;
    }

    public String getKpp() {
        return kpp;
    }

    public CompanyCreateForm setKpp(String kpp) {
        this.kpp = kpp;
        return this;
    }

    public String getBankInn() {
        return bankInn;
    }

    public CompanyCreateForm setBankInn(String bankInn) {
        this.bankInn = bankInn;
        return this;
    }

    public String getBankKpp() {
        return bankKpp;
    }

    public CompanyCreateForm setBankKpp(String bankKpp) {
        this.bankKpp = bankKpp;
        return this;
    }
}
