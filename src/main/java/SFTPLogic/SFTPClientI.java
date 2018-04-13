package SFTPLogic;

import Exceptions.SFTPClientException;

public interface SFTPClientI {

    void connect() throws SFTPClientException;
    void uploadFile(String sourceFileName,String destFileName) throws SFTPClientException;
    void retrieveFile(String sourceFileName,String destFileName) throws SFTPClientException;
    void disconnect();

}
