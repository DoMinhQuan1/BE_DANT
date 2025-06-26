package com.example.Gears.Service;

import org.springframework.stereotype.Service;

import com.example.Gears.Entity.Contact;
@Service
public interface ContactService {
	public Contact saveContact(Contact contact);
}
