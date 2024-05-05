package com.tracker.tracker.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
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

    private String Uuid;
    private String legalAddress; // Юридический адрес компании
    private String actualAddress; // Фактический адрес компании
    private String email; // Электронная почта компании
    private String phoneNumber; // Номер телефона компании
    private String directorFullName; // ФИО директора компании
    private String ogrn; // ОГРН компании (если применимо)
    private String okpo; // ОКПО компании (если применимо)
    private LocalDateTime dateOfCreate; // Дата создания на сайте

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Company setCustomer(Customer customer) {
        this.customer = customer;
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

    public String getDirectorFullName() {
        return directorFullName;
    }

    public Company setDirectorFullName(String directorFullName) {
        this.directorFullName = directorFullName;
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

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public Company setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
        return this;
    }

    public String getUuid() {
        return Uuid;
    }

    public Company setUuid(String Uuid) {
        this.Uuid = Uuid;
        return this;
    }
}
