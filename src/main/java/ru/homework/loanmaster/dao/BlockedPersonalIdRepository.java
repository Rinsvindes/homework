package ru.homework.loanmaster.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.homework.loanmaster.model.BlockedPersonalId;

import java.util.List;

public interface BlockedPersonalIdRepository extends CrudRepository<BlockedPersonalId, Long> {
    @Query("from BlockedPersonalId l")
    List<BlockedPersonalId> getBlockedPersonalIds();
}
