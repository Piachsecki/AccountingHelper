package com.piasecki.mapperImpl;

import com.piasecki.domain.Address;
import com.piasecki.domain.Company;
import com.piasecki.dto.CompanyDTO;
import com.piasecki.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

@Service
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public Company mapCompanyDTOtoCompanyEntity(CompanyDTO companyDTO) {
        return Company.builder()
                .companyName(companyDTO.getCompanyName())
                .NIP(companyDTO.getCompanyNIP())
                .address(Address.builder()
                        .street(companyDTO.getCompanyAddress())
                        .build())
                .build();
    }
}
