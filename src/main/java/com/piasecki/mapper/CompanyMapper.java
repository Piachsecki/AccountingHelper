package com.piasecki.mapper;

import com.piasecki.domain.Company;
import com.piasecki.dto.CompanyDTO;

public interface CompanyMapper {
    Company mapCompanyDTOtoCompanyEntity(CompanyDTO companyDTO);
}
