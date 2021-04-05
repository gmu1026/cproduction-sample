package net.cproduction.test.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Tag extends BaseEntity implements Serializable {
    @Id
    @Column(name = "TAG_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long no;

    private String name;

    @OneToMany(mappedBy = "tag")
    private List<Diary> diaries = new ArrayList<>();

    @Builder
    public Tag(String name) {
        this.name = name;
    }

    public void addDiary(Diary diary) {
        this.diaries.add(diary);

        if (diary.getTag() != this) {
            diary.setTag(this);
        }
    }

    public void update(String name) {
        this.name = name;
    }
}

