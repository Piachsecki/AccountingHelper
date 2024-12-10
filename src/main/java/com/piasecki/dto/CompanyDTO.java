package com.piasecki.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDTO {
    private String companyName;
    private String companyAddress;
    private String companyNIP;
}
