package com.scm.Service;

import com.scm.Binding.SearchCriteria;

import com.scm.Entity.StudentEnq;
import org.springframework.stereotype.Service;

import java.util.List;
public interface EnquiryService {

    public boolean addEnq(StudentEnq se);

    public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s);
}