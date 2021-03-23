package fontys.frontend;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assumptions.assumeThat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.service.query.NodeQuery;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
@ExtendWith( ApplicationExtension.class )
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class GUIAppTest {

    private Stage stage;

    static {
        if ( Boolean.getBoolean( "SERVER" ) ) {
            System.setProperty( "java.awt.headless", "true" );
            System.setProperty( "testfx.robot", "glass" );
            System.setProperty( "testfx.headless", "true" );
            System.setProperty( "prism.order", "sw" );
            System.setProperty( "prism.text", "t2k" );
            System.setProperty( "glass.platform", "Monocle" );
            System.setProperty( "monocle.platform", "Headless" );
        }
    }

    @Start
    void start( Stage stage ) throws Exception {
        this.stage = stage;
        GUIApp main = new GUIApp();
        main.start( stage );
    }


    //@Disabled("Think TDD")query
    @Test
    void tSwitchToCustomer() {
        FxRobot rob = new FxRobot();
//        TextField editor =
        NodeQuery lookup = rob.lookup("#customerName"); //(Node t) -> t instanceof TextField));//.queryFirst();
        TextField textField = (TextField)lookup.query();
        assumeThat(textField).isNotNull();
        rob.clickOn( textField).write( "Hello World");
        NodeQuery buttonLookup = rob.lookup("#StoreCustomer"); //(Node t) -> t instanceof TextField));//.queryFirst();
        Button submit = (Button)buttonLookup.query();
        rob.clickOn( submit);
        fail( "method SwitchToCustomer completed succesfully; you know what to do" );
    }


}
