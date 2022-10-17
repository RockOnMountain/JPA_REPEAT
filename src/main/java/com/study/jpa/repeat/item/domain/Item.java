package com.study.jpa.repeat.item.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.study.jpa.repeat.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "item")
@Entity
public class Item extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemOption> itemOptions = new ArrayList<>();

    // 연관관계 메소드
    public void addItemOption(ItemOption itemOption) {

        this.itemOptions.add(itemOption);
        itemOption.setItem(this);
    }

}
