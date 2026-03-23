package org.bahmni.module.eventoutbox;

import org.openmrs.module.BaseModuleActivator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BahmniEventOutboxActivator extends BaseModuleActivator {

	private static final Logger log = LoggerFactory.getLogger(BahmniEventOutboxActivator.class);

	@Override
	public void started() {
		log.info("Bahmni Event Outbox Module started");
	}

	@Override
	public void stopped() {
		log.info("Bahmni Event Outbox Module stopped");
	}

}
