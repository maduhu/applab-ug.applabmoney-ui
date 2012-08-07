package metomeui.validator;

import metomeui.model.AccountType;
import metomeui.model.AmlBarring;
import metomeui.model.GlobalKeywordCharge;
import metomeui.model.Language;
import metomeui.model.MemoGroup;
import metomeui.model.Message;
import metomeui.model.NdcListOffnet;
import metomeui.model.NdcListPToP;
import metomeui.model.SystemConfiguration;
import metomeui.model.User;
import metomeui.service.SystemSettingsService;
import metomeui.service.SystemSettingsServiceImplementation;
import metomeui.service.UssdMenuService;
import metomeui.service.UssdMenuServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

@Component("systemSettingsValidator")
public class SystemSettingsValidator implements Validator {

	@Autowired
	SystemSettingsService systemSettingsService = new SystemSettingsServiceImplementation();

	@Override
	public boolean supports(Class<?> clazz) {
		return Message.class.isAssignableFrom(clazz)
				|| Language.class.isAssignableFrom(clazz)
				|| AccountType.class.isAssignableFrom(clazz)
				|| AmlBarring.class.isAssignableFrom(clazz)
				|| GlobalKeywordCharge.class.isAssignableFrom(clazz)
				|| MemoGroup.class.isAssignableFrom(clazz)
				|| NdcListOffnet.class.isAssignableFrom(clazz)
				|| NdcListPToP.class.isAssignableFrom(clazz)
				|| SystemConfiguration.class.isAssignableFrom(clazz)
				|| User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors, Long id) {
		if (target instanceof Message) {
			Message message = (Message) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "messageCode",
					"messageCode.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "messageText",
					"messageText.required");
		} else if (target instanceof Language) {
			Language language = (Language) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "languageName",
					"languageName.required");
			if ((language.getLanguageName() != null)
					|| (language.getLanguageName() != "")) {
				if ((systemSettingsService.checkIfDuplicateLanguageName(
						language.getLanguageName(), language.getLanguageId())) == true) {
					errors.rejectValue("predefInputItemName",
							"predefInputItemName.duplicate");
				}
			}
		} else if (target instanceof MemoGroup) {
			MemoGroup memoGroup = (MemoGroup) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memoGroupName",
					"memoGroupName.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memoGroupCode",
					"memoGroupCode.required");
		} else if (target instanceof NdcListOffnet) {
			NdcListOffnet ndcListOffnet = (NdcListOffnet) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ndc",
					"ndc.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"networkOperator", "networkOperator.required");
		} else if (target instanceof NdcListPToP) {
			NdcListPToP ndcListPToP = (NdcListPToP) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ndc",
					"ndc.required");
		} else if (target instanceof AccountType) {
			AccountType accountType = (AccountType) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"accountTypeCode", "accountTypeCode.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"accountTypeName", "accountTypeName.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"accountMaximumBookBalance",
					"accountMaximumBookBalance.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"accountTypeBitMap", "accountTypeBitMap.required");
		} else if (target instanceof GlobalKeywordCharge) {
			GlobalKeywordCharge globalKeywordCharge = (GlobalKeywordCharge) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chargePercent",
					"chargePercent.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chargeFixed",
					"chargeFixed.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lowRange",
					"lowRange.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "highRange",
					"highRange.required");
			if (globalKeywordCharge.getLowRange() >= globalKeywordCharge
					.getHighRange()) {
				errors.rejectValue("lowRange", "lowRange.invalid");
			}
			if (globalKeywordCharge.getHighRange() <= globalKeywordCharge
					.getLowRange()) {
				errors.rejectValue("highRange", "highRange.invalid");
			}
		} else if (target instanceof SystemConfiguration) {
			SystemConfiguration systemConfiguration = (SystemConfiguration) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ndc",
					"ndc.required");
		}
	}

	@Override
	public void validate(Object target, Errors errors) {
		validate(target, errors, null);
	}
}
