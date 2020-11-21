package gui.user;

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
        });
        controller.getUpdateLastNameButton().setOnAction(e -> {
            var ret = RegisterUserLogic.checkStringBasedOnType(
                    controller.getLastNameTextField().getText(), RegisterUserLogic.Field.LAST_NAME);
            controller.setLastNameError(RegisterUserController.getTextErrorLabel(ret));
        });
        controller.getUpdatePhoneButton().setOnAction(e -> {
            var ret = RegisterUserLogic.checkStringBasedOnType(
                    controller.getPhoneTextField().getText(), RegisterUserLogic.Field.PHONE_NUMBER);
            controller.setPhoneError(RegisterUserController.getTextErrorLabel(ret));
        });
        controller.getUpdateCityButton().setOnAction(e -> {

        });
    }



}
