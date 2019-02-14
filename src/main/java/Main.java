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
        // CN commonName 一般名字
        // L localityName 地方名
        // ST stateOrProvinceName 州省名
        // O organizationName 组织名
        // OU organizationalUnitName 组织单位名
        // C countryName 国家
        // STREET streetAddress 街道地址
        // DC domainComponent 领域
        // UID user id 用户ID
        /*X500Name issue = new X500Name("CN=MyRootCA,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String issuePfxPath = "d:/ROOTCA/ROOTCA_CLIENT.pfx";
        String issueCrtPath = "d:/ROOTCA/ROOTCA_CLIENT.crt";
        String issuePriKeyPath = "d:/ROOTCA/ROOTCA_CLIENT_PRI.pem";
        String issueAlias = "MyRootCA";*/

        //创建服务端密钥对
        createServerCerts();
        //创建客户端密钥对
        //createClientCerts();
/*        File f = new File("mycert.bin");
        if( !f.exists() ){
            f.createNewFile();
        }
        OutputStream out = new FileOutputStream(f);
        String str = "MHcCAQEEIJAk/aSRlirtTqlYB9v2aJcHygAEhcCBB9ikieTzz/9FoAoGCCqGSM49AwEHoUQDQgAEvdOSAQXoIntoQUDJ+PNQ4T5jwEa1h7Ub3bYjAY5Ye1YZ2oXaOSDa3/G+49wolMmaw/YrNQSVyws82Tuf0FcUEQ==";
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bs = decoder.decodeBuffer(str);
        out.write(bs);
        out.close();
        if(true){
            return;
        }*/




        X500Name issue = new X500Name("CN=MyRootCAClient,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String issuePfxPath = "d:/CERT/client/rootstore.pfx";
        String issueCrtPath = "d:/CERT/client/rootca.crt";
        String issuePriKeyPath = "d:/CERT/client/rootkey.pem";
        String issueAlias = "MyRootCAClient";
        //root
/*
        CertUtil.createRootCert(issuePfxPath,
                issueCrtPath, issuePriKeyPath,issue,issueAlias);
*/
        X500Name subject = new X500Name(
                "CN=clientsecond,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String subjectAlias = "clientsecond";
        String issuePassword = "123456";
        String subjectPassword = "123456";
        String subjectPfxPath = "D:\\CERT\\client\\secondstore.pfx";
        String subjectCrtPath = "D:\\CERT\\client\\secondcert.crt";
        String subjectPemPath = "D:\\CERT\\client\\secondkey.pem";
        String chainPath = "D:\\CERT\\client\\secondchain.pem";



        X500Name subject2 = new X500Name(
                "CN=clientthird,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String subject2Alias = "clientthird";
        String subject2Password = "123456";
/*
        CertUtil.createIssueCert(issue,subject,issueAlias,issuePfxPath,issuePassword,
                issueCrtPath,subjectAlias,subjectPfxPath,subjectPassword,subjectCrtPath,subjectPemPath,chainPath);
*/

        String subject2PfxPath = "D:\\CERT\\client\\thirdstore.pfx";
        String subject2CrtPath = "D:\\CERT\\client\\thirdcert.crt";
        String pem2Path = "D:\\CERT\\client\\thirdkey.pem";
        String chain3Path = "D:\\CERT\\client\\thirdchain.pem";

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
    }

    private static void createServerCerts() throws Exception {

        X500Name issue = new X500Name("CN=ROOT,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String issuePfxPath = "d:/CERT/server/rootstore.pfx";
        String issueCrtPath = "d:/CERT/server/rootca.crt";
        String issuePriKeyPath = "d:/CERT/server/rootkey.pem";
        String issueAlias = "MyRootCAServer";
        String rootcapath = "d:/CERT/server/rootca.pem";
        //root
        CertUtil.createRootCert(issuePfxPath,
                issueCrtPath, issuePriKeyPath,issue,issueAlias,rootcapath);


        X500Name subject = new X500Name(
                "CN=catl-test.yunext.com,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String subjectAlias = "mycert5";
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
