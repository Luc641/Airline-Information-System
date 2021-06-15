package com.group_twelve.gui;

import com.group_twelve.businesslogic.BusinessLogic;
import com.group_twelve.businesslogic.PriceReductionManager;
import com.group_twelve.entities.PriceReduction;
import com.group_twelve.entities.PriceReductionType;
import java.lang.reflect.Field;

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
import javafx.scene.control.ListView;

@ExtendWith( MockitoExtension.class )
public class PriceReductionListTest {

    @Mock
    BusinessLogic mockBusinessLogic;
    
    @Mock
    PriceReductionManager prmMock;
    
    
    GUIApp guiApp;
    
    @BeforeEach
    public void setup(){
        //when(mockBusinessLogic.getManager(PriceReduction.class)).thenReturn(prmMock);
        //GUIApp.setBusinessLoicAPI(mockBusinessLogic);
    }
    
    @ParameterizedTest
    @CsvSource({"1,static price reduction 15%,description,0.15,STATIC",
                "2,static price reduction 45%,description,0.45,STATIC",
                "3,dynamic sunhour price reduction,description,0.1,SUNHOURS"})
    public void testPopulateList(String id, String name, String description, String percentage, String type) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
//        ArrayList<PriceReduction> arrayList = new ArrayList<>();
//        arrayList.add(new PriceReduction(Integer.valueOf(id), name, description, Double.valueOf(percentage), PriceReductionType.valueOf(type)));
//        when(prmMock.getAll()).thenReturn(arrayList);
//        priceReductionList prl = new priceReductionList();
//        prl.lvPriceReductions = new ListView<String>();
//        prl.initialize();
//        assertThat((String) prl.lvPriceReductions.getSelectionModel().getSelectedItem())
//                .as("List populated correctly")
//                .isNotNull()
//                .isEqualTo(name);
    }
}

// Test does not work, due to not being able to initialize ListView, which is also not initialized by itself upon creation
// Thus making the main component of the test null and not usable
