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
    private String directorFullName; // ФИО директора компании
    private String ogrn; // ОГРН компании (если применимо)
    private String okpo; // ОКПО компании (если применимо)

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

    public String getDirectorFullName() {
        return directorFullName;
    }

    public CompanyModel setDirectorFullName(String directorFullName) {
        this.directorFullName = directorFullName;
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
}
