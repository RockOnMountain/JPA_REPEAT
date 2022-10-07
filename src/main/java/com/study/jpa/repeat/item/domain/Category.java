package com.study.jpa.repeat.item.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id")
    private Long id;

    private String name;

    /*
        self 연관관계
        1) parent의 객체를 가져올 때는 parent_id를 확인하여 가져옵니다.
        2) childeren 리스트를 가져올 때는 parent_id가 id와 같은 Category들을 가져옵니다.
        3) mappedBy = "parent" -> @JoinColumn(name = "parent_id") -> parent_id로 join해서 가져옵니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();


    // 연관관계 메소드
    public void addChildCategory(Category child) {
        this.children.add(child);
        child.setParent(this);
    }

}
