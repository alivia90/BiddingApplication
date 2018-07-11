package com.org.bidding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.org.bidding.model.BidderCurrentExtract;
import com.org.bidding.service.BiddingDetailsUpdation;
import com.org.bidding.service.impl.BiddingDetailsUpdationImpl;

public class ApplicationTest {

	static List<BidderCurrentExtract> bidderCurrentList = new ArrayList();
	static ConcurrentHashMap<String, BidderCurrentExtract> biddingInfoMap = new ConcurrentHashMap<String, BidderCurrentExtract>();
	static BidderCurrentExtract bidderCurrent;

	ConcurrentHashMap<String, BidderCurrentExtract> BidderProcess() {
		

		Scanner sc = new Scanner(System.in);

		System.out.println("enter Number of Bidder");
		int i = sc.nextInt();
		for (int j = 0; j <= i - 1; j++) {
			System.out.println("enter bidderName");
			String name = sc.next();
			System.out.println("enter startbid");
			int startBid = sc.nextInt();
			System.out.println("enter maxbid");
			int maxBid = sc.nextInt();
			System.out.println("enter increament factor");
			int autoIncAmt = sc.nextInt();

			bidderCurrent = new BidderCurrentExtract(startBid, name, maxBid, startBid, autoIncAmt);
			bidderCurrent.setTimestamp(LocalDateTime.now());
			biddingInfoMap.put(name + bidderCurrent.getTimestamp(), bidderCurrent);

		}

		return biddingInfoMap;
	}

	public static void main(String arg[]) {
		ApplicationTest client = new ApplicationTest();
		BiddingDetailsUpdation biddingdetails = new BiddingDetailsUpdationImpl();
		ConcurrentHashMap<String, BidderCurrentExtract> biddingInfoMap = client.BidderProcess();
		Set<Entry<String, BidderCurrentExtract>> entrySet = biddingInfoMap.entrySet();
		for (Entry entry : entrySet) {
			bidderCurrentList.add((BidderCurrentExtract) entry.getValue());
		}
		List<BidderCurrentExtract> updatedCurrentList = biddingdetails.update(bidderCurrentList);
		BidderCurrentExtract bidderHighest = biddingdetails.getHighestBidder(updatedCurrentList);
		System.out.println(bidderHighest.getBiddername());
		System.out.println(bidderHighest.getCurrentBid());
	}
}
