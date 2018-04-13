import Exceptions.SFTPClientException;
import SFTPLogic.SFTPClient;
import SFTPLogic.SFTPDataGenerator;

public class Main {

    public static void main(String[] args){

        SFTPDataGenerator dg = new SFTPDataGenerator();
        String filename = dg.generateTransactionBatch(100);
        SFTPClient sftpc = new SFTPClient("localhost",22,"do",PW,2222);
            try {
                sftpc.connect();
            } catch (SFTPClientException e) {
                System.out.println("Connection failed, quitting");
                return;
            }
        try {
            sftpc.uploadFile("/Users/do/IdeaProjects/sftpclient/files/"+filename,
                    "/Users/do/Documents/REQUESTDOCUMENTS/"+filename);
        } catch (SFTPClientException e) {
            e.printStackTrace();
        }
        sftpc.retrieveFile();
        sftpc.disconnect();
    }
}
