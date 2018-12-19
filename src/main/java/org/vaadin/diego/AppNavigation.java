package org.vaadin.diego;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;

@Tag("app-navigation")
@HtmlImport("frontend://styles/app-navigation.html")
public class AppNavigation extends Div implements AfterNavigationObserver
{
    private static final long serialVersionUID = 1L;

    //@Id("tabs")
    private Tabs tabs;

    private Anchor homeTarget;
    private Div branding;

    private List<String> hrefs = new ArrayList<>();
    private String logoutHref;
    private String defaultHref;
    private String currentHref;

    public void init(List<String> pages, String defaultHref, String logoutHref) {

        this.logoutHref = logoutHref;
        this.defaultHref = defaultHref;

        addClassName("toolbar");

        tabs = new Tabs();
        homeTarget = new Anchor();

        homeTarget.setHref("https://www.google.com/");
        homeTarget.setTarget("_blank");

        branding = new Div(homeTarget);
        branding.setClassName("branding");


        for (String page : pages) {
            Tab tab = new Tab(new IronIcon("vaadin", ""), new Span("Diego"));
            tab.getElement().setAttribute("theme", "icon-on-top");
            hrefs.add("");
            tabs.add(tab);
        }

        tabs.addSelectedChangeListener(e -> navigate());

        add(branding, tabs);

    }

    private void navigate() {
        int idx = tabs.getSelectedIndex();
        if (idx >= 0 && idx < hrefs.size()) {
            String href = hrefs.get(idx);
            if (href.equals(logoutHref)) {
                // The logout button is a 'normal' URL, not Flow-managed but
                // handled by Spring Security.
                UI.getCurrent().getPage().executeJavaScript("location.assign('logout')");
            } else if (!href.equals(currentHref)) {
                UI.getCurrent().navigate(href);
            }
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        String href = event.getLocation().getFirstSegment().isEmpty() ? defaultHref
                : event.getLocation().getFirstSegment();


        currentHref = href;
        tabs.setSelectedIndex(hrefs.indexOf(href));
    }
}