module com.pharmasoft {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires org.json;
//
//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires org.kordamp.bootstrapfx.core;
//
    opens com.pharmasoft to javafx.fxml;
//    opens com.pharmasoft.Controllers to javafx.fxml;
    opens com.pharmasoft.Controllers.Forms to javafx.fxml;
    opens com.pharmasoft.Controllers.Menus to javafx.fxml;

    exports com.pharmasoft;
    exports com.pharmasoft.Controllers;
    opens com.pharmasoft.Controllers to javafx.fxml;


}