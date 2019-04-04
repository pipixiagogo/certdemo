package SearchWebCert;

import cert.CertUtil;
import sun.security.x509.X500Name;

public class MyTest2 {
    public static void main(String[] args)throws Exception {


        X500Name issue = new X500Name("CN=ROOT,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String issuePfxPath = "D:/CERT/server/rootstore.pfx";
        String issueCrtPath = "D:/CERT/server/rootca.crt";
        String issuePriKeyPath = "D:/CERT/server/rootkey.pem";
        String rootcapath = "D:/CERT/server/rootca.pem";
        String issueAlias = "MyRootCA";
        //root
        CertUtil.createRootCert(issuePfxPath,
                issueCrtPath, issuePriKeyPath,issue,issueAlias,rootcapath);
        X500Name subject = new X500Name(
                "CN=SecondROOT,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String subjectAlias = "serversecond";
        String issuePassword = "123456";
        String subjectPassword = "123456";
        String subjectPfxPath = "D:\\CERT\\server\\secondstore.pfx";
        String subjectCrtPath = "D:\\CERT\\server\\secondcert.crt";
        String subjectPemPath = "D:\\CERT\\server\\secondkey.pem";
        String chainPath = "D:\\CERT\\server\\secondchain.pem";
        CertUtil.createIssueCert(issue,subject,issueAlias,issuePfxPath,issuePassword,
                issueCrtPath,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath,subjectPemPath,chainPath);

    }
}
