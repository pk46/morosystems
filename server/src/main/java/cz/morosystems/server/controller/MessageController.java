package cz.morosystems.server.controller;

import cz.morosystems.server.model.Message;
import cz.morosystems.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageRepository repository;

    @GetMapping(value="/messages")
    public List<Message> getInfo() {

        return repository.findAll();
    }
}
