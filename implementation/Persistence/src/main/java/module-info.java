module persistence_module {
    requires transitive businessentities_api_module;
    requires transitive dbconnection_module;
    requires java.sql;
    exports com.group_twelve.persistence;

}