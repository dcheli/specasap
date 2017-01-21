package net.services.api.specasap.model;

import java.util.ArrayList;
import java.util.List;

public class IAPReceipt {
	
	private String environment;
	private String status;
	private Receipt receipt;
	private String latest_receipt;
	List<LatestReceiptInfo> latest_receipt_info = new ArrayList<LatestReceiptInfo>();
	
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public String getLatest_receipt() {
		return latest_receipt;
	}
	public void setLatest_receipt(String latest_receipt) {
		this.latest_receipt = latest_receipt;
	}
	public List<LatestReceiptInfo> getLatest_receipt_info() {
		return latest_receipt_info;
	}
	public void setLatest_receipt_info(List<LatestReceiptInfo> latest_receipt_info) {
		this.latest_receipt_info = latest_receipt_info;
	}
	@Override
    public String toString()
    {
        return "ClassPojo [environment = "+environment+", status = "+status+", receipt = " + receipt +", latest_receipt_info = " + latest_receipt_info + ", latest_receipt = " + latest_receipt + "]";
    }

}
