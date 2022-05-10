package com.interview.domain;

import java.time.LocalDateTime;

public class TimeAwareMapImpl<K,V> implements  TimeAwareMap<K,V>{
    @Override
    public V get(K k, LocalDateTime time) {
        return null;
    }

    @Override
    public void put(K k, LocalDateTime time, V v) {

    }
}
