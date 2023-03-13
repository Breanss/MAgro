package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cropId;

    private String variety;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "FIELD_CROP",
            joinColumns = {
                    @JoinColumn(name = "CROP_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "FIELD_ID")
            }
    )
    private Field field;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "SEASON_CROP",
            joinColumns = {
                    @JoinColumn(name = "CROP_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "SEASON_ID")
            }
    )
    private Season season;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "TYPECROP_CROP",
            joinColumns = {
                    @JoinColumn(name = "CROP_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "TYPE_ID")
            }
    )
    private TypeCrop typeCrop;

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public TypeCrop getTypeCrop() {
        return typeCrop;
    }

    public void setTypeCrop(TypeCrop typeCrop) {
        this.typeCrop = typeCrop;
    }
}
