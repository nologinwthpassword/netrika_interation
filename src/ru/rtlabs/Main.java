package ru.rtlabs;

import ru.rtlabs.DB.DBWorker;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

       DBWorker connection = new DBWorker();
       WebSession webSession = new WebSession();
        webSession.getUid(connection);
        SoapSender sender = new SoapSender(webSession.getWebSessionUid());
        sender.parse(connection);

    }
}
