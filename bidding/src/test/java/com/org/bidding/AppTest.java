package com.org.bidding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Test;

import com.org.bidding.model.BidderCurrentExtract;
import com.org.bidding.service.BiddingDetailsUpdation;
import com.org.bidding.service.impl.BiddingDetailsUpdationImpl;

/**
 * Unit test for simple App.
 */
public class AppTest {

	BiddingDetailsUpdation biddingdetails = new BiddingDetailsUpdationImpl();
	List<BidderCurrentExtract> bidderCurrentList = new ArrayList();

	@Test
	public void testgetWinner() {

		ConcurrentHashMap<String, BidderCurrentExtract> biddingInfoMap = new ConcurrentHashMap<String, BidderCurrentExtract>();
		BidderCurrentExtract bid = new BidderCurrentExtract(50, "Alice", 80, 50, 3);
		bid.setTimestamp(LocalDateTime.now());
		biddingInfoMap.put(bid.getBiddername() + bid.getTimestamp(), bid);
		bid = new BidderCurrentExtract(60, "Aaron", 82, 60, 2);
		bid.setTimestamp(LocalDateTime.now());
		biddingInfoMap.put(bid.getBiddername() + bid.getTimestamp(), bid);

		bid = new BidderCurrentExtract(55, "Amanda", 85, 55, 5);
		bid.setTimestamp(LocalDateTime.now());

		Set<Entry<String, BidderCurrentExtract>> entrySet = biddingInfoMap.entrySet();
		for (Entry entry : entrySet) {
			bidderCurrentList.add((BidderCurrentExtract) entry.getValue());
		}

		List<BidderCurrentExtract> updatedCurrentList = biddingdetails.update(bidderCurrentList);
		BidderCurrentExtract bidderHighest = biddingdetails.getHighestBidder(updatedCurrentList);
		Assert.assertEquals(bidderHighest.getBiddername(), "Aaron");
	}
}
