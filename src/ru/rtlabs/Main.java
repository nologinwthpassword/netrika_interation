package ru.rtlabs;

import ru.rtlabs.DB.DBWorker;
import ru.rtlabs.DB.DBWorker2;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException {

        DBWorker connection = new DBWorker();

        WebSession webSession = new WebSession();
        webSession.getUid(connection);
        try {
            connection.getConnection().close();
            Thread.sleep(100);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        DBWorker2 connection2 = new DBWorker2();
        SoapSender sender = new SoapSender(webSession.getWebSessionUid());
        sender.parse(connection2);

    }
}
