package cz.fio.testjavistaspring.service;

import java.util.Map;

public interface IContactService {

    boolean areContactParamsValid(Map<String, String> contactParams);

    String writeContact(Map<String, String> contactParams);

}
