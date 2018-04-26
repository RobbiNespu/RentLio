package com.chamodshehanka.rentLioClient.view.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author chamodshehanka on 4/2/2018
 * @project RentLio
 **/
public class DashBoardUIController implements Initializable {

    @FXML
    public AnchorPane rootPane;

    @FXML
    public AnchorPane parameterizedPane;

    @FXML
    public AnchorPane paneTopHeader;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sideMenuActions();
        loadMenuUI();
    }

    private void sideMenuActions(){
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        try {
            AnchorPane sideAnchorPane = FXMLLoader.load(getClass()
                    .getResource("/com/chamodshehanka/rentLioClient/view/fxml/SideMenuUI.fxml"));
            drawer.setSidePane(sideAnchorPane);
            drawer.close();
            drawer.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        transition.setRate(-0.7);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)-> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (!drawer.isShown()) {
                AnchorPane sideAnchorPane;
                try {
                    sideAnchorPane = FXMLLoader.load(getClass()
                            .getResource("/com/chamodshehanka/rentLioClient/view/fxml/SideMenuUI.fxml"));
                    drawer.setVisible(true);
                    drawer.setSidePane(sideAnchorPane);
                    drawer.open();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                drawer.close();
                drawer.setVisible(false);
            }
        });
    }

    private void loadMenuUI(){
        try {
            AnchorPane paneLogin = FXMLLoader
                    .load(getClass().getResource("/com/chamodshehanka/rentLioClient/view/fxml/MenuUI.fxml"));
            parameterizedPane.getChildren().setAll(paneLogin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean setAnchorPaneTo(AnchorPane anchorPane) {

        System.out.println("In method"+anchorPane);

        System.out.println("Method Called");
        if (anchorPane != null) {
            try {
                System.out.println("Anchor Pane setting..");
                parameterizedPane.getChildren().setAll(anchorPane);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            System.out.println("there is no anchor pane");
            return false;
        }
    }

    @FXML
    private void windowClose() {
        Platform.exit();
    }
}
