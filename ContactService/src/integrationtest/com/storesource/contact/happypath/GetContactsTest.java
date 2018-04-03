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
public class GetContactsTest {
	
	@Autowired
	private ContactController picktripController;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@LocalServerPort
	private int port;
	
	@SuppressWarnings("deprecation")
	@Test
	public void GetSingleContactbyContactIDSuccessfully() throws Exception {

		headers.add("Content-Type", "application/json");
		headers.add("Accept-Type", "application/json");
		headers.add("User-Context", ContactRequestCreator.ValidAuthenticatedUserContextString() );
		headers.add("Authorization","Basic MTIzMzQzNTM2Mjc6dGVzdHBhc3N3b3Jk");
		String resultExpected = "{\"emailAddress\":\"test@test.com\",\"name\":\"Testeshwar Updated\",\"contactID\":\"12334353627test@test.com\",\"phoneNumbers\":{\"testDefault\":\"78587587587\",\"testSecondary\":\"78587953434\"}}";
		//Add a read to confirm if contact is not present before getting
		
		HttpEntity<ContactRequest> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("contactprocessor/contacts/12334353627test@test.com"), HttpMethod.GET,
				entity, String.class);
		assertEquals(resultExpected, response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void GetMultipleContactsbyContactIDbyEmailIDsuccessfully() throws Exception {

		headers.add("Content-Type", "application/json");
		headers.add("Accept-Type", "application/json");
		headers.add("User-Context", ContactRequestCreator.ValidAuthenticatedUserContextString() );
		headers.add("Authorization","Basic MTIzMzQzNTM2Mjc6dGVzdHBhc3N3b3Jk");
		String resultExpected = "{\"emailAddress\":\"test@test.com\",\"name\":\"Testeshwar Updated\",\"contactID\":\"12334353627test@test.com\",\"phoneNumbers\":{\"testDefault\":\"78587587587\",\"testSecondary\":\"78587953434\"}}";
		//Add a read to confirm if contact is not present before getting
		
		HttpEntity<ContactRequest> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("contactprocessor/contacts/12334353627test@test.com"), HttpMethod.GET,
				entity, String.class);
		assertEquals(resultExpected, response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}