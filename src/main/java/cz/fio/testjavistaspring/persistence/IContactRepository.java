package cz.fio.testjavistaspring.persistence;

import cz.fio.testjavistaspring.entity.ContactEntity;

public interface IContactRepository {
    String saveContact(ContactEntity contactEntity);

}
