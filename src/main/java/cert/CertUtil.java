package cert;



import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.OutputEncryptor;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.*;

import java.io.*;
import java.security.*;

import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

public class CertUtil {
    private static SecureRandom secureRandom;
    private static String RSAOREC = "RSA";//  EC
    private static String SIGN_AL = "SHA1withRSA";// SHA384withECDSA
    private static int LEN = 1024;//256
    static {
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建根证书（证书有效期10年，私钥保存密码“123456”，公钥算法“RSA”，签名算法“MD5WithRSA”）
     *
     * @param rootPfxPath
     *            Personal Information Exchange 路径
     * @param rootCrtPath
     *            证书路径
     * @param issue
     *            颁发者&接收颁发者
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws InvalidKeyException
     * @throws IOException
     * @throws CertificateException
     * @throws SignatureException
     * @throws KeyStoreException
     */
    //创建根证书 issue颁发者
    public static void createRootCert(
//            String issuePfxPath = "d:/CERT/client/rootstore.pfx";
//            String issueCrtPath = "d:/CERT/client/rootca.crt";
//            String issuePriKeyPath = "d:/CERT/client/rootkey.pem";
//            String issueAlias = "MyRootCAClient";
//            String rootpem = "d:/CERT/client/rootca.pem";
//            //root
//            CertUtil.createRootCert(issuePfxPath,
//            issueCrtPath, issuePriKeyPath,issue,issueAlias,rootpem);

           // rootPriPemPath:rootkey.pem
           //  rootAlias别名  rootAlias
           //issue ：X500Name
           //chainPath ：rootca.pem
            String rootPfxPath, String rootCrtPath,String rootPriPemPath,
            X500Name issue,String rootAlias,String chainPath) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, CertificateException, SignatureException, IOException, KeyStoreException, SignatureException, InvalidKeyException {
        CertAndKeyGen rootCertAndKeyGen = new CertAndKeyGen(RSAOREC,
                SIGN_AL, null);
        rootCertAndKeyGen.setRandom(secureRandom);
        rootCertAndKeyGen.generate(LEN);
        X509Certificate rootCertificate = rootCertAndKeyGen.getSelfCertificate(
                issue, 3650 * 24L * 60L * 60L);
        X509Certificate[] X509Certificates = new X509Certificate[] { rootCertificate };
        String password = "123456";

        //rootAlias根别名  私钥  密码 证书链 pfx文件路径  pem文件路径 证书链文件路径
        createKeyStore(rootAlias, rootCertAndKeyGen.getPrivateKey(), password
                .toCharArray(),  X509Certificates, rootPfxPath,rootPriPemPath,chainPath);
        FileOutputStream fos = new FileOutputStream(new File(rootCrtPath));
        fos.write(rootCertificate.getEncoded());
        fos.close();
    }

    /**
     * 颁布证书
     *
     * @param issue
     * @param subject
     * @param issueAlias
     * @param issuePfxPath
     * @param issuePassword
     * @param issueCrtPath
     * @param subjectAlias
     * @param subjectPfxPath
     * @param subjectPassword
     * @param subjectCrtPath
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws InvalidKeyException
     * @throws CertificateException
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws SignatureException
     */
    //签发证书
    public static void createIssueCert(X500Name issue, X500Name subject,
                                String issueAlias, String issuePfxPath, String issuePassword,
                                String issueCrtPath, String subjectAlias, String subjectPfxPath,
                                String subjectPassword, String subjectCrtPath,String subjectPemPath,String chainPath)
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, CertificateException, IOException,
            KeyStoreException, UnrecoverableKeyException, SignatureException {
        CertAndKeyGen certAndKeyGen = new CertAndKeyGen(RSAOREC,
                SIGN_AL, null);
        certAndKeyGen.setRandom(secureRandom);
        certAndKeyGen.generate(LEN);
        String sigAlg = SIGN_AL;
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
                certificate,issueCertificate };//issueCertificate
        createKeyStore(subjectAlias, certAndKeyGen.getPrivateKey(),
                subjectPassword.toCharArray(),X509Certificates, subjectPfxPath,subjectPemPath,chainPath);
        FileOutputStream fos = new FileOutputStream(new File(subjectCrtPath));
        fos.write(certificate.getEncoded());
        fos.close();
    }

    //保存私钥
    public static void savePrivateKey(File file, PrivateKey privateKey, OutputEncryptor encryptor) throws IOException {
        //JcaPKCS8Generator jcaPKCS8Generator = new JcaPKCS8Generator(privateKey, encryptor);
        StringWriter stringWriter = new StringWriter();
//        try (JcaPEMWriter pw = new JcaPEMWriter(stringWriter)) {
//            pw.writeObject(privateKey);
//        }
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(new FileWriter(file));
        jcaPEMWriter.writeObject(privateKey);
        jcaPEMWriter.close();
        //OutputStream out = new FileOutputStream(file);
       // System.out.println(stringWriter.toString());
//        out.write(stringWriter.toString().getBytes());
//        out.close();
    }
    /**
     * 证书私钥存储设施
     *
     * @param alias
     *            KeyStore别名
     * @param key
     *            密钥（这里是私钥）
     * @param password
     *            保存密码
     * @param chain
     *            证书链
     * @param filePath
     *            PFX文件路径
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     */
    //生成证书存储文件 filePath pfx文件路径
    private static void createKeyStore(String alias, Key key, char[] password,
                                       java.security.cert.Certificate[] chain, String filePath,String pemPath,String chainPath) throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException {
        File f = new File(pemPath);
        if( !f.exists() ){
            f.createNewFile();
        }
        boolean flag = true;
        if( null == chainPath || "".equals(chainPath) ){
            flag = false;
        }

        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        keyStore.load(null, password);
        keyStore.setKeyEntry(alias, key, password, chain);
        FileOutputStream fos = new FileOutputStream(filePath);
        keyStore.store(fos, password);
        fos.close();
        //保存私钥
        savePrivateKey(f,(PrivateKey) key,null);
/*        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(key.getEncoded());
        System.out.println( "byte:" + ByteUtils.toHexString(key.getEncoded()));
        System.out.println("base64:" + encoded);
        fw.write("-----BEGIN EC PRIVATE KEY-----\r\n");
        fw.write(encoded);
        fw.write("\r\n-----END EC PRIVATE KEY-----");  //非必须
        fw.close();*/
        if ( flag ){
            File ff = new File(chainPath);
            if( !ff.exists() ){
                ff.createNewFile();
            }
            JcaPEMWriter writer1 = new JcaPEMWriter( new FileWriter( ff ) );
            for( java.security.cert.Certificate cert : chain  ){
                writer1.writeObject( cert );
            }
            writer1.close();
        }
    }
    /**
     * 读取PFX文件中的私钥
     *
     * @param alias
     *            别名
     * @param pfxPath
     *            PFX文件路径
     * @param password
     *            密码
     * @return
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     * @throws UnrecoverableKeyException
     */
    public static PrivateKey readPrivateKey(String alias, String pfxPath,
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

    /**
     * 读取X.509证书
     *
     * @param crtPath
     *            证书路径
     * @return
     * @throws CertificateException
     * @throws IOException
     */
    public static X509Certificate readX509Certificate(String crtPath)
            throws CertificateException, IOException {
        InputStream inStream = null;
        inStream = new FileInputStream(crtPath);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf
                .generateCertificate(inStream);
        inStream.close();
        return cert;
    }
    //签发二级证书
    public static void createIssue2Cert(X500Name issue, X500Name subject,
                                        String issueAlias, String issuePfxPath, String issuePassword,
                                        String issueCrtPath, String rootCrtPath,String subjectAlias,
                                        String subjectPfxPath,
                                        String subjectPassword,
                                        String subjectCrtPath,String pemPath,String chainPath) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, IOException, CertificateException, UnrecoverableKeyException, KeyStoreException, SignatureException {
        CertAndKeyGen certAndKeyGen = new CertAndKeyGen(RSAOREC,
                SIGN_AL, null);
        certAndKeyGen.setRandom(secureRandom);
        certAndKeyGen.generate(LEN);
        String sigAlg = SIGN_AL;
        // 1年
        long validity = 3640 * 24L * 60L * 60L;
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
        X509Certificate rootcert = readX509Certificate(rootCrtPath);
        X509Certificate[] X509Certificates = new X509Certificate[] {
                certificate,issueCertificate,rootcert};//, issueCertificate,rootcert
        createKeyStore(subjectAlias, certAndKeyGen.getPrivateKey(),
                subjectPassword.toCharArray(),X509Certificates, subjectPfxPath,pemPath,chainPath);
        FileOutputStream fos = new FileOutputStream(new File(subjectCrtPath));
        fos.write(certificate.getEncoded());
        fos.close();
    }
}
