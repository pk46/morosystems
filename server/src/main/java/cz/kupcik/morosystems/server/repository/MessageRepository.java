package cz.kupcik.morosystems.server.repository;

import cz.kupcik.morosystems.server.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
