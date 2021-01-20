package org.ordjo.service;

import org.ordjo.model.Interest;
import org.ordjo.model.InterestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InterestService {
    @Autowired
    private InterestDao interestDao;

    public long count() {
        return interestDao.count();
    }

    public Interest get(String interestName) {
        return interestDao.findOneByName(interestName);
    }

    public void save(Interest interest) {
        interestDao.save(interest);
    }

    @Transactional
    public Interest createIfNotExist(String interestText) {
        Interest interest = interestDao.findOneByName(interestText);

        if (interest == null) {
            interest = new Interest(interestText);
            interestDao.save(interest);
        }

        return interest;
    }
}
