package cz.fio.testjavistaspring.service.impl;

import cz.fio.testjavistaspring.entity.ContactEntity;
import cz.fio.testjavistaspring.persistence.IContactRepository;
import cz.fio.testjavistaspring.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final IContactRepository contactRepository;

    private ContactEntity createContactEntity(Map<String, String> contactParams) {
        return new ContactEntity(
                contactParams.get("firstName"),
                contactParams.get("lastName"),
                contactParams.get("email")
        );
    }

    @Override
    public boolean areContactParamsValid(Map<String, String> contactParams) {
        return contactParams.containsKey("firstName") &&
                contactParams.containsKey("lastName") &&
                contactParams.containsKey("email") &&
                contactParams.size() == 3;
    }

    @Override
    public String writeContact(Map<String, String> contactParams) {
        ContactEntity newEntity = createContactEntity(contactParams);

        return contactRepository.saveContact(newEntity);
    }
}
