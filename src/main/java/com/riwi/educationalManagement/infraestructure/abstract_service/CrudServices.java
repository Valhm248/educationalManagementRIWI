package com.riwi.educationalManagement.infraestructure.abstract_service;

import org.springframework.data.domain.Page;

public interface CrudServices <RQ, RS, ID> {

    RS create(RQ request);

    RS get(ID id);

    Page<RS> getAll(int page, int size);

    RS update(RQ request, ID id);

    void delete(ID id);    
}
