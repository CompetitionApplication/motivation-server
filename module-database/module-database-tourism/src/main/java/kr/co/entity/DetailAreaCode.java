package kr.co.entity;

import jakarta.persistence.*;
import kr.co.config.BooleanConverter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailAreaCode {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    private String detailAreaCodeId;
    private String code;
    private String name;
    private String country;
    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean delYn;
    @ManyToOne
    @JoinColumn(name = "areaCode_id")
    private AreaCode areaCode;

    public void delete() {
        this.delYn = true;
    }

}
