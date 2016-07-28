package ru.rtlabs.SoapBuilding;

public class SoapBuild {

    public static String patientSoapBuild(String patientUid, String lpuId, String gUid){
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:AddPatient>\n" +
                "         <tem:guid>"+gUid+"</tem:guid>\n" +
                "         <tem:idLPU>"+lpuId+"</tem:idLPU>\n" +
                "         <tem:patientUid>"+patientUid+"</tem:patientUid>\n" +
                "      </tem:AddPatient>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
    public static String caseSoapBuild(String caseId, String lpuId, String gUid){
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">   \n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:AddCase>\n" +
                "         <tem:guid>"+gUid+"</tem:guid>\n" +
                "         <tem:caseId>"+caseId+"</tem:caseId>\n" +
                "         <!--Optional:-->\n" +
                "            <tem:idLPU>"+lpuId+"</tem:idLPU>\n" +
                "         </tem:AddCase>\n" +
                "      </soapenv:Body>\n" +
                "   </soapenv:Envelope>";
    }
}
