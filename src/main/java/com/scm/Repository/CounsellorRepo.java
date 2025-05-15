package com.scm.Repository;


import com.scm.Entity.Counsellor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer>{

    public Counsellor findByEmail(String email);
    public Counsellor findByEmailAndPwd(String email, String pwd);

}



