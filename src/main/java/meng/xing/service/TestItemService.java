package meng.xing.service;

import meng.xing.entity.TestItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestItemService {
    Page<TestItem> findAllTestItems(Pageable pageable);

    Page<TestItem> findTestItemsByType(String type, Pageable pageable);
}
