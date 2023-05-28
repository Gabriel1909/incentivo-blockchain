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
@Table(name = "admissions", schema = "mimiciv_hosp")
@Getter
@Setter
@NoArgsConstructor
public class Admissions {

    @Id
    @Column(name = "hadm_id", nullable = false)
    private Integer hadmId;

    @Column(name = "subject_id", nullable = false)
    private Integer subjectId;

    @Column(name = "admittime", nullable = false)
    private Timestamp admittime;

    @Column(name = "dischtime")
    private Timestamp dischtime;

    @Column(name = "deathtime")
    private Timestamp deathtime;

    @Column(name = "admission_type", nullable = false)
    private String admissionType;

    @Column(name = "admit_provider_id", length = 10)
    private String admitProviderId;

    @Column(name = "admission_location", length = 60)
    private String admissionLocation;

    @Column(name = "discharge_location", length = 60)
    private String dischargeLocation;

    @Column(name = "insurance")
    private String insurance;

    @Column(name = "language", length = 10)
    private String language;

    @Column(name = "marital_status", length = 30)
    private String maritalStatus;

    @Column(name = "race", length = 80)
    private String race;

    @Column(name = "edregtime")
    private Timestamp edregtime;

    @Column(name = "edouttime")
    private Timestamp edouttime;

    @Column(name = "hospital_expire_flag")
    private Short hospitalExpireFlag;
}