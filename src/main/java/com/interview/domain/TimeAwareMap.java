package com.interview.domain;

import java.time.LocalDateTime;

public interface TimeAwareMap<K,V> {
    V get(K k, LocalDateTime time);
    void put(K k, LocalDateTime time, V v);
}
