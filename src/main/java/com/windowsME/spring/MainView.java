package com.WindowsMe.spring;

import static com.vaadin.flow.component.Tag.H1;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@StyleSheet("frontend://styles/styles.css")
    
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends Div 
{
    public MainView()
    {
        /*Main view Components*/
        H1 header       = new H1("Tims Toy Shop");
        LoginForm form  = new LoginForm();
        addClassName("main-layout");
        
        setSizeFull();

        /*Styles*/
        header.getElement().getThemeList().add("dark");
        

  
        add(header);
        add(form);
        
        
     
    }

}
