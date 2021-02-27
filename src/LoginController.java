import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController {

    public void setLogView(Login logView) {
        this.logView = logView;
    }

    Login logView;
    Register regView;
    MainWindow mainView;
    Database model;

    public LoginController(Login lview, Database model, Register rview, MainWindow mview) {
        this.logView = lview;
        this.model = model;
        this.regView = rview;
        this.mainView = mview;

        this.logView.addLoginListener(new LoginListener());

        this.logView.addRegisterListener(new RegisterListener());

        //this.regView.addListener(new RegViewListener());
    }

    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String acquiredUser = logView.getUsername();
            String acquiredPassword = logView.getPassword();
            try {
                Boolean check = model.checkUserPresence(acquiredUser,acquiredPassword);
                if(check){

                    mainView.setVisible(true);
                    logView.setVisible(false);
                    regView.setVisible(false);
                    User user = new User(acquiredUser);
                    model.setCurrentUser(user);

                }
                else
                    logView.showDeniedAccess();

            } catch (SQLException ex) {

                logView.showDeniedAccess();
            }


        }
    }

    class RegisterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            //regView.setVisible(true);
            regView = new Register();                     //TODO: LO SWITCH TRA VISTE DOVREBBE ESSERE GESTITO COSì,SENZA VISTE GLOBALI
            regView.setVisible(true);
            regView.addListener(new RegViewListener());

        }
    }

    class RegViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String newUser = regView.getUsername();
            String newPassword = regView.getPassword();

            if (newUser.length()==0 || newPassword.length() == 0) {
                regView.multifunctionalLabel.setForeground(Color.red);
                regView.multifunctionalLabel.setText("Null values are not valid!");
            }

            else if (model.isExistingUsername(newUser)) {
                regView.multifunctionalLabel.setForeground(Color.red);
                regView.multifunctionalLabel.setText("Username already present!");
            }

            else{
                model.registerNewUser(newUser,newPassword);
                regView.multifunctionalLabel.setForeground(Color.green);
                regView.multifunctionalLabel.setText("Congrats,you've been registered!");
            }


        }
    }


}


