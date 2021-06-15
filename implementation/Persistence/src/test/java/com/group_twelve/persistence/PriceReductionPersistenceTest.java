package com.group_twelve.persistence;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.PriceReduction;
import com.group_twelve.entities.PriceReduction;
import com.group_twelve.entities.PriceReductionType;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class PriceReductionPersistenceTest {
    
    @Mock
    SQLConnection databaseMock;
    @Mock
    Function<? super Object[], ? extends PriceReduction> creator;

    PriceReductionPersistence priceReductionPersistence;
    
    @BeforeEach
    void startup(){
        // Init the SUT
        this.priceReductionPersistence = new PriceReductionPersistence(databaseMock, creator);
    }
    
    @Test
    void testLoad() throws SQLException{
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);
        when(databaseMock.query("SELECT PR.ID, PR.prName, PR.description, PR.amount, PRT.typeName FROM PriceReduction PR "
                    + "JOIN PriceReductionType PRT ON PR.prType = PRT.ID")).thenReturn(mockResultSet);
        assertThat(priceReductionPersistence.load())
                .isNotNull()
                .isEmpty();
    }
    @Test
    void testDelete(){
        
    }
}
