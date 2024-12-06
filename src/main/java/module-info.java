module edu.Livraria {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.almasb.fxgl.all;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires jdk.compiler;
    requires org.hibernate.orm.core;
    //requires java.persistence;
    requires java.naming;
    requires jakarta.persistence;
    requires static lombok;
    requires java.sql; // Adicione esta linha
    // requires org.hibernate.orm.core; // Módulo do Hibernate
    //requires java.naming; // Adicione essa linha
    //requires org.hibernate.orm.core;
    //requires jakarta.persistence;

    opens edu.Livraria.model.entity to org.hibernate.orm.core;
    exports edu.Livraria.model.entity;
    opens edu.Livraria.controller to javafx.fxml;
    exports edu.Livraria;
    exports edu.Livraria.model.services;

    exports edu.Livraria.controller to javafx.fxml;
}