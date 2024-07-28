package com.gahoccode.controller;

import java.io.IOException;

import com.gahoccode.pojo.Account;
import com.gahoccode.service.AccountService;
import com.gahoccode.service.IAccountService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtPassword;

	private IAccountService iAccountService;

	public LoginController() {
		iAccountService = new AccountService("hibernate.cfg.xml");
	}

	@FXML
	public void Login() throws IOException {
		Account account = iAccountService.findByUserName(txtUsername.getText());
		if (account != null && account.getPassword().equals(txtPassword.getText())) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Dashboard.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setHeight(450);
			stage.setWidth(650);
			stage.centerOnScreen();
			stage.setTitle("Student Management");
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("You have no permission to access this function!");
			alert.show();
		}
	}

}
