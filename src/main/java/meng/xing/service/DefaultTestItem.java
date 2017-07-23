package meng.xing.service;

import meng.xing.entity.TestItem;
import meng.xing.repository.TestItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DefaultTestItem implements TestItemService {
    @Autowired
    TestItemRepository testItemRepository;

    @Override
    public Page<TestItem> findAllTestItems(Pageable pageable) {
        return testItemRepository.findAll(pageable);
    }
}
