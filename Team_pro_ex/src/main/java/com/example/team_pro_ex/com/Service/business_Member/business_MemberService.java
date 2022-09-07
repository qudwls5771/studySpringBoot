package com.example.team_pro_ex.com.Service.business_Member;

import com.example.team_pro_ex.com.Entity.business_Member.business_Member;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface business_MemberService {

    List<business_Member> getbusiness_MemberList(business_Member business_member);

    void insertBusiness_Member(business_Member business_member);

    void updateBusiness_Member(business_Member business_member);

    void deleteUpdateBusiness_Member(business_Member business_member);

    public Map<String, String > business_Member_Availability(Errors errors);


}
