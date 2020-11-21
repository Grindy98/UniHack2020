package gui.user;

public class AccountSettingsLogic {
    private AccountSettingsController controller;

    public AccountSettingsLogic(AccountSettingsController controller){
        this.controller = controller;

        controller.getUpdateNameButton().setOnAction(e -> System.out.println("This works"));
    }
}
