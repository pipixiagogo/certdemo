package X509CertTest;

import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import java.io.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class MyMain {
    public static final String ROOT_PATH = "rootca.crt";
    public static final String ROOT_PEM = "rootcert_server.pem";

    public static void main(String[] args) {
        InputStream stream = null;
        try {
            File file = new File("D://MyCert123.pem");
            stream = MyMain.class.getClassLoader().getResourceAsStream(ROOT_PEM);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf
                    .generateCertificate(stream);

            JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(new FileWriter(file));
            System.out.println(cert);
            jcaPEMWriter.writeObject(cert);
            jcaPEMWriter.close();
        } catch (Exception e) {

        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
