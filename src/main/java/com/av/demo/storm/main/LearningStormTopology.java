package com.av.demo.storm.main;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

import com.av.demo.storm.bolt.LearningStormBolt;
import com.av.demo.storm.spout.LearningStormSpout;

public class LearningStormTopology {
	public static final String MY_ID = "LearningStormTopology";
	
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		// create an instance of TopologyBuilder class
		TopologyBuilder builder = new TopologyBuilder();
			// set the spout class
			builder.setSpout(LearningStormSpout.MY_ID, new LearningStormSpout(), 2);
			// set the bolt class
			builder
				.setBolt(LearningStormBolt.MY_ID, new LearningStormBolt(), 4)
				.shuffleGrouping(LearningStormSpout.MY_ID);
		Config conf = new Config();
		conf.setDebug(true);
		// create an instance of LocalCluster class for
		// executing topology in local mode.
		LocalCluster cluster = new LocalCluster();
		// LearningStormTopolgy is the name of submitted topology.
		cluster.submitTopology(MY_ID, conf, builder.createTopology());
		try {
			Thread.sleep(10000);
		} catch (Exception exception) {
			System.out.println("Thread interrupted exception : " + exception);
		}
		// kill the LearningStormTopology
		cluster.killTopology("LearningStormToplogy");
		// shutdown the storm test cluster
		cluster.shutdown();
	}
}