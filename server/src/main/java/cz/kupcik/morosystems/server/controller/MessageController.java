package cz.kupcik.morosystems.server.controller;

import cz.kupcik.morosystems.server.model.Message;
import cz.kupcik.morosystems.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
