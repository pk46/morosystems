package cz.kupcik.morosystems.server.controller;

import cz.kupcik.morosystems.server.model.Message;
import cz.kupcik.morosystems.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Autowired
    MessageRepository repository;

    @GetMapping(value="/messages")
    public ModelAndView getInfo(Map<String, Object> model) {
        List<Message> messages = repository.findAll();
        model.put("dataList", messages);
        return new ModelAndView("messages", model);
    }
}
