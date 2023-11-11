package thuan.java.email.services.postmark;

import java.io.IOException;
import java.util.HashMap;

import com.postmarkapp.postmark.Postmark;
import com.postmarkapp.postmark.client.ApiClient;
import com.postmarkapp.postmark.client.data.model.templates.TemplatedMessage;
import com.postmarkapp.postmark.client.exception.PostmarkException;

/**
 * Hello world!
 *
 */
public class App {
	private static final String FROM_EMAIL = "pharmacity-online@thuan-it.com";
	private static final String API_KEY = "3359bef8-a13f-4332-bbcf-a00f25abf1e3";

	public static void main(String[] args) {
		sendWithResetPassword("thuan.uit@gmail.com");
	}

	private static void sendWithResetPassword(String sendTo) {
		ApiClient client = Postmark.getApiClient(API_KEY);
		TemplatedMessage message = new TemplatedMessage(FROM_EMAIL, sendTo);
		// Template Id for Password reset
		message.setTemplateId(33779624);
		// set model as HashMap
		HashMap<String, String> model = new HashMap<String, String>();
		model.put("product_name", "thuan-it Service Team");
		model.put("company_name", "thuan-it Company");
		model.put("company_address", "Da Nang, Viet Nam");

		model.put("name", "Mr XXXX");
		model.put("action_url", "www.thuan-it.com/reset-password");
		model.put("support_url", "www.thuan-it.com/support");

		message.setTemplateModel(model);
		try {
			client.deliverMessageWithTemplate(message);
			System.out.println("Email sent");
		} catch (PostmarkException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
