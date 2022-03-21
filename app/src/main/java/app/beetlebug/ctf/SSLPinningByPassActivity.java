package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.sql.Connection;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import app.beetlebug.R;

public class SSLPinningByPassActivity extends AppCompatActivity {

    public SSLContext context = null;
    public SSLContext context1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sslpinning_by_pass);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
    }

    public void doBasicAuth(View view) {
        new Connection().execute();
    }

    private class Connection extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            connect();
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
        }

        private void connect() {
            CertificateFactory cf = null;
            try {
                cf = CertificateFactory.getInstance("X.509");
            } catch (CertificateException e) {
                e.printStackTrace();
            }

            try {
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return false;
                    }
                });
                InputStream caInput = getAssets().open("cacert.crt");
                Certificate ca = null;
                try {
                    ca = cf.generateCertificate(caInput);
                } catch (CertificateException e) {
                    e.printStackTrace();
                } finally {
                    caInput.close();
                }

                // create keystore containing trusted CAs
                String keystoreType = KeyStore.getDefaultType();
                KeyStore keyStore = KeyStore.getInstance(keystoreType);
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", ca);

                // create a TrustManager that trusts the CAs in our Keystore
                String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
                tmf.init(keyStore);

                // create SSLContext that uses TrustManager
                context = SSLContext.getInstance("TLS");
                context.init(null, tmf.getTrustManagers(), null);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
            URL url = null;
            try {
                url = new URL("https://github.com");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpsURLConnection urlConnection = null;
            try {
                urlConnection = (HttpsURLConnection) url.openConnection();
                final String basicAuth = "123";
//                final String basicAuth = "Basic " + Base64.getEncoder().toString("user:pass".getBytes(), Base64.NO_WRAP);
                urlConnection.setRequestProperty("Authorization", basicAuth);
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.setSSLSocketFactory(context.getSocketFactory());
            try {
                System.out.println(urlConnection.getResponseMessage());
                System.out.println(urlConnection.getResponseCode());
                if (urlConnection.getResponseCode() == 200) {
                    InputStream in = urlConnection.getInputStream();
                    String line;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder out = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                    }
                    System.out.println(out.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}