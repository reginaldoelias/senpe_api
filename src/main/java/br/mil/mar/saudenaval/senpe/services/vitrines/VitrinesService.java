package br.mil.mar.saudenaval.senpe.services.vitrines;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;

@Service
public class VitrinesService {

    String url =  "https://www.marinha.mil.br/saudenaval/jsonapi/node/vitrine_home";

    public ResponseEntity<String> loadVitrines(){
        OkHttpClient client = getunsafeOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        ResponseEntity<String> result;

        try(Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()){
               // throw new IOException("Erro inesperado: " + response);
                result = ResponseEntity.badRequest().body("Erro Inesperado");
            }else {
                result = ResponseEntity.ok().body(response.body().string());
                //System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ResponseEntity.badRequest().body("Erro Inesperado");
        }
        return result;
    }


        private OkHttpClient getunsafeOkHttpClient(){
            try {
                // Configurando TrustManager para ignorar validação SSL
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                            }
                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                            }
                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Inicializando o contexto SSL
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                // Construindo o OkHttpClient
                return new OkHttpClient.Builder()
                        .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                        .hostnameVerifier((hostname, session) -> true) // Ignorar validação do hostname
                        .build();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }



