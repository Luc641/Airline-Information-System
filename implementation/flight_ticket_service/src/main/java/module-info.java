module com.group_twelve.flight_ticket_service {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.group_twelve.flight_ticket_service to javafx.fxml;
    exports com.group_twelve.flight_ticket_service;
}
