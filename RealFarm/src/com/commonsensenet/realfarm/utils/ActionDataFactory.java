package com.commonsensenet.realfarm.utils;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commonsensenet.realfarm.R;
import com.commonsensenet.realfarm.TopSelectorActivity;
import com.commonsensenet.realfarm.dataaccess.RealFarmDatabase;
import com.commonsensenet.realfarm.dataaccess.RealFarmProvider;
import com.commonsensenet.realfarm.model.Resource;
import com.commonsensenet.realfarm.model.aggregate.AggregateItem;
import com.commonsensenet.realfarm.model.aggregate.UserAggregateItem;
import com.commonsensenet.realfarm.view.AggregateItemAdapter;
import com.commonsensenet.realfarm.view.AggregateItemWrapper;
import com.commonsensenet.realfarm.view.GeneralAggregateItemWrapper;
import com.commonsensenet.realfarm.view.MarketItemAdapter;

/**
 * 
 * @author Oscar Bola�os <@oscarbolanos>
 * 
 */
public final class ActionDataFactory {

	public static AggregateItemAdapter getAdapter(int currentAction,
			int mActionTypeId, TopSelectorActivity topEnabledActivity,
			List<AggregateItem> mItems, RealFarmProvider mDataProvider,
			RealFarmProvider mDataProvider2) {
		switch (currentAction) {
		case TopSelectorActivity.LIST_WITH_TOP_SELECTOR_TYPE_MARKET:
			return new MarketItemAdapter(topEnabledActivity, mItems,
					mDataProvider);
		case TopSelectorActivity.LIST_WITH_TOP_SELECTOR_TYPE_AGGREGATE:
			return new AggregateItemAdapter(topEnabledActivity, mItems,
					mActionTypeId, mDataProvider);
		default:
			return null;
		}
	}

	public static List<AggregateItem> getAggregateData(int actionTypeId,
			RealFarmProvider dataProvider, int seedTypeId) {

		switch (actionTypeId) {

		case RealFarmDatabase.ACTION_TYPE_SOW_ID:
			return dataProvider.getAggregateItemsSow(actionTypeId, seedTypeId);
		case RealFarmDatabase.ACTION_TYPE_FERTILIZE_ID:
			return dataProvider.getAggregateItemsFertilize(actionTypeId,
					seedTypeId);
		case RealFarmDatabase.ACTION_TYPE_IRRIGATE_ID:
			return dataProvider.getAggregateItemsIrrigate(actionTypeId,
					seedTypeId);
		case RealFarmDatabase.ACTION_TYPE_REPORT_ID:
			return dataProvider.getAggregateItemsReport(actionTypeId,
					seedTypeId);
		case RealFarmDatabase.ACTION_TYPE_HARVEST_ID:
			return dataProvider.getAggregateItemsHarvest(actionTypeId,
					seedTypeId);
		case RealFarmDatabase.ACTION_TYPE_SPRAY_ID:
			return dataProvider
					.getAggregateItemsSpray(actionTypeId, seedTypeId);
		case RealFarmDatabase.ACTION_TYPE_SELL_ID:
			// here the seedTypeId is cropTypeId
			return dataProvider.getAggregateItemsSell(actionTypeId, seedTypeId);
		default:
			return null;
		}
	}

	public static AggregateItemWrapper getAggregateWrapper(Context context,
			View row, ViewGroup parent, int actionTypeId) {

		// item to return
		AggregateItemWrapper wrapper;

		// layout inflator service.
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// for now all the wrappers use the same layout.
		row = li.inflate(R.layout.tpl_aggregate_item, parent, false);

		// gets the appropriate type of AggregateItemWrapper
		wrapper = getAggregateWrapper(row, actionTypeId);

		return wrapper;
	}

	public static AggregateItemWrapper getAggregateWrapper(View row,
			int actionTypeId) {
		return new GeneralAggregateItemWrapper(row);
	}

	public static List<AggregateItem> getData(int currentAction,
			RealFarmProvider mDataProvider, int mActionTypeId,
			int cropSeedTypeId, Resource daysSelectorData) {
		switch (currentAction) {
		case TopSelectorActivity.LIST_WITH_TOP_SELECTOR_TYPE_MARKET:
			return mDataProvider.getMarketData(cropSeedTypeId,
					-daysSelectorData.getValue());
		case TopSelectorActivity.LIST_WITH_TOP_SELECTOR_TYPE_AGGREGATE:
			return ActionDataFactory.getAggregateData(mActionTypeId,
					mDataProvider, cropSeedTypeId);
		default:
			return null;
		}
	}

	public static Resource getTopSelectorData(int actionTypeId,
			RealFarmProvider dataProvider, long userId) {

		Resource res;
		switch (actionTypeId) {
		case RealFarmDatabase.ACTION_TYPE_SELL_ID:
			// if user does not have a plot, select ground nut (id = 1)
			res = dataProvider.getTopSelectorDataCrop(userId, 1);
			return res;
		default:
			// if user doesn't have a plot, select TMV2 (id = 4)
			res = dataProvider.getTopSelectorDataVar(userId, 4);
			return res;
		}
	}

	// TODO: get varieties and crops THIS SEASON
	public static List<Resource> getTopSelectorList(int actionTypeId,
			RealFarmProvider dataProvider) {
		switch (actionTypeId) {
		case RealFarmDatabase.ACTION_TYPE_SELL_ID:
			return dataProvider.getCropTypes();
		default:
			return dataProvider.getVarieties();
		}
	}

	public static List<UserAggregateItem> getUserAggregateData(
			AggregateItem aggregateItem, RealFarmProvider dataProvider) {
		System.out.println(aggregateItem.getActionTypeId());

		switch (aggregateItem.getActionTypeId()) {
		case RealFarmDatabase.ACTION_TYPE_SOW_ID:
			return dataProvider.getUserAggregateItemSow(
					aggregateItem.getActionTypeId(),
					aggregateItem.getSelector1(), aggregateItem.getSelector2());
		case RealFarmDatabase.ACTION_TYPE_FERTILIZE_ID:
			return dataProvider.getUserAggregateItemFertilize(
					aggregateItem.getActionTypeId(),
					aggregateItem.getSelector1(), aggregateItem.getSelector2());
		case RealFarmDatabase.ACTION_TYPE_IRRIGATE_ID:
			return dataProvider.getUserAggregateItemIrrigate(
					aggregateItem.getActionTypeId(),
					aggregateItem.getSelector1(), aggregateItem.getSelector2());
		case RealFarmDatabase.ACTION_TYPE_REPORT_ID:
			return dataProvider.getUserAggregateItemReport(
					aggregateItem.getActionTypeId(),
					aggregateItem.getSelector1(), aggregateItem.getSelector2());
		case RealFarmDatabase.ACTION_TYPE_HARVEST_ID:
			return dataProvider.getUserAggregateItemHarvest(
					aggregateItem.getActionTypeId(),
					aggregateItem.getSelector1());
		case RealFarmDatabase.ACTION_TYPE_SPRAY_ID:
			return dataProvider.getUserAggregateItemSpray(
					aggregateItem.getActionTypeId(),
					aggregateItem.getSelector1(), aggregateItem.getSelector2(),
					aggregateItem.getSelector3());
		case RealFarmDatabase.ACTION_TYPE_SELL_ID:
			return dataProvider.getUserAggregateItemSell(
					aggregateItem.getActionTypeId(),
					aggregateItem.getSelector1(), aggregateItem.getSelector2(),
					aggregateItem.getSelector3());
		default:
			return null;
		}
	}

	public static List<UserAggregateItem> getUserList(int currentAction,
			int cropSeedTypeId, AggregateItem selectedItem,
			Resource daysSelectorData, RealFarmProvider mDataProvider) {
		switch (currentAction) {
		case TopSelectorActivity.LIST_WITH_TOP_SELECTOR_TYPE_MARKET:
			return mDataProvider.getUserMarketData(cropSeedTypeId,
					selectedItem.getSelector1(), -daysSelectorData.getValue());
		case TopSelectorActivity.LIST_WITH_TOP_SELECTOR_TYPE_AGGREGATE:
			return getUserAggregateData(selectedItem, mDataProvider);
		default:
			return null;
		}
	}

	/**
	 * This class can not be instantiated.
	 */
	private ActionDataFactory() {

	}
}
