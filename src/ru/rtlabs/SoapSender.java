package ru.rtlabs;

import ru.rtlabs.DB.DBWorker2;
import ru.rtlabs.SoapBuilding.SoapBuild;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SoapSender {
    private String guid;
    private String wsdlUrl;
    private String webUid;
    private String caseSearch;
    private String nameSpacePatientSend;
    private String nameSpaceCaseSend;

public SoapSender(String webUid){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("config.properties");
            property.load(fis);
            this.guid = property.getProperty("app.guid");
            this.wsdlUrl = property.getProperty("wsdl.url");
            this.caseSearch = property.getProperty("case.search");
            this.nameSpacePatientSend = property.getProperty("namespace.patientAdd");
            this.nameSpaceCaseSend = property.getProperty("namespace.caseAdd");
        } catch (FileNotFoundException e1) {
            System.err.println("ОШИБКА: Файл properties отсуствует!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    this.webUid = webUid;
    }

public void parse(DBWorker2 connection){
    try {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(this.caseSearch);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String lpuname = lpuSearch(resultSet.getInt(2), connection);
            sendRequestPatient(SoapBuild.patientSoapBuild(resultSet.getString(5), lpuname , this.guid));
            sendRequestCase(SoapBuild.caseSoapBuild(String.valueOf(resultSet.getInt(1)), lpuname, this.guid));
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
}
    private String lpuSearch(Integer clinicId, DBWorker2 connection){
        String sql = "SELECT netrika_lpu from supp.netrika_lpu where lpu_id = ?";
        String lpu = null;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, clinicId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                lpu = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lpu;
    }
    private void sendRequestPatient(String soap) throws IOException {
        URL url = new URL(wsdlUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestProperty("SOAPAction", this.nameSpacePatientSend);
        conn.setRequestProperty("Cookie", this.webUid);
        OutputStream reqStream = conn.getOutputStream();
        reqStream.write(soap.getBytes());
    }
    private void sendRequestCase(String soap) throws IOException {
        URL url = new URL(wsdlUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestProperty("SOAPAction", this.nameSpaceCaseSend);
        conn.setRequestProperty("Cookie", this.webUid);
        OutputStream reqStream = conn.getOutputStream();
        reqStream.write(soap.getBytes());
    }

}
