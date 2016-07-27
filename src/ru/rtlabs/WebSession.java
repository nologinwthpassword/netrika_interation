package ru.rtlabs;
import ru.rtlabs.DB.DBWorker;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WebSession {

    private String webSessionUid;
    public void getUid(DBWorker connection){
        String sql = "select aud_id from com_audit_trail where aud_user = 'zabbixuser' ORDER BY aud_date DESC limit 1";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                this.webSessionUid = resultSet.getString(1);
                System.out.println("WEB Session UID получен: " + resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getWebSessionUid() {
        return webSessionUid;
    }
}
