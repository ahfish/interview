package com.interview.domain;

import java.time.LocalDateTime;

public class TimeAwareMapImpl<K,V> implements  TimeAwareMap<K,V>{
    @Override
    public V get(K k, LocalDateTime time) {
        throw new RuntimeException("No Yet Implement");
    }

    @Override
    public void put(K k, LocalDateTime time, V v) {
        throw new RuntimeException("No Yet Implement");
    }

    class InternalEntry  {
        private V v;
        private LocalDateTime time;
        public InternalEntry(V v, LocalDateTime time) {
            this.v = v;
            this.time = time;
        }
    }
}
