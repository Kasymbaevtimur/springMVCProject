package peaksoft.dao;

import peaksoft.entities.Company;
import peaksoft.entities.Course;

import java.util.List;

public interface CompanyDAO {

    List<Company> getAllCompanies();


    void addCompany(Company company);

    Company getCompanyById(Long id);

    void updateCompany(Company company);

    void deleteCompany(Company company);

}
