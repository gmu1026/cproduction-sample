package net.cproduction.test.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DIARY", indexes = @Index(name = "i_diary_title", columnList = "title"))
@NoArgsConstructor
@Getter
public class Diary extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long no;

    private String title;

    @Column(length = 4000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "TAG_NO")
    private Tag tag;

    @Builder
    public Diary (String title, String note, Tag tag) {
        this.title = title;
        this.note = note;
        this.tag = tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;

        if (!tag.getDiaries().contains(this)) {
            tag.getDiaries().add(this);
        }
    }

    public void update(String title, String note) {
        this.title = title;
        this.note = note;
    }
}
