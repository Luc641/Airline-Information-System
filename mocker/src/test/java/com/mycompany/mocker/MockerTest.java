package com.mycompany.mocker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.function.IntBinaryOperator;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author hom
 */
@ExtendWith(MockitoExtension.class)
public class MockerTest {
    
    
   @Mock
   IntBinaryOperator op;
   
   @BeforeEach
   void setupTestInstance(){
       op = mock(IntBinaryOperator.class);
   
   }
   
    //@Disabled("think TDD")
    @Test
    public void tAPlusB() {
        // mock returns wrong answer.
        when(op.applyAsInt( 6,7)).thenReturn( 42);
       int result = op.applyAsInt( 6,7);
        assertThat(result).as("expected sum")
                .isEqualTo( 6+7);
        fail( "method tAPlusB reached end. You know what to do." );
    }
}
