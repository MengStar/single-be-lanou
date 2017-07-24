package meng.xing.service;

import meng.xing.entity.TestItem;
import meng.xing.repository.TestItemRepository;
import meng.xing.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DefaultTestItem implements TestItemService {
    final Logger logger = LoggerFactory.getLogger(DefaultTestItem.class);
    @Autowired
    TestItemRepository testItemRepository;
    @Autowired
    UserRoleRepository userRoleRepository;


    @Override
    public Page<TestItem> findTestItemsByType(String type, Pageable pageable) {
        return testItemRepository.findByType(type, pageable);
    }

    @Override
    public boolean addTestItme(TestItem testItem) {
        return false;
    }

    @Override
    public boolean updateTestItem(TestItem testItem) {
        return false;
    }

    @Override
    public boolean deleteTestItemById(Long id) {
        return false;
    }
}
