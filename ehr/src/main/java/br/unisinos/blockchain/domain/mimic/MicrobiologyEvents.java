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
@Table(name = "microbiologyevents", schema = "mimiciv_hosp")
@Getter
@Setter
@NoArgsConstructor
public class MicrobiologyEvents {

    @Id
    @Column(name = "microevent_id", nullable = false)
    private Integer microeventId;

    @Column(name = "subject_id", nullable = false)
    private Integer subjectId;

    @Column(name = "hadm_id")
    private Integer hadmId;

    @Column(name = "micro_specimen_id", nullable = false)
    private Integer microSpecimenId;

    @Column(name = "order_provider_id", length = 10)
    private String orderProviderId;

    @Column(name = "chartdate", nullable = false)
    private Timestamp chartDate;

    @Column(name = "charttime")
    private Timestamp chartTime;

    @Column(name = "spec_itemid", nullable = false)
    private Integer specItemId;

    @Column(name = "spec_type_desc", nullable = false, length = 100)
    private String specTypeDesc;

    @Column(name = "test_seq", nullable = false)
    private Integer testSeq;

    @Column(name = "storedate")
    private Timestamp storeDate;

    @Column(name = "storetime")
    private Timestamp storeTime;

    @Column(name = "test_itemid")
    private Integer testItemId;

    @Column(name = "test_name", length = 100)
    private String testName;

    @Column(name = "org_itemid")
    private Integer orgItemId;

    @Column(name = "org_name", length = 100)
    private String orgName;

    @Column(name = "isolate_num")
    private Short isolateNum;

    @Column(name = "quantity", length = 50)
    private String quantity;

    @Column(name = "ab_itemid")
    private Integer abItemId;

    @Column(name = "ab_name", length = 30)
    private String abName;

    @Column(name = "dilution_text", length = 10)
    private String dilutionText;

    @Column(name = "dilution_comparison", length = 20)
    private String dilutionComparison;

    @Column(name = "dilution_value")
    private Double dilutionValue;

    @Column(name = "interpretation", length = 5)
    private String interpretation;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;
}