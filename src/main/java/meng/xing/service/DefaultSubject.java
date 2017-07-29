package meng.xing.service;

import meng.xing.entity.Subject;
import meng.xing.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultSubject implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    @Override
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }
}
