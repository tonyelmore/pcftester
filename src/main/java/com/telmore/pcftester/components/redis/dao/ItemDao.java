package com.telmore.pcftester.components.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.telmore.pcftester.components.redis.domain.Item;

@Repository
@Transactional
public class ItemDao {

    private static final String KEY = "item_key";

    @Autowired
    private RedisTemplate<String, Item> redisTemplate;

    public void addItem(Item item) {
        redisTemplate.opsForList().leftPush(KEY, item);
    }
    public long getNumberOfItems() {
        return redisTemplate.opsForList().size(KEY);
    }
    public Item getItemAtIndex(Integer index) {
        return redisTemplate.opsForList().index(KEY, index);
    }
    public void removeItem(Item item) {
        redisTemplate.opsForList().remove(KEY, 1, item);
    }

}
