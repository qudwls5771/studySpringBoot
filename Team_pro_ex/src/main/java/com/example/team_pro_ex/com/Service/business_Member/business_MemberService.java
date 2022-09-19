package com.example.team_pro_ex.com.Service.business_Member;

import com.example.team_pro_ex.com.Entity.business_Member.businessMember;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface business_MemberService {

    List<businessMember> getbusiness_MemberList(businessMember business_member);

    void insertBusiness_Member(businessMember business_member);

    void updateBusiness_Member(businessMember business_member);

    void deleteUpdateBusiness_Member(businessMember business_member);

    public Map<String, String > business_Member_Availability(Errors errors);


    public businessMember getMember(businessMember business_member);

}
