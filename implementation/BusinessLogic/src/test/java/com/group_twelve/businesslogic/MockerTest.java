package com.group_twelve.businesslogic;

import static org.mockito.Mockito.*;
import static org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author hom
 */
@ExtendWith(MockitoExtension.class)
public class MockerTest {
//@Disabled("think TDD")

    @Mock
    Appendable ap;
    @Test
    public void mockTest() throws IOException {
        ap.append( "Hello");
        
        
        fail( "method mockTest reached end. You know what to do." );
    }
}
