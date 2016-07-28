package ru.rtlabs;

import ru.rtlabs.DB.DBWorker;
import ru.rtlabs.DB.DBWorker2;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        DBWorker connection = new DBWorker();
        DBWorker2 connection2 = new DBWorker2();
        WebSession webSession = new WebSession();
        webSession.getUid(connection);
        SoapSender sender = new SoapSender(webSession.getWebSessionUid());
        sender.parse(connection2);

    }
}
