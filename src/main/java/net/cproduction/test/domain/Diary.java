package net.cproduction.test.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DIARY")
@NoArgsConstructor
@Getter
public class Diary extends BaseEntity implements Serializable {
    private String title;
    private String note;

    @Builder
    public Diary (String title, String note) {
        this.title = title;
        this.note = note;
    }

    public void update(String title, String note) {
        this.title = title;
        this.note = note;
    }
}
