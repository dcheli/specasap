package net.services.api.specasap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PrivacyPolicy {
	
	String generalInformation;
	String informationGatheringAndUsage;
	String cookies;
	String dataStorage;
	String disclosure;
	String euAndSwissSafeHarbor;
	String changes;
	String questions;
	String lastReviewedOrUpdated;
	public PrivacyPolicy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PrivacyPolicy(String generalInformation, String informationGatheringAndUsage, String cookies,
			String dataStorage, String disclosure, String euAndSwissSafeHarbor, String changes, String questions,
			String lastReviewedOrUpdated) {
		super();
		this.generalInformation = generalInformation;
		this.informationGatheringAndUsage = informationGatheringAndUsage;
		this.cookies = cookies;
		this.dataStorage = dataStorage;
		this.disclosure = disclosure;
		this.euAndSwissSafeHarbor = euAndSwissSafeHarbor;
		this.changes = changes;
		this.questions = questions;
		this.lastReviewedOrUpdated = lastReviewedOrUpdated;
	}
	public String getGeneralInformation() {
		return generalInformation;
	}
	public void setGeneralInformation(String generalInformation) {
		this.generalInformation = generalInformation;
	}
	public String getInformationGatheringAndUsage() {
		return informationGatheringAndUsage;
	}
	public void setInformationGatheringAndUsage(String informationGatheringAndUsage) {
		this.informationGatheringAndUsage = informationGatheringAndUsage;
	}
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
	}
	public String getDataStorage() {
		return dataStorage;
	}
	public void setDataStorage(String dataStorage) {
		this.dataStorage = dataStorage;
	}
	public String getDisclosure() {
		return disclosure;
	}
	public void setDisclosure(String disclosure) {
		this.disclosure = disclosure;
	}
	public String getEuAndSwissSafeHarbor() {
		return euAndSwissSafeHarbor;
	}
	public void setEuAndSwissSafeHarbor(String euAndSwissSafeHarbor) {
		this.euAndSwissSafeHarbor = euAndSwissSafeHarbor;
	}
	public String getChanges() {
		return changes;
	}
	public void setChanges(String changes) {
		this.changes = changes;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getLastReviewedOrUpdated() {
		return lastReviewedOrUpdated;
	}
	public void setLastReviewedOrUpdated(String lastReviewedOrUpdated) {
		this.lastReviewedOrUpdated = lastReviewedOrUpdated;
	}
}
