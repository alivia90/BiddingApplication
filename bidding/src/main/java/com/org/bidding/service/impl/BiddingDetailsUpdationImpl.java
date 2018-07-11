package com.org.bidding.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.org.bidding.model.BidderCurrentExtract;
import com.org.bidding.model.BidderInformation;
import com.org.bidding.service.BiddingDetailsUpdation;

public class BiddingDetailsUpdationImpl implements BiddingDetailsUpdation {

	List<BidderInformation> bidderInfoList = new ArrayList<>();

	@Override
	public BidderCurrentExtract getHighestBidder(List<BidderCurrentExtract> bidderCurrentList) {
		BidderCurrentExtract bidderHighest = Collections.max(bidderCurrentList,
				Comparator.comparingInt(BidderCurrentExtract::getCurrentBid));
		return bidderHighest;

	}

	@Override
	public List<BidderCurrentExtract> update(List<BidderCurrentExtract> bidderCurrentList) {
		BidderCurrentExtract bidderCurrent;
		int bidderHighest = getHighestBidder(bidderCurrentList).getCurrentBid();
		List<BidderCurrentExtract> updatedBidderCurrentList = new ArrayList();

		for (BidderCurrentExtract bidderCurrentExtract : bidderCurrentList) {
			if (bidderCurrentExtract.getCurrentBid() < bidderHighest) {
				if (bidderCurrentExtract.getCurrentBid()
						+ bidderCurrentExtract.getAutoIncAmount() > bidderCurrentExtract.getMaxBid()) {
					return bidderCurrentList;
				}

				int updatedCurBid = bidderCurrentExtract.getCurrentBid() + bidderCurrentExtract.getAutoIncAmount();
				bidderCurrent = new BidderCurrentExtract(bidderCurrentExtract.getStartingBid(),
						bidderCurrentExtract.getBiddername(), bidderCurrentExtract.getMaxBid(), updatedCurBid,
						bidderCurrentExtract.getAutoIncAmount());

				updatedBidderCurrentList.add(bidderCurrent);
			} else {
				bidderCurrent = new BidderCurrentExtract(bidderCurrentExtract.getStartingBid(),
						bidderCurrentExtract.getBiddername(), bidderCurrentExtract.getMaxBid(),
						bidderCurrentExtract.getCurrentBid(), bidderCurrentExtract.getAutoIncAmount());
				updatedBidderCurrentList.add(bidderCurrent);
			}
		}

		for (BidderCurrentExtract updatedList : updatedBidderCurrentList) {
			BidderInformation bidderInfo = new BidderInformation(updatedList.getStartingBid(),
					updatedList.getBiddername(), updatedList.getMaxBid(), updatedList.getCurrentBid(),
					updatedList.getAutoIncAmount());
			bidderInfo.setTimestamp(updatedList.getTimestamp());
			bidderInfoList.add(bidderInfo);

		}
		List<BidderInformation> equalsEntry = new ArrayList();
		BidderInformation winnerHavingsameBid;
		for (BidderInformation bidder : bidderInfoList) {
			if (bidder.getCurrentBid().equals(getHighestBidder(updatedBidderCurrentList).getCurrentBid())) {
				equalsEntry.add(bidder);
			}

		}
		List<BidderCurrentExtract> bidderOwnList = new ArrayList<>();
		BidderCurrentExtract winnerHavingEqualBid;
		if (equalsEntry.size() > 1) {
			winnerHavingsameBid = Collections.max(equalsEntry, Comparator.comparing(BidderInformation::getTimestamp));
			winnerHavingEqualBid = new BidderCurrentExtract(winnerHavingsameBid.getStartingBid(),
					winnerHavingsameBid.getBiddername(), winnerHavingsameBid.getMaxBid(),
					winnerHavingsameBid.getCurrentBid(), winnerHavingsameBid.getAutoIncAmount());
			bidderOwnList.add(winnerHavingEqualBid);
			return bidderOwnList;
		}

		return update(updatedBidderCurrentList);

	}

}
