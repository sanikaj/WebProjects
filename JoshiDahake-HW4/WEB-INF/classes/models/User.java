/**
 *
 * @author Sanika
 */
package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import super_airways.Query_Database;

public class User implements Serializable {

    private boolean isValid;
    private String username;
    private String password;
    private Map<String, String> map = new HashMap<String, String>();
    static final long serialVersionUID = 42L;

    public User() {
    }

    public User(String username, String password) {
    }

    public String getUserName() {
        return this.username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void storeInMap(String username, String password) {
        map.put(password, username);
    }

    public void setProperties(String username, String password) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("userPasswords.properties");

            // set the properties value
            prop.setProperty(username, password);

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public String getProperties(String value) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("userPasswords.properties");

            // load a properties file
            prop.load(input);
            // the property value and print it out
            if (prop.getProperty(value) != null) {
                System.out.println(prop.getProperty(value));
                return prop.getProperty(value);
            } else {
                return null;
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public boolean findUserWithUserName(String username) {
        int count = (new Query_Database()).checkUserNameExists(username);
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean findUser(String username, String password) {
        //Here we implement the logic to search for the user in the database.
        int count = (new Query_Database()).checkValueExists(username,password);
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isValid() {
        return this.isValid;
    }
}
