package com.gahoccode.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gahoccode.pojo.Student;
import com.gahoccode.service.IStudentService;
import com.gahoccode.service.StudentService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardController implements Initializable{
	
	@FXML
	private TableView<Student> tbData;
	
	@FXML
	public TableColumn<Student,Integer> studentId;
	
	@FXML
	public TableColumn<Student, String> firstName;
	
	@FXML
	public TableColumn<Student, String> lastName;
	
	@FXML
	public TableColumn<Student,Integer> totalMark;
	
	@FXML
	private TextField txtFirstName;
	
	@FXML
	private TextField txtLastName;
	
	@FXML
	private TextField txtTotalMark;
	
	private int idStudent;
	
	private IStudentService iStudentService;
	
	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	private ObservableList<Student> studentsModel;
	
	public DashboardController() {
		iStudentService = new StudentService("hibernate.cfg.xml");
		studentsModel = FXCollections.observableArrayList(iStudentService.findAll());
	}
	
	public void showAlert(String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	public void showStudent(Student student) {
		this.setIdStudent(student.getId());
		this.txtFirstName.setText(student.getFirstName());
		this.txtLastName.setText(student.getLastName());
		this.txtTotalMark.setText(String.valueOf(student.getMarks()));
	}
	public void refreshDataTable() {
		this.setIdStudent(0);
		this.txtFirstName.setText("");
		this.txtLastName.setText("");
		this.txtTotalMark.setText("");
		studentsModel = FXCollections.observableArrayList(iStudentService.findAll());
		tbData.setItems(studentsModel);
	}
	
	
	

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		studentId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		totalMark.setCellValueFactory(new PropertyValueFactory<>("Marks"));
		tbData.setItems(studentsModel);
		tbData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
				if(tbData.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel selectionModel = tbData.getSelectionModel();
					ObservableList selectedCells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
					
					Object studentID = tablePosition.getTableColumn().getCellData(index);
					try {
						Student student = iStudentService.findById(Integer.valueOf(studentID.toString()));
						showStudent(student);
					} catch (Exception e) {
						showAlert("Information Board!", "Please choose the First Cell!");
					}
				}
				
			}
			
			
		});
	}
	
	@FXML
	public void addStudent() throws IOException{
		Student student = new Student(this.txtFirstName.getText(), this.txtLastName.getText(), Integer.parseInt(txtTotalMark.getText()));
		iStudentService.save(student);
		refreshDataTable();
	}
	
	@FXML
	public void updateStudent() {
		Student student = new Student(this.idStudent, this.txtFirstName.getText(),
				this.txtLastName.getText(), Integer.parseInt(txtTotalMark.getText()));
		iStudentService.update(student);
		refreshDataTable();
	}
	@FXML
	public void deleteStudent() {
		iStudentService.delete(this.getIdStudent());
		refreshDataTable();
	}
	
}
