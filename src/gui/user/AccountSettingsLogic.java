package gui.user;

import database.UpdateColumn;
import gui.login.RegisterUserController;
import gui.login.RegisterUserLogic;

public class AccountSettingsLogic {
    private AccountSettingsController controller;

    public AccountSettingsLogic(AccountSettingsController controller){
        this.controller = controller;

        controller.getUpdateFirstNameButton().setOnAction(e -> {
            var ret = RegisterUserLogic.checkStringBasedOnType(
                    controller.getFirstNameTextField().getText(), RegisterUserLogic.Field.FIRST_NAME);
            controller.setFirstNameError(RegisterUserController.getTextErrorLabel(ret));

            UpdateColumn.updateGivenColumn("firstName",controller.getFirstNameTextField().getText() , controller.getUserName());
        });
        controller.getUpdateLastNameButton().setOnAction(e -> {
            var ret = RegisterUserLogic.checkStringBasedOnType(
                    controller.getLastNameTextField().getText(), RegisterUserLogic.Field.LAST_NAME);
            controller.setLastNameError(RegisterUserController.getTextErrorLabel(ret));

            UpdateColumn.updateGivenColumn("lastName",controller.getLastNameTextField().getText() , controller.getUserName());
        });
        controller.getUpdatePhoneButton().setOnAction(e -> {
            var ret = RegisterUserLogic.checkStringBasedOnType(
                    controller.getPhoneTextField().getText(), RegisterUserLogic.Field.PHONE_NUMBER);
            controller.setPhoneError(RegisterUserController.getTextErrorLabel(ret));

            UpdateColumn.updateGivenColumn("nr",controller.getPhoneTextField().getText() , controller.getUserName());
        });
        controller.getUpdateCityButton().setOnAction(e -> {
            var ret = RegisterUserLogic.checkStringBasedOnType(
                    controller.getCity(), RegisterUserLogic.Field.PHONE_NUMBER);
            controller.setPhoneError(RegisterUserController.getTextErrorLabel(ret));

            UpdateColumn.updateGivenColumn("City",controller.getCity() , controller.getUserName());
        });
    }



}
