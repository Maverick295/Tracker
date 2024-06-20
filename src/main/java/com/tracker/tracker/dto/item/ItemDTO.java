package com.tracker.tracker.dto.item;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public class ItemDTO {

    @NotBlank(message = "The uuid can't be empty", groups = Create.class)
    private String uuid;
    // Наименование продукта
    @NotBlank(message = "The product name cannot be empty", groups = Create.class)
    @Size(min = 2, max = 255, message = "The name should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})
    private String name;
    // Артикул продукта
    @NotBlank(message = "The article can't be empty", groups = Create.class)
    @Size(min = 2, max = 100, message = "The article can't be longer than 100 characters",
            groups = {Create.class, Update.class})
    private String article;
    // Бренд продукта
    @NotBlank(message = "The brand can't be empty", groups = Create.class)
    @Size(min = 2, max = 100, message = "The brand should be between 2 and 100 symbols",
            groups = {Create.class, Update.class})
    private String brand;
    // Штрихкод продукта
    private String barcode;
    // Цвет продукта
    @NotBlank(message = "The color can't be empty", groups = Create.class)
    @Size(max = 50, message = "The color should be between 0 and 50 symbols",
            groups = {Create.class, Update.class})
    private String color;
    // Размер продукта
    @NotBlank(message = "The size can't be empty", groups = Create.class)
    @Size(min = 1, max = 10, message = "The color should be between 1 and 10 symbols",
            groups = {Create.class, Update.class})
    private String size;
    // Состав продукта
    @NotBlank(message = "The composition can't be empty", groups = Create.class)
    @Size(min = 2, max = 255, message = "The composition should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})
    private String composition;
    // Производитель продукта
    @NotBlank(message = "The company can't be empty", groups = Create.class)
    @Size(min = 2, max = 100, message = "The company should be between 2 and 100 symbols",
            groups = {Create.class, Update.class})
    private String company;
    // Количество продукта на складе
    @Min(value = 0, message = "The quantity of the product cannot be negative",
            groups = {Create.class, Update.class})
    private Integer quantity;
    // Количество этикеток на каждый продукт
    @Min(value = 0, message = "The number of labels cannot be negative",
            groups = {Create.class, Update.class})
    private Integer labelsPerProduct;
    // Дополнительный текст (например, благодарность клиенту)
    @Size(min = 2, max = 255, message = "The additionalText should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})
    private String additionalText;
    // Ссылка на фото товара
    @URL(groups = {Create.class, Update.class})
    private String photoUrl;
    // Тип упаковки
    @NotBlank(message = "The packagingType can't be empty", groups = Create.class)
    @Size(min = 2, max = 100, message = "The packagingType should be between 2 and 100 symbols",
            groups = {Create.class, Update.class})
    private String packagingType;
    // Нужна ли комплектация, если да, то какая
    @Size(min = 2, max = 255, message = "The assemblyNeeded should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})
    private String assemblyNeeded;
    // Нужна ли проверка на брак, если да, то что считается браком
    @Size(min = 2, max = 255, message = "The qualityCheckNeeded should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})
    private String qualityCheckNeeded;
    // Информация о замене бирок (указать: наши или вы предоставляете)
    @NotBlank(message = "The tagChange can't be empty", groups = Create.class)
    @Size(min = 2, max = 255, message = "The tagChange should be between 2 and 255 symbols",
            groups = {Create.class, Update.class})
    private String tagChange;
    // Отрезаем ли бирку пришитую на одежде
    @NotBlank(message = "The cutTag information can't be empty", groups = Create.class)
    @Size(max = 10, message = "The cutTag should be between 0 and 10 symbols",
            groups = {Create.class, Update.class})
    private String cutTag;

    @NotNull(message = "The pricePerUnit can't be empty", groups = Create.class)
    @DecimalMin(value = "0.01", message = "The price per piece must be greater than 0",
            groups = {Create.class, Update.class})
    @Digits(integer = 10, fraction = 2, message = "The price must be a valid monetary value with no more" +
            " than 10 digits before and 2 after the decimal point",
            groups = {Create.class, Update.class})
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

    public interface Create {
    }

    public interface Update {
    }
}
