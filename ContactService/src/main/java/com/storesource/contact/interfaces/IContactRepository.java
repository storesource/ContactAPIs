package com.storesource.contact.interfaces;

import com.storesource.contact.repository.DynamoContactDTO;

public interface IContactRepository {

	void create(DynamoContactDTO contact);

}
