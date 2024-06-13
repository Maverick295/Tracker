package com.tracker.tracker.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String companyName; // Название компании

    @Column(nullable = false)
    private String legalEntity; // Юридическое лицо: ИП, ООО и т.д.

    @Column(nullable = false, unique = true)
    private String inn; // ИНН компании, уникальный

    @Column(nullable = false)
    private String bankAccount; // Расчетный счет компании

    @Column(nullable = false)
    private String bankBik; // БИК банка, в котором открыт расчетный счет

    @Column(unique = true)
    private String uuid;

    @Column(nullable = false)
    private String ogrnip;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String correspondentAccount;
    private String legalAddress; // Юридический адрес компании
    private String actualAddress; // Фактический адрес компании
    private String email; // Электронная почта компании
    private String phoneNumber; // Номер телефона компании
    private String ogrn; // ОГРН компании (если применимо)
    private String okpo; // ОКПО компании (если применимо)
    private LocalDate dateOfCreate; // Дата создания на сайте
    private String directorName;
    private String directorSurname;
    private String directorPatronymic;
    private String kpp;
    private String bankInn;
    private String bankKpp;

    public User getUser() {
        return user;
    }

    public Company setUser(User user) {
        this.user = user;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Company setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public Company setLegalEntity(String legalEntity) {
        this.legalEntity = legalEntity;
        return this;
    }

    public String getInn() {
        return inn;
    }

    public Company setInn(String inn) {
        this.inn = inn;
        return this;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public Company setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public String getBankBik() {
        return bankBik;
    }

    public Company setBankBik(String bankBik) {
        this.bankBik = bankBik;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Company setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getOgrnip() {
        return ogrnip;
    }

    public Company setOgrnip(String ogrnip) {
        this.ogrnip = ogrnip;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public Company setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public Company setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
        return this;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public Company setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
        return this;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public Company setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Company setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Company setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getOgrn() {
        return ogrn;
    }

    public Company setOgrn(String ogrn) {
        this.ogrn = ogrn;
        return this;
    }

    public String getOkpo() {
        return okpo;
    }

    public Company setOkpo(String okpo) {
        this.okpo = okpo;
        return this;
    }

    public LocalDate getDateOfCreate() {
        return dateOfCreate;
    }

    public Company setDateOfCreate(LocalDate dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
        return this;
    }

    public String getDirectorName() {
        return directorName;
    }

    public Company setDirectorName(String directorName) {
        this.directorName = directorName;
        return this;
    }

    public String getDirectorSurname() {
        return directorSurname;
    }

    public Company setDirectorSurname(String directorSurname) {
        this.directorSurname = directorSurname;
        return this;
    }

    public String getDirectorPatronymic() {
        return directorPatronymic;
    }

    public Company setDirectorPatronymic(String directorPatronymic) {
        this.directorPatronymic = directorPatronymic;
        return this;
    }

    public String getKpp() {
        return kpp;
    }

    public Company setKpp(String kpp) {
        this.kpp = kpp;
        return this;
    }

    public String getBankInn() {
        return bankInn;
    }

    public Company setBankInn(String bankInn) {
        this.bankInn = bankInn;
        return this;
    }

    public String getBankKpp() {
        return bankKpp;
    }

    public Company setBankKpp(String bankKpp) {
        this.bankKpp = bankKpp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(user, company.user) &&
            Objects.equals(companyName, company.companyName) &&
            Objects.equals(legalEntity, company.legalEntity) &&
            Objects.equals(inn, company.inn) &&
            Objects.equals(bankAccount, company.bankAccount) &&
            Objects.equals(bankBik, company.bankBik) &&
            Objects.equals(uuid, company.uuid) &&
            Objects.equals(ogrnip, company.ogrnip) &&
            Objects.equals(bankName, company.bankName) &&
            Objects.equals(correspondentAccount, company.correspondentAccount) &&
            Objects.equals(legalAddress, company.legalAddress) &&
            Objects.equals(actualAddress, company.actualAddress) &&
            Objects.equals(email, company.email) &&
            Objects.equals(phoneNumber, company.phoneNumber) &&
            Objects.equals(ogrn, company.ogrn) &&
            Objects.equals(okpo, company.okpo) &&
            Objects.equals(dateOfCreate, company.dateOfCreate) &&
            Objects.equals(directorName, company.directorName) &&
            Objects.equals(directorSurname, company.directorSurname) &&
            Objects.equals(directorPatronymic, company.directorPatronymic) &&
            Objects.equals(kpp, company.kpp) && Objects.equals(bankInn, company.bankInn) &&
            Objects.equals(bankKpp, company.bankKpp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            user,
            companyName,
            legalEntity,
            inn,
            bankAccount,
            bankBik,
            uuid,
            ogrnip,
            bankName,
            correspondentAccount,
            legalAddress,
            actualAddress,
            email,
            phoneNumber,
            ogrn,
            okpo,
            dateOfCreate,
            directorName,
            directorSurname,
            directorPatronymic,
            kpp,
            bankInn,
            bankKpp
        );
    }
}
