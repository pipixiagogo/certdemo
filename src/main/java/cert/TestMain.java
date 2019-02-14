package cert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class TestMain {
    public static void main(String[] args) throws CertificateException, IOException,KeyStoreException{
        InputStream inStream = null;
        inStream = TestMain.class.getClassLoader().getResourceAsStream("rootcert_server.pem");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf
                .generateCertificate(inStream);
        inStream.close();
        System.out.println(cert);

//        InputStream in = TestMain.class.getClassLoader().getResourceAsStream("rootcert_server.pem");
//        KeyStore ks = KeyStore.getInstance("pkcs12");
       // ks.load(in,"123456".toCharArray());

    }
}
