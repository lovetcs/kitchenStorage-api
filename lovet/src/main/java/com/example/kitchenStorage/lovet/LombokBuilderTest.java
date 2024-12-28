package com.example.kitchenStorage.lovet;

public class LombokBuilderTest {
    public static void main(String[] args) {
        TestEntity testEntity = TestEntity.builder()
                .field1("Test")
                .field2(123)
                .build();

        System.out.println(testEntity);
    }
}
