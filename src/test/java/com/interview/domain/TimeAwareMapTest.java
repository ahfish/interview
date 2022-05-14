package com.interview.domain;

import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeAwareMapTest extends TestCase {

    @Test
    public void testGetMostRecentValue() {
        //Given
        TimeAwareMapImpl<String, String> timeAwareMap = new TimeAwareMapImpl<>();
        timeAwareMap.put("Key1", LocalDateTime.of(2022, 1, 1, 12, 44,56), "Key1Value1");
        timeAwareMap.put("Key1", LocalDateTime.of(2022, 1, 5, 15, 44,56), "Key1Value2");
        timeAwareMap.put("Key1", LocalDateTime.of(2022, 2, 1, 15, 44,56), "Key1Value3");
        timeAwareMap.put("Key2", LocalDateTime.of(2022, 1, 5, 15, 44,56), "Key2Value1");

        //When
        String actual = timeAwareMap.get("Key1", LocalDateTime.of(2022, 2, 1, 13, 44,56));

        //Then
        assertThat(actual).isEqualTo("Key1Value2");
    }

    @Test
    public void testIfThereIsNoRecentValue() {
        //Given
        TimeAwareMapImpl<String, String> timeAwareMap = new TimeAwareMapImpl<>();
        timeAwareMap.put("Key1", LocalDateTime.of(2022, 1, 1, 12, 44,56), "Key1Value1");
        timeAwareMap.put("Key1", LocalDateTime.of(2022, 1, 5, 15, 44,56), "Key1Value2");
        timeAwareMap.put("Key1", LocalDateTime.of(2022, 2, 1, 15, 44,56), "Key1Value3");
        timeAwareMap.put("Key2", LocalDateTime.of(2022, 1, 5, 15, 44,56), "Key2Value1");

        //When
        String actual = timeAwareMap.get("Key2", LocalDateTime.of(2022, 1, 1, 12, 44,56));

        //Then
        assertThat(actual).isNull();
    }

    @Test
    public void testIfSameKeyISProvided() {
        //Given
        TimeAwareMapImpl<String, String> timeAwareMap = new TimeAwareMapImpl<>();
        timeAwareMap.put("Key1", LocalDateTime.of(2022, 1, 1, 12, 44,56), "Key1Value1");
        timeAwareMap.put("Key1", LocalDateTime.of(2022, 1, 5, 15, 44,56), "Key1Value2");
        timeAwareMap.put("Key1", LocalDateTime.of(2022, 2, 1, 15, 44,56), "Key1Value3");
        timeAwareMap.put("Key2", LocalDateTime.of(2022, 1, 5, 15, 44,56), "Key2Value1");
        timeAwareMap.put("Key2", LocalDateTime.of(2022, 1, 1, 15, 44,56), "Key2Value2");

        //When
        String actual = timeAwareMap.get("Key2", LocalDateTime.of(2022, 1, 2, 12, 44,56));

        //Then
        assertThat(actual).isEqualTo("Key2Value2");

        //When
        timeAwareMap.put("Key2", LocalDateTime.of(2022, 1, 1, 15, 44,56), "Key2Value3");
        actual = timeAwareMap.get("Key2", LocalDateTime.of(2022, 1, 2, 12, 44,56));

        //Then
        assertThat(actual).isEqualTo("Key2Value3");


    }

}