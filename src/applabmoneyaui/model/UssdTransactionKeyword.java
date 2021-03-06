package applabmoneyaui.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import applabmoneyaui.model.AmlBarring;
import applabmoneyaui.model.UssdKeywordStep;
import applabmoneyaui.model.UssdMenuItem;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TRANSACTION_KEYWORDS")
public class UssdTransactionKeyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "KEYWORD_ID")
	private Long keywordId;

	@NotEmpty
	@Column(name = "KEYWORD_NAME", nullable = false, length = 100)
	private String keywordName;

	@NotEmpty
	@Column(name = "KEYWORD_CODE", nullable = false, length = 20)
	private String keywordCode;

	@Column(name = "KEYWORD_DESC", nullable = true, length = 100)
	private String keywordDescription;

	@Column(name = "STEPS_COUNT", nullable = true, length = 11)
	private Integer keywordStepsCount;

	@Column(name = "ENABLED_FLG", nullable = true)
	private Integer keywordEnabledFlag;

	@OneToMany(targetEntity = UssdKeywordStep.class, mappedBy = "transactionKeyword", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "KEYWORD_ID", insertable = false, updatable = false, nullable = true)
	private List<UssdKeywordStep> keywordSteps;

	@OneToMany(targetEntity = UssdMenuItem.class, mappedBy = "menuItemKeyword", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "KEYWORD_ID", insertable = false, updatable = false, nullable = true)
	private List<UssdMenuItem> menuItems;

	@OneToMany(targetEntity = AmlBarring.class, mappedBy = "transactionKeyword", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "KEYWORD_ID", insertable = false, updatable = false, nullable = true)
	private List<AmlBarring> keywordAmlBarring;

	public UssdTransactionKeyword() {

	}

	public UssdTransactionKeyword(String keywordName, String keywordCode,
			String keywordDescription, Integer keywordStepsCount,
			Integer keywordEnabledFlag) {
		this.keywordName = keywordName;
		this.keywordCode = keywordCode;
		this.keywordDescription = keywordDescription;
		this.keywordStepsCount = keywordStepsCount;
		this.keywordEnabledFlag = keywordEnabledFlag;
		this.keywordSteps = new ArrayList<UssdKeywordStep>();
		this.menuItems = new ArrayList<UssdMenuItem>();
		this.setAmlBarringKeywords(new ArrayList<AmlBarring>());
	}

	public Long getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}

	public String getKeywordName() {
		return keywordName;
	}

	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	public String getKeywordCode() {
		return keywordCode;
	}

	public void setKeywordCode(String keywordCode) {
		this.keywordCode = keywordCode;
	}

	public String getKeywordDescription() {
		return keywordDescription;
	}

	public void setKeywordDescription(String keywordDescription) {
		this.keywordDescription = keywordDescription;
	}

	public Integer getKeywordStepsCount() {
		return keywordStepsCount;
	}

	public void setKeywordStepsCount(Integer keywordStepsCount) {
		this.keywordStepsCount = keywordStepsCount;
	}

	public Integer getKeywordEnabledFlag() {
		return keywordEnabledFlag;
	}

	public void setKeywordEnabledFlag(Integer keywordEnabledFlag) {
		this.keywordEnabledFlag = keywordEnabledFlag;
	}

	public List<UssdKeywordStep> getKeywordSteps() {
		return keywordSteps;
	}

	public void setKeywordSteps(List<UssdKeywordStep> keywordSteps) {
		this.keywordSteps = keywordSteps;
	}

	public List<UssdMenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<UssdMenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public List<AmlBarring> getAmlBarringKeywords() {
		return keywordAmlBarring;
	}

	public void setAmlBarringKeywords(List<AmlBarring> keywordAmlBarring) {
		this.keywordAmlBarring = keywordAmlBarring;
	}
}