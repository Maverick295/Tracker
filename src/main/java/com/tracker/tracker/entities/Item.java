package com.tracker.tracker.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {

    // TODO: Надо добавить аннтоации для валидидирования,
    //  когда будет известно какие поля обязательны
    //  + добавить в разные группы для валидирования
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Уникальный идентификатор продукта
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name") // Наименование продукта
    private String name;

    @Column(name = "article") // Артикул продукта
    private String article;

    @Column(name = "brand") // Бренд продукта
    private String brand;

    @Column(name = "barcode") // Штрихкод продукта
    private String barcode;

    @Column(name = "color") // Цвет продукта
    private String color;

    @Column(name = "size") // Размер продукта
    private String size;

    @Column(name = "composition") // Состав продукта
    private String composition;

    @ManyToOne
    @JoinColumn(name = "company_id")// Производитель продукта
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")// Поставщик продукта
    private User supplier;

    @Column(name = "quantity") // Количество продукта на складе
    private Integer quantity;

    @Column(name = "labels_per_product") // Количество этикеток на каждый продукт
    private Integer labelsPerProduct;

    @Column(name = "additional_text") // Дополнительный текст (например, благодарность клиенту)
    private String additionalText;

    @Column(name = "photo_url") // Ссылка на фото товара
    private String photoUrl;

    @Column(name = "packaging_type") // Тип упаковки
    private String packagingType;

    @Column(name = "assembly_needed") // Нужна ли комплектация, если да, то какая
    private String assemblyNeeded;

    @Column(name = "quality_check_needed") // Нужна ли проверка на брак, если да, то что считается браком
    private String qualityCheckNeeded;

    @Column(name = "tag_change") // Информация о замене бирок (указать: наши или вы предоставляете)
    private String tagChange;

    @Column(name = "cut_tag") // Отрезаем ли бирку пришитую на одежде
    private String cutTag;

    @Column(name = "price_per_unit")
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
