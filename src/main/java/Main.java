import cert.CertUtil;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import sun.misc.BASE64Decoder;
import sun.security.x509.X500Name;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
//        // CN commonName 一般名字
//        // L localityName 地方名
//        // ST stateOrProvinceName 州省名
//        // O organizationName 组织名
//        // OU organizationalUnitName 组织单位名
//        // C countryName 国家
//        // STREET streetAddress 街道地址
//        // DC domainComponent 领域
//        // UID user id 用户ID
//        /*X500Name issue = new X500Name("CN=MyRootCA,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//
//        String issueAlias = "MyRootCA";*/
//
//        //创建服务端密钥对
      createServerCerts();
        //createClientCerts();//生成客户端证书链
//        X500Name issue = new X500Name("CN=ROOT,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String issuePfxPath = "D:/CERT/server/rootstore.pfx";
//        String issueCrtPath = "D:/CERT/server/rootca.crt";
//        String issuePriKeyPath = "D:/CERT/server/rootkey.pem";
//        String issueAlias = "MyRootCAServer";
//        String rootcapath = "D:/CERT/server/rootca.pem";
//        //root
//        CertUtil.createRootCert(issuePfxPath,
//                issueCrtPath, issuePriKeyPath,issue,issueAlias,rootcapath);
//        X500Name subject = new X500Name(
//                "CN=secondServer,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String subjectAlias = "mycert5";
//        String issuePassword = "123456";
//        String subjectPassword = "123456";
//        String subjectPfxPath = "d:\\CERT\\server\\secondstore.pfx";
//        String subjectCrtPath = "d:\\CERT\\server\\secondcert.crt";
//        String subjectPemPath = "d:\\CERT\\server\\secondkey.pem";
//        String chainPath = "d:\\CERT\\server\\secondchain.pem";
//        CertUtil.createIssueCert(issue,subject,issueAlias,issuePfxPath,issuePassword,
//                issueCrtPath,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath,subjectPemPath,chainPath);
////
//        //创建客户端密钥对
//        //createClientCerts();
///*        File f = new File("mycert.bin");
//        if( !f.exists() ){
//            f.createNewFile();
//        }
//        OutputStream out = new FileOutputStream(f);
//        String str = "MHcCAQEEIJAk/aSRlirtTqlYB9v2aJcHygAEhcCBB9ikieTzz/9FoAoGCCqGSM49AwEHoUQDQgAEvdOSAQXoIntoQUDJ+PNQ4T5jwEa1h7Ub3bYjAY5Ye1YZ2oXaOSDa3/G+49wolMmaw/YrNQSVyws82Tuf0FcUEQ==";
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] bs = decoder.decodeBuffer(str);
//        out.write(bs);
//        out.close();
//        if(true){
//            return;
//        }*/
//
//
//
//
//        X500Name issue = new X500Name("CN=MyRootCAClient,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String issuePfxPath = "d:/CERT/clients/rootstore.pfx";
//        String issueCrtPath = "d:/CERT/clients/rootca.crt";
//        String issuePriKeyPath = "d:/CERT/clients/rootkey.pem";
//        String issuePripemPath = "d:/CERT/clients/ca.pem";
//        String issueAlias = "MyRootCAClient";
//        //root
//
//        CertUtil.createRootCert(issuePfxPath,issueCrtPath,issuePriKeyPath,issue,issueAlias,issuePripemPath);
//
//        X500Name subject = new X500Name(
//                "CN=clientsecond,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String subjectAlias = "clientsecond";
//        String issuePassword = "123456";
//        String subjectPassword = "123456";
//        String subjectPfxPath = "D:\\CERT\\clients\\secondstore.pfx";
//        String subjectCrtPath = "D:\\CERT\\clients\\secondcert.crt";
//        String subjectPemPath = "D:\\CERT\\clients\\secondkey.pem";
//        String chainPath = "D:\\CERT\\clients\\secondchain.pem";
//
//        X500Name subject2 = new X500Name(
//                "CN=clientthird,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String subject2Alias = "clientthird";
//        String subject2Password = "123456";
//        CertUtil.createIssueCert(issue,subject,issueAlias,issuePfxPath,issuePassword,
//                issueCrtPath,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath,subjectPemPath,chainPath);
////
////        String subject2PfxPath = "D:\\CERT\\clients\\thirdstore.pfx";
////        String subject2CrtPath = "D:\\CERT\\clients\\thirdcert.crt";
////        String pem2Path = "D:\\CERT\\clients\\thirdkey.pem";
////        String chain3Path = "D:\\CERT\\clients\\thirdchain.pem";
////
////        CertUtil.createIssue2Cert(subject,subject2,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath,
////                issueCrtPath,subject2Alias,subject2PfxPath,subject2Password,subject2CrtPath,pem2Path,chain3Path);
//        X500Name issue = new X500Name("CN=MyRootCAServer,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String issuePfxPath = "d:/CERT/servers/rootstore.pfx";
//        String issueCrtPath = "d:/CERT/servers/rootca.crt";
//        String issuePriKeyPath = "d:/CERT/servers/rootkey.pem";
//        String issuePripemPath = "d:/CERT/servers/ca.pem";
//        String issueAlias = "MyRootCAServer";
//        //root
//
//        CertUtil.createRootCert(issuePfxPath,issueCrtPath,issuePriKeyPath,issue,issueAlias,issuePripemPath);
////
//        X500Name subject = new X500Name(
//                "CN=Serversecond,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String subjectAlias = "serversecond";
//        String issuePassword = "123456";
//        String subjectPassword = "123456";
//        String subjectPfxPath = "D:\\CERT\\servers\\secondstore.pfx";
//        String subjectCrtPath = "D:\\CERT\\servers\\secondcert.crt";
//        String subjectPemPath = "D:\\CERT\\servers\\secondkey.pem";
//        String chainPath = "D:\\CERT\\servers\\secondchain.pem";
//
//        X500Name subject2 = new X500Name(
//                "CN=serverthird,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String subject2Alias = "serverthird";
//        String subject2Password = "123456";
//        CertUtil.createIssueCert(issue,subject,issueAlias,issuePfxPath,issuePassword,
//                issueCrtPath,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath,subjectPemPath,chainPath);
//
//        String subject2PfxPath = "D:\\CERT\\servers\\thirdstore.pfx";
//        String subject2CrtPath = "D:\\CERT\\servers\\thirdcert.crt";
//        String pem2Path = "D:\\CERT\\servers\\thirdkey.pem";
//        String chain3Path = "D:\\CERT\\servers\\thirdchain.pem";
//
//        CertUtil.createIssue2Cert(subject,subject2,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath,
//                issueCrtPath,subject2Alias,subject2PfxPath,subject2Password,subject2CrtPath,pem2Path,chain3Path);


    }

    //创建//创建客户端密钥对
    private static void createClientCerts() throws Exception {
        X500Name issue = new X500Name("CN=MyRootCAClient,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String issuePfxPath = "d:/CERT/client/rootstore.pfx";
        String issueCrtPath = "d:/CERT/client/rootca.crt";
        String issuePriKeyPath = "d:/CERT/client/rootkey.pem";
        String issueAlias = "MyRootCAClient";
        String rootpem = "d:/CERT/client/rootca.pem";
        //root
        CertUtil.createRootCert(issuePfxPath,
                issueCrtPath, issuePriKeyPath,issue,issueAlias,rootpem);

        X500Name subject = new X500Name(
                "CN=clientsecond,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String subjectAlias = "clientsecond";
        String issuePassword = "123456";
        String subjectPassword = "123456";
        String subjectPfxPath = "D:\\CERT\\client\\secondstore.pfx";
        String subjectCrtPath = "D:\\CERT\\client\\secondcert.crt";
        String subjectPemPath = "D:\\CERT\\client\\secondkey.pem";
        String chainPath = "D:\\CERT\\client\\secondchain.pem";
        CertUtil.createIssueCert(issue,subject,issueAlias,issuePfxPath,issuePassword,
                issueCrtPath,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath,subjectPemPath,chainPath);
//
//        X500Name third = new X500Name(
//                "CN=clientthird,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String thirdtAlias = "clientthird";
//        String thirdPassword = "123456";
//        String subPassword = "123456";
//        String thirdPfxPath = "D:\\CERT\\client\\thirdstore.pfx";
//        String thirdCrtPath = "D:\\CERT\\client\\thirdcert.crt";
//        String thirdPemPath = "D:\\CERT\\client\\thirdkey.pem";
//        String thirdchainPath = "D:\\CERT\\client\\thirdchain.pem";
//        CertUtil.createIssue2Cert(subject,third,subjectAlias,subjectPfxPath,subjectPassword,
//                subjectCrtPath,issueCrtPath,thirdtAlias,thirdPfxPath,subPassword,thirdCrtPath,
//                thirdPemPath,thirdchainPath);
    }

    private static void createServerCerts() throws Exception {

        X500Name issue = new X500Name("CN=ROOT,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String issuePfxPath = "D:/CERT/server/rootstore.pfx";
        String issueCrtPath = "D:/CERT/server/rootca.crt";
        String issuePriKeyPath = "D:/CERT/server/rootkey.pem";
        String issueAlias = "MyRootCAServer";
        String rootcapath = "D:/CERT/server/rootca.pem";
        //root
        CertUtil.createRootCert(issuePfxPath,
                issueCrtPath, issuePriKeyPath,issue,issueAlias,rootcapath);
        X500Name subject = new X500Name(
                "CN=secondServer,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String subjectAlias = "mycert5";
        String issuePassword = "123456";
        String subjectPassword = "123456";
        String subjectPfxPath = "d:\\CERT\\server\\secondstore.pfx";
        String subjectCrtPath = "d:\\CERT\\server\\secondcert.crt";
        String subjectPemPath = "d:\\CERT\\server\\secondkey.pem";
        String chainPath = "d:\\CERT\\server\\secondchain.pem";
        CertUtil.createIssueCert(issue,subject,issueAlias,issuePfxPath,issuePassword,
                issueCrtPath,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath,subjectPemPath,chainPath);

//        X500Name subject6 = new X500Name(
//                "CN=thirdServer,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
//        String subjectAlias6 = "mycert6";
//        String issuePassword6 = "123456";
//        String subjectPassword6 = "123456";
//        String subjectPfxPath6 = "d:\\CERT\\server\\thirdstore.pfx";
//        String subjectCrtPath6 = "d:\\CERT\\server\\thirdcert.crt";
//        String subjectPemPath6 = "d:\\CERT\\server\\thirdkey.pem";
//        String chainPath6 = "d:\\CERT\\server\\thirdchain.pem";
//        CertUtil.createIssueCert(subject,subject6,subjectAlias,subjectPfxPath,issuePassword6,
//                subjectCrtPath,subjectAlias6,subjectPfxPath6,subjectPassword6,subjectCrtPath6,
//                subjectPemPath6,chainPath6);

    }


}
