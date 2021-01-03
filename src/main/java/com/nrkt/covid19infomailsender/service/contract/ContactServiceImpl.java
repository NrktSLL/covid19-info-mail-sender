package com.nrkt.covid19infomailsender.service.contract;

import com.nrkt.covid19infomailsender.exception.CustomNotFoundException;
import com.nrkt.covid19infomailsender.models.PersonDto;
import com.nrkt.covid19infomailsender.domain.Person;
import com.nrkt.covid19infomailsender.repository.ContactRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContactServiceImpl implements ContactService {

    ContactRepository contactRepository;

    @Override
    @Transactional
    public void subscribe(PersonDto person) {
        var personInfo = Person.builder()
                .name(person.getName())
                .email(person.getEmail())
                .country(person.getCountry())
                .build();

        contactRepository.save(personInfo);
    }

    @Override
    public void unsubscribe(String email) {
        Person person = contactRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomNotFoundException("No record found for the specified mail!"));
        contactRepository.delete(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return contactRepository.findAll();
    }
}
