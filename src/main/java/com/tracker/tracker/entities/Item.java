package com.tracker.tracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Уникальный идентификатор продукта
    private Long id;

    @Column(name = "uuid")
    @NotBlank(message = "The uuid can't be empty", groups = Create.class)
    private String uuid;

    @Column(name = "name")
    @NotBlank(message = "The item name can't be empty",
            groups = {Create.class, Update.class})
    @Size(min = 2, max = 255, message = "The item name should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})// Наименование продукта
    private String name;

    @Column(name = "article")
    @NotBlank(message = "The article can't be empty",
            groups = {Create.class, Update.class})
    @Size(min = 2, max = 100, message = "The article can't be longer than 100 characters",
            groups = {Create.class, Update.class})// Артикул продукта
    private String article;

    @Column(name = "brand")
    @NotBlank(message = "The brand can't be empty",
            groups = {Create.class, Update.class})
    @Size(min = 2, max = 100, message = "The brand should be between 2 and 100 symbols",
            groups = {Create.class, Update.class})// Бренд продукта
    private String brand;

    @Column(name = "barcode")
    // Штрихкод продукта
    private String barcode;

    @Column(name = "color")
    @NotBlank(message = "The color can't be empty", groups = {Create.class, Update.class})
    @Size(max = 50, message = "The color should be between 0 and 50 symbols",
            groups = {Create.class, Update.class})// Цвет продукта
    private String color;

    @Column(name = "size")
    @NotBlank(message = "The size can't be empty", groups = {Create.class, Update.class})
    @Size(min = 1, max = 10, message = "The size should be between 1 and 10 symbols",
            groups = {Create.class, Update.class})// Размер продукта
    private String size;

    @Column(name = "composition")
    @NotBlank(message = "The composition can't be empty",
            groups = {Create.class, Update.class})
    @Size(min = 2, max = 255, message = "The composition should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})// Состав продукта
    private String composition;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @NotNull(message = "The company can't be empty", groups = Create.class)// Производитель продукта
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "The supplier can't be empty", groups = Create.class)// Поставщик продукта
    private User supplier;

    @Column(name = "quantity")
    @Min(value = 0, message = "The quantity of the product cannot be negative",
            groups = {Create.class, Update.class})
    @NotNull(message = "The quantity can't be empty",
            groups = {Create.class, Update.class})// Количество продукта на складе
    private Integer quantity;

    @Column(name = "labels_per_product")
    @Min(value = 0, message = "The number of labels cannot be negative",
            groups = {Create.class, Update.class})
    @NotNull(message = "The number of labels cannot be empty",
            groups = {Create.class, Update.class})// Количество этикеток на каждый продукт
    private Integer labelsPerProduct;

    @Column(name = "additional_text") // Дополнительный текст (например, благодарность клиенту)
    private String additionalText;

    @Column(name = "photo_url") // Ссылка на фото товара
    private String photoUrl;

    @Column(name = "packaging_type")
    @NotBlank(message = "The packagingType can't be empty",
            groups = {Create.class, Update.class})
    @Size(min = 2, max = 100, message = "The packagingType should be between 2 and 100 symbols",
            groups = {Create.class, Update.class})// Тип упаковки
    private String packagingType;

    @Column(name = "assembly_needed")
    @Size(min = 2, max = 255, message = "The assemblyNeeded should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})// Нужна ли комплектация, если да, то какая
    private String assemblyNeeded;

    @Column(name = "quality_check_needed")
    @Size(min = 2, max = 255, message = "The qualityCheckNeeded should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})// Нужна ли проверка на брак, если да, то что считается браком
    private String qualityCheckNeeded;

    @Column(name = "tag_change")
    @NotBlank(message = "The tagChange can't be empty",
            groups = {Create.class, Update.class})
    @Size(min = 2, max = 255, message = "The tagChange should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})// Информация о замене бирок (указать: наши или вы предоставляете)
    private String tagChange;

    @Column(name = "cut_tag")
    @NotBlank(message = "The cutTag information can't be empty",
            groups = {Create.class, Update.class})
    @Size(max = 10, message = "The cutTag should be between 0 and 10 symbols",
            groups = {Create.class, Update.class})// Отрезаем ли бирку пришитую на одежде
    private String cutTag;

    @Column(name = "price_per_unit")
    @NotNull(message = "The pricePerUnit can't be empty",
            groups = {Create.class, Update.class})
    @DecimalMin(value = "0.01", message = "The price per piece must be greater than 0",
            groups = {Create.class, Update.class})
    @Digits(integer = 10, fraction = 2, message = "The price must be a valid monetary value with no more" +
            " than 10 digits before and 2 after the decimal point",
            groups = {Create.class, Update.class})
    private BigDecimal pricePerUnit; // Цена за штуку

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Item setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getArticle() {
        return article;
    }

    public Item setArticle(String article) {
        this.article = article;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Item setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getBarcode() {
        return barcode;
    }

    public Item setBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Item setColor(String color) {
        this.color = color;
        return this;
    }

    public String getSize() {
        return size;
    }

    public Item setSize(String size) {
        this.size = size;
        return this;
    }

    public String getComposition() {
        return composition;
    }

    public Item setComposition(String composition) {
        this.composition = composition;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public Item setCompany(Company manufacturer) {
        this.company = manufacturer;
        return this;
    }

    public User getSupplier() {
        return supplier;
    }

    public Item setSupplier(User supplier) {
        this.supplier = supplier;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getLabelsPerProduct() {
        return labelsPerProduct;
    }

    public Item setLabelsPerProduct(Integer labelsPerProduct) {
        this.labelsPerProduct = labelsPerProduct;
        return this;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public Item setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Item setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public Item setPackagingType(String packagingType) {
        this.packagingType = packagingType;
        return this;
    }

    public String getAssemblyNeeded() {
        return assemblyNeeded;
    }

    public Item setAssemblyNeeded(String assemblyNeeded) {
        this.assemblyNeeded = assemblyNeeded;
        return this;
    }

    public String getQualityCheckNeeded() {
        return qualityCheckNeeded;
    }

    public Item setQualityCheckNeeded(String qualityCheckNeeded) {
        this.qualityCheckNeeded = qualityCheckNeeded;
        return this;
    }

    public String getTagChange() {
        return tagChange;
    }

    public Item setTagChange(String tagChange) {
        this.tagChange = tagChange;
        return this;
    }

    public String getCutTag() {
        return cutTag;
    }

    public Item setCutTag(String cutTag) {
        this.cutTag = cutTag;
        return this;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public Item setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public interface Update {
    }

    public interface Create {
    }
}
