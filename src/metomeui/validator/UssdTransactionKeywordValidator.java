package metomeui.validator;

import metomeui.model.UssdTransactionKeyword;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component("ussdTransactionKeywordValidator")
public class UssdTransactionKeywordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UssdTransactionKeyword.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UssdTransactionKeyword ussdTransactionKeyword = (UssdTransactionKeyword) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "keywordName",
				"keywordName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "keywordCode",
				"keywordCode.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "keywordStepsCount",
				"keywordStepsCount.required");
		if (ussdTransactionKeyword.getKeywordEnabledFlag()==null) {
			ussdTransactionKeyword.setKeywordEnabledFlag(0);
		}
		
		if (ussdTransactionKeyword.getKeywordCode().length()>10){
			errors.rejectValue("keywordName",
					"keywordCode.lengthLimit");
			
		}
		/*if (ussdTransactionKeyword.getKeywordId() == 0) {
			errors.rejectValue("keywordName",
					"keywordName.required");
		}*/
	}
}
