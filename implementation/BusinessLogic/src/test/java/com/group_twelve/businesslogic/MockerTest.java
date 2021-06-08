package com.group_twelve.businesslogic;


import java.util.function.IntBinaryOperator;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
        
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Pieter van den Hombergh {@ pieter.van.den.hombergh@gmail.com}
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
        when(op.applyAsInt( 6,7)).thenReturn( 6+7);
       int result = op.applyAsInt( 6,7);
        assertThat(result).as("expected sum")
                .isEqualTo( 6+7);
//        fail( "method tAPlusB reached end. You know what to do." );
    }
}
