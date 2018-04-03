package com.storesource.contact.happypath;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.storesource.contact.ContactApplication;
import com.storesource.contact.api.ContactController;
import com.storesource.contact.interfaces.IContact;
import com.storesource.contact.interfaces.IContactService;
import com.storesource.contact.repository.AWSDynamoRepository;
import com.storesource.contact.repository.DynamoContactDTO;
import com.storesource.contact.request.ContactRequest;
import com.storesource.contact.service.ContactService;
import com.storesource.contact.utils.ContactRequestCreator;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ContactApplication.class)
public class UpdateContactTest {
	
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
		
		ContactRequest requestBody = ContactRequestCreator.ValidContactRequestBody();
		//Add a read to confirm item is present and validate for a parameter in DB and requestBody created above, which is later updated
		requestBody.setName("Testeshwar Updated");
		
		HttpEntity<ContactRequest> entity = new HttpEntity<>(requestBody, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("contactprocessor/contacts/12334353627test@test.com"), HttpMethod.PUT,
				entity, String.class);
		assertEquals("Contact successfully updated", response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
		//Add an assert to confirm item is updated
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
