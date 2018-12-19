package org.vaadin.diego;



import java.util.Arrays;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
@HtmlImport("frontend://styles/app-navigation.html")
public class MainView extends VerticalLayout {

    public MainView() {
        setSizeFull();

        AppNavigation appNavigation = new AppNavigation();

        appNavigation.init(Arrays.asList("diego","diego","diego","diego"),"diego","diego");

        add(appNavigation);
    }
}
