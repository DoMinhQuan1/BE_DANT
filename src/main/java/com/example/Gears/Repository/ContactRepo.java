package com.example.Gears.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gears.Entity.Category;
import com.example.Gears.Entity.Contact;
@Repository
public interface ContactRepo  extends JpaRepository<Contact, Long>{

}
