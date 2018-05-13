package com.storesource.contact.happypath;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.storesource.contact.ContactApplication;
import com.storesource.contact.api.ContactController;
import com.storesource.contact.request.ContactRequest;
import com.storesource.contact.utils.ContactRequestCreator;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ContactApplication.class)
public class DeleteContactTest {
	
	@Autowired
	private ContactController picktripController;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@LocalServerPort
	private int port;
	
	@SuppressWarnings("deprecation")
	@Test
	public void DeleteSingleContactSuccessfully() throws Exception {

		headers.add("Content-Type", "application/json");
		headers.add("Accept-Type", "application/json");
		headers.add("User-Context", ContactRequestCreator.ValidAuthenticatedUserContextString() );
		headers.add("Authorization","Basic MTIzMzQzNTM2Mjc6dGVzdHBhc3N3b3Jk");

		//Add a read to confirm item is present
		
		HttpEntity<ContactRequest> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("contactprocessor/contacts/12334353627test@test.com"), HttpMethod.DELETE,
				entity, String.class);
		assertEquals("Contact successfully deleted", response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
		//Add an assert to confirm item is deleted
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}