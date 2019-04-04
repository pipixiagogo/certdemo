package X509CertTest;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.*;

import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

public class X509CertTest {

    private SecureRandom secureRandom;
    public X509CertTest() {
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    //颁发证书
    public void createIssueCert(X500Name issue, X500Name subject,
                                String issueAlias, String issuePfxPath, String issuePassword,
                                String issueCrtPath, String subjectAlias, String subjectPfxPath,
                                String subjectPassword, String subjectCrtPath)
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, CertificateException, IOException,
            KeyStoreException, UnrecoverableKeyException, SignatureException {
        CertAndKeyGen certAndKeyGen = new CertAndKeyGen("RSA", "MD5WithRSA",
                null);
        certAndKeyGen.setRandom(secureRandom);
        certAndKeyGen.generate(1024);
        String sigAlg = "MD5WithRSA";
        // 1年
        long validity = 3650 * 24L * 60L * 60L;
        Date firstDate = new Date();
        Date lastDate;
        lastDate = new Date();
        lastDate.setTime(firstDate.getTime() + validity * 1000);
        CertificateValidity interval = new CertificateValidity(firstDate,
                lastDate);
        X509CertInfo info = new X509CertInfo();
        // Add all mandatory attributes
        info.set(X509CertInfo.VERSION, new CertificateVersion(
                CertificateVersion.V3));
        info.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(
                new java.util.Random().nextInt() & 0x7fffffff));

        AlgorithmId algID = AlgorithmId.get(sigAlg);
        info.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algID));
        info.set(X509CertInfo.SUBJECT, subject);
        info.set(X509CertInfo.KEY, new CertificateX509Key(certAndKeyGen
                .getPublicKey()));
        info.set(X509CertInfo.VALIDITY, interval);
        info.set(X509CertInfo.ISSUER, issue);
        PrivateKey privateKey = readPrivateKey(issueAlias, issuePfxPath,
                issuePassword);
        X509CertImpl cert = new X509CertImpl(info);
        cert.sign(privateKey, sigAlg);
        X509Certificate certificate = (X509Certificate) cert;
        X509Certificate issueCertificate = readX509Certificate(issueCrtPath);
        X509Certificate[] X509Certificates = new X509Certificate[] {
                certificate, issueCertificate };
        createKeyStore(subjectAlias, certAndKeyGen.getPrivateKey(),
                subjectPassword.toCharArray(), X509Certificates, subjectPfxPath);
        FileOutputStream fos = new FileOutputStream(new File(subjectCrtPath));
        fos.write(certificate.getEncoded());
        fos.close();
    }

    //创建根证书
    public void createRootCert(String rootPfxPath, String issueCrtPath,
                               X500Name issue,String rootPriPemPath,String chainPath) throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException, IOException,
            CertificateException, SignatureException, KeyStoreException {
        //生成根证书密钥对
        CertAndKeyGen rootCertAndKeyGen = new CertAndKeyGen("RSA",
                "MD5WithRSA", null);
        //加密算法
        rootCertAndKeyGen.setRandom(secureRandom);
        //生成长度
        rootCertAndKeyGen.generate(1024);
        //根证书的信息  (别名跟有效期)
        X509Certificate rootCertificate = rootCertAndKeyGen.getSelfCertificate(
                issue, 3650 * 24L * 60L * 60L);
        //证书链
        X509Certificate[] X509Certificates = new X509Certificate[] { rootCertificate };
        String password = "123456";
        //创建密钥库
        createKeyStore("RootCA", rootCertAndKeyGen.getPrivateKey(), password
                .toCharArray(), X509Certificates, rootPfxPath);

        FileOutputStream fos = new FileOutputStream(new File(issueCrtPath));
        fos.write(rootCertificate.getEncoded());
        fos.close();
    }

    private void createKeyStore(String rootCA, PrivateKey privateKey, char[] password, X509Certificate[] chain, String rootPfxPath, String rootPriPemPath, String chainPath) {
        try {
            KeyStore keyStore = KeyStore.getInstance("pkcs12");
            keyStore.load(null,password);
            keyStore.setKeyEntry(rootCA,privateKey,password,chain);
            FileOutputStream fos = new FileOutputStream(rootPfxPath);
            keyStore.store(fos,password);
            fos.close();

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //证书私钥存储设施
        //  alias KeyStore别名 密钥（这里是私钥）保存密码 证书链 PFX文件路径
    private void createKeyStore(String alias, Key key, char[] password,
                                java.security.cert.Certificate[] chain, String issuePfxPath) throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException {

        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        keyStore.load(null, password);
        keyStore.setKeyEntry(alias, key, password, chain);
        FileOutputStream fos = new FileOutputStream(issuePfxPath);
        keyStore.store(fos, password);
        fos.close();
    }

    //读取PFX文件中的私钥
    public PrivateKey readPrivateKey(String alias, String pfxPath,
                                     String password) throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException,
            UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        FileInputStream fis = null;
        fis = new FileInputStream(pfxPath);
        keyStore.load(fis, password.toCharArray());
        fis.close();
        return (PrivateKey) keyStore.getKey(alias, password.toCharArray());
    }

    //读取X.509证书
    public X509Certificate readX509Certificate(String crtPath)
            throws CertificateException, IOException {
        InputStream inStream = null;
        inStream = new FileInputStream(crtPath);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf
                .generateCertificate(inStream);
        inStream.close();
        return cert;
    }

    public static void main(String args[]) throws IOException {

        // CN commonName 一般名字
        // L localityName 地方名
        // ST stateOrProvinceName 州省名
        // O organizationName 组织名
        // OU organizationalUnitName 组织单位名
        // C countryName 国家
        // STREET streetAddress 街道地址
        // DC domainComponent 领域
        // UID user id 用户ID
        X500Name issue = new X500Name("CN=RootCA,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        X500Name subject = new X500Name(
                "CN=subject,OU=ISI,O=BenZeph,L=CD,ST=SC,C=CN");
        String issuePfxPath = "D://MyCert/ROOTCA.pfx";
        String issueCrtPath = "D://MyCert/ROOTCA.crt";
//        String issuePemPath = "D://MyCert/ROOTCA.pem";
//        String issuePemPath = "D://MyCert/ROOTCA.pem";
        String subjectPfxPath = "D://MyCert/ISSUE.pfx";
        String subjectCrtPath = "D://MyCert/ISSUE.crt";

        String issueAlias = "RootCA";
        String subjectAlias = "subject";

        String issuePassword = "123456";
        String subjectPassword = "123456";

        X509CertTest test = new X509CertTest();

        try {
            test.createRootCert(issuePfxPath, issueCrtPath, issue,null,null);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            test.createIssueCert(issue, subject, issueAlias, issuePfxPath,
                    issuePassword, issueCrtPath, subjectAlias, subjectPfxPath,
                    subjectPassword, subjectCrtPath);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
