package com.happycommunity.framework.mq.rocketmq;
/**
 * @Title:MQTopicEnum.java
 * @Description:
 */
public enum MQTopicEnum implements MQTopicAndTag{

	/** 开发测试用的*/
	MQ_TEST("mqTestGroup","mqTestTopic","mqTestTag","开发测试使用"),
	
	ORDER_SAVE("orderSaveGroup","orderSaveTopic","ORDER_SAVE","保存订单"),
	ORDER_DETAIL_SAVE("orderDetailSaveGroup","orderDetailSaveTopic","ORDER_DETAIL_SAVE","保存订单明细"),
	ORDER_STATUS_UPDATE("orderStatusUpdateGroup","orderStatusUpdateTopic","ORDER_STATUS_UPDATE","修改订单状态");

	/** Group */
	private String group;
	/** Topic */
	private String topic;
	/** Tag */
	private String tag;
	/** Desc */
	private String desc;

	MQTopicEnum(String group, String topic, String tag, String desc) {
		this.group = group;
		this.topic = topic;
		this.tag = tag;
		this.desc = desc;
	}

	public String[] getTopicAndTag() {
		String[] topicAndTag = new String[] { this.topic, this.tag, this.desc };
		return topicAndTag;
	}

	public String getGroup() {
		return group;
	}

	public MQTopicEnum setGroup(String group) {
		this.group = group;
		return this;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}