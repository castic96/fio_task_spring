package cz.fio.testjavistaspring.controller;

import cz.fio.testjavistaspring.configuration.Pages;
import cz.fio.testjavistaspring.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(
        path = Pages.CONTACT
)
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @GetMapping
    public String getContact(@RequestParam Map<String, String> params) {

        if (contactService.areContactParamsValid(params)) {
            return contactService.writeContact(params);
        }
        else {
            return "Contact parameters are invalid!";
        }

    }
}
