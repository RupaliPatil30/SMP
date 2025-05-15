package com.scm.Service;

import com.scm.Binding.DashboardResponse;
import com.scm.Entity.Counsellor;
import org.springframework.stereotype.Service;

public interface CounsellorService {

    public String saveCounsellor(Counsellor c);

    public Counsellor loginCheck(String email, String pwd);

    public boolean recoverPwd(String email);

    public DashboardResponse getDashboardInfo(Integer cid);

}