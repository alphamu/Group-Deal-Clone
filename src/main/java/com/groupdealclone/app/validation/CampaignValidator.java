package com.groupdealclone.app.validation;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.groupdealclone.app.domain.Campaign;


public class CampaignValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Campaign.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
        Campaign c = (Campaign) target;
        Date startDate = c.getStartDate();
        Date endDate = c.getEndDate();
        if (startDate == null) {
            errors.rejectValue("startDate", "validation.required");
        }
        if (endDate == null) {
            errors.rejectValue("endDate", "validation.required");
        }
        if(startDate != null && endDate != null && endDate.before(startDate)){
        	errors.rejectValue("endDate", "validation.notbefore.startdate");
        }
//        if(c.getImageStore() == null || c.getImageStore().getImage() == null || c.getImageStore().getImage().size() > 0){
//        	errors.rejectValue("imageStore", "validation.required");
//        }
	}

}
