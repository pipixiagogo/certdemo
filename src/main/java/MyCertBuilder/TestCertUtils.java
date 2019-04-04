package MyCertBuilder;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sun.security.x509.X500Name;

import java.io.IOException;

public class TestCertUtils {
    public static void main(String[] args)throws Exception {
        testCertBuild();
        testClientBuild();
    }
    public static void testCertBuild() throws Exception{
        X500Name root = new X500Name("CN=MyServer,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String rootPfxPath = "d:/CERT/MyServer/server_rootcapfx.pfx";
        String rootCrtPath = "d:/CERT/MyServer/server_rootcacrt.crt";
        String rootPriKeyPath = "d:/CERT/MyServer/server_rootkey.pem";
        String rootAlias = "MyServer";
        String chainPem = "d:/CERT/MyServer/server_rootca.pem";
        String rootPassword="123456";
        CertUtils.createRootCert(
                rootAlias,rootPfxPath,rootCrtPath,root,rootPassword,rootPriKeyPath,chainPem);
        X500Name second = new X500Name(
                "CN=MyServerTwo,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String secondAlias = "MyServerTwo";
        String secondPassword = "123456";
        String secondPfxPath = "D:\\CERT\\MyServer\\server_secondstore.pfx";
        String secondCrtPath = "D:\\CERT\\MyServer\\server_secondcert.crt";
        String secondPemPath = "D:\\CERT\\MyServer\\server_secondkey.pem";
        String secondChainPath = "D:\\CERT\\MyServer\\server_secondchain.pem";

        CertUtils.createIssueCert(root,second,rootAlias,rootPfxPath,rootPassword,rootCrtPath,
                secondAlias,secondPfxPath,secondPassword,secondCrtPath,secondPemPath,secondChainPath);
    }

    public static void testClientBuild() throws Exception{
        X500Name root = new X500Name("CN=MyClient,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String rootPfxPath = "d:/CERT/MyClient/clien_troot_store.pfx";
        String rootCrtPath = "d:/CERT/MyClient/client_root_ca.crt";
        String rootPriKeyPath = "d:/CERT/MyClient/client_root_key.pem";
        String rootAlias = "MyClient";
        String chainPem = "d:/CERT/MyClient/client_rootca.pem";
        String rootPassword="123456";
        CertUtils.createRootCert(
                rootAlias,rootPfxPath,rootCrtPath,root,rootPassword,rootPriKeyPath,chainPem);
        X500Name second = new X500Name(
                "CN=MyClientTwo,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String secondAlias = "MyClientTwo";
        String secondPassword = "123456";
        String secondPfxPath = "D:\\CERT\\MyClient\\client_second_store.pfx";
        String secondCrtPath = "D:\\CERT\\MyClient\\client_second_cert.crt";
        String secondPemPath = "D:\\CERT\\MyClient\\client_second_key.pem";
        String secondChainPath = "D:\\CERT\\MyClient\\client_second_chain.pem";

        CertUtils.createIssueCert(root,second,rootAlias,rootPfxPath,rootPassword,rootCrtPath,
                secondAlias,secondPfxPath,secondPassword,secondCrtPath,secondPemPath,secondChainPath);
    }

}
