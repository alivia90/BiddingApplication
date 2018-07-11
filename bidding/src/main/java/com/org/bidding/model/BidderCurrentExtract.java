package com.org.bidding.model;

import java.util.Comparator;

public class BidderCurrentExtract extends BidderInformation implements Comparator<BidderInformation> {

	public BidderCurrentExtract(Integer startingBid, String biddername, Integer maxBid, Integer currentBid,
			Integer autoIncAmount) {
		super(startingBid, biddername, maxBid, currentBid, autoIncAmount);
	}

	@Override
	public int compare(BidderInformation o1, BidderInformation o2) {
		return o1.getCurrentBid().compareTo(o2.getCurrentBid());
	}

}
