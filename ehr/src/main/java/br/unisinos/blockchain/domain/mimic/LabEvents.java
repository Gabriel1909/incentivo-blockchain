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
@Table(name = "labevents", schema = "mimiciv_hosp")
@Getter
@Setter
@NoArgsConstructor
public class LabEvents {

    @Id
    @Column(name = "labevent_id", nullable = false)
    private Integer labEventId;

    @Column(name = "subject_id", nullable = false)
    private Integer subjectId;

    @Column(name = "hadm_id")
    private Integer hadmId;

    @Column(name = "specimen_id", nullable = false)
    private Integer specimenId;

    @Column(name = "itemid", nullable = false)
    private Integer itemId;

    @Column(name = "order_provider_id", length = 10)
    private String orderProviderId;

    @Column(name = "charttime")
    private Timestamp chartTime;

    @Column(name = "storetime")
    private Timestamp storeTime;

    @Column(name = "value", length = 200)
    private String value;

    @Column(name = "valuenum")
    private Double valueNum;

    @Column(name = "valueuom", length = 20)
    private String valueUom;

    @Column(name = "ref_range_lower")
    private Double refRangeLower;

    @Column(name = "ref_range_upper")
    private Double refRangeUpper;

    @Column(name = "flag", length = 10)
    private String flag;

    @Column(name = "priority", length = 7)
    private String priority;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;
}