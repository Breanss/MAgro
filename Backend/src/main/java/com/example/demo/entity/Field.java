package com.example.demo.entity;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;
    private String fieldWojewodztwo;
    private String fieldGmina;
    private String fieldMiejscowosc;
    private String fieldNumber;
    private String fieldNumberRegistration;
    private String fieldName;
    private BigDecimal fieldArea;
    private String fieldArgonomicCategory;
    private String fieldProperty;
    private Boolean fieldActive = true;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "USER_FIELD",
            joinColumns = {
                    @JoinColumn(name = "FIELD_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "USER_ID")
            }
    )
    private User user;


    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldNumberRegistration() {
        return fieldNumberRegistration;
    }

    public void setFieldNumberRegistration(String fieldNumberRegistration) {
        this.fieldNumberRegistration = fieldNumberRegistration;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldNameField(String fieldNameField) {
        this.fieldName = fieldNameField;
    }

    public BigDecimal getFieldArea() {
        return fieldArea;
    }

    public void setFieldArea(BigDecimal fieldArea) {
        this.fieldArea = fieldArea;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldArgonomicCategory() {
        return fieldArgonomicCategory;
    }

    public void setFieldArgonomicCategory(String fieldArgonomicCategory) {
        this.fieldArgonomicCategory = fieldArgonomicCategory;
    }

    public String getFieldProperty() {
        return fieldProperty;
    }

    public void setFieldProperty(String fieldProperty) {
        this.fieldProperty = fieldProperty;
    }

    public Boolean getFieldActive() {
        return fieldActive;
    }

    public void setFieldActive(Boolean fieldActive) {
        this.fieldActive = fieldActive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFieldWojewodztwo() {
        return fieldWojewodztwo;
    }

    public void setFieldWojewodztwo(String fieldWojewodztwo) {
        this.fieldWojewodztwo = fieldWojewodztwo;
    }

    public String getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(String fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public String getFieldGmina() {
        return fieldGmina;
    }

    public void setFieldGmina(String fieldGmina) {
        this.fieldGmina = fieldGmina;
    }

    public String getFieldMiejscowosc() {
        return fieldMiejscowosc;
    }

    public void setFieldMiejscowosc(String fieldMiejscowosc) {
        this.fieldMiejscowosc = fieldMiejscowosc;
    }
}
