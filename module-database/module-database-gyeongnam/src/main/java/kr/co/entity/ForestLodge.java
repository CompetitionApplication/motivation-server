package kr.co.entity;

import jakarta.persistence.*;
import kr.co.config.BooleanToYNConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ForestLodge {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "산림욕장")
    private String forestLodgeId;
    @Comment(value = "휴양림명")
    private String lodgeName;
    @Comment(value = "시도명")
    @Column(length = 50)
    private String province;
    @Comment(value = "휴양림구분")
    @Column(length = 50)
    private String lodgeType;
    @Comment(value = "휴양림면적")
    @Column(precision = 10, scale = 2)
    private BigDecimal area;
    @Comment(value = "수용인원수")
    private int capacity;
    @Comment(value = "입장료")
    @Column(length = 50)
    private String entryFee;
    @Comment(value = "숙박여부")
    @Column(columnDefinition = "VARCHAR(1) default 'N'")
    @Convert(converter = BooleanToYNConverter.class)
    private boolean accommodation;
    @Comment(value = "주요시설명")
    @Column(length = 50)
    private String mainFacilities;
    @Comment(value = "관리기관명")
    private String managingAgency;
    @Comment(value = "휴양림전화번호")
    @Column(length = 50)
    private String lodgePhone;
    @OneToOne
    @JoinColumn(name = "place_info_id")
    private PlaceInfo placeInfo;
}
