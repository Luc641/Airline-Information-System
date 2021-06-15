package com.group_twelve.businesslogic;

import com.group_twelve.entities.PriceReduction;
import com.group_twelve.entities.PriceReductionType;
import com.group_twelve.persistence.PriceReductionPersistence;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@ExtendWith( MockitoExtension.class )
public class PriceReductionManagerTest {
    @Mock
    PriceReductionPersistence persistenceMock;

    PriceReductionManager priceReductionManager;

    @BeforeEach
    public void setup() {
        this.priceReductionManager = new PriceReductionManager( persistenceMock );
    }
    
    @ParameterizedTest
    @CsvSource({"1,static price reduction 15%,description,0.15,STATIC",
                "2,static price reduction 45%,description,0.45,STATIC",
                "3,dynamic sunhour price reduction,description,0.1,SUNHOURS"})
    void TestCreate(String id, String name, String description, String percentage, String type){
        PriceReduction pr = PriceReductionManager.create(new String[]{id, name, description, percentage, type});
        SoftAssertions.assertSoftly( v -> {
            v.assertThat(pr.getID()).as("ID parsed correctly").isEqualTo(Integer.valueOf(id));
            v.assertThat(pr.getName()).as("Name parsed correctly").isEqualTo(name);
            v.assertThat(pr.getDescription()).as("Description parsed correctly").isEqualTo(description);
            v.assertThat(pr.getPercentage()).as("Percentage parsed correctly").isEqualTo(Double.valueOf(percentage));
            v.assertThat(pr.getType()).as("Type parsed correctly").isEqualTo(PriceReductionType.valueOf(type));
        } );
    }
    
    @Test
    void TestDelete(){
        when(persistenceMock.delete(1)).thenReturn(true);
        assertThat(priceReductionManager.delete(1))
                .as("Delete command successfully passed down to persistence layer")
                .isNotNull()
                .isTrue();
    }
    
    @ParameterizedTest
    @CsvSource({"1,static price reduction 15%,description,0.15,STATIC",
                "2,static price reduction 45%,description,0.45,STATIC",
                "3,dynamic sunhour price reduction,description,0.1,SUNHOURS"})
    void TestSave(String id, String name, String description, String percentage, String type){
        PriceReduction pr = new PriceReduction(-1, name, description, Double.valueOf(percentage), PriceReductionType.valueOf(type));
        when(persistenceMock.save(pr)).thenReturn(true);
        assertThat(priceReductionManager.save(pr))
                .as("Save command successfully passed down to persistence layer")
                .isNotNull()
                .isTrue();
    }
    
    
}
