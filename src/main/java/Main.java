import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args){
        System.out.println(ResourceBundle.getBundle("requests/SQL requests").getString("get_all_students"));
    }
}
