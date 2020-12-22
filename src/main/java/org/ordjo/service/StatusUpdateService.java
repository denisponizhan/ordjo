package org.ordjo.service;

import org.ordjo.model.StatusUpdate;
import org.ordjo.model.StatusUpdateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusUpdateService {

    @Autowired
    private StatusUpdateDao statusUpdateDao;

    public void save(StatusUpdate statusUpdate) {
        statusUpdateDao.save(statusUpdate);
    }

    public StatusUpdate getLatest() {
        return statusUpdateDao.findFirstByOrderByAddedDesc();
    }

    public StatusUpdate findById(Long id) {
        return statusUpdateDao.findById(id).get();
    }
}
