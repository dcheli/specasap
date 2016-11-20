package net.services.api.specasap.model;

public class TermsOfService {
		String introduction; 
		String accountTerms;
		String paymentRefundsUpgradingandDowngrading; 
		String cancellationAndTermination;
		String modificationsToThServiceAndPrices;
		String copyrightAndCOntentOwnership;
		String generalConditions; 
		String lastReviewedOrUpdated;
		
		public TermsOfService() {
			super();
			// TODO Auto-generated constructor stub
		}
		public TermsOfService(String introduction, String accountTerms, String paymentRefundsUpgradingandDowngrading,
				String cancellationAndTermination, String modificationsToThServiceAndPrices,
				String copyrightAndCOntentOwnership, String generalConditions, String lastReviewedOrUpdated) {
			super();
			this.introduction = introduction;
			this.accountTerms = accountTerms;
			this.paymentRefundsUpgradingandDowngrading = paymentRefundsUpgradingandDowngrading;
			this.cancellationAndTermination = cancellationAndTermination;
			this.modificationsToThServiceAndPrices = modificationsToThServiceAndPrices;
			this.copyrightAndCOntentOwnership = copyrightAndCOntentOwnership;
			this.generalConditions = generalConditions;
			this.lastReviewedOrUpdated = lastReviewedOrUpdated;
		}
		public String getIntroduction() {
			return introduction;
		}
		public void setIntroduction(String introduction) {
			this.introduction = introduction;
		}
		public String getAccountTerms() {
			return accountTerms;
		}
		public void setAccountTerms(String accountTerms) {
			this.accountTerms = accountTerms;
		}
		public String getPaymentRefundsUpgradingandDowngrading() {
			return paymentRefundsUpgradingandDowngrading;
		}
		public void setPaymentRefundsUpgradingandDowngrading(String paymentRefundsUpgradingandDowngrading) {
			this.paymentRefundsUpgradingandDowngrading = paymentRefundsUpgradingandDowngrading;
		}
		public String getModificationsToThServiceAndPrices() {
			return modificationsToThServiceAndPrices;
		}
		public void setModificationsToThServiceAndPrices(String modificationsToThServiceAndPrices) {
			this.modificationsToThServiceAndPrices = modificationsToThServiceAndPrices;
		}
		public String getCopyrightAndCOntentOwnership() {
			return copyrightAndCOntentOwnership;
		}
		public void setCopyrightAndCOntentOwnership(String copyrightAndCOntentOwnership) {
			this.copyrightAndCOntentOwnership = copyrightAndCOntentOwnership;
		}
		public String getGeneralConditions() {
			return generalConditions;
		}
		public void setGeneralConditions(String generalConditions) {
			this.generalConditions = generalConditions;
		}
		public String getLastReviewedOrUpdated() {
			return lastReviewedOrUpdated;
		}
		public void setLastReviewedOrUpdated(String lastReviewedOrUpdated) {
			this.lastReviewedOrUpdated = lastReviewedOrUpdated;
		}
		public String getCancellationAndTermination() {
			return cancellationAndTermination;
		}
		public void setCancellationAndTermination(String cancellationAndTermination) {
			this.cancellationAndTermination = cancellationAndTermination;
		}
}
