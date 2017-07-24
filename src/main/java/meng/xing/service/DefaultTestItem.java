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
    public Page<TestItem> findAllTestItems(Pageable pageable) {
        Page<TestItem> page1 = testItemRepository.findAll(pageable);
        //若是懒加载，则需要调用一哈，否则不会去查询
        //page1.forEach(testItem -> testItem.getSubject().toString());
        return page1;

    }

    @Override
    public Page<TestItem> findTestItemsByType(String type, Pageable pageable) {
        return testItemRepository.findByType(type,pageable);
    }
}
