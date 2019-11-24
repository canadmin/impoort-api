package com.impoort.impoortapi.repository.company;

import com.impoort.impoortapi.domain.company.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Experience,Integer> {
    List<Experience> findAllByCompanyIdAndStillWork(String companyId,boolean stillWork);
    List<Experience> findByWorkerId(String workerId);

}
