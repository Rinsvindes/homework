package ru.homework.loanmaster.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.homework.loanmaster.dao.BlockedPersonalIdRepository;
import ru.homework.loanmaster.model.BlockedPersonalId;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlackListService {

    @Resource
    private BlockedPersonalIdRepository blockedPersonalIdRepository;

    private Set<String> blockedList = new HashSet<>();

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void reloadBlackList() {
        List<BlockedPersonalId> blockedPersonalIdList = blockedPersonalIdRepository.getBlockedPersonalIds();
        if (!blockedPersonalIdList.isEmpty()) {
            blockedList = blockedPersonalIdList.stream()
                    .map(BlockedPersonalId::getPersonalId)
                    .collect(Collectors.toSet());
        }
    }

    public boolean isPersonalIdBlocked(String personalId) {
        return blockedList.contains(personalId);
    }

}
