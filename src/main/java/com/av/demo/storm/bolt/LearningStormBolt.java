package com.av.demo.storm.bolt;

import com.av.demo.storm.spout.LearningStormSpout;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class LearningStormBolt extends BaseBasicBolt {
	private static final long serialVersionUID = 1L;
	public static final String MY_ID = "LearningStormBolt";

	public void execute(Tuple input, BasicOutputCollector collector) {
		String test = input.getStringByField(LearningStormSpout.MY_OUTPUT_FIELD_1);
		System.out.println("Name of input site is : " + test);
	}
	public void declareOutputFields(OutputFieldsDeclarer declarer) {}
}