package SFTPLogic;

import DB.DbI;
import DB.PostGreSQLDb;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Random;

public class SFTPDataGenerator {

    LinkedList<String> names;

    public SFTPDataGenerator() {
        DbI dbRef = new PostGreSQLDb();
        dbRef.createConnection();
        names = (LinkedList<String>)dbRef.retrieveAllUsernames();
    }

    public String generateTransactionBatch(int nrOfTransactions){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        int toNr,fromNr = 0;
        double amount = 0;
        String to;
        String from;

        for(int i = 0;i<nrOfTransactions;i++){
            amount = rand.nextInt(50000);

            toNr = rand.nextInt(2);
            do fromNr = rand.nextInt(2); while(toNr==fromNr);
            to = names.get(toNr);
            from = names.get(fromNr);

            sb.append(createTransactionString(amount,to,from));
        }
        BufferedWriter writer = null;
        byte[] encodedBytes;
        String filename = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(sb.toString().getBytes("UTF-8"));

            encodedBytes = Base64.getEncoder().encode(hash);
            filename = new String(encodedBytes);
            filename = filename.replace("/","!");
            writer = new BufferedWriter( new FileWriter("/Users/do/IdeaProjects/sftpclient/files/"+filename));
            writer.write( sb.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            try{
                writer.close( );
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filename;
    }

    private String createTransactionString(double amount, String to, String from){
        return to+" "+from+" "+amount+"\n";
    }

}
