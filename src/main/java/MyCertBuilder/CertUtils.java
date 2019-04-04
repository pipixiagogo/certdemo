package MyCertBuilder;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.*;

import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

public class CertUtils {
    private static SecureRandom secureRandom;
    private static String RSAOREC = "RSA";//  EC
    private static String SIGN_AL = "SHA1withRSA";// SHA384withECDSA  MD5WithRSA
    private static int LEN = 1024;
    static {
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
    //创建根证书
    public static void createRootCert(String rootAlias,String rootPfxPath, String issueCrtPath,
                               X500Name issue,String password, String rootPrivatePemPath, String chainPath) throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException, IOException,
            CertificateException, SignatureException, KeyStoreException {
        //生成根证书密钥对
        CertAndKeyGen rootCertAndKeyGen = new CertAndKeyGen(RSAOREC,
                SIGN_AL, null);
        //加密算法
        rootCertAndKeyGen.setRandom(secureRandom);
        //生成长度
        rootCertAndKeyGen.generate(LEN);
        //根证书的信息  (别名跟有效期)
        X509Certificate rootCertificate = rootCertAndKeyGen.getSelfCertificate(
                issue, 3650 * 24L * 60L * 60L);
        //证书链
        X509Certificate[] X509Certificates = new X509Certificate[] { rootCertificate };
        //创建密钥库
        createKeyStore(rootAlias, rootCertAndKeyGen.getPrivateKey(), password
                .toCharArray(), X509Certificates, rootPfxPath,rootPrivatePemPath,chainPath);

        FileOutputStream fos = new FileOutputStream(new File(issueCrtPath));
        fos.write(rootCertificate.getEncoded());
        fos.close();
    }

    private static void createKeyStore(String rootAlias, PrivateKey privateKey, char[] password,
                                X509Certificate[] chain, String rootPfxPath,
                                String rootPrivatePemPath, String chainPath) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
        //保存根证书的pfx文件
        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        keyStore.load(null, password);
        keyStore.setKeyEntry(rootAlias, privateKey, password, chain);
        FileOutputStream fos = new FileOutputStream(rootPfxPath);
        keyStore.store(fos, password);
        fos.close();
        //保存私钥文件
        savePrivateKey(rootPrivatePemPath,privateKey);
        saveChainPem(chainPath,chain);
    }

    private static void saveChainPem(String chainPath, X509Certificate[] chain) throws IOException{
        File file = new File(chainPath);
        if(!file.exists()){
            file.createNewFile();
        }
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(new FileWriter(file));
        for(X509Certificate cert:chain){
            jcaPEMWriter.writeObject(cert);
        }
        jcaPEMWriter.close();
    }

    private static void savePrivateKey(String rootPrivatePemPath, PrivateKey privateKey) throws IOException{
        File file = new File(rootPrivatePemPath);
        if(!file.exists()){
            file.createNewFile();
        }
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(new FileWriter(file));
        jcaPEMWriter.writeObject(privateKey);
        jcaPEMWriter.close();
    }


    //颁发证书
    public static void createIssueCert(X500Name root, X500Name second,
                                String rootAlias, String rootPfxPath, String rootPassword,
                                String rootCrtPath, String secondAlias, String secondPfxPath,
                                String secondPassword, String secondCrtPath,String secondPrivatePem,
                                String secondChainPem)
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, CertificateException, IOException,
            KeyStoreException, UnrecoverableKeyException, SignatureException {
        CertAndKeyGen certAndKeyGen = new CertAndKeyGen(RSAOREC, SIGN_AL,
                null);
        certAndKeyGen.setRandom(secureRandom);
        certAndKeyGen.generate(1024);
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
        info.set(X509CertInfo.SUBJECT,second );
        info.set(X509CertInfo.KEY, new CertificateX509Key(certAndKeyGen
                .getPublicKey()));
        info.set(X509CertInfo.VALIDITY, interval);
        info.set(X509CertInfo.ISSUER,root );
        PrivateKey privateKey = readPrivateKey(rootAlias, rootPfxPath,
                rootPassword);
        X509CertImpl cert = new X509CertImpl(info);
        cert.sign(privateKey, sigAlg);
        X509Certificate certificate = (X509Certificate) cert;
        X509Certificate issueCertificate = readX509Certificate(rootCrtPath);
        X509Certificate[] X509Certificates = new X509Certificate[] {
                certificate, issueCertificate };
        createKeyStore(secondAlias, certAndKeyGen.getPrivateKey(),
                secondPassword.toCharArray(), X509Certificates, secondPfxPath,secondPrivatePem,secondChainPem);
        FileOutputStream fos = new FileOutputStream(new File(secondCrtPath));
        fos.write(certificate.getEncoded());
        fos.close();
    }

   //读取cet证书
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
    //读取私钥 从pfx文件中  pfx文件中有私钥跟公钥 crt文件只有公钥
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



}
