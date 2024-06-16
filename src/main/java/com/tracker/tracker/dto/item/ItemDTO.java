package com.tracker.tracker.dto.item;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ItemDTO {
    // TODO: Надо добавить аннтоации для валидидирования, когда будет известно какие поля обязательны
    private String uuid;
    // Наименование продукта
    @NotBlank(message = "Наименование продукта не может быть пустым")
    @Size(min = 2, max = 255, message = "name should be between 2 and 50 symbols")
    private String name;
    // Артикул продукта
    @NotBlank(message = "Артикул продукта не может быть пустым")
    @Size(min = 2, max = 100, message = "Артикул продукта не может быть длиннее 100 символов")
    private String article;
    // Бренд продукта
    private String brand;
    // Штрихкод продукта
    private String barcode;
    // Цвет продукта
    private String color;
    // Размер продукта
    private String size;
    // Состав продукта
    private String composition;
    // Производитель продукта
    private String company;
    // Количество продукта на складе
    @Min(value = 0, message = "Количество товара не может быть отрицательным")
    private Integer quantity;
    // Количество этикеток на каждый продукт
    private Integer labelsPerProduct;
    // Дополнительный текст (например, благодарность клиенту)
    private String additionalText;
    // Ссылка на фото товара
    private String photoUrl;
    // Тип упаковки
    private String packagingType;
    // Нужна ли комплектация, если да, то какая
    private String assemblyNeeded;
    // Нужна ли проверка на брак, если да, то что считается браком
    private String qualityCheckNeeded;
    // Информация о замене бирок (указать: наши или вы предоставляете)
    private String tagChange;
    // Отрезаем ли бирку пришитую на одежде
    private String cutTag;

    @NotNull(message = "Цена за штуку не может быть пустой")
    @DecimalMin(value = "0.01", message = "Цена за штуку должна быть больше 0")
    @Digits(integer = 10, fraction = 2, message = "Цена должна быть валидным денежным значением с не более чем 10 цифрами до и 2 после запятой")
    private BigDecimal pricePerUnit;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLabelsPerProduct() {
        return labelsPerProduct;
    }

    public void setLabelsPerProduct(Integer labelsPerProduct) {
        this.labelsPerProduct = labelsPerProduct;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public String getAssemblyNeeded() {
        return assemblyNeeded;
    }

    public void setAssemblyNeeded(String assemblyNeeded) {
        this.assemblyNeeded = assemblyNeeded;
    }

    public String getQualityCheckNeeded() {
        return qualityCheckNeeded;
    }

    public void setQualityCheckNeeded(String qualityCheckNeeded) {
        this.qualityCheckNeeded = qualityCheckNeeded;
    }

    public String getTagChange() {
        return tagChange;
    }

    public void setTagChange(String tagChange) {
        this.tagChange = tagChange;
    }

    public String getCutTag() {
        return cutTag;
    }

    public void setCutTag(String cutTag) {
        this.cutTag = cutTag;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
