package com.org.bidding.service;

import java.util.List;

import com.org.bidding.model.BidderCurrentExtract;

public interface BiddingDetailsUpdation {

	public BidderCurrentExtract getHighestBidder(List<BidderCurrentExtract> bidderCurrentList);

	public List<BidderCurrentExtract> update(List<BidderCurrentExtract> bidderCurrentList);

}
