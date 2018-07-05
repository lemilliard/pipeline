/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.pipeline.common;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import fr.epsi.i4.pipeline.Main;
import fr.epsi.i4.pipeline.microservice.microserviceclient.MicroServiceClient;
import fr.epsi.i4.pipeline.microservice.microserviceclient.MicroServiceResource;
import fr.epsi.i4.pipeline.microservice.microserviceclient.Resource;
import fr.epsi.i4.pipeline.model.Mail;
import fr.epsi.i4.pipeline.model.Response;
import fr.epsi.i4.pipeline.model.bdd.abonnement.Abonnement;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author kbouzan
 */
public class ThreadMail implements Runnable {

    private Abonnement[] abonnements;
    private String subject;
    private String body;
    private HttpPost httpPost;
    private Gson gson;
    private HttpClient client;

    public ThreadMail(Abonnement[] abonnements, String subject, String body, HttpPost httpPost, Gson gson, HttpClient client) {
        this.abonnements = abonnements;
        this.subject = subject;
        this.body = body;
        this.httpPost = httpPost;
        this.gson = gson;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            sendToMail(abonnements, subject, body, httpPost, gson, client);
        } catch (IOException ex) {
            Logger.getLogger(ThreadMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendToMail(Abonnement[] abonnements, String subject, String body, HttpPost httpPost, Gson gson, HttpClient client) throws UnsupportedEncodingException, IOException {
        Mail mail;
        StringEntity stringEntity;
        httpPost.setHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        for (Abonnement abonnement : abonnements) {
            mail = new Mail(abonnement.user.email, subject, body);
            stringEntity = new StringEntity(gson.toJson(mail));
            httpPost.setEntity(stringEntity);

            client.execute(httpPost);
        }
    }

}
