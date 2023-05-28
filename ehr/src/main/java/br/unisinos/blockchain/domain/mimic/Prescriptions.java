package br.unisinos.blockchain.domain.mimic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "prescriptions", schema = "mimiciv_hosp")
@Getter
@Setter
@NoArgsConstructor
public class Prescriptions {

    @Id
    @Column(name = "hadm_id", nullable = false)
    private Integer hadmId;

    @Column(name = "subject_id", nullable = false)
    private Integer subjectId;

    @Column(name = "pharmacy_id", nullable = false)
    private Integer pharmacyId;

    @Column(name = "poe_id", length = 25)
    private String poeId;

    @Column(name = "poe_seq")
    private Integer poeSeq;

    @Column(name = "order_provider_id", length = 10)
    private String orderProviderId;

    @Column(name = "starttime", precision = 3)
    private Timestamp starttime;

    @Column(name = "stoptime", precision = 3)
    private Timestamp stoptime;

    @Column(name = "drug_type", nullable = false, length = 20)
    private String drugType;

    @Column(name = "drug", nullable = false)
    private String drug;

    @Column(name = "formulary_drug_cd", length = 50)
    private String formularyDrugCd;

    @Column(name = "gsn")
    private String gsn;

    @Column(name = "ndc", length = 25)
    private String ndc;

    @Column(name = "prod_strength")
    private String prodStrength;

    @Column(name = "form_rx", length = 25)
    private String formRx;

    @Column(name = "dose_val_rx", length = 100)
    private String doseValRx;

    @Column(name = "dose_unit_rx", length = 50)
    private String doseUnitRx;

    @Column(name = "form_val_disp", length = 50)
    private String formValDisp;

    @Column(name = "form_unit_disp", length = 50)
    private String formUnitDisp;

    @Column(name = "doses_per_24_hrs")
    private Double dosesPer24Hrs;

    @Column(name = "route", length = 50)
    private String route;
}