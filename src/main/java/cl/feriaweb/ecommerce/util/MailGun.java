/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.util;

/**
 *
 * @author Alejandro
 */
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MailGun {
	public JsonNode sendSimpleMessage() throws UnirestException {
		HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + "sandbox7ba3328d4bda478ea13765a23e1e5024.mailgun.org" + "/messages")
				.basicAuth("api", "key-08967245d563b33a2303f98673f6457d")
				.queryString("from", "Alejandro Arévalo Sánchez <alejandro.arevalo.sanchez@gmail.com>")
				.queryString("to", "alej.arevalo@alumnos.duoc.cl")
				.queryString("subject", "hello")
				.queryString("text", "testing").asJson();
		return request.getBody();
	}
}