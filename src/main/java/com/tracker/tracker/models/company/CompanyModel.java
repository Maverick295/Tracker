package com.tracker.tracker.models.company;

public class CompanyModel {
    private String companyName; // Название компании
    private String legalEntity; // Юридическое лицо: ИП, ООО и т.д.
    private String inn; // ИНН компании, уникальный
    private String bankAccount; // Расчетный счет компании
    private String bankBik; // БИК банка, в котором открыт расчетный счет
    private String legalAddress; // Юридический адрес компании
    private String actualAddress; // Фактический адрес компании
    private String email; // Электронная почта компании
    private String phoneNumber; // Номер телефона компании
    private String ogrn; // ОГРН компании (если применимо)
    private String okpo; // ОКПО компании (если применимо)
    private String Uuid;
    private String ogrnip;
    private String bankName;
    private String correspondentAccount;
    private String directorName;
    private String directorSurname;
    private String directorPatronymic;
    private String kpp;
    private String bankInn;
    private String bankKpp;

    public String getCompanyName() {
        return companyName;
    }

    public CompanyModel setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public CompanyModel setLegalEntity(String legalEntity) {
        this.legalEntity = legalEntity;
        return this;
    }

    public String getInn() {
        return inn;
    }

    public CompanyModel setInn(String inn) {
        this.inn = inn;
        return this;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public CompanyModel setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public String getBankBik() {
        return bankBik;
    }

    public CompanyModel setBankBik(String bankBik) {
        this.bankBik = bankBik;
        return this;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public CompanyModel setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
        return this;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public CompanyModel setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CompanyModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CompanyModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getOgrn() {
        return ogrn;
    }

    public CompanyModel setOgrn(String ogrn) {
        this.ogrn = ogrn;
        return this;
    }

    public String getOkpo() {
        return okpo;
    }

    public CompanyModel setOkpo(String okpo) {
        this.okpo = okpo;
        return this;
    }

    public String getUuid() {
        return Uuid;
    }

    public CompanyModel setUuid(String uuid) {
        Uuid = uuid;
        return this;
    }

    public String getOgrnip() {
        return ogrnip;
    }

    public CompanyModel setOgrnip(String ogrnip) {
        this.ogrnip = ogrnip;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public CompanyModel setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public CompanyModel setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
        return this;
    }

    public String getDirectorName() {
        return directorName;
    }

    public CompanyModel setDirectorName(String directorName) {
        this.directorName = directorName;
        return this;
    }

    public String getDirectorSurname() {
        return directorSurname;
    }

    public CompanyModel setDirectorSurname(String directorSurname) {
        this.directorSurname = directorSurname;
        return this;
    }

    public String getDirectorPatronymic() {
        return directorPatronymic;
    }

    public CompanyModel setDirectorPatronymic(String directorPatronymic) {
        this.directorPatronymic = directorPatronymic;
        return this;
    }

    public String getKpp() {
        return kpp;
    }

    public CompanyModel setKpp(String kpp) {
        this.kpp = kpp;
        return this;
    }

    public String getBankInn() {
        return bankInn;
    }

    public CompanyModel setBankInn(String bankInn) {
        this.bankInn = bankInn;
        return this;
    }

    public String getBankKpp() {
        return bankKpp;
    }

    public CompanyModel setBankKpp(String bankKpp) {
        this.bankKpp = bankKpp;
        return this;
    }
}
