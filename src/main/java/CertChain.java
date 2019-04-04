import cert.CertUtil;
import sun.security.x509.X500Name;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Scanner;

public class CertChain {
//    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException,
//            InvalidKeyException, CertificateException, IOException,
//            KeyStoreException, UnrecoverableKeyException, SignatureException {
//        X500Name issue = new X500Name("CN=RootCA,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        CertUtil.createRootCert("d:/CA/ROOTCA.pfx","d:/CA/ROOTCA.crt",issue);
//        X500Name subject = new X500Name(
//                "CN=subject,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String issuePfxPath = "d:/CA/ROOTCA.pfx";
//        String issueCrtPath = "d:/CA/ROOTCA.crt";
//
//        String issueAlias = "RootCA";
//        String subjectAlias = "subject";
//        String issuePassword = "123456";
//        String subjectPassword = "123456";
//        //Scanner scanner = new Scanner(System.in);
//        int i = 233;
//        String pfxpath = "d:/client-cert/" + (i);
//        File file = new File(pfxpath);
//        if( !file.exists() ){
//            file.mkdirs();
//        }
//        //一级证书
//        String subjectPfxPath = "d:/client-cert/" + (i) + "/ISSUE.pfx";
//        String subjectCrtPath = "d:/client-cert/" + (i) + "/ISSUE.crt";
//        CertUtil.createIssueCert(issue,subject,issueAlias,issuePfxPath,issuePassword,
//                issueCrtPath,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath);
//        //二级证书
//        X500Name subject2 = new X500Name(
//                "CN=subject2,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String subject2Alias = "subject2";
//        String subject2PfxPath = "d:/client-cert/" + (i) + "/ISSUE2.pfx";
//        String subject2CrtPath = "d:/client-cert/" + (i) + "/ISSUE2.crt";
//        CertUtil.createIssue2Cert(subject,subject2,subjectAlias,subjectPfxPath,subjectPassword,
//                 subjectCrtPath,issueCrtPath,subject2Alias,subject2PfxPath,subjectPassword,subject2CrtPath);
//
//   }
}
