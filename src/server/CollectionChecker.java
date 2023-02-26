package server;

import data.Coordinates;
import data.StudyGroup;
import data.exceptions.CrossingIdException;
import data.exceptions.CrossingPasportIDException;
import data.exceptions.IdException;

public class CollectionChecker {
    CollectionManager collectionManager;


    public CollectionChecker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean checkId(Integer id) throws IdException, CrossingIdException {
        if (id <= 0) {
            throw new IdException();
        }
        for (StudyGroup studyGroup : collectionManager.getStudyGroups()) {
            if (studyGroup.getId().equals(id)) {
                throw new CrossingIdException();
            }
        }
        return true;
    }

    public boolean checkPasportID(String msg) throws CrossingPasportIDException {
        for (StudyGroup studyGroup: collectionManager.getStudyGroups()){
            if (studyGroup.getGroupAdmin().getPassportID().equals(msg)){
                throw new CrossingPasportIDException("Введены не уникальные паспортные данные");
            }
        }
        return true;
    }
}
