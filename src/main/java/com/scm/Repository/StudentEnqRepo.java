package com.scm.Repository;


import com.scm.Entity.StudentEnq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface StudentEnqRepo extends JpaRepository<StudentEnq, Integer>{

    public List<StudentEnq> findByCid(Integer cid);

}

