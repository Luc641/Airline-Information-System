package fontys.frontend;

import businessentitiesapi.Customer;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import businesslogic.BusinessLogicAPI;

public class CustomerController {
    
    @FXML
    TextField customerName, dateOfBirth;
    
    private BusinessLogicAPI businessLogicAPI;

    public CustomerController() {
    }
    
    public CustomerController( BusinessLogicAPI logicAPI) {
        this.businessLogicAPI = logicAPI;
    }

    @FXML
    private void switchToSecondary() throws IOException {
        GUIApp.setRoot("secondary");
    }
    
    @FXML
    private void storeCustomer(){
        Customer c = businessLogicAPI.getCustomerManager().createCustomer(customerName.getText(), LocalDate.parse(dateOfBirth.getText()));
        businessLogicAPI.getCustomerManager().add(c);
    }
}
