
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Scanner;

public class principal {

    public static void main(String[] args) {        
        checkardiretorios();
        MainMenu Menu = new MainMenu();
        Menu.Iniciar();
    }

    static void checkardiretorios() {
        File file = new File("C:\\Hotel\\Clientes");
        if (!file.exists()) {
            file.mkdirs();
        }

        File file2 = new File("C:\\Hotel\\Quartos");
        if (!file2.exists()) {
            file2.mkdirs();
        }
    }

}
