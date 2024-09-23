package com.uwv.spring_boot.repository;

import com.uwv.spring_boot.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<Message, UUID> {

    Optional<Message> findByMessage(String message);

}
