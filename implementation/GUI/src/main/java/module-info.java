module GUI_module {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires businessentities_api_module;
    requires transitive businesslogic_module;
    opens com.group_twelve.gui to javafx.fxml;
    exports com.group_twelve.gui;
    exports com.group_twelve.gui.searchable;
    opens com.group_twelve.gui.searchable to javafx.fxml;
}
