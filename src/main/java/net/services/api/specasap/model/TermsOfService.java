package net.services.api.specasap.model;

public class TermsOfService {
		String introduction; 
		String accountTerms;
		String paymentRefundsUpgradingAndDowngrading; 
		String cancellationAndTermination;
		String modificationsToTheServiceAndPrices;
		String copyrightAndContentOwnership;
		String generalConditions; 
		String lastReviewedOrUpdated;
		
		public TermsOfService() {
			super();
			// TODO Auto-generated constructor stub
		}
		public TermsOfService(String introduction, String accountTerms, String paymentRefundsUpgradingAndDowngrading,
				String cancellationAndTermination, String modificationsToTheServiceAndPrices,
				String copyrightAndContentOwnership, String generalConditions, String lastReviewedOrUpdated) {
			super();
			this.introduction = introduction;
			this.accountTerms = accountTerms;
			this.paymentRefundsUpgradingAndDowngrading = paymentRefundsUpgradingAndDowngrading;
			this.cancellationAndTermination = cancellationAndTermination;
			this.modificationsToTheServiceAndPrices = modificationsToTheServiceAndPrices;
			this.copyrightAndContentOwnership = copyrightAndContentOwnership;
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
		public String getPaymentRefundsUpgradingAndDowngrading() {
			return paymentRefundsUpgradingAndDowngrading;
		}
		public void setPaymentRefundsUpgradingAndDowngrading(String paymentRefundsUpgradingAndDowngrading) {
			this.paymentRefundsUpgradingAndDowngrading = paymentRefundsUpgradingAndDowngrading;
		}
		public String getModificationsToTheServiceAndPrices() {
			return modificationsToTheServiceAndPrices;
		}
		public void setModificationsToTheServiceAndPrices(String modificationsToTheServiceAndPrices) {
			this.modificationsToTheServiceAndPrices = modificationsToTheServiceAndPrices;
		}
		public String getCopyrightAndContentOwnership() {
			return copyrightAndContentOwnership;
		}
		public void setCopyrightAndContentOwnership(String copyrightAndContentOwnership) {
			this.copyrightAndContentOwnership = copyrightAndContentOwnership;
		}
}
