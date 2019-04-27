package com.leadDirec.dispatcher.filter;

import java.util.Collections;
import java.util.List;

public class ChannelFilterBuilder {

    public List<ChannelFilter> channelFilters;

    /**
     * 获取channelFilters
     *
     * @return the channelFilters
     */
    public List<ChannelFilter> getChannelFilters() {
        if (channelFilters == null) {
            return Collections.emptyList();
        }
        return channelFilters;
    }

    /**
     * 设置channelFilters
     *
     * @param channelFilters
     *            the channelFilters to set
     */
    public void setChannelFilters(List<ChannelFilter> channelFilters) {
        this.channelFilters = channelFilters;
    }

}
