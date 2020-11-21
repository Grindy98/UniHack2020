package gui.login;

import database.GetCityUser;
import database.LoginDB;
import database.UpdateColumn;
import database.UserExistence;
import gui.Main;
import gui.user.User;
import gui.user.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import gui.sceneUtilities.SceneManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public static final String serverIP = "79.118.4.205";

    @FXML
    private Button registerButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void loginButtonClicked(ActionEvent actionEvent) {

        String user = username.getText();
        String pw = password.getText();

        boolean check = LoginDB.checkLoginData(user, pw);
        User userInst = GetCityUser.getUserByUsername(user);

        if (check == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, "User or password incorrect");
            a.showAndWait();
        }
        if (check){
            ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).setUserName(userInst.username);
            ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).setCityLabel(userInst.address);
            ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).setRoleLabel(getTypeAsString(userInst.type));
            fill_services(userInst.address, userInst.type);
            Main.getI().changeSceneOnMainStage(SceneManager.Type.USER);
        }

    }

    public void fill_services(String city, gui.user.User.Type type) {
        String connectionUrl = "jdbc:sqlserver://" + serverIP + ":1433;"
                + "database=LoginInformation;"
                + "user=suru;"
                + "password=1234;"
                + "encrypt= false;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";

        try (
                Connection conn = DriverManager.getConnection(connectionUrl);
                Statement stmt = conn.createStatement();
        ) {
            String selectStr;
            if (type == User.Type.CLIENT)
                selectStr = "select * " +
                    "from [LoginInformation]" + " where city like '" +
                    city + "' and userType like '1'";
            else
                selectStr = "select * " +
                        "from [LoginInformation]" + " where city like '" +
                        city + "' and userType like '0'";
            ResultSet res = stmt.executeQuery(selectStr);
            int i;
            ArrayList<String> list_username = new ArrayList<String>();
            while(res.next()) {
                list_username.add(res.getString("loginID"));
            }
            for (i = 0; i < list_username.size(); i++) {
                User new_user = GetCityUser.getUserByUsername(list_username.get(i));
                List<Services.Type> service = new_user.serviceList.getAssociateService();
                for (Services.Type var : service) {
                    ((UserController) SceneManager.getI().getController(SceneManager.Type.USER)).addNewElement(new_user, var);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTypeAsString(User.Type type){
        if (type == User.Type.CLIENT) {
            return "Client";
        }
        else {
            return "Provider";
        }
    }

    public void registerButtonClicked(ActionEvent actionEvent){
          Main.getI().changeSceneOnMainStage(SceneManager.Type.REGISTER_USER);
    }
}
