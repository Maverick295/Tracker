package com.tracker.tracker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;
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

    private String legalAddress; // Юридический адрес компании
    private String actualAddress; // Фактический адрес компании
    private String email; // Электронная почта компании
    private String phoneNumber; // Номер телефона компании
    private String directorFullName; // ФИО директора компании
    private String directorPosition; // Должность директора компании
    private String mainActivity; // Основной вид деятельности компании
    private String companyStatus; // Статус компании (активная, приостановленная и т. д.)
    private String ogrn; // ОГРН компании (если применимо)
    private String okpo; // ОКПО компании (если применимо)
}
