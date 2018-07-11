package com.org.bidding.model;

import java.time.LocalDateTime;
import java.util.Comparator;

public class BidderInformation implements Comparator<BidderInformation> {

	private Integer startingBid;
	private String biddername;
	private Integer maxBid;
	private Integer currentBid;
	private Integer autoIncAmount;

	public BidderInformation(Integer startingBid, String biddername, Integer maxBid, Integer currentBid,
			Integer autoIncAmount) {
		super();
		this.startingBid = startingBid;
		this.biddername = biddername;
		this.maxBid = maxBid;
		this.currentBid = currentBid;
		this.autoIncAmount = autoIncAmount;
	}

	public String getBiddername() {
		return biddername;
	}

	public Integer getStartingBid() {
		return startingBid;
	}

	public Integer getMaxBid() {
		return maxBid;
	}

	public Integer getCurrentBid() {
		return currentBid;
	}

	public Integer getAutoIncAmount() {
		return autoIncAmount;
	}

	private LocalDateTime timestamp = LocalDateTime.now();

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "BidderInformation [startingBid=" + startingBid + ", maxBid=" + maxBid + ", currentBid=" + currentBid
				+ ", autoIncAmount=" + autoIncAmount + ", timestamp=" + timestamp + "]";
	}

	public int compare(BidderInformation o1, BidderInformation o2) {
		return o2.getTimestamp().compareTo(o1.getTimestamp());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((biddername == null) ? 0 : biddername.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BidderInformation other = (BidderInformation) obj;
		if (biddername == null) {
			if (other.biddername != null)
				return false;
		} else if (!biddername.equals(other.biddername))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}
