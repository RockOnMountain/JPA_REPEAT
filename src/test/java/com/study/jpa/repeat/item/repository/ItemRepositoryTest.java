package com.study.jpa.repeat.item.repository;

import java.util.List;
import java.util.Random;
import com.study.jpa.repeat.item.domain.Item;
import com.study.jpa.repeat.item.domain.ItemOption;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    Random random = new Random();


    @Test
    void item들_저장() throws Exception {

        // given
        List<Item> items = List.of(this.createItem("루브르 박물관"), this.createItem("제주도 숙소"), this.createItem("서울 광화문"));

        // when
        List<Item> saveItems = itemRepository.saveAll(items);

        // then
        assertThat(saveItems).hasSize(3);
    }


    private Item createItem(String name) {

        Item item = new Item();
        item.setName(name);

        int itemOptionRandomCnt = random.nextInt(3) + 1; // 1 ~ 3

        for(int i = 0; i < itemOptionRandomCnt; i++) {
            item.addItemOption(this.createItemOption(name, i));
        }

        return item;
    }


    private ItemOption createItemOption(String name, int index) {

        ItemOption itemOption = new ItemOption();

        itemOption.setPrice(random.nextInt(1000) + 1000);
        itemOption.setStockQuantity(100);
        itemOption.setName(name + " 옵션" + (index + 1));

        return itemOption;
    }

}
