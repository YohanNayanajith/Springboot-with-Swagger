package com.example.train;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class TrainApplicationTests {
    Calculator underTest = new Calculator();

    //junit test annotation
    @Test
//    void contextLoads() {
//    }
    void itShouldAddTwoNumbers() {
        //given
        int numberOne = 10;
        int numberTwo = 20;

        //when
        int result = underTest.add(numberOne, numberTwo);

        //then
        assertThat(result).isEqualTo(30);
    }

    class Calculator{
        int add(int a,int b){
            return a+b;
        }
    }

}
