package kr.co.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BadgeCode {
    @Id
    private String badgeCode;

    private String badgeCodeType;

    @OneToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;
}
